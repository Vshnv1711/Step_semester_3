package problem1;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Pre-compile linter for field-reach classification, based purely on
 * real Java visibility rules (never hardcoded field names).
 */
public class AccessChecker {

    /**
     * Decides whether a single access attempt would be ALLOWED or DENIED
     * under real Java visibility rules.
     *
     * @param fieldModifier   one of "private", "default", "protected", "public"
     * @param accessorContext one of "SAME_CLASS", "SAME_PACKAGE", "DIFFERENT_PACKAGE"
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                throw new IllegalArgumentException("Unknown fieldModifier: " + fieldModifier);
        }
    }

    /**
     * Groups classification results by modifier (not as one flat total).
     * Every one of the four modifiers is always present in the summary,
     * even if it had zero attempts in this particular batch.
     *
     * @param attempts array of {fieldModifier, accessorContext} pairs
     */
    public static String summarizeByModifier(String[][] attempts) {
        // Fixed insertion order so the output order is deterministic,
        // and every modifier is present regardless of what appears in the batch.
        Map<String, int[]> counts = new LinkedHashMap<>();
        counts.put("private", new int[2]);
        counts.put("default", new int[2]);
        counts.put("protected", new int[2]);
        counts.put("public", new int[2]);

        for (String[] attempt : attempts) {
            String modifier = attempt[0];
            String context = attempt[1];
            String result = classifyAccess(modifier, context);

            int[] tally = counts.get(modifier);
            if (result.equals("ALLOWED")) {
                tally[0]++;
            } else {
                tally[1]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, int[]> entry : counts.entrySet()) {
            if (!first) {
                sb.append(" | ");
            }
            first = false;
            int[] tally = entry.getValue();
            sb.append(entry.getKey())
              .append(": ")
              .append(tally[0])
              .append(" allowed / ")
              .append(tally[1])
              .append(" denied");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));       // ALLOWED
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(attempts));
    }
}
