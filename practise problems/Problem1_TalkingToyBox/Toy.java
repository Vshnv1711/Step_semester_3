package problem1;

/**
 * Toy is intentionally abstract: there is no such thing as a generic toy
 * with no sound of its own. Every concrete toy MUST supply makeSound().
 *
 * The static counter lives here, inside Toy's own constructor, so that
 * every subclass automatically gets a unique, sequential toyId the moment
 * it calls super(...) -- the logic only needs to exist in one place.
 */
public abstract class Toy {

    // Shared across every Toy ever created, regardless of subclass.
    private static int counter = 1000;

    private final String toyId;
    protected final String name;

    public Toy(String name) {
        this.name = name;
        counter++;
        this.toyId = "TOY-" + counter;
    }

    // No default sound exists -- every subclass must define its own.
    public abstract String makeSound();

    public String getToyId() {
        return toyId;
    }

    // NOTE: `new Toy("generic")` does NOT compile because Toy is abstract.
    // Only ToyCar, ToyRobot, or any other concrete subclass can be
    // instantiated. This is enforced by the compiler, not by convention.
}
