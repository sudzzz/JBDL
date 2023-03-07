package JavaStreamsAndLambda;

// Java Program Demonstrate
// stream in Java
import java.util.*;

public class WithStream {

    public static void main(String[] args)
    {

        // Creates a list using asList()
        List<Integer> l = Arrays.asList(5, 10, 20, 30, 8, 7);

        // Taking the list as a source
        // Applying stream
        // filtering out the even numbers
        // filtering out the numbers > 10
        // Printing the list
        l.stream()
            .filter(x -> x % 2 == 0)
            .filter(x-> x>10)
            .forEach(System.out::println);
    }
}