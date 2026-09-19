package problem5;

public abstract class DeliveryNote {

    // Every note type must define its own base confirmation message.
    public abstract String confirmDelivery();

    // Compile-time (overloaded) polymorphism: same method name, different
    // parameter list, resolved by the compiler based on the call site.
    // Delegates to confirmDelivery() so the core message only lives once.
    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}
