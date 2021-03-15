//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

public class ControlBus extends ch.gitik.demopc.components.Bus {
    public static final int READ = 2;
    public static final int WRITE = 1;

    public ControlBus() {
        this(2);
    }

    public ControlBus(int width) {
        super(width, "CONTROL_BUS");
    }

    public ControlBus(ch.gitik.demopc.components.Component cmp) throws IllegalAccessException {
        this();
        this.addListener(cmp);
    }
}
