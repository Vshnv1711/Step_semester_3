package problem2.premium;

import problem2.LibraryMember;

/**
 * A different package extending LibraryMember, demonstrating live the exact
 * rule AccessChecker.classifyAccess encodes for protected fields:
 *
 *   - via "own type" (a PremiumMember reference)  -> compiles (ALLOWED)
 *   - via "parent type" (a LibraryMember reference) -> does NOT compile (DENIED)
 */
public class PremiumMember extends LibraryMember {

    private int loyaltyPoints;

    public PremiumMember(String finesOwed, int loyaltyPoints) {
        super(finesOwed);
        this.loyaltyPoints = loyaltyPoints;
    }

    public void ownTypeAccessCompiles(PremiumMember other) {
        // ALLOWED: reached through the subclass's own type.
        String owed = other.finesOwed;
        System.out.println("Own-type access works: " + owed);
    }

    // public void parentTypeAccessFailsToCompile(LibraryMember other) {
    //     // DENIED: this line would NOT compile if uncommented -
    //     // protected access across packages requires the subclass's own type,
    //     // not the parent type, even though we ARE the subclass.
    //     String owed = other.finesOwed;
    // }
}
