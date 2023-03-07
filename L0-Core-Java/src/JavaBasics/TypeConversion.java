package JavaBasics;

public class TypeConversion {
    public static void main(String []args){
        TypeConversion typeConversion = new TypeConversion();
        typeConversion.implicit();
        typeConversion.explicit();
    }
    public void implicit(){
        int x = 100;
        long y = x;
        float z = y;
        System.out.println(x+" "+y+" "+z);
    }
    public void explicit(){
        double d = 65.4;
        int i = (int) d;
        char c = (char) i;
        System.out.println(d+" "+i+" "+c);
    }
}
