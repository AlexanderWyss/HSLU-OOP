//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

import java.io.IOException;

public class Io extends Memory {
    public static final String ID = "IO";

    public Io(AddressBus abus, DataBus dbus, ControlBus cbus) throws IllegalAccessException {
        super(2, 4, 25, "IO");
        if (abus != null && cbus != null && dbus != null) {
            this.setReadOnly(false);
            this.setDataBus(dbus);
            abus.addListener(this);
            cbus.addListener(this);
            dbus.addListener(this);
        } else {
            throw new IllegalAccessException("Argument(s) may not be null!");
        }
    }

    public boolean isReadOnly() {
        return this.getAddress() == 26;
    }

    public void reset() throws IOException {
        super.reset();

        try {
            this.setWord(25, new Word(4, 0));
        } catch (IllegalAccessException var2) {
            throw new IOException(var2.getMessage());
        }
    }
}
