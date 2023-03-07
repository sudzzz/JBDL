package JavaBasics;

class Test1 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<10;i++)
            System.out.println("Inside Test Thread");
    }
}

public class MultithreadingImplementingRunnable {
    public static void main(String []args) throws InterruptedException {
        Thread t = new Thread(new Test1());
        t.start();
        for(int i=0;i<10;i++){
            System.out.println("Inside Main Thread");
            Thread.sleep(1);
        }

    }

}
