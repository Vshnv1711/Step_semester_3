import java.util.Arrays;

/**
 * Base class for all gym memberships.
 *
 * Cumulative across Problems 1-5:
 *  - validated constructor + batch sign-up (P1)
 *  - polymorphic displayInfo() (P2)
 *  - protected, overridable chargeLateFee() with a private, defensively-copied history (P3)
 *  - polymorphic batchPrint() using instanceof-guarded downcasts (P4)
 *  - final membershipNumber from a shared static counter, overloaded payFee(),
 *    a non-regex referral-code validator, and a null-safe weekly check-in report (P5)
 */
public class GymMember {

    // ---- Problem 5: shared static counter for membership numbers ----
    private static int memberCounter = 2000;

    // ---- Problem 1 ----
    private final String memberId;
    private final int monthlyFee;

    // ---- Problem 2 ----
    private int sessionsAttended;

    // ---- Problem 3: private, encapsulated late-fee history ----
    private final int[] lateFeeHistory = new int[10];
    private int lateFeeCount = 0;

    // ---- Problem 5 ----
    private final String membershipNumber; // final: set once, never reassigned, no setter
    private int feesPaid;
    private String lastPaymentMode; // set by the two-arg payFee overload

    /**
     * Primary constructor (Problem 1). Validates memberId, then assigns a
     * membership number from the shared static counter (Problem 5).
     */
    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("memberId must be at least 4 non-blank characters");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;

        memberCounter++;
        this.membershipNumber = "GYM-" + memberCounter;
    }

    /**
     * Overload used by Problem 5's examples, where only a monthlyFee is supplied.
     * Delegates to the primary constructor with a placeholder id so that the
     * validation rule and membership-number assignment live in exactly one place.
     */
    public GymMember(int monthlyFee) {
        this("AUTO-GEN", monthlyFee);
    }

    // ---------------------------------------------------------------
    // Problem 2: attendance
    // ---------------------------------------------------------------
    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    /** Every subclass overrides this to describe itself (Problem 2). */
    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }

    // ---------------------------------------------------------------
    // Problem 3: late fees
    // ---------------------------------------------------------------

    /**
     * Applies a late fee and records it. Subclasses may override this to
     * change the amount actually charged (see PremiumMember), but every
     * override should funnel back through this method via super(...) so the
     * recording logic lives in exactly one place.
     */
    protected void chargeLateFee(int amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    /** Defensive copy - callers can never mutate the real internal history. */
    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }

    // ---------------------------------------------------------------
    // Problem 5: membership number, fees, referral codes, check-ins
    // ---------------------------------------------------------------

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public static int getMembersEnrolled() {
        return memberCounter - 2000;
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    /** Records the mode, then reuses the flat-amount overload instead of duplicating the total logic. */
    public void payFee(int amount, String mode) {
        this.lastPaymentMode = mode;
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getLastPaymentMode() {
        return lastPaymentMode;
    }

    /**
     * Format: "G" + two digits + one uppercase letter, e.g. "G45B".
     * Checked with charAt()/Character.isDigit()/isUpperCase() only - no regex.
     */
    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    /**
     * Walks a week's worth of check-ins, skipping nulls, and tallies how many
     * were group-class members versus regular (non-group) members.
     */
    public static String processWeeklyCheckIn(GymMember[] members) {
        int nullSkipped = 0, group = 0, individual = 0;
        for (GymMember m : members) {
            if (m == null) {
                nullSkipped++;
                continue;
            }
            if (m instanceof GroupClassMember) {
                group++;
            } else {
                individual++;
            }
        }
        int processed = group + individual;
        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }

    // ---------------------------------------------------------------
    // Problem 1: batch sign-up
    // ---------------------------------------------------------------

    /**
     * Attempts to construct one GymMember per entry. Construction itself is
     * the validator - signUpBatch never pre-checks the strings.
     */
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0, rejected = 0;
        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    // ---------------------------------------------------------------
    // Problem 2: classification & totals across the hierarchy
    // ---------------------------------------------------------------

    /** Decided purely with instanceof - no manual "type" field anywhere. */
    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Direct subclass";
        } else {
            return "Base class member";
        }
    }

    /** Sums getSessionsAttended() polymorphically - never branches on type. */
    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember m : members) {
            total += m.getSessionsAttended();
        }
        return total;
    }

    // ---------------------------------------------------------------
    // Problem 4: mixed-roster announcement
    // ---------------------------------------------------------------

    /**
     * Builds one announcement with a single StringBuilder. Printing itself is
     * fully polymorphic (displayInfo()); only the "trainer via downcast" extra
     * needs an instanceof-guarded cast, since trainerName isn't part of the
     * base GymMember contract.
     */
    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (GymMember m : members) {
            sb.append(m.displayInfo());
            if (m instanceof PremiumMember) {
                PremiumMember pm = (PremiumMember) m;
                sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }
}
