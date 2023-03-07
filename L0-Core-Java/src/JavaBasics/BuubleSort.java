package JavaBasics;

import java.util.Scanner;

public class BuubleSort {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        bubbleSort(arr);
        for(int item : arr){
            System.out.print(item+" ");
        }
    }

    public static void bubbleSort(int[] arr){
        boolean swapped = false;
        for(int i=0;i<arr.length-1;i++){
            for(int j = 0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            //If no two elements are swapped by inner loop, then the array is already sorted
            if(!swapped)
                break;
        }

    }
}
