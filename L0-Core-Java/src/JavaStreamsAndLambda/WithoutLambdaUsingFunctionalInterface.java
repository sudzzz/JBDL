package JavaStreamsAndLambda;


// Java code to illustrate functional
// interface without using Lambda Expression
import java.util.*;
import java.util.function.Predicate;

public class WithoutLambdaUsingFunctionalInterface
{
    // Take a Collection and a Predicate
    //  helps in unit-testing
    // Collection can accept LinkedList,
    // ArrayList, Queue etc
    static void printCond(Collection<Integer> c, Predicate<Integer> p)
    {
        // Traverse the Collection
        // Print only those elements which
        // pass the test
        for (Integer x: c)
            if(p.test(x))
                System.out.print(x + " ");
    }
    public static void main(String args[])
    {
        // This class implements Predicate Interface
        class MyPredicateEven implements Predicate<Integer>
        {
            // The only abstract method
            // to return when the integer is even
            public boolean test(Integer x)
            {
                return(x%2 == 0);
            }
        }

        // ArrayList to check the function
        List<Integer> al = new ArrayList<>(Arrays.asList(10, 5, 20, 7, 30));

        // Passing the collection ArrayList
        // and an object of MyPredicateEven class
        // Since the class MyPredicateEven implements
        // Predicate interface it can be passed as an object
        // to be assigned to a variable p of Predicate type
        printCond(al, new MyPredicateEven());
    }
}

