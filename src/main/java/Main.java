import context.DI.DependencyInjector;
import context.DI.annotations.Autowired;
import sm.HelloSayer;

public class Main {

    public static void main(String[] args) throws Exception {
        DependencyInjector dependencyInjector = new DependencyInjector();
        dependencyInjector.addConfigurations();

        HelloSayer helloSayer = dependencyInjector.getBean(HelloSayer.class);

        helloSayer.dogGreetings();

        System.out.println();

        helloSayer.catGreetings();

        // все работает, можно еще покрыть тестами))
    }
}
