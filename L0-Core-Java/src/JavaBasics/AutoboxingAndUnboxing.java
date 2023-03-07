package JavaBasics;

public class AutoboxingAndUnboxing {
    public static void main(String[] args){
        int x1 =10;
        Integer x2 = x1;     //Autoboxing
        int x3 = x2;         //Unboxing
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
        AutoboxingAndUnboxing test = new AutoboxingAndUnboxing();
        test.check();
    }

    public void check(){
        Integer x1 = 4;
        Integer x2 = 4;
        if(x1==x2){
            System.out.println("Same");
        }
        else {
            System.out.println("Not Same");
        }
    }
}


