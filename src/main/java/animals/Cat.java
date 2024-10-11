package animals;

public class Cat implements Greetings {
    @Override
    public String greetings() {
        System.out.println("Cat says: ");
        return "Meow! ^-^";
    }
}
