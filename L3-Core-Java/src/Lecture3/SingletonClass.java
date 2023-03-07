package Lecture3;

public class SingletonClass {

    private static volatile SingletonClass object = null;

    private SingletonClass(){

    }

    public static SingletonClass getInstance() {
        if(object == null){
            synchronized (SingletonClass.class) {
                if(object == null) {
                    object = new SingletonClass();
                }
            }
        }

        return object;
    }



    public static void main(String[] args) {
        SingletonClass singletonClass = new SingletonClass();

    }
}