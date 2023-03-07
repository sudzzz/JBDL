package JavaBasics;

class Test extends Thread{
    public void run(){
        for(int i=0;i<10;i++)
            System.out.println("Inside Test Thread");
    }
}
public class MultithreadingUsingThreadClass {
    public static void main(String []args) throws InterruptedException {
        Test t = new Test();
        t.start();
        for(int i=0;i<10;i++){
            System.out.println("Inside Main Thread");
            Thread.sleep(1);
        }

    }

}
