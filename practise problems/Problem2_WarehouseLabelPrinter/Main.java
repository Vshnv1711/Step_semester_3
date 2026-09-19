package problem2;

public class Main {

    // Works on ANY mix of Printable implementers, related or not.
    static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        System.out.println(p.printLabel()); // Package label: TRK-88

        Invoice i = new Invoice("INV-42");
        System.out.println(i.printLabel()); // Invoice label: INV-42

        printAll(new Printable[]{ p, i });
        // Package label: TRK-88
        // Invoice label: INV-42
    }
}
