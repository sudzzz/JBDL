package JavaBasics;

public class JavaOutput {
    public static void main(String []args){
        double pi = Math.PI;
        System.out.println(pi);
        System.out.format("%.2f\n",pi);  //print 2 digits after decimal
        System.out.format("%5.2f\n",pi); //print 5 characters in total and 2 digits after decimal. The remaining characters are filled with space.
        //if we give 3.2f instead of 5.2f, it will still print 3.14 as it is the minimum requirement
        System.out.format("%05.2f\n",pi);//print 5 characters in total and 2 digits after decimal. The remaining characters are filled with 0.
    }
}
