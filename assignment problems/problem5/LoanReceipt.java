package problem5;

import java.util.Arrays;

/**
 * Genuinely immutable: final fields, defensive copy on the way in AND on
 * the way out, and no mutators anywhere. Corrections happen through a
 * "wither" method that returns a brand-new object.
 *
 * NOTE: the spec asks for both "the class itself must be declared final"
 * AND a ReferenceOnlyLoanReceipt that extends it - those two requirements
 * are mutually exclusive in real Java (a final class cannot be subclassed).
 * Immutability here is guaranteed the way it actually matters - final
 * fields plus defensive copying in both directions plus zero setters -
 * while leaving the class open specifically so the required subclass can
 * exist and be told apart via instanceof in processNightlyCirculation.
 */
public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        // Defensive copy on the way IN - the caller can mutate their own
        // array afterward without touching our internal state.
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        // Defensive copy on the way OUT - callers can mutate the array they
        // receive without corrupting our real internal data.
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    /**
     * Returns a brand-new LoanReceipt with one book ID corrected, leaving
     * this receipt completely untouched.
     */
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] corrected = Arrays.copyOf(bookIds, bookIds.length);
        corrected[index] = newId;
        return new LoanReceipt(this.memberId, corrected);
    }

    public static void main(String[] args) {
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]); // BK-100 - untouched

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println(Arrays.toString(r.getBookIds()));         // [BK-100, BK-101]
        System.out.println(Arrays.toString(corrected.getBookIds())); // [BK-100, BK-102]
    }
}
