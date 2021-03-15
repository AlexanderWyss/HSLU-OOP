//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

import java.io.IOException;

public class PCounter extends ComponentListener {
    public static final String ID = "PC";
    private int fCount = 0;
    private int fMax = 9;

    public PCounter(int max) {
        super("PC");
        this.fCount = 0;
    }

    public void reset() throws IOException {
        this.fCount = 0;
        this.broadcast();
    }

    public void increment() throws IOException {
        ++this.fCount;
        if (this.fCount > this.fMax) {
            this.fCount = 0;
        }

        this.broadcast();
    }

    public void setCounter(int value) throws IOException {
        this.fCount = value;
        if (this.fCount > this.fMax) {
            this.fCount = 0;
        }

        this.broadcast();
    }

    public int getCounter() {
        return this.fCount;
    }

    private void broadcast() throws IOException {
        try {
            this.broadcast(new Word(5, this.fCount), 0);
        } catch (IllegalAccessException var2) {
            throw new IOException(this.fID + ": Unable to broadcast reset!");
        }
    }
}
