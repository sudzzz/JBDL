package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Addition of a and b " + MathOperations.add(a, b));

        System.out.println("Division of a and b " + MathOperations.divide(a, b));
        System.out.println("Power of a and b" + MathOperations.power(a, b));


        System.out.println("concatenate a " + MathOperations.concatenate(a));
    }
}