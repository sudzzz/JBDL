package JavaBasics;

public class CommandLineArguments {
    public static void main(String []args){
        if(args.length > 0){
            System.out.println("Arguments are ");
            for(String str : args){
                System.out.print(str + " ");
            }
        }
        else {
            System.out.println("No Arguments");
        }
    }
}
