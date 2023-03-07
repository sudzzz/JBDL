package Lecture2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FindFirstEvenNumberInAList {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of array");
        int size = sc.nextInt();

        for(int i=0;i<size;i++){
            int a = sc.nextInt();
            list.add(a);
        }

        //FindFirst
        Optional<Integer> firstEvenNumber = list.stream()
                .filter(x -> {
                    System.out.println("Inside findFirst filter for element:" + x);
                    return x % 2 == 0;
                }).findFirst();

        System.out.println(firstEvenNumber);

        Optional<Integer> firstEvenNumberP = list.parallelStream()
                .filter(x -> {
                    System.out.println("Inside findFirst filter for element parallel:" + x);
                    return x % 2 == 0;
                }).findFirst();
        System.out.println(firstEvenNumberP);

        //FindAny
        Optional<Integer> firstEvenNumber1 = list.stream()
                .filter(x -> {
                    System.out.println("Inside findAny filter for element:" + x);
                    return x % 2 == 0;
                }).findAny();

        System.out.println(firstEvenNumber1);

        Optional<Integer> firstEvenNumberP1 = list.parallelStream()
                .filter(x -> {
                    System.out.println("Inside findAny for element parallel:" + x);
                    return x % 2 == 0;
                }).findAny();
        System.out.println(firstEvenNumberP1);

    }
}
