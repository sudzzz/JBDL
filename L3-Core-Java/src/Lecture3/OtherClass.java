package Lecture3;

import java.util.ArrayList;
import java.util.List;

public class OtherClass extends Thread{
    public static void main(String[] args) {

        List<OtherClass> otherClassList = new ArrayList<>();
        otherClassList.add(new OtherClass());
        otherClassList.add(new OtherClass());
        otherClassList.add(new OtherClass());
        otherClassList.add(new OtherClass());
        otherClassList.add(new OtherClass());
        otherClassList.add(new OtherClass());
        otherClassList.add(new OtherClass());
        otherClassList.add(new OtherClass());

        otherClassList.stream().forEach(x -> x.start());
    }

    @Override
    public void run() {

        SingletonClass obj1 = SingletonClass.getInstance();
        System.out.println("In thread " + currentThread() + ", obj = " + obj1);

    }
}