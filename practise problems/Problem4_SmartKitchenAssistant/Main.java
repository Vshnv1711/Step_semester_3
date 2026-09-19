package problem4;

public class Main {
    public static void main(String[] args) {
        Blender b = new Blender();

        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel()); // 3

        b.setSpeedLevel(9); // rejected, out of range
        System.out.println(b.getSpeedLevel()); // still 3

        System.out.println(b.prepare()); // Blending at speed 3
        System.out.println(b.clean());   // Blender rinsed and dried
    }
}
