//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

import java.io.IOException;

public abstract class Bus extends ComponentListener {
    public static final String ADDRESS_BUS = "ADDRESS_BUS";
    public static final String DATA_BUS = "DATA_BUS";
    public static final String CONTROL_BUS = "CONTROL_BUS";
    private int fWidth = 0;

    public Bus(int width, String id) {
        super(id);
        this.fWidth = width;
    }

    public void broadcast(Word word, int address) throws IllegalAccessException {
        if (word.getWidth() > this.fWidth) {
            throw new IllegalAccessException("Error: word to big for bus!");
        } else {
            super.broadcast(word, address);
        }
    }

    public void reset() throws IOException {
        try {
            this.broadcast(new Word(this.fWidth), 0);
        } catch (IllegalAccessException var2) {
            throw new IOException("Unable to reset bus!");
        }
    }

    private void checkBustype(String type) throws IllegalArgumentException {
    }
}
