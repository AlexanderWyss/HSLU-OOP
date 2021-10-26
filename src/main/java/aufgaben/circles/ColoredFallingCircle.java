package aufgaben.circles;

import aufgaben.shape.Circle;
import aufgaben.shape.Point;

import java.awt.*;

public class ColoredFallingCircle extends Circle implements Runnable {
    private Color color;
    private final int fallingSpeed;
    private boolean isDying = false;

    public ColoredFallingCircle(Point position, int diameter, Color color, int fallingSpeed) {
        super(position, diameter);
        this.color = color;
        this.fallingSpeed = fallingSpeed;
    }

    @Override
    public void run() {
        while (getDiameter() > 0 && !Thread.currentThread().isInterrupted()) {
            if (isDying) {
                setDiameter(getDiameter() - 2);
                getPosition().moveRelative(1, 2);
                color = color.brighter();
                color = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(color.getAlpha() - 20, 0));
            } else {
                getPosition().moveRelative(0, fallingSpeed);
            }
            try {
                //noinspection BusyWait
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Color getColor() {
        return color;
    }

    public void kill() {
        isDying = true;
    }

    public boolean isDead() {
        return getDiameter() <= 0;
    }

    public static class ColoredFallingCircleBuilder {
        private int diameter;
        private Color color;
        private int fallingSpeed;
        private Point position;

        public ColoredFallingCircleBuilder setPosition(Point position) {
            this.position = position;
            return this;
        }

        public ColoredFallingCircleBuilder setDiameter(int diameter) {
            this.diameter = diameter;
            return this;
        }

        public ColoredFallingCircleBuilder setColor(Color color) {
            this.color = color;
            return this;
        }

        public ColoredFallingCircleBuilder setFallingSpeed(int fallingSpeed) {
            this.fallingSpeed = fallingSpeed;
            return this;
        }

        public ColoredFallingCircle build() {
            return new ColoredFallingCircle(position, diameter, color, fallingSpeed);
        }
    }
}
