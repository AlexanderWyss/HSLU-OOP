package aufgaben.switchable;

class Motor implements Switchable, Named {

    private int rpm = 0;
    private String name = getClass().getName();

    @Override
    public void switchOn() {
        rpm = (int) ((Math.random() * 1000) + 1);
    }

    @Override
    public void switchOff() {
        rpm = 0;
    }

    @Override
    public boolean isSwitchedOn() {
        return rpm > 0;
    }

    @Override
    public boolean isSwitchedOff() {
        return rpm == 0;
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
