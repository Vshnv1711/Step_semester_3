/**
 * Category B - Problem 1 (Basic)
 * Library Membership Foundation & Batch Enrollment Validator
 *
 * LibraryMember is the base class with a validated constructor - a memberId
 * that's blank, whitespace-only, or shorter than 4 characters is rejected
 * right there, so every other piece of code (including enrollBatch) can
 * simply trust that any LibraryMember it holds is already valid.
 *
 * StudentMember forwards the shared fields via super(...) instead of
 * duplicating memberId/borrowLimit as its own fields.
 *
 * NOTE: LibraryMember/StudentMember here already include the pieces that
 * Problem 2 (displayInfo, subclassing) and Problem 3 (chargeFine, fine
 * history) build on, since this is one continuously growing hierarchy
 * across the three problems rather than three unrelated ones.
 */
public class Problem1_LibraryEnrollment {

    static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;
        for (String memberId : memberIds) {
            try {
                new LibraryMember(memberId, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                // enrollBatch never pre-validates the strings itself - it just
                // tries to construct one and lets the constructor's own rule decide.
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected"); // LB1 is only 3 characters
        }

        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed()); // 2

        System.out.println(enrollBatch(
                new String[]{"STU1", "LB1", "STU2", " ", "STU3"}, 3
        )); // Enrolled: 3 | Rejected: 2
    }
}

class LibraryMember {

    private final String memberId;
    private final int borrowLimit;
    private int booksBorrowed;

    // Every fine ever charged (base class or any override) lands here.
    // Kept as a List internally purely so getFineHistory() can hand out a
    // brand-new array every time without ever exposing this field itself.
    private final java.util.List<Integer> fineHistory = new java.util.ArrayList<>();

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "memberId must be at least 4 non-blank characters: " + memberId);
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    // Overridden by StudentMember, which halves the amount before delegating
    // back here via super.chargeFine(...) - this is the only place a fine
    // actually gets recorded into fineHistory.
    protected void chargeFine(int amount) {
        fineHistory.add(amount);
    }

    public int[] getFineHistory() {
        int[] copy = new int[fineHistory.size()];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = fineHistory.get(i);
        }
        return copy; // a fresh array every call - never the real internal list
    }

    public int getTotalFine() {
        int total = 0;
        for (int fine : fineHistory) {
            total += fine;
        }
        return total;
    }
}

class StudentMember extends LibraryMember {

    private final String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
    }

    @Override
    protected void chargeFine(int amount) {
        // Reuses the parent's own deduction + recording logic in one call -
        // no second, separate recording step lives here.
        super.chargeFine(amount / 2);
    }
}
