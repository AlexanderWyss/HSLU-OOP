package aufgaben.sw04;

class Motor implements Switchable {

    private int rpm = 0;

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
}
