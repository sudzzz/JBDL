package JavaBasics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JavaInput {
    public static void main(String []args) throws IOException{
        JavaInput javaInput = new JavaInput();
        javaInput.bufferedReader();
        javaInput.scannerReader();
    }

    public void bufferedReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a String");
        String str = br.readLine();
        System.out.println("Enter a Number");
        int x = Integer.parseInt(br.readLine());
        System.out.println("You Entered "+str+" "+x);
    }

    public void scannerReader(){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println("You Entered String "+str);
        int x = sc.nextInt();
        System.out.println("You Entered Integer "+x);
        float f = sc.nextFloat();
        System.out.println("You Entered Float "+f);
    }
}
