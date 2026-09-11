/**
 * Category B - Problem 3 (Intermediate)
 * Seat Booking Encapsulation Guard
 *
 * seatsTotal / seatsAvailable are both private, with no direct setter for
 * seatsAvailable at all - the only ways to change it are the validated
 * bookSeat() / cancelBooking() methods, which silently no-op instead of
 * pushing the count out of [0, seatsTotal].
 */
public class Problem3_CineScreen {

    public static void main(String[] args) {
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected: " + e.getMessage());
        }

        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat(); // no seat left -> silently rejected
        System.out.println(c.getSeatsAvailable()); // 0

        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking(); // already full -> silently rejected
        System.out.println(c.getSeatsAvailable()); // 2
    }
}

class CineScreen {
    private final int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException("seatsTotal must be a positive integer");
        }
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
        // else: nothing to book, ignore silently
    }

    public void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
        // else: already at full capacity, nothing genuine to undo
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }
}
