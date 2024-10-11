package context.DI;

import context.DI.annotations.Autowired;
import context.DI.annotations.Bean;
import context.DI.annotations.Configuration;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**

    Класс, реализующий инъекцию зависимостей, это ядро программы

    Чтобы программа работала правильно, нужно чтобы ВСЕ действующие классы были бинами,
    То есть есть класс SomeClass, он должен быть бином, чтобы в него внедридлись зависимости
    Можно думать о все этом как о большом графе :)

 **/

public class DependencyInjector implements DI {
    private final Set<Object> applicationContext = new HashSet<>();

    @Override
    public void addConfigurations() throws Exception {
        Reflections reflections = new Reflections("config");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Configuration.class);

        // профодимся по конфигурациям, собираем с них бины с создаем их инстансы
        for (Class<?> clazz: classes) {
            addBeansToContext(clazz);
        }
    }

    @Override
    public void addBeansToContext(Class<?> configClass) throws Exception {
        // Создание экземпляра класса
        Object configClassInstance = configClass.getDeclaredConstructor().newInstance();

        // исполняем те методы, которые должны возвращать экземпляры бинов
        for (Method method: configClassInstance.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Bean.class)) {
                Object bean = method.invoke(configClassInstance);
                applicationContext.add(bean);
            }
        }

        // инжектим зависимости в бины (если они есть, зависимости)
        injectDependencies();
    }

    @Override
    // Возвращает инстанс бина, если его нету в контексте - null
    public <T> T getBean(Class<T> clazz) {
        for (Object bean: applicationContext) {
            if (clazz.isInstance(bean)) {
                return clazz.cast(bean);
            }
        }

        return null;
    }

    @Override
    public void injectDependencies() throws Exception {
        for (Object bean: applicationContext) {
            for (Field field: bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Optional<Object> dependency = Optional.ofNullable(getBean(field.getType()));

                    if (dependency.isPresent()) {
                        field.setAccessible(true);
                        field.set(bean, dependency.get());
                    } else {
                        throw new Exception("Зависимость " + field.getType().getName() + " не содержиться в контексте");
                    }
                }
            }
        }
    }
}