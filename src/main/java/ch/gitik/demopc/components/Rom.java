//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

public class Rom extends Memory {
    public static final String ID = "ROM";

    public Rom(AddressBus abus, DataBus dbus, ControlBus cbus) throws IllegalAccessException {
        super(10, 4, 0, "ROM");
        if (abus != null && cbus != null && dbus != null) {
            this.setReadOnly(true);
            this.setDataBus(dbus);
            abus.addListener(this);
            cbus.addListener(this);
            dbus.addListener(this);
        } else {
            throw new IllegalAccessException("Argument(s) may not be null!");
        }
    }
}
