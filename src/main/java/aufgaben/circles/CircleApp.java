package aufgaben.circles;

import javax.swing.*;

public class CircleApp {
    public static void main(String[] args) {
        CircleCanvas circleCanvas = new CircleCanvas();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(circleCanvas);
        new DaemonThread(circleCanvas).start();
        frame.setVisible(true);
    }
}
