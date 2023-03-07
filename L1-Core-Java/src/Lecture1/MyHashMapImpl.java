package Lecture1;

public class MyHashMapImpl {
    public static void main(String[] args){
        /**
         * If we create reference of Interface we will not be able to use
         * extra methods which are created in the class MyHashMap like divide() in this case.
         * It is basically reference of Interface and Instance of Class.
         */

        MyMap map = new MyHashMap();
        map.add(1,2);
        map.subtract(3,2);
        map.multiply(2 ,5);

        /**
         * Default functions are automatically implemented in inherited class
         * So we are able to use all the functions in MyMap and MyHashMap
         * It is basically reference and Instance of Class.
         */
        MyHashMap map1 = new MyHashMap();
        map1.add(1,1);
        map1.subtract(1,0);
        map1.multiply(1,1);
        map1.divide(1,1);


        /**
         * Functional Interface can be used for using Lambda Expression
         */
        FuncInterface funcInterface1 = (a,b)->a+b+1;

        FuncInterface funcInterface3 = (a,b)->{
            System.out.println("In Lambda Function");
            return a+b+1;
        };
        FuncInterface funcInterface2 = new FuncInterface() {
            @Override
            public int add(int a, int b) {
                return a+b+1;
            }
        };

        System.out.println(funcInterface1.add(3,4));
        System.out.println(funcInterface2.add(3,4));


    }
}
