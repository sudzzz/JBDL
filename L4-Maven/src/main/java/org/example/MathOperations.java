package org.example;

public class MathOperations {

    public static int add(int a, int b){
        return a + b;
    }

    public static int subtract(int a, int b){
        return a - b;
    }

    public static int multiply(int a, int b){
        return a * b;
    }

    public static int divide(int a, int b){
        return a / b;
    }

    public static int power(int a, int b){
        return (int)Math.pow(a, b);
    }

    public static String concatenate(int a){
        return "" + a + a;
    }

}