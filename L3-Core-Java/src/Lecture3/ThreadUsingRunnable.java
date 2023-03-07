package Lecture3;

public class ThreadUsingRunnable implements Runnable {

    public static void main(String[] args) {

        System.out.println("Inside main function, thread  = " + Thread.currentThread());
        ThreadUsingRunnable threadUsingRunnable = new ThreadUsingRunnable();

        Thread thread = new Thread(threadUsingRunnable);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Inside run function of thread - " + Thread.currentThread());
    }
}