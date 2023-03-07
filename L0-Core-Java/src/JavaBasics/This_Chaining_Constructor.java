package JavaBasics;

class Point1{
    int x;
    int y;
    Point1(int x,int y){
        this.x = x;
        this.y = y;
    }

    Point1(){
        this(10,10);
    }
    void print(){
        System.out.println(x+" "+y);
    }
}
public class This_Chaining_Constructor {
    public static void main(String []args){
        Point1 p1 = new Point1(10,20);
        Point1 p2 = new Point1();
        p1.print();
        p2.print();
    }
}
