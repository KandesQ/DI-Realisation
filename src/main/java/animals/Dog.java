package animals;

public class Dog implements Greetings {
    @Override
    public String greetings() {
        System.out.println("Dog says: ");
        return "Woof! %(0/-\\0)%";
    }
}
