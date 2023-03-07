package Lecture2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestClass {

    public static void main(String[] args) throws IOException {

        // 1. Unchecked exceptions - Runtime
        // 2. Checked exceptions - Compile - you need to handle it
//
//        try {
//            System.out.println(func());
//        }catch (Exception e){
//            e.printStackTrace();
//            throw e;
//        }

        funcTryWithResource();

    }

    public static void func() {

//        try {
//            FileReader fileReader = new FileReader("/Users/pa/dump1.txt");
//            FileWriter fileWriter = new FileWriter("/Users/pa/dump1.txt");
//
////            int a = 1 / 0;
//            return "Hello from try!";
//        }catch (IOException e){
//            System.out.println("In catch block");
//            throw new MyException();
//        }finally {
//            System.out.println("In finally block:");
//        }

        Scanner scanner = null;
        try{
            scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            System.out.println(number);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            scanner.close();
        }
    }

    public static void funcTryWithResource(){

//        try(Scanner scanner = new Scanner(System.in)){
//            int number = scanner.nextInt();
//            System.out.println(number);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        try(SumOfSquaresOfEvenNumbersInAList obj = new SumOfSquaresOfEvenNumbersInAList()){

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
