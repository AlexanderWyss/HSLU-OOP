package aufgaben.circles;

import aufgaben.shape.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static aufgaben.circles.ColoredFallingCircle.ColoredFallingCircleBuilder;

public class CircleCanvas extends JPanel implements MouseListener, MouseMotionListener, Runnable {
    private final List<ColoredFallingCircle> circles = Collections.synchronizedList(new ArrayList<>());
    private final Color[] colors = new Color[]{Color.RED, Color.BLACK, Color.BLUE, Color.yellow, Color.GREEN, Color.MAGENTA};

    public CircleCanvas() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            repaint();
            synchronized (circles) {
                Iterator<ColoredFallingCircle> iterator = circles.iterator();
                while (iterator.hasNext()) {
                    ColoredFallingCircle circle = iterator.next();
                    if (circle.isDead()) {
                        iterator.remove();
                    } else if (circle.getPosition().getY() + circle.getDiameter() >= getHeight()) {
                        circle.kill();
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (circles) {
            for (ColoredFallingCircle circle : circles) {
                g.setColor(circle.getColor());
                Point position = circle.getPosition();
                g.fillOval(position.getX(), position.getY(), circle.getDiameter(), circle.getDiameter());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        createCircle(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        createCircle(e);
    }

    private void createCircle(MouseEvent e) {
        ColoredFallingCircle fallingCircleTask = new ColoredFallingCircleBuilder() //
                .setPosition(new Point(e.getX(), e.getY())) //
                .setDiameter(randomInt(20, 51)) //
                .setColor(randomColor()) //
                .setFallingSpeed(randomInt(1, 10)) //
                .build();
        circles.add(fallingCircleTask);
        new DaemonThread(fallingCircleTask).start();
    }

    private Color randomColor() {
        return colors[randomInt(0, colors.length)];
    }

    private int randomInt(int lowerInclusive, int upperExclusive) {
        return ThreadLocalRandom.current().nextInt(lowerInclusive, upperExclusive);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
