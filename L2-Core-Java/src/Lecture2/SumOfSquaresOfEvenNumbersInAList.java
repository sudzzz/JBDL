package Lecture2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class SumOfSquaresOfEvenNumbersInAList implements AutoCloseable {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of array");
        int size = sc.nextInt();

        for(int i=0;i<size;i++){
            int a = sc.nextInt();
            list.add(a);
        }
        //Approach 1 --> using loop

        int sum = 0;
        for(Integer num:list){
            if(num%2==0){
                sum += num*num;
            }
        }
        System.out.println(sum);

        //Approach 2 --> using streams

        int result = list.stream().filter(new Predicate<Integer>() {
            //Filter is used to filter the even numbers from the stream
            @Override
            public boolean test(Integer integer) {
                return integer%2==0;
            }
        }).map(new Function<Integer, Integer>() {
            //map is used to convert the filtered numbers to their square
            @Override
            public Integer apply(Integer integer) {
                return integer*integer;
            }
        }).reduce(0, new BinaryOperator<Integer>() {
            //reduce is used to calculate the cumulative sum of the numbers passed.
            //Identity will be 0 for addition and subtraction whereas it will be 1 for multiplication and division.
            //It is a terminal operation. Reduce means we are reducing our stream to single element which is returned at last.
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                System.out.println("In reduce "+integer+" and "+integer2);
                return integer+integer2;
            }
        });

        System.out.println(result);

        //Approach 3 --> full lambda and streams

        int ans = list.stream().filter(x->x%2==0)
                .map(x->x*x)
                .reduce(0, (integer, integer2) -> integer+integer2);

        System.out.println(ans);

        //Approach 4 --> full lambda and streams with method reference
        int res = list.stream().filter(x->x%2==0)
                .map(x->x*x)
                .reduce(0, Integer::sum);
        System.out.println(res);

        //Approach 5 --> Using parallel stream
        //For parallel stream, the reduce functions will get different values as there are many worker threads
        //But it gives correct output because, in list data is stored in ordered fashion which neglects other threads result
        //and calculates correctly.

        int res1 = list.parallelStream().filter(x->{
            System.out.println("In parallel filter function "+x+" thread = "+Thread.currentThread().getName());
            return x%2==0;
        }).map(x->{
            System.out.println("In parallel map function "+x+" thread = "+Thread.currentThread().getName());
            return x*x;
        }).reduce(0,(x1,x2)->{
            System.out.println("In parallel reduce function "+x1+" and "+x2+" thread = "+Thread.currentThread().getName());
            return x1+x2;
        });
        System.out.println(res1);

        //Using normal stream with print statements

        int res2 = list.stream().filter(x->{
            System.out.println("In filter function "+x+" thread = "+Thread.currentThread().getName());
            return x%2==0;
        }).map(x->{
            System.out.println("In map function "+x+" thread = "+Thread.currentThread().getName());
            return x*x;
        }).reduce(0,(x1,x2)->{
            System.out.println("In reduce function "+x1+" and "+x2+" thread = "+Thread.currentThread().getName());
            return x1+x2;
        });
        System.out.println(res2);

        //Command to check number of cores present in our machine
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void close() throws Exception {
        System.out.println("I am in the close function of SumOfSquaresOfEvenNumbersInAList class");
    }
}