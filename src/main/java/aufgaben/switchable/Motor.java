package aufgaben.switchable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

class Motor implements Switchable, Named {
    private int rpm = 0;
    private String name = getClass().getName();
    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    @Override
    public void switchOn() {
        setRpm(1000);
    }

    @Override
    public void switchOff() {
        setRpm(0);
    }

    @Override
    public boolean isSwitchedOn() {
        return !isSwitchedOff();
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

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public void setRpm(int rpm) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "rpm", this.rpm, rpm);
        this.rpm = rpm;
        firePropertyChangeEvent(event);
    }

    private void firePropertyChangeEvent(PropertyChangeEvent event) {
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(event);
        }
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.remove(listener);
    }
}
