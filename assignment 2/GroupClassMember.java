/**
 * Hierarchical inheritance: GroupClassMember extends GymMember directly,
 * completely independent of the PremiumMember/EliteMember branch.
 */
public class GroupClassMember extends GymMember {

    private final String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    /** Overload used by Problem 5's examples, where only a monthlyFee is supplied. */
    public GroupClassMember(int monthlyFee, String className) {
        this("AUTO-GEN", monthlyFee, className);
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
    }
}
