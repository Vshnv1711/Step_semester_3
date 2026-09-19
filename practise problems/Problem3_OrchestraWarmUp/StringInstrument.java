package problem3;

public class StringInstrument extends Instrument {

    public StringInstrument() {
        super();
    }

    // Instrument.play() is abstract (no body to call via super.play()),
    // so this is the first concrete link in the chain -- the base
    // message that every string instrument's warm-up builds on.
    @Override
    public String play() {
        return "Strumming the strings";
    }
}
