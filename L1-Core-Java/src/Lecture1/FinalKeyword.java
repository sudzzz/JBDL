package Lecture1;

public class FinalKeyword {

    // 1. Variables - These can be defined while declaring or in the constructor
    // 2. Functions - Final functions can not be overridden in child classes
    // 3. Classes -

    final int a = 5;
    static int b = 10;
    static final int c;

    static {
        b = 50;
        System.out.println("In first static block " + b);
        c = 10;
    }

    static {
        b = 70;
        System.out.println("In another static block " + b);
    }

//    public FinalKeyword() {
//        this.a = 10;
//    }

    public static void func(){
        System.out.println(b);
    }

    public static void main(String[] args) {
        System.out.println("Starting main....");
        FinalKeyword o = new FinalKeyword();
        func();
        b = 60;
        func();

//        System.out.println(b);
    }
}
