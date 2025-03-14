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

    static boolean allShipsSunk(char[][] opponentGrid, char[][] trackingGrid) {
        for (int i = 0; i < trackingGrid.length; i++) {
            for (int j = 0; j < trackingGrid.length; j++) {
                if(trackingGrid[i][j] == 'S' && opponentGrid[i][j] != 'X'){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean shipSunk(char[][] opponentGrid, char[][] trackingGrid, int row, int col) {
        int holdRow = row;
        boolean horizontal = true;
        int shipSize = 1;
        int holdRow2 = row;
        int holdCol2 = col;
        while(row-1 >= 0 && opponentGrid[row-1][col] == 'S'){
            shipSize++;
            holdRow2--;
            horizontal = false;
            if(trackingGrid[row-1][col] != 'X'){
                return false;
            }
            row--;
        }
        row = holdRow;
        while(row+1 < 10 && opponentGrid[row+1][col] == 'S'){
            shipSize++;
            horizontal = false;
            if(trackingGrid[row+1][col] != 'X'){
                return false;
            }
            row++;
        }

        int holdCol = col;
        while(col-1 >= 0 && opponentGrid[row][col-1] == 'S'){
            shipSize++;
            holdCol2--;
            if(trackingGrid[row][col-1] != 'X'){
                return false;
            }
            col--;
        }
        col = holdCol;
        while(col+1 < 10 && opponentGrid[row][col+1] == 'S'){
            shipSize++;
            if(trackingGrid[row][col+1] != 'X'){
                return false;
            }
            col++;
        }
        col = holdCol2;
        row = holdRow2;

        if(horizontal){
            for (int i = -1; i < shipSize + 1; i++) {
                for (int j = -1; j < 2; j++) {
                    if(row + j >= 0 && row + j < 10 && col + i >= 0 && col + i < 10){
                        if(trackingGrid[row + j][col + i] == '~'){
                            trackingGrid[row + j][col + i] = 'O';
                        }
                    }
                }
            }
        }
        else{
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < shipSize + 1; j++) {
                    if(row + j >= 0 && row + j < 10 && col + i >= 0 && col + i < 10){
                        if(trackingGrid[row + j][col + i] == '~'){
                            trackingGrid[row + j][col + i] = 'O';
                        }
                    }
                }
            }
        }

        return true;
    }
}
