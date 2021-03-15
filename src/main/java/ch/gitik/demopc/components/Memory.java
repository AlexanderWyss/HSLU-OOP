//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.gitik.demopc.components;

import java.io.IOException;

class Memory extends ComponentListener {
    private Word[] fMem = null;
    private int fSize = 0;
    private int fOffset = 0;
    private int fAddress = 0;
    private boolean fSelected = false;
    private Bus fDataBus = null;
    private Word fDataWord = null;
    private boolean fReadOnly = false;
    private int fWordWidth = 0;

    public Memory(int size, int width, int offset, String id) {
        super(id);
        this.fSize = size;
        this.fOffset = offset;
        this.fWordWidth = width;
        this.fMem = new Word[this.fSize];

        for(int i = 0; i < size; ++i) {
            this.fMem[i] = new Word(width);
        }

    }

    public void setDataBus(Bus databus) {
        this.fDataBus = databus;
    }

    public void setReadOnly(boolean value) {
        this.fReadOnly = value;
    }

    public void reset() throws IOException {
        try {
            this.broadcast(new Word(1, 0), -1);
        } catch (IllegalAccessException var5) {
            System.out.println(this.fID + ": reset() partially failed: " + var5.getMessage());
        }

        this.fSelected = false;
        this.fDataWord = null;
        this.fAddress = 0;
        if (!this.fReadOnly) {
            Word w = new Word(this.fWordWidth);

            for(int i = 0; i < this.fMem.length; ++i) {
                try {
                    this.setWord(i + this.fOffset, w);
                } catch (Exception var4) {
                }
            }
        }

    }

    public void setWord(int address, Word word) throws IllegalAccessException {
        address = this.relativeAddress(address);
        this.checkAddress(address);
        this.fMem[address] = word.copy();
        this.broadcast(word, address);
    }

    public Word getWord(int address) throws IllegalAccessException {
        address = this.relativeAddress(address);
        this.checkAddress(address);
        return this.fMem[address];
    }

    public void setBit(int address, int offset, boolean value) throws IllegalAccessException {
        this.checkAddress(address);
        this.fMem[address].setBit(offset, value);
        this.broadcast(this.fMem[address], address);
    }

    public boolean getBit(int address, int offset) throws IllegalAccessException {
        this.checkAddress(address);
        return this.fMem[address].getBit(offset);
    }

    private void checkAddress(int address) throws IllegalAccessException {
        if (address < 0 || address > this.fSize - 1) {
            throw new IllegalAccessException("Illegal Address!");
        }
    }

    private int relativeAddress(int address) {
        return address - this.fOffset;
    }

    public void wordChanged(Word word, int address, String id) throws IllegalAccessException {
        if (id == "ADDRESS_BUS") {
            int addr = word.toInteger();
            if (addr >= this.fOffset && addr < this.fOffset + this.fSize) {
                this.fAddress = addr;
                this.fSelected = true;
                this.broadcast(new Word(1, 1), -1);
            } else {
                this.broadcast(new Word(1, 0), -1);
                this.fSelected = false;
            }
        } else if (id == "CONTROL_BUS") {
            if (this.fSelected) {
                if (word.toInteger() == 2) {
                    this.fDataBus.broadcast(this.getWord(this.fAddress), this.fAddress);
                } else if (word.toInteger() == 1) {
                    if (this.isReadOnly()) {
                        throw new IllegalAccessException("Memory object '" + this.fID + "' is write-protected!");
                    }

                    this.setWord(this.fAddress, this.fDataWord);
                }
            }
        } else if (id == "DATA_BUS") {
            this.fDataWord = word;
        }

    }

    public boolean isReadOnly() {
        return this.fReadOnly;
    }

    public int getAddress() {
        return this.fAddress;
    }
}
