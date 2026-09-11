package problem2;

/**
 * Extends the Problem 1 linter with the subclass-in-a-different-package rule,
 * which is where protected genuinely diverges depending on the compile-time
 * type used to reach the field:
 *
 *   - SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE:
 *       the subclass accesses the protected member through a reference typed
 *       as the subclass itself (or one of its own instances) -> ALLOWED.
 *
 *   - SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE:
 *       the subclass tries to reach the member through a reference typed as
 *       the parent class (e.g. a plain LibraryMember instance/parameter)
 *       -> DENIED, because protected access across packages is only granted
 *       via the subclass's own type, not the parent's.
 */
public class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                // private never survives a subclass-in-a-different-package attempt.
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                // default never crosses a package boundary, subclass or not.
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                    return "ALLOWED";
                }
                // DIFFERENT_PACKAGE and SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE are both denied.
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                throw new IllegalArgumentException("Unknown fieldModifier: " + fieldModifier);
        }
    }

    /**
     * Scans attempts strictly in order and stops at the very first one that
     * would be denied. Does not build a full result list first.
     *
     * @return a description of the first denied attempt, or "None Denied"
     */
    public static String firstDeniedAttempt(String[][] attempts) {
        for (int i = 0; i < attempts.length; i++) {
            String modifier = attempts[i][0];
            String context = attempts[i][1];

            if (classifyAccess(modifier, context).equals("DENIED")) {
                return modifier + " via " + context + " (attempt #" + (i + 1) + ")";
            }
        }
        return "None Denied";
    }

    public static void main(String[] args) {
        String[][] attempts1 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(attempts1));
        // protected via SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE (attempt #2)

        String[][] attempts2 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(attempts2));
        // None Denied
    }
}
