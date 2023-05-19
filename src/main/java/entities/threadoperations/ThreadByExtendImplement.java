package entities.threadoperations;

public class ThreadByExtendImplement extends Thread {
    @Override
    public void run() {
        System.out.println("Thread created by ThreadByExtendImplement");
    }

    public Thread createThreadByRunnable() {
        Runnable runnable = () -> System.out.println("Thread created by runnable");
        return new Thread(runnable);
    }

    public Thread createThreadByThreadClass() {
        return new ThreadByExtendImplement();
    }
}
