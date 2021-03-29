package aufgaben.switchable;

public class CountingSwitchable implements Switchable, Named {
    private boolean value = false;
    private int count = 0;
    private String name = getClass().getName();


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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
