/**
 * Category B - Problem 3 (Intermediate)
 * The Student Discount & Fine Ledger
 *
 * The actual fine logic lives on LibraryMember/StudentMember (see
 * Problem1_LibraryEnrollment.java) - chargeFine is defined once on
 * LibraryMember and overridden with @Override on StudentMember, which
 * halves the amount and delegates to super.chargeFine(...) rather than
 * reimplementing the recording logic. This file just demonstrates it.
 */
public class Problem3_FineLedger {

    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine()); // 50 - StudentMember halves every fine

        int[] history = s.getFineHistory();
        history[0] = 999; // tampering with the returned array...
        System.out.println(java.util.Arrays.toString(s.getFineHistory())); // [50] - untouched

        // A second fine to show the history keeps growing correctly.
        s.chargeFine(40);
        System.out.println(java.util.Arrays.toString(s.getFineHistory())); // [50, 20]
        System.out.println(s.getTotalFine()); // 70
    }
}
