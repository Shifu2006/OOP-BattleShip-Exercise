import java.util.Random;
import java.util.Scanner;

public class ShipPlacer{
    static Random rand = new Random();
    static Scanner input = new Scanner(System.in);
    static Scanner input2 = new Scanner(System.in);
    static Coordinate coordinate = new Coordinate();
    static Utils utils = new Utils();

    public void placeShipRandomly(Board board, Ship ship) {
        boolean placed = false;
        boolean horizontal = rand.nextBoolean();
        while (!placed) {
            int row = rand.nextInt(board.getSize());
            int col = rand.nextInt(board.getSize());
            placed = board.placeShip(ship, row, col, horizontal);
        }
    }   

    public void userPlaceShip(Board board, Ship ship){
        boolean placed = false;
        while(!placed){
            coordinate.getCoordinate(board.getSize());
            int row = coordinate.row;
            int col = coordinate.col;
            boolean horizontal = utils.isHorizontal();
            placed = board.placeShip(ship, row, col, horizontal);
        }
    }
}