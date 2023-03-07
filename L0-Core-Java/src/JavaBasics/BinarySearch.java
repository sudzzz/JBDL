package JavaBasics;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int findNum = sc.nextInt();
        if(binarySearch(arr,findNum)){
            System.out.println(findNum + " is present in the array");
        }else{
            System.out.println(findNum + " is not present in the array");
        }
    }

    public static boolean binarySearch(int[] arr,int num){
        int low = 0;
        int high = arr.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(arr[mid]==num)
                return true;
            else if(arr[mid]>num)
                high = mid-1;
            else
                low = mid+1;
        }
        return false;
    }
}
