import java.util.Random;


public class ShipPlacer{

    static Random rand = new Random();

    public void placeShipRandomly(Board board, Ship ship) {
        boolean placed = false;
        boolean horizontal = rand.nextBoolean();
        while (!placed) {
            int row = rand.nextInt(board.getSize());
            int col = rand.nextInt(board.getSize());
            placed = board.placeShip(ship, row, col, horizontal);
        }
    }
}