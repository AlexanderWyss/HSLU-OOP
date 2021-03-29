package aufgaben.switchable;

public class CountingSwitchable implements Switchable {
    private boolean value = false;
    private int count = 0;


    @Override
    public void switchOn() {
        value = true;
        count++;
    }

    @Override
    public void switchOff() {
        value = false;
        count++;
    }

    @Override
    public boolean isSwitchedOn() {
        return value;
    }

    @Override
    public boolean isSwitchedOff() {
        return !value;
    }

    public int getCount() {
        return count;
    }
}
