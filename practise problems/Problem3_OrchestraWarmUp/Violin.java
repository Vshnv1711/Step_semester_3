package problem3;

public class Violin extends StringInstrument {

    public Violin() {
        super();
    }

    // Reuses StringInstrument's message via super.play() and only
    // appends the extra detail specific to a violin -- never rebuilding
    // the whole message from scratch.
    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}
