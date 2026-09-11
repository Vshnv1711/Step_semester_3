/**
 * Category B - Problem 1 (Basic)
 * Movie Ticket Field Visibility Checker
 *
 * AccessChecker classifies whether an access attempt is ALLOWED or DENIED
 * based on the real Java visibility rules for private / default / protected / public,
 * for three contexts: SAME_CLASS, SAME_PACKAGE, DIFFERENT_PACKAGE.
 *
 * MovieTicket is built with an access level on each field chosen for a real reason:
 *  - seatNumber  : private   -> unique internal state of one ticket, only the class itself should touch it
 *  - screenId    : default   -> the booking engine (same package) needs it, but outsiders shouldn't
 *  - ticketPrice : protected -> a subclass (see Problem 2's PremiumMovieTicket) needs to read/adjust it
 *  - movieTitle  : public    -> harmless display info, safe for anyone to read
 */
public class Problem1_AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                // Problem 1 only deals with these three contexts, so protected behaves
                // like default here (the cross-package subclass wrinkle is Problem 2's job).
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                throw new IllegalArgumentException("Unknown fieldModifier: " + fieldModifier);
        }
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;
        for (String[] attempt : attempts) {
            String result = classifyAccess(attempt[0], attempt[1]);
            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));          // ALLOWED
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED

        String[][] batch = {
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(batch)); // Allowed: 2 | Denied: 1

        MovieTicket ticket = new MovieTicket("A1", "S1", 250.0, "Inception");
        System.out.println(ticket.getMovieTitle());
        System.out.println(ticket.getSeatNumber());
    }
}

class MovieTicket {
    private String seatNumber;
    String screenId;           // default / package-private
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}
