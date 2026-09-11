/**
 * Category B - Problem 4 (Intermediate)
 * MovieBookingProfile JavaBean & OTP Property
 *
 * A JavaBean-compliant class: public no-arg constructor, a convenience
 * constructor chained via this(...), getX/setX pairs (isX for booleans),
 * and a genuinely write-only "otp" property - setOtp exists, but no
 * getter for it exists anywhere on the class, so it can never be read back.
 */
public class Problem4_MovieBookingProfile {

    public static void main(String[] args) {
        MovieBookingProfile p1 = new MovieBookingProfile("Rahul Dev");
        System.out.println(p1.getName()); // Rahul Dev

        p1.setConfirmed(true);
        System.out.println(p1.isConfirmed()); // true

        p1.setOtp("4471"); // write-only: there's no getOtp() to call, by design

        MovieBookingProfile p2 = new MovieBookingProfile();
        p2.setName("Anita Rao");
        System.out.println(p2.getName()); // Anita Rao
        System.out.println(p2.isConfirmed()); // false (default)
    }
}

class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // Write-only property: no getOtp()/isOtp() anywhere on this class,
    // so once set, the OTP can never be read back out.
    public void setOtp(String otp) {
        this.otp = otp;
    }
}
