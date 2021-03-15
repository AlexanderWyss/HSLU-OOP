//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

public class AddressBus extends Bus {
    public AddressBus() {
        super(5, "ADDRESS_BUS");
    }

    public AddressBus(int width) {
        super(width, "CONTROL_BUS");
    }

    public AddressBus(Component cmp) throws IllegalAccessException {
        this();
        this.addListener(cmp);
    }
}
