import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class ShipPlacer{
    static Random rand = new Random();
    static Scanner input = new Scanner(System.in);
    static Scanner input2 = new Scanner(System.in);
    static Coordinate coordinate = new Coordinate();
    static Utils utils = new Utils();

    public ShipPlacer(){

    }

    public void placeShipRandomly(Board board, Ship ship) {
        boolean placed = false;
        boolean horizontal = rand.nextBoolean();
        int maxAttemps = board.getSize() * board.getSize();
        int attemps = 0;
        while (!placed) {
            int row = rand.nextInt(board.getSize());
            int col = rand.nextInt(board.getSize());
            placed = board.placeShip(ship, row, col, horizontal);
            attemps++;
            if(attemps == maxAttemps){
                horizontal = !horizontal;
                attemps = 0;
            }
        }
    }   

    public void userPlaceShip(Board board, Ship ship){
        boolean placed = false;
        while(!placed){
            System.out.println("Place ship with size of " + ship.size);
            coordinate.getCoordinateForPlace(board.getSize());
            int row = coordinate.row;
            int col = coordinate.col;
            boolean horizontal = utils.isHorizontal();
            placed = board.placeShip(ship, row, col, horizontal);
            if (!placed){
                System.out.println("Each ship must be placed at least one cell apart from other ships.");
                board.printGrid();
            }
        }
        board.printGrid();
    }

    public boolean askIfRandom(){
        System.out.print("Do you want to place it by yourself? (yes/no): ");
        String inputS = input.nextLine();
        inputS = inputS.toLowerCase();
        while (true) { 
            if(inputS.equals("yes")){
                return true;
            }
            else if(inputS.equals("no")){
                return false;
            }
            System.out.println("wrong input!");
            System.out.print("Do you want to place it by yourself? (yes/no): ");
            inputS = input.nextLine();
        }
    }

    public void setShips(ArrayList<Ship> ships, int boardSize){
        ships.clear();
        if(boardSize < 8){
            ships.add(new Ship(2));
            ships.add(new Ship(2));
            ships.add(new Ship(3));
            if(boardSize == 7){
                ships.add(new Ship(3));
            }
        }
        else{
            boolean isEven = false;
            int shipSize = 2;
            while(!(boardSize < 2)){
                if(isEven){
                    shipSize += 1;
                }
                isEven = !isEven;
                ships.add(new Ship(shipSize));
                boardSize -= 2;
            }
            if(boardSize > 0){
                if(isEven){
                    shipSize += 1;
                }
                ships.add(new Ship(shipSize));
            }
        }
    }
}