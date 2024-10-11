package config;

import animals.Cat;
import animals.Dog;
import com.sun.tools.javac.Main;
import context.DI.DI;
import context.DI.annotations.Bean;
import context.DI.annotations.Configuration;
import sm.HelloSayer;

@Configuration
public class AppConfig {

    @Bean
    public HelloSayer helloSayer() {
        return new HelloSayer();
    }

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public Dog dog() {
        return new Dog();
    }
}
