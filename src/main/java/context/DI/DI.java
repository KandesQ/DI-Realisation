package context.DI;

/*

 Этот интерфейс нужен для сокрытия, на случай если захотим подменить реализацию

  Включает в себя несколько методов:
    addConfigurations - сканирует папку config и добавляет в контекст все классы помеченные @Configuration

    <T> T getBean(T clazz) - возвращает бин из контекста если он там есть

    injectDependencies(Class<?> clazz) - метод, реализующий алгоритм BFS обхода графа с очередью


*/

public interface DI {
    // сканирует и создает классы конфиги, потом создает бины которые в них прописаны
    void addConfigurations() throws Exception;

    void addBeansToContext(Class<?> configClass) throws Exception;

    <T> T getBean(Class<T> clazz);

    void injectDependencies() throws Exception;
}
