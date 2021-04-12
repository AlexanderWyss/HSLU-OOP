package aufgaben.elements;

public class Mercury extends Element {
    public Mercury() {
        super(80, "Hg", -38.83f, 357);
    }

    @Override
    public String toString() {
        return super.toString() + " GIFTIG";
    }
}
