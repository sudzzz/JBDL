package JavaBasics;

public class Main {
    public static void main(String[] args) {
        Main.InnerClass innerClass = new InnerClass();
        innerClass.func(10);
    }

    private static class InnerClass{
        public void func(int a){
            System.out.println("a = "+ a);
        }
    }
}