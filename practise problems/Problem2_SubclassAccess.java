/**
 * Category B - Problem 2 (Basic -> Intermediate)
 * Subclass Ticket Access
 *
 * Extends the access checker to 5 contexts, adding the classic "protected across
 * packages via inheritance" gotcha:
 *   - SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE    -> accessed through a variable declared
 *                                                as the SUBCLASS's own type -> ALLOWED
 *   - SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE -> accessed through a variable declared
 *                                                as the PARENT's type -> DENIED
 *
 * private and default stay DENIED in both of those cases, since neither rule has
 * anything to do with inheritance in the first place.
 */
public class Problem2_SubclassAccess {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                    return "ALLOWED";
                }
                // covers DIFFERENT_PACKAGE and SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                throw new IllegalArgumentException("Unknown fieldModifier: " + fieldModifier);
        }
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));    // ALLOWED
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));  // DENIED
        System.out.println(classifyAccess("private", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));       // DENIED
        System.out.println(classifyAccess("default", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));    // DENIED
        System.out.println(classifyAccess("public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));     // ALLOWED

        PremiumMovieTicket premium = new PremiumMovieTicket("B4", "S2", 500.0, "Dune");
        premium.showPrice();
    }
}

/**
 * In a real project this class would live in a different package from MovieTicket
 * (that's the whole point of the exercise). It's kept in the same default package
 * here only so the demo compiles as a single, self-contained folder of files.
 */
class PremiumMovieTicket extends MovieTicket {

    public PremiumMovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        super(seatNumber, screenId, ticketPrice, movieTitle);
    }

    void showPrice() {
        // ALLOWED: this.ticketPrice is accessed through the subclass's OWN type ('this'
        // is a PremiumMovieTicket), which matches SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE.
        System.out.println("Premium price: " + this.ticketPrice);
    }

    // If this method instead took a plain "MovieTicket other" parameter (the PARENT type)
    // and tried other.ticketPrice, that access would be DENIED across packages -
    // Java checks the *declared* type of the reference, not the actual runtime object.
}
