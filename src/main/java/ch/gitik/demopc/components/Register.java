//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

import java.io.IOException;
import java.util.Vector;

public class Register extends Word implements Component {
    private Vector fListener = new Vector();
    private String fID;
    private int fWidth = 0;
    private ComponentListener fCompListener = null;

    public Register(int width, String id) {
        super(width);
        this.fCompListener = new ComponentListener(id);
        this.fID = id;
        this.fWidth = width;
    }

    public void reset() throws IOException {
        this.setInteger(0);

        try {
            this.broadcast(new Word(this.fWidth), 0);
        } catch (IllegalAccessException var2) {
            throw new IOException(this.fID + " broadcasting reset failed:" + var2.getMessage());
        }
    }

    public void setInteger(int value) {
        super.setInteger(value);

        try {
            this.broadcast(new Word(this.fWidth, value), 0);
        } catch (IllegalAccessException var3) {
            System.out.println(this.fID + " broadcasting reset failed:" + var3.getMessage());
        }

    }

    public void wordChanged(Word word, int address, String id) {
    }

    public void addListener(Component comp) throws IllegalAccessException {
        this.fCompListener.addListener(comp);
    }

    public void broadcast(Word word, int address) throws IllegalAccessException {
        this.fCompListener.broadcast(word, address);
    }
}
