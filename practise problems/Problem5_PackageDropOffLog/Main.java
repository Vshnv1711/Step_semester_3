package problem5;

public class Main {

    // Loops through a mixed array and calls confirmDelivery() on each
    // note -- resolved at runtime (true polymorphism) since every
    // element is stored/referenced as the parent type DeliveryNote.
    static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());
        // Parcel TRK-1 delivered

        System.out.println(p.confirmDelivery("J. Smith"));
        // Parcel TRK-1 delivered, signed by J. Smith

        DeliveryNote ref = p; // upcasting: ParcelNote stored as its parent type
        logAll(new DeliveryNote[]{ ref, new LetterNote("TRK-2") });
        // Parcel TRK-1 delivered
        // Letter TRK-2 delivered
    }
}
