package problem1;

/**
 * LibraryMember, deliberately designed field-by-field so each one uses
 * the narrowest access level that still satisfies its requirement:
 *
 *  - membershipPin : private   -> reachable only from inside LibraryMember itself.
 *  - branchCode    : default   -> reachable only from classes in the same package.
 *  - finesOwed     : default   -> same-package reach (subclass/cross-package rules
 *                                 are handled separately in Problem 2's AccessChecker).
 *  - displayName   : public    -> reachable from anywhere.
 */
public class LibraryMember {

    private String membershipPin;

    String branchCode; // default (package-private) access

    String finesOwed; // default (package-private) access

    public String displayName;

    public LibraryMember(String membershipPin, String branchCode, String finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    // Only LibraryMember itself can reach membershipPin directly (private),
    // so any controlled access from within the class goes through here.
    private String getMembershipPin() {
        return membershipPin;
    }
}
