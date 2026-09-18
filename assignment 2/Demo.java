public class Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 1 ===");
        try {
            new GymMember("GM1", 1000);
            System.out.println("construction succeeded (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println(p.getSessionsAttended()); // 2

        System.out.println(GymMember.signUpBatch(
                new String[]{"MEM1", "GM1", "MEM2", " ", "MEM3"}, 1000)); // Signed Up: 3 | Rejected: 2

        System.out.println("\n=== Problem 2 ===");
        System.out.println(new GymMember("MEM1", 1000).displayInfo());
        System.out.println(new PremiumMember("MEM2", 2000, "Coach Riya").displayInfo());
        System.out.println(new EliteMember("MEM3", 3000, "Coach Arjun", "L12").displayInfo());
        System.out.println(new GroupClassMember("MEM4", 1500, "Zumba").displayInfo());

        PremiumMember premiumMember = new PremiumMember("MEM5", 2000, "Coach Riya");
        EliteMember eliteMember = new EliteMember("MEM6", 3000, "Coach Arjun", "L12");
        GroupClassMember groupClassMember = new GroupClassMember("MEM7", 1500, "Zumba");

        System.out.println(GymMember.classifyGeneration(eliteMember));       // Multilevel descendant (3 generations deep)
        System.out.println(GymMember.classifyGeneration(groupClassMember)); // Hierarchical sibling (independent branch)

        for (int i = 0; i < 3; i++) premiumMember.attendSession();
        for (int i = 0; i < 2; i++) eliteMember.attendSession();
        for (int i = 0; i < 4; i++) groupClassMember.attendSession();
        System.out.println(GymMember.getTotalSessionsAttended(
                new GymMember[]{premiumMember, eliteMember, groupClassMember})); // 9

        System.out.println("\n=== Problem 3 ===");
        PremiumMember p3 = new PremiumMember("MEM5", 2000, "Coach Riya");
        p3.chargeLateFee(200);
        System.out.println(p3.getTotalLateFees()); // 100

        int[] history = p3.getLateFeeHistory();
        history[0] = 999; // tampering with the returned copy
        System.out.println(java.util.Arrays.toString(p3.getLateFeeHistory())); // [100]

        System.out.println("\n=== Problem 4 ===");
        System.out.println(GymMember.batchPrint(new GymMember[]{
                new GymMember("MEM6", 1000),
                new PremiumMember("MEM7", 2000, "Coach Riya")
        }));

        try {
            GymMember plain = new GymMember("MEM8", 1000);
            PremiumMember bad = (PremiumMember) plain; // compiles, throws at runtime
            System.out.println("cast succeeded (unexpected): " + bad);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }

        System.out.println("\n=== Problem 5 ===");
        GymMember m1 = new GymMember(1000);
        System.out.println(m1.getMembershipNumber());       // GYM-2001 (depends on how many objects made so far)
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(GymMember.isValidReferralCode("G45B")); // true
        System.out.println(GymMember.isValidReferralCode("G4B"));  // false
        System.out.println(GymMember.isValidReferralCode("X45B")); // false

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid()); // 1000

        System.out.println(GymMember.processWeeklyCheckIn(new GymMember[]{
                new GroupClassMember(1500, "Zumba"), null, new GymMember(1000)
        })); // 2 processed | 1 null skipped | 1 group | 1 individual
    }
}
