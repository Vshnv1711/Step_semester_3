/**
 * Category B - Problem 2 (Basic -> Intermediate)
 * Three Branches of the Membership Tree
 *
 * HonorsStudentMember extends StudentMember (multilevel: LibraryMember ->
 * StudentMember -> HonorsStudentMember, three generations deep).
 * FacultyMember extends LibraryMember directly - a completely independent
 * branch, hierarchical rather than multilevel.
 *
 * classifyGeneration(...) tells the two apart using instanceof alone - no
 * class here carries a manual "type" field.
 */
public class Problem2_MembershipHierarchy {

    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Direct subclass (2 generations deep)";
        } else {
            return "Base member (LibraryMember)";
        }
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            // Polymorphism does the work here - calling getBooksBorrowed() through
            // a LibraryMember reference already runs each object's own version,
            // whatever subclass it actually is, with no type-checking needed.
            total += member.getBooksBorrowed();
        }
        return total;
    }

    public static void main(String[] args) {
        LibraryMember general = new LibraryMember("STU1", 3);
        StudentMember student = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember honors = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember faculty = new FacultyMember("STU4", 5, "Physics");

        System.out.println(general.displayInfo());
        // General Member | Books Borrowed: 0
        System.out.println(student.displayInfo());
        // Student Member | Course: CSE | Books Borrowed: 0
        System.out.println(honors.displayInfo());
        // Honors Student Member | Course: ECE | Bonus Limit: 2 | Books Borrowed: 0
        System.out.println(faculty.displayInfo());
        // Faculty Member | Department: Physics | Books Borrowed: 0

        System.out.println(classifyGeneration(honors));  // Multilevel descendant (3 generations deep)
        System.out.println(classifyGeneration(faculty)); // Hierarchical sibling (independent branch)

        student.borrowBook();
        student.borrowBook();       // studentMember has borrowed 2
        honors.borrowBook();        // honorsMember has borrowed 1
        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();       // facultyMember has borrowed 3

        System.out.println(getTotalBooksBorrowed(
                new LibraryMember[]{student, honors, faculty}
        )); // 6
    }
}

class HonorsStudentMember extends StudentMember {

    private final int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    public int getBonusLimit() {
        return bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + getCourse()
                + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class FacultyMember extends LibraryMember {

    private final String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department
                + " | Books Borrowed: " + getBooksBorrowed();
    }
}
