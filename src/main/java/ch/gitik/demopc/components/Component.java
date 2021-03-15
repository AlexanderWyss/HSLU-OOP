//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

import java.io.IOException;

public interface Component {
    int CHIP_SELECT = -1;

    void broadcast(Word var1, int var2) throws IllegalAccessException;

    void wordChanged(Word var1, int var2, String var3) throws IllegalAccessException;

    void addListener(Component var1) throws IllegalAccessException;

    void reset() throws IOException;
}
