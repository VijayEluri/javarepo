package samples.innnerclasses;

/**
 * @author Miguel Reyes
 *         Date: 10/19/15
 *         Time: 1:09 PM
 */
public class Car {

    class Engine {
        Engine() {
            Car.this.drive();
        }

    }

    public static void main(String[] args) {
        Car car = new Car();
        car.go();
    }

    void go() {
        Engine engine = new Engine();
    }

    void drive() {
        System.out.println("hi!");
    }

}
