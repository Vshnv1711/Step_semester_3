package problem5;

/**
 * A LoanReceipt for books that never leave the building - they settle
 * differently during nightly circulation, distinguished via instanceof.
 */
public final class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
