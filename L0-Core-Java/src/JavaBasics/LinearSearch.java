package JavaBasics;

import java.util.Scanner;

public class LinearSearch {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int findNum = sc.nextInt();
        if(linearSearch(arr,findNum)){
            System.out.println(findNum + " is present in the array");
        }else{
            System.out.println(findNum + " is not present in the array");
        }
    }
    public static boolean linearSearch(int[] arr,int num){
        for (int j : arr) {
            if (j == num)
                return true;
        }
        return false;
    }
}
