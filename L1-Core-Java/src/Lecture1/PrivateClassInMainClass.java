package Lecture1;

public class PrivateClassInMainClass {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        PrivateClassInMainClass.InnerClass innerClass = new InnerClass();
        innerClass.func(10);
    }

    private static class InnerClass{
        private int a;

        private void func(int a){
            this.a = a;
            System.out.println("a = "+a);
        }
    }
}