//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

import ch.gitik.demopc.DemoComputer;

import java.io.IOException;

public class CPU extends ch.gitik.demopc.components.ComponentListener implements Runnable {
    public static final String REG_0 = "Reg0";
    public static final String REG_1 = "Reg1";
    public static final String REG_I = "InstReg";
    private static final String ID = "CPU";
    private int fFetchCount = 0;
    private DemoComputer fGui = null;
    private Word fArgument = null;
    private boolean fAuto = false;
    private long fDelay = 10L;
    private Register Reg0 = new Register(5, "Reg0");
    private Register Reg1 = new Register(4, "Reg1");
    private Register InstReg = new Register(4, "InstReg");
    private AddressBus fAddressBus = null;
    private DataBus fDataBus = null;
    private ch.gitik.demopc.components.ControlBus fControlBus = null;
    private PCounter fPrgCounter = new PCounter(9);
    private Word fBusWord = null;
    private Thread fCpuThread = null;

    public CPU(AddressBus abus, DataBus dbus, ch.gitik.demopc.components.ControlBus cbus) throws IllegalAccessException {
        super("CPU");
        this.fAddressBus = abus;
        this.fDataBus = dbus;
        this.fControlBus = cbus;
        this.fDataBus.addListener(this);
        this.fAddressBus.addListener(this);
        this.fControlBus.addListener(this);
        this.fPrgCounter.addListener(this);
        this.Reg0.addListener(this);
        this.Reg1.addListener(this);
        this.InstReg.addListener(this);
    }

    public synchronized void start() {
        if (this.fCpuThread == null) {
            this.fCpuThread = new Thread(this);
            this.fCpuThread.setDaemon(true);
            this.fCpuThread.start();
        }

    }

    public synchronized void stop() {
        if (this.fCpuThread != null) {
            Thread tmpT = this.fCpuThread;
            this.fCpuThread = null;
            this.notify();
            if (this.fAuto) {
                tmpT.interrupt();
            }
        }

    }

    public void wordChanged(Word word, int address, String id) throws IllegalAccessException {
        if (id.equals("DATA_BUS")) {
            this.fBusWord = word;
        } else if (id.equals("PC")) {
            this.broadcast(word, 0);
        } else if (id.equals("Reg0")) {
            this.broadcast(word, 1);
        } else if (id.equals("Reg1")) {
            this.broadcast(word, 2);
        } else if (id.equals("InstReg")) {
            this.broadcast(word, 3);
        }

    }

    public void setDataBus(DataBus bus) {
        this.fDataBus = bus;
    }

    public synchronized void clock() {
        this.notify();
    }

    private synchronized void suspend() throws InterruptedException {
        if (!this.fAuto) {
            this.wait();
        } else {
            Thread.currentThread();
            Thread.sleep(this.fDelay);
        }

        if (this.fCpuThread == null) {
            throw new InterruptedException(this.fID + ":Shutdown...");
        }
    }

    public void reset() throws IOException {
        this.fPrgCounter.reset();
        this.Reg0.reset();
        this.Reg1.reset();
        this.InstReg.reset();
        this.fFetchCount = 0;
    }

    public void setAuto(boolean value) {
        this.fAuto = value;
        if (this.fAuto) {
            this.clock();
        }

    }

    public void toggleAuto() {
        Object syncObj = new Object();
        synchronized(syncObj) {
            this.fAuto = !this.fAuto;
        }

        if (this.fAuto) {
            this.clock();
        }

    }

    public boolean isAuto() {
        return this.fAuto;
    }

    public void setGUI(DemoComputer gui) {
        this.fGui = gui;
    }

    public void setDelay(long delay) {
        this.fDelay = delay;
    }

    public void run() {
        while(true) {
            try {
                if (Thread.currentThread() == this.fCpuThread) {
                    this.fetch();
                    if (this.fPrgCounter.getCounter() == 0 && this.fFetchCount == 1) {
                        this.fFetchCount = 0;
                        continue;
                    }

                    this.execute();
                    continue;
                }
            } catch (InterruptedException var2) {
                System.out.println("CPU-Exception: " + var2.getMessage());
            } catch (Exception var3) {
                System.out.println("CPU Crashed :" + var3.getMessage());
                if (this.fGui != null) {
                    this.fGui.crash(var3.getMessage());
                }
            }

            return;
        }
    }

    private void fetch() throws IOException, InterruptedException {
        try {
            this.suspend();
            this.fAddressBus.broadcast(new Word(5, this.fPrgCounter.getCounter()), 0);
            this.suspend();
            this.fControlBus.broadcast(new Word(2, 2), 0);
            if (this.fFetchCount == 0) {
                this.suspend();
                this.InstReg.setInteger(this.fBusWord.toInteger());
                ++this.fFetchCount;
            } else {
                this.fArgument = this.fBusWord;
            }

            this.suspend();
            this.fPrgCounter.increment();
        } catch (IllegalAccessException var2) {
            System.out.println(this.fID + ": fetch()-Exception: " + var2.getMessage());
            var2.printStackTrace();
        }

    }

    private void execute() throws IllegalAccessException, InterruptedException {
        try {
            switch(this.InstReg.toInteger()) {
                case 0:
                    this.stoR0();
                    break;
                case 1:
                    this.stoR1();
                    break;
                case 2:
                    this.ldR0();
                    break;
                case 3:
                    this.ldR1();
                    break;
                case 4:
                    this.mviR0();
                    break;
                case 5:
                    this.mviR1();
                    break;
                case 6:
                    this.jmp();
                    break;
                case 7:
                    this.jc();
                    break;
                case 8:
                    this.in();
                    break;
                case 9:
                    this.movR1R0();
                    break;
                case 10:
                    this.movR0R1();
                    break;
                case 11:
                    this.out();
                    break;
                case 12:
                    this.jnc();
                    break;
                case 13:
                    this.addR1();
                    break;
                case 14:
                    this.asl();
                    break;
                case 15:
                    this.rar();
            }

            this.fFetchCount = 0;
        } catch (IOException var2) {
            System.out.println(this.fID + ": execute-Exception (" + var2.getMessage() + ")");
            var2.printStackTrace();
        }

    }

    private void stoR0() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        if (this.fArgument.toInteger() > 4) {
            this.fArgument.setInteger(15);
        }

        this.fAddressBus.broadcast(new Word(5, this.fArgument.toInteger() + 16), 0);
        this.suspend();
        this.fDataBus.broadcast(new Word(4, this.Reg0.toInteger()), 0);
        this.suspend();
        this.fControlBus.broadcast(new Word(2, 1), 0);
    }

    private void stoR1() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        if (this.fArgument.toInteger() > 4) {
            this.fArgument.setInteger(15);
        }

        this.fAddressBus.broadcast(new Word(5, this.fArgument.toInteger() + 16), 0);
        this.suspend();
        this.fDataBus.broadcast(new Word(4, this.Reg1.toInteger()), 0);
        this.suspend();
        this.fControlBus.broadcast(new Word(2, 1), 0);
    }

    private void ldR0() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        this.fAddressBus.broadcast(new Word(5, this.fArgument.toInteger() + 16), 0);
        this.suspend();
        this.fControlBus.broadcast(new Word(2, 2), 0);
        this.suspend();
        if (this.fArgument.toInteger() < 4) {
            this.Reg0.setInteger(this.fBusWord.toInteger());
        }

    }

    private void ldR1() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        this.fAddressBus.broadcast(new Word(5, this.fArgument.toInteger() + 16), 0);
        this.suspend();
        this.fControlBus.broadcast(new Word(2, 2), 0);
        this.suspend();
        if (this.fArgument.toInteger() < 4) {
            this.Reg1.setInteger(this.fBusWord.toInteger());
        }

    }

    private void mviR0() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        this.Reg0.setInteger(this.fArgument.toInteger());
    }

    private void mviR1() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        this.Reg1.setInteger(this.fArgument.toInteger());
    }

    private void jmp() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        this.fPrgCounter.setCounter(this.fArgument.toInteger());
    }

    private void jc() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        if (this.Reg0.getBit(this.Reg0.getWidth() - 1)) {
            this.fPrgCounter.setCounter(this.fArgument.toInteger());
        }

    }

    private void jnc() throws IllegalAccessException, InterruptedException, IOException {
        this.fetch();
        this.suspend();
        if (!this.Reg0.getBit(this.Reg0.getWidth() - 1)) {
            this.fPrgCounter.setCounter(this.fArgument.toInteger());
        }

    }

    private void in() throws IllegalAccessException, InterruptedException {
        this.suspend();
        this.fAddressBus.broadcast(new Word(5, 26), 0);
        this.suspend();
        this.fControlBus.broadcast(new Word(2, 2), 0);
        this.suspend();
        this.Reg0.setInteger(this.fBusWord.toInteger());
    }

    private void movR1R0() throws IllegalAccessException, InterruptedException {
        this.suspend();
        this.Reg1.setInteger(this.Reg0.toInteger());
    }

    private void movR0R1() throws IllegalAccessException, InterruptedException {
        this.suspend();
        this.Reg0.setInteger(this.Reg1.toInteger());
    }

    private void out() throws IllegalAccessException, InterruptedException {
        this.suspend();
        this.fDataBus.broadcast(new Word(4, this.Reg0.toInteger()), 0);
        this.suspend();
        this.fAddressBus.broadcast(new Word(5, 25), 0);
        this.suspend();
        this.fControlBus.broadcast(new Word(2, 1), 0);
    }

    private void addR1() throws IllegalAccessException, InterruptedException {
        this.suspend();
        int tmpReg = this.Reg0.toInteger() + this.Reg1.toInteger();
        tmpReg %= 16;
        this.Reg0.setInteger(tmpReg);
    }

    private void asl() throws IllegalAccessException, InterruptedException {
        this.suspend();
        this.Reg0.setInteger(this.Reg0.toInteger() * 2);
    }

    private void rar() throws IllegalAccessException, InterruptedException {
        this.suspend();
        int tmpReg = this.Reg0.toInteger();
        if (this.Reg0.getBit(0)) {
            this.Reg0.setInteger(tmpReg / 2 + 16);
        } else {
            this.Reg0.setInteger(tmpReg / 2);
        }

    }
}
