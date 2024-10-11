package sm;

import animals.Cat;
import animals.Dog;
import com.sun.jdi.event.StepEvent;
import context.DI.annotations.Autowired;

public class HelloSayer {

    @Autowired
    private Cat catty;

    @Autowired
    private Dog doggy;

    public void dogGreetings() {
        System.out.println(doggy.greetings());
    }

    public void catGreetings() {
        System.out.println(catty.greetings());
    }
}
