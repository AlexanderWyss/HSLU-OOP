//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc;

import ch.gitik.demopc.components.Component;
import ch.gitik.demopc.components.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class DemoComputer extends Panel implements Component {
    private boolean fInitialised = false;
    private boolean fCrashed = false;
    private Image fBackImg = null;
    private Image fBufferedImage = null;
    private Hashtable fRec = new Hashtable();
    private CPU cpu = null;
    private Rom rom = null;
    private Ram ram = null;
    private Io io = null;
    private AddressBus adressBus = null;
    private DataBus dataBus = null;
    private ControlBus controlBus = null;

    public DemoComputer() {
    }

    private String resolveXY(int x, int y, Rectangle r) {
        String id = null;
        String element = "unknown";
        Rectangle rect = null;
        Enumeration e = this.fRec.keys();

        while (e.hasMoreElements()) {
            id = (String) e.nextElement();
            rect = (Rectangle) this.fRec.get(id);
            r.x = rect.x;
            r.y = rect.y;
            r.width = rect.width;
            r.height = rect.height;
            if (x >= r.x && x <= r.width && y >= r.y && y <= r.height) {
                element = id;
                break;
            }
        }

        return element;
    }

    private void handleMouseClick(int x, int y) {
        if (this.fCrashed) {
            System.out.println("CPU crashed! Refusing to handle events...");
        } else {
            Rectangle r = new Rectangle();
            String element = this.resolveXY(x, y, r);
            if (element.equals("clock")) {
                if (!this.cpu.isAuto()) {
                    this.cpu.clock();
                }

            } else {
                int col;
                if (element.equals("rom")) {
                    y -= r.y;
                    col = y / 18;
                    x -= r.x;
                    int colx = x / 18;
                    colx = 3 - colx;

                    try {
                        this.rom.setBit(col, colx, !this.rom.getBit(col, colx));
                    } catch (IllegalAccessException var8) {
                        System.out.println("Unable to toggle bit: " + var8.getMessage());
                    }

                } else if (element.equals("reset")) {
                    try {
                        this.reset();
                    } catch (IOException var9) {
                        System.out.println("Unable to reset machine (" + var9.getMessage() + ")");
                    }

                } else if (element.equals("input")) {
                    col = 3 - (y - r.y) / 18;

                    try {
                        this.io.setBit(1, col, !this.io.getBit(1, col));
                    } catch (IllegalAccessException var10) {
                        System.out.println("Unable to toggle bit: " + var10.getMessage());
                    }

                } else if (element.equals("auto")) {
                    this.drawWord(r.x + 2, r.y + 1, new Word(1, this.cpu.isAuto() ? 0 : 1), Color.red);
                    this.drawWord(30, 180, new Word(1, !this.cpu.isAuto() ? 0 : 1), Color.green);
                    this.cpu.toggleAuto();
                }
            }
        }
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        if (img != this.fBackImg) {
            return false;
        } else if (infoflags == 3) {
            this.fBufferedImage.getGraphics().drawImage(img, 0, 0, this);
            return true;
        } else if (infoflags == 32) {
            this.fBufferedImage.getGraphics().drawImage(img, 0, 0, this);
            this.repaint();
            return false;
        } else if (infoflags == 64) {
            this.fBufferedImage.getGraphics().drawString("Error loading Background image!", 10, 10);
            this.repaint();
            return false;
        } else if (infoflags == 128) {
            this.fBufferedImage.getGraphics().drawString("Error loading Background image!", 10, 10);
            this.repaint();
            return false;
        } else {
            return true;
        }
    }

    public void update(Graphics g) {
        this.paint(g);
    }

    public void init(String[] args) {
        long delay = 10L;

        try {
            try {
                if (args.length > 0) {
                    delay = Long.parseLong(args[0]);
                }
            } catch (NumberFormatException var5) {
                System.out.println("Warning: Invalid delay value!");
            } catch (Exception var6) {
            }

            this.buildRec();
            this.adressBus = new AddressBus(this);
            this.dataBus = new DataBus(this);
            this.controlBus = new ControlBus(this);
            this.cpu = new CPU(this.adressBus, this.dataBus, this.controlBus);
            this.cpu.addListener(this);
            this.cpu.setGUI(this);
            this.cpu.setDelay(delay);
            this.rom = new Rom(this.adressBus, this.dataBus, this.controlBus);
            this.rom.addListener(this);
            this.ram = new Ram(this.adressBus, this.dataBus, this.controlBus);
            this.ram.addListener(this);
            this.io = new Io(this.adressBus, this.dataBus, this.controlBus);
            this.io.addListener(this);
            this.fBufferedImage = this.createImage(614, 400);

            try {
                ClassLoader cl = this.getClass().getClassLoader();
                this.fBackImg = Toolkit.getDefaultToolkit().getImage(cl.getResource("ch/gitik/demopc/res/panel.gif"));
                if (this.fBackImg != null) {
                    this.fBufferedImage.getGraphics().drawImage(this.fBackImg, 0, 0, this);
                } else {
                    System.out.println("Warning: BackGround-Image could not be loaded!");
                }
            } catch (Exception var4) {
                System.out.println("Unable to get image!");
            }

            this.addMouseListener(new DemoComputer.EventHandler(this));
        } catch (IllegalAccessException var7) {
            System.out.println("Error while creating Components: " + var7.getMessage());
            this.fBackImg = null;
            return;
        } catch (Exception var8) {
            System.out.println("DemoComp: EXCEPTION" + var8.getMessage());
            var8.printStackTrace();
        }

        this.fInitialised = true;
    }

    public void start() {
        this.cpu.start();
    }

    public void paint(Graphics g) {
        if (this.fBufferedImage != null) {
            g.drawImage(this.fBufferedImage, 0, 0, (Color) null, this);
        }

    }

    public void stop() {
        this.cpu.stop();
    }

    public void destroy() {
        this.cpu.stop();
        this.fBackImg.flush();
        this.fBufferedImage.flush();
        this.cpu = null;
        this.adressBus = null;
        this.dataBus = null;
        this.controlBus = null;
        this.rom = null;
        this.ram = null;
        this.io = null;
        this.fInitialised = false;
    }

    public synchronized void reset() throws IOException {
        this.cpu.stop();
        this.cpu.reset();
        this.adressBus.reset();
        this.dataBus.reset();
        this.controlBus.reset();
        this.rom.reset();
        this.ram.reset();
        this.io.reset();
        this.cpu.start();
    }

    public void wordChanged(Word word, int address, String id) {
        if (id == "DATA_BUS") {
            this.drawWord(87, 55, word, new Color(50, 50, 255));
        } else if (id == "CONTROL_BUS") {
            this.drawWord(22, 281, word, new Color(0, 255, 0));
        } else if (id == "ADDRESS_BUS") {
            this.drawWord(68, 281, word, Color.blue);
        } else if (id.equals("ROM")) {
            if (address == -1) {
                this.drawWord(192, 281, word, new Color(0, 255, 0));
            } else {
                this.drawWord(232, 98 + address * 18, word, Color.red);
            }
        } else if (id.equals("RAM")) {
            if (address == -1) {
                this.drawWord(333, 281, word, new Color(0, 255, 0));
            } else {
                this.drawWord(372, 98 + address * 18, word, new Color(255, 0, 255));
            }
        } else if (id.equals("IO")) {
            if (address == -1) {
                this.drawWord(472, 281, word, new Color(0, 255, 0), true);
            } else {
                this.drawWord(568, 98 + address * 107, word, new Color(255, 255, 0), true);
            }
        } else if (id.equals("CPU")) {
            switch (address) {
                case 0:
                    this.drawWord(69, 220, word, new Color(0, 0, 255));
                    break;
                case 1:
                    this.drawWord(69, 99, word, new Color(255, 0, 0));
                    break;
                case 2:
                    this.drawWord(88, 140, word, new Color(255, 0, 0));
                    break;
                case 3:
                    this.drawWord(88, 180, word, new Color(0, 255, 0));
            }
        }

    }

    private void drawWord(int x, int y, Word word, Color c) {
        this.drawWord(x, y, word, c, false);
    }

    private void drawWord(int x, int y, Word word, Color c, boolean v) {
        Graphics g = this.getGraphics();
        Graphics bg = this.fBufferedImage.getGraphics();

        for (int i = 0; i < word.getWidth(); ++i) {
            if (word.getBit(i)) {
                g.setColor(c);
                bg.setColor(c);
            } else {
                g.setColor(c.darker().darker().darker());
                bg.setColor(c.darker().darker().darker());
            }

            if (!v) {
                g.fillOval(x + (word.getWidth() - i - 1) * 19, y, 14, 14);
                bg.fillOval(x + (word.getWidth() - i - 1) * 19, y, 14, 14);
            } else {
                g.fillOval(x, y + (word.getWidth() - i - 1) * 18, 14, 14);
                bg.fillOval(x, y + (word.getWidth() - i - 1) * 18, 14, 14);
            }
        }

    }

    public void addListener(Component cmp) {
    }

    public void broadcast(Word word, int address) {
    }

    private void buildRec() {
        this.fRec.put("reset", new Rectangle(29, 139, 43, 153));
        this.fRec.put("clock", new Rectangle(29, 179, 43, 193));
        this.fRec.put("rom", new Rectangle(231, 97, 302, 273));
        this.fRec.put("input", new Rectangle(568, 205, 582, 273));
        this.fRec.put("pc", new Rectangle(67, 218, 159, 234));
        this.fRec.put("aba", new Rectangle(67, 281, 0, 0));
        this.fRec.put("dat", new Rectangle(86, 54, 0, 0));
        this.fRec.put("rw", new Rectangle(21, 281, 0, 0));
        this.fRec.put("instr", new Rectangle(87, 179, 0, 0));
        this.fRec.put("romsel", new Rectangle(191, 279, 0, 0));
        this.fRec.put("ramsel", new Rectangle(331, 279, 0, 0));
        this.fRec.put("iosel", new Rectangle(471, 279, 0, 0));
        this.fRec.put("auto", new Rectangle(28, 218, 44, 234));
    }

    public static void main(String[] args) {
        Frame f = new Frame("4-bit Demo-Computer");
        DemoComputer dc = new DemoComputer();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setSize(620, 422);
        f.add("Center", dc);
        f.show();
        dc.init(args);
        dc.start();
    }

    public void crash(String msg) {
        System.out.println("CPU crashed: " + msg + " (check stdout for more information)");
        Graphics g = this.fBufferedImage.getGraphics();
        Rectangle r = new Rectangle(210, 180, 200, 30);
        g.setColor(Color.black);
        g.drawRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.white);
        g.fillRect(r.x + 1, r.y + 1, r.width - 1, r.height - 1);
        g.setFont(new Font("SansSerif", 1, 24));
        g.setColor(Color.black);
        g.drawString("CPU CRASHED!", r.x + 11, r.y + 25);
        g.setColor(Color.red);
        g.drawString("CPU CRASHED!", r.x + 10, r.y + 24);
        g = null;
        this.repaint();
        this.fCrashed = true;
        this.cpu.stop();
    }

    private class EventHandler implements MouseListener {
        private DemoComputer fParent = null;

        EventHandler(DemoComputer parent) {
            this.fParent = parent;
        }

        public void mousePressed(MouseEvent e) {
            e.consume();
            DemoComputer.this.handleMouseClick(e.getX(), e.getY());
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
}
