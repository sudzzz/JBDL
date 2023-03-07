package JavaOOPs;

class Student{
    String name;
    private float CGPA;

    public float getCGPA(){
        return this.CGPA;
    }

    public void setCGPA(float CGPA){
        this.CGPA = CGPA;
    }
}
public class Encapsulation {
    public static void main(String []args){
        Student s1 = new Student();
        s1.setCGPA(9.7f);

        System.out.println(s1.getCGPA());
    }
}
