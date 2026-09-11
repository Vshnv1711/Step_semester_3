package problem4;

/**
 * A fully compliant JavaBean for the kiosk's scanning framework.
 *
 *  - membershipId : a public setter genuinely exists (the framework requires
 *                    it to be scannable), but it only takes effect once.
 *  - securityAnswer: genuinely write-only - stored only as a one-way
 *                    transformed value, and no getter exists anywhere.
 */
public class LibraryMember {

    private String membershipId;
    private boolean membershipIdSet = false;

    private String name;

    private boolean premiumMember;

    private String securityAnswerHash;

    // Public no-argument constructor, required by the JavaBean convention.
    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    // A real, public setter exists (the framework's scan requires it),
    // but every call after the first is silently ignored.
    public void setMembershipId(String id) {
        if (!membershipIdSet) {
            this.membershipId = id;
            this.membershipIdSet = true;
        }
        // later calls: silently ignored, write-once semantics preserved
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // isX() naming, as required for a boolean property.
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only: stores a one-way transformed value. No getSecurityAnswer()
    // or any other method on this class ever exposes it again.
    public void setSecurityAnswer(String answer) {
        this.securityAnswerHash = oneWayHash(answer);
    }

    // Deterministic, one-way transformation - no real crypto library needed.
    private static String oneWayHash(String input) {
        if (input == null) {
            return null;
        }
        int hash = 17;
        for (int i = 0; i < input.length(); i++) {
            hash = hash * 31 + input.charAt(i);
        }
        return Integer.toHexString(hash);
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println(m.getMembershipId()); // LIB-8841

        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId()); // still LIB-8841

        System.out.println(m.isPremiumMember()); // true

        m.setSecurityAnswer("BlueMountain"); // stored, but unrecoverable
    }
}
