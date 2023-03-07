package Lecture1;

@FunctionalInterface
public interface FuncInterface {
     int add(int a,int b);

     default int subtract(int a,int b){
         return a-b;
     }
}
