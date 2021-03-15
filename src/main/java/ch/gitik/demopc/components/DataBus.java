//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

public class DataBus extends Bus {
    public DataBus() {
        super(4, "DATA_BUS");
    }

    public DataBus(int width) {
        super(width, "DATA_BUS");
    }

    public DataBus(Component cmp) throws IllegalAccessException {
        this();
        this.addListener(cmp);
    }
}
