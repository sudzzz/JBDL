package Lecture3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FactorialMT extends Thread{

    int number;
    BigInteger result;

    public static void main(String[] args) {

        // increase the number of elements --> ~20
        // Task 1 --> create separate thread for every element - A1
        // Task 2 --> create only threads == processors you have for your JVM and calculate accordingly - A2
        // Task 3 --> create 2-3 threads only - A3

        Integer[] numbers = {10000, 20000, 50000, 30000, 43000, 50000, 65000, 15000, 42000};
        Integer[] shortNumbers = {1, 2, 3, 4, 5, 6, 7, 8};

        List<FactorialMT> threads;

        long start = System.currentTimeMillis();
        threads = Arrays.stream(numbers)
                .map(x -> {
                    FactorialMT thread = new FactorialMT(x);
                    thread.start();
                    return thread;
                }).collect(Collectors.toList());

//        threads.stream().forEach(x -> System.out.println());

        // Not sequential - will have to prove this wrong
        threads.stream().forEach(x -> {
            try {
                x.join();
                System.out.println("Input = " + x.number + ", output = " + x.result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();

        System.out.println("Total time = " + (end-start));
    }

    public FactorialMT(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.result = calculateFactorial();
//        System.out.println("In thread: " + currentThread() +
//                ", for number " + this.number + ", output = " + result);
    }

    public BigInteger calculateFactorial(){
        BigInteger result = BigInteger.ONE;

        for(int i = 2; i <= this.number; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}