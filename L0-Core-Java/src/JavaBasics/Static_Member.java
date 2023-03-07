package JavaBasics;


class Player{
    String name;
    int id;

//    static int playerCount = 0;
    private static int playerCount = 0;

    Player(String name){
        this.name = name;
        this.id = ++playerCount;
    }

    static int getPlayerCount(){
        return playerCount;
    }

    void printDetails(){
        System.out.println(id+" : "+name);
    }

}
public class Static_Member {
    public static void main(String []args){
        System.out.println(Player.getPlayerCount());
        Player p1 = new Player("Sudhir");
        System.out.println(Player.getPlayerCount());
        Player p2 = new Player("Ajay");
        System.out.println(Player.getPlayerCount());
        p1.printDetails();
        p2.printDetails();

        //We can access static members either by using Class or by using objects
//        System.out.println(Player.playerCount);
//        System.out.println(p1.playerCount);
//        System.out.println(p2.playerCount);
    }
}
