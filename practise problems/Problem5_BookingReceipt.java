import java.util.Arrays;

/**
 * Category B - Problem 5 (Intermediate -> Advanced)
 * Immutable Booking Receipt & Nightly Settlement
 *
 * Design note on BookingReceipt vs "final class":
 * The brief asks for BookingReceipt's fields to be final AND the class itself final.
 * Taken literally, that would make it impossible for GroupBookingReceipt to extend it -
 * yet the settlement processor is required to take a single BookingReceipt[] array and
 * use instanceof to tell a GroupBookingReceipt apart from a regular one, which only makes
 * sense if GroupBookingReceipt IS a BookingReceipt. So here BookingReceipt is left
 * non-final (to allow that one intentional subclass) but is kept just as immutable in
 * every other sense: all fields are final, there are no setters, and every array in/out
 * is defensively copied. GroupBookingReceipt adds one extra final field and no mutators.
 */
public class Problem5_BookingReceipt {

    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X"; // mutate the array we got back
        System.out.println(b.getSeatNumbers()[0]); // A1 - receipt is unaffected

        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println(Arrays.toString(b.getSeatNumbers()));       // [A1, A2]
        System.out.println(Arrays.toString(updated.getSeatNumbers())); // [A1, A3]

        BookingReceipt[] batch = {
                new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
                null,
                new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println(processNightlySettlement(batch));
        // 2 processed | 1 null skipped | 1 group | 1 individual
    }

    static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (BookingReceipt r : receipts) {
            if (r == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (r instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }
}

class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone(); // defensive copy IN
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone(); // defensive copy OUT
    }

    /** "Wither" pattern: never mutates this receipt, always returns a new one. */
    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] newSeats = this.seatNumbers.clone();
        newSeats[index] = newSeat;
        return new BookingReceipt(this.bookingId, newSeats);
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}
