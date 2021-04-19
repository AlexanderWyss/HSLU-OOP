package aufgaben.elements;

public final class Mercury extends Element {
    public Mercury() {
        super(80, "Hg", "Mercury", -38.83f, 357);
    }

    @Override
    public String toString() {
        return super.toString() + " GIFTIG";
    }
}
