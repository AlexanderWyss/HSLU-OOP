//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

public class Ram extends Memory {
    public static final String ID = "RAM";

    public Ram(AddressBus abus, DataBus dbus, ControlBus cbus) throws IllegalAccessException {
        super(4, 4, 16, "RAM");
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
}
