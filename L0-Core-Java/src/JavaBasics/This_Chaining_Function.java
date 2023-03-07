package JavaBasics;

class Point{
    int x;
    int y;
    Point(int x,int y){
        this.x = x;
        this.y = y;
    }
    Point setX(int x){
        this.x = x;
        return this; // returns the reference of current object so that setY() function can be called
    }
    Point setY(int y){
        this.y = y;
        return this;
    }
    void print(){
        System.out.println(x+" "+y);
    }
}
public class This_Chaining_Function {
    public static void main(String []args){
        Point p1 = new Point(10,20);
        Point p2 = new Point(30,40);
        p1.print();
        p2.print();
        p1.setX(50).setY(60);
        p1.print();
    }

}
