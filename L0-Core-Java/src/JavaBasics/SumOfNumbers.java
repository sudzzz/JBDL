package JavaBasics;

import java.util.Scanner;

import static java.lang.System.exit;

public class SumOfNumbers {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number");
        int num = sc.nextInt();
        if(num < 0){
            System.out.println("Invalid number");
            exit(0);
        }
        else {
            int sum = (num*(num+1))/2;
            System.out.println("Sum of n natural numbers is "+sum);
        }
    }
}
