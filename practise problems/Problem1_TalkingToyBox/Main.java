package problem1;

public class Main {
    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        System.out.println(c.makeSound());   // Speedster: Vroom vroom!

        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(r.makeSound());   // Bolt: Beep boop!

        System.out.println(c.getToyId());    // TOY-1001
        System.out.println(r.getToyId());    // TOY-1002

        // The following line is intentionally commented out because it
        // must NOT compile: Toy is abstract and has no sound of its own.
        // Toy t = new Toy("Generic"); // <-- compile-time error
    }
}
