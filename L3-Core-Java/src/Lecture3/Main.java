package Lecture3;

public class Main {
    int a;


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Currently executing in thread ..." + Thread.currentThread()); // 1ms


        MyThread thread = new MyThread();
//
//        MyThread thread2 = new MyThread();

//        MyThread[] threads = {thread, thread2};

//        thread.setDaemon(true);
//        thread2.setDaemon(true);

        try {
            thread.start();
        }catch (Exception e){
            System.out.println("In catch block of main thread");
            e.printStackTrace();
        }
//        thread2.start();

//        thread2.start();

        System.out.println("Time before waiting " + System.currentTimeMillis());


//        for (Thread ithThread : threads){
//            waitForMe(ithThread);
//        }

        System.out.println("In main thread, after creating new thread..." + System.currentTimeMillis());

        System.out.println("Main function ends....");
    }

    public static void waitForMe(Thread i) throws InterruptedException {
        System.out.println("In wait for me, thread i " + i);
        synchronized (i) {
            i.wait(20);
        }
    }

    // Daemon threads - Background threads which will not impact the application running time
    // Non daemon threads - Which will make the JVM wait until they are done processing (Foreground threads)
    //

    // Wait for me time  - Min (thread completion time = 100 ms, timeout = 50ms) - T1 = 50 ms
    // Wait for me time  - Min (thread completion time = 50ms, timeout = 50ms) - T2 = 50 ms
}