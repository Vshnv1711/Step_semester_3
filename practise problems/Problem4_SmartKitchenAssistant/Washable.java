package problem4;

/**
 * Cleaning is a completely separate concern from how a tool prepares
 * food, so it lives in its own interface rather than in KitchenTool.
 */
public interface Washable {
    String clean();
}
