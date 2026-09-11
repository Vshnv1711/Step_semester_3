package problem3;

/**
 * copiesAvailable can only ever move through checkOut()/checkIn() -
 * "one door in, one door out." Both invalid transitions are rejected
 * silently, and the invariant 0 <= copiesAvailable <= copiesTotal
 * always holds.
 */
public class BookInventory {

    private final int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    /**
     * Rejected (no state change) if no copies are currently available.
     */
    public void checkOut() {
        if (copiesAvailable <= 0) {
            return; // silently reject - nothing left to check out
        }
        copiesAvailable--;
    }

    /**
     * Rejected (no state change) if the inventory is already at full
     * capacity - there is nothing genuine to "return."
     */
    public void checkIn() {
        if (copiesAvailable >= copiesTotal) {
            return; // silently reject - already at full capacity
        }
        copiesAvailable++;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // 4th attempt - rejected
        System.out.println(b.getCopiesAvailable()); // 0

        BookInventory b2 = new BookInventory(3);
        b2.checkIn();
        b2.checkIn();
        b2.checkIn();
        b2.checkIn(); // 4th attempt - rejected
        System.out.println(b2.getCopiesAvailable()); // 3
    }
}
