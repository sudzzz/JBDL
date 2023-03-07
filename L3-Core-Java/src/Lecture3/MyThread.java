package Lecture3;

public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("Inside run function, thread: " + Thread.currentThread()); // 1ms

        int a = 1/0;

        int sum = 0;
        for(int i = 0; i < 10; i++){
            sum += i;
        } // 3ms

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sum = " + sum);
    }
}
