package problem5;

/**
 * Nightly circulation processor. Sets up one-time, class-level state via a
 * static block, distinguishes reference-only receipts from regular ones
 * using instanceof, and tolerates null placeholder entries from a flaky
 * scanner feed without ever crashing the run.
 */
public class CirculationLedger {

    private static final String BRANCH_CODE;

    static {
        // One-time, class-level setup - runs exactly once when the class loads.
        BRANCH_CODE = "PT-MAIN";
    }

    public static String getBranchCode() {
        return BRANCH_CODE;
    }

    /**
     * Single pass, O(1) extra space beyond the running counters.
     */
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
            processed++;
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(receipts));
        // 2 processed | 1 null skipped | 1 reference-only | 1 regular
    }
}
