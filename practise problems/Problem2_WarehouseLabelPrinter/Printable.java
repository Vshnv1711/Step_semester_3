package problem2;

/**
 * The only thing PackageBox and Invoice will ever share is this contract.
 * They have no common parent class and nothing else in common.
 */
public interface Printable {
    String printLabel();
}
