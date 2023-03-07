package JavaOOPs;

class Bicycle{
    public int gear;
    public int speed;

    public Bicycle(int gear,int speed){
        this.gear = gear;
        this.speed = speed;
    }

    public void applyBrake(int decrement){
        speed -= decrement;
    }

    public void speedUp(int increment){
        speed += increment;
    }
    public void printInfo(){
        System.out.println("gears are "+gear);
        System.out.println("speed is "+speed);
    }
}

class MountainBike extends Bicycle{
    public int seatHeight;
    public MountainBike(int gear, int speed, int seatHeight) {
        super(gear, speed); //always needs to be the first statement
        this.seatHeight = seatHeight;
    }

    public void setSeatHeight(int newValue){
        this.seatHeight = newValue;
    }
    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("seatHeight is "+seatHeight);
    }
}
public class Inheritance {
    public static void main(String []args){
        MountainBike mb = new MountainBike(3,100,25);
        mb.printInfo();
        mb.speedUp(20);
        mb.printInfo();
        mb.setSeatHeight(22);

    }
}
