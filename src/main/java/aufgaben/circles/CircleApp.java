package aufgaben.circles;

import javax.swing.*;

public class CircleApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        CircleCanvas circleCanvas = new CircleCanvas();
        frame.add(circleCanvas);
        new DaemonThread(circleCanvas).start();
        frame.setVisible(true);
    }
}
