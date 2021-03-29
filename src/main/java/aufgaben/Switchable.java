package aufgaben;

/**
 * Implements a two way switch.
 */
interface Switchable {
    /**
     * Turns the switch on.
     */
    void switchOn();

    /**
     * Turns the switch off.
     */
    void switchOff();

    /**
     * Checks if the switch is on.
     *
     * @return {@code true} when switch is on, otherwise {@code false}.
     */
    boolean isSwitchedOn();

    /**
     * Checks if the switch is off.
     *
     * @return {@code true} when switch is off, otherwise {@code false}.
     */
    boolean isSwitchedOff();
}
