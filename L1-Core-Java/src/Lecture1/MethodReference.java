package Lecture1;

import java.util.Arrays;
import java.util.List;

public class MethodReference {

    public static void main(String[] args){

        List<String> cities = Arrays.asList("Delhi","Mumbai");

        /**
         * Convert to Uppercase in normal stream
         */

        cities.stream().map(x->x.toUpperCase())
                .forEach(x->System.out.println(x));

        /**
         * Other Way using method reference
         */
        cities.stream().map(x->x.toUpperCase())
                .forEach(System.out::println);

        /**
         * Other Way using normal method and method reference
         */
        cities.stream().map(x->replace(x))
                .forEach(System.out::println);

        /**
         * Other Way using only method reference
         */
        cities.stream().map(MethodReference::replace)
                .forEach(System.out::println);


    }


    public static String replace(String a){
        return a.toUpperCase();
    }
}
