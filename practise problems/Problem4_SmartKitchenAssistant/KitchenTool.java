package problem4;

public abstract class KitchenTool {

    // Private field -- only reachable through the JavaBean getter/setter.
    private int speedLevel = 1;

    public abstract String prepare();

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        // Reject first, assign second -- an invalid value never touches
        // the field, so the previous valid value is simply kept.
        if (speedLevel < 1 || speedLevel > 5) {
            return;
        }
        this.speedLevel = speedLevel;
    }
}
