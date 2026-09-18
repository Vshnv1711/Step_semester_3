/**
 * Single inheritance: PremiumMember extends GymMember directly.
 * memberId and monthlyFee are never duplicated here - they are forwarded via super(...).
 */
public class PremiumMember extends GymMember {

    private final String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }

    /**
     * Halves the fee, then reuses the parent's own deduction/recording logic
     * via super(...) instead of reimplementing it.
     */
    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}
