package Lecture1;

import java.util.Map;

public class MyHashMap implements MyMap{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int subtract(int a, int b) {
        return a-b;
    }

    public int divide(int a,int b){
        return a/b;
    }

    public double power(int a,int b){
        return Math.pow(a,b);
    }
}
