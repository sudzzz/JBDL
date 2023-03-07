package Lecture1;

public interface MyMap {

    int add(int a,int b);

    int subtract(int a,int b);

    default int multiply(int a,int b){
        return a*b;
    }
}
