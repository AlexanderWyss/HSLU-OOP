//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

public class Word implements Cloneable {
    private boolean[] fBits;

    public Word(int width) {
        this.fBits = null;
        this.fBits = new boolean[width];
    }

    public Word(int width, int value) {
        this(width);
        this.setInteger(value);
    }

    public boolean getBit(int offset) throws IllegalArgumentException {
        this.validateOffset(offset);
        return this.fBits[offset];
    }

    public void setBit(int offset, boolean value) throws IllegalArgumentException {
        this.validateOffset(offset);
        synchronized(this.fBits) {
            this.fBits[offset] = value;
        }
    }

    private void validateOffset(int offset) throws IllegalArgumentException {
        if (offset < 0 || offset > this.fBits.length) {
            throw new IllegalArgumentException("Illegal Offset");
        }
    }

    public Word copy() {
        try {
            return (Word)this.clone();
        } catch (Exception var2) {
            return null;
        }
    }

    public int toInteger() {
        int i = 0;

        for(int j = 0; j < this.fBits.length; ++j) {
            if (this.fBits[j]) {
                i = (int)((double)i + Math.pow(2.0D, (double)j));
            }
        }

        return i;
    }

    public String toString() {
        String s = new String();

        for(int i = this.fBits.length - 1; i >= 0; --i) {
            if (this.fBits[i]) {
                s = s.concat("1");
            } else {
                s = s.concat("0");
            }
        }

        s = s.concat(", [" + this.toInteger() + "]");
        return s;
    }

    public void setInteger(int value) {
        for(int i = 0; i < this.fBits.length; ++i) {
            if (value % 2 == 0) {
                this.fBits[i] = false;
            } else {
                this.fBits[i] = true;
            }

            value /= 2;
        }

    }

    public int getWidth() {
        return this.fBits.length;
    }
}
