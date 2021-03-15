//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class ComponentListener implements ch.gitik.demopc.components.Component {
    private Vector fListener = new Vector();
    protected String fID = null;

    public ComponentListener(String id) {
        this.fID = id;
    }

    public void addListener(ch.gitik.demopc.components.Component cmp) throws IllegalAccessException {
        if (this.fListener.contains(cmp)) {
            throw new IllegalAccessException("Listener already added!");
        } else {
            this.fListener.addElement(cmp);
        }
    }

    public void broadcast(Word word, int address) throws IllegalAccessException {
        Enumeration e = this.fListener.elements();

        while(e.hasMoreElements()) {
            ch.gitik.demopc.components.Component cmp = (ch.gitik.demopc.components.Component)e.nextElement();
            cmp.wordChanged(word, address, this.fID);
        }

    }

    public void wordChanged(Word word, int address, String id) throws IllegalAccessException {
    }

    public void reset() throws IOException {
    }
}
