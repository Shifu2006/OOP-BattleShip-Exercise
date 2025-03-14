import java.util.Scanner;
import java.util.Random;

public class Game{

    static Scanner input = new Scanner(System.in);
    static Scanner input2 = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        
    }

    public int askSize(){
        System.out.print("enter size of boards(6 - 26): ");
        int n = input2.nextInt();
        if(n < 6 || n > 26){
            System.out.println("out of range...");
            return askSize();
        }
        return n;
    }

    public static void placeShipRandomly(Board board, Ship ship) {
        boolean placed = false;
        boolean horizontal = rand.nextBoolean();
        while (!placed) {
            int row = rand.nextInt(board.getSize());
            int col = rand.nextInt(board.getSize());
            placed = board.placeShip(ship, row, col, horizontal);
        }
    }


}

