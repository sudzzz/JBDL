package Lecture1;

import java.util.*;

/**
 * A list of numbers is given,
 * 1 1 1 2 2 1 1 3 3 3 3 1 1 3 3 3 3 3 3 3 3 1 1 3 3 3 1 2 2
 * which is to be converted to frequency map
 * From frequency map, calculate its total sum
 * 2 -> 4
 * 1 -> 10
 * 3 -> 15
 * print value of 2*4 + 1*10 + 3*15 = 63
 */

public class Question1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter the size of List");
        int size = sc.nextInt();
        for(int i=0;i<size;i++){
            int a = sc.nextInt();
            numbers.add(a);
        }
        Question1 obj = new Question1();
        Map<Integer,Integer> mp = obj.getMap(numbers);
        System.out.println(mp);
        long sum = obj.findSum(mp);
        System.out.println("Sum is "+sum);
    }

    public Map<Integer,Integer> getMap(List<Integer> numbers){
        Map<Integer,Integer> mp = new HashMap<>();
        mp.put(numbers.get(0),1);
        for(int i = 1;i<numbers.size();i++){
            if(mp.containsKey(numbers.get(i))){
                mp.put(numbers.get(i),mp.get(numbers.get(i))+1);
            }else{
                mp.put(numbers.get(i),1);
            }
        }
        return mp;
    }

    public long findSum(Map<Integer,Integer> mp){
        long sum = 0;
        for(int x : mp.keySet()){
            sum = sum+ (x*mp.get(x));
        }
        return sum;
    }
}
