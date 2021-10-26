package aufgaben.circles;

public class DaemonThread extends Thread {
    public DaemonThread(Runnable runnable) {
        super(runnable);
        setDaemon(true);
    }
}
