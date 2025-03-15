import java.util.Scanner;

public class Utils{

    Scanner input = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);


    public boolean isValidCoordinate(String inputS, int boardSize) {
        if (inputS.length() != 2 && inputS.length() != 3) return false;
        char col = inputS.charAt(0);
        int row = 0;
        for(int i = 1; i < inputS.length(); i++){
            if(inputS.charAt(i) < '0' && inputS.charAt(i) > '9') return false;
            row = row * 10 + (inputS.charAt(i) - '0');
        }
        return (col >= 'A' && col < 'A' + boardSize) && (row > 0 && row <= boardSize);
    }

    public boolean isHorizontal(){
        System.out.print("horizontal? (yes/no): ");
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
            System.out.print("horizontal? (yes/no): ");
            inputS = input.nextLine();
        }
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

    public boolean allShipsSunk(char[][] opponentGrid, char[][] trackingGrid) {
        for (int i = 0; i < trackingGrid.length; i++) {
            for (int j = 0; j < trackingGrid.length; j++) {
                if(trackingGrid[i][j] == 'S' && opponentGrid[i][j] != 'X'){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean shipSunk(char[][] opponentGrid, char[][] trackingGrid, int row, int col) {
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
        while(row+1 < trackingGrid.length && opponentGrid[row+1][col] == 'S'){
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
        while(col+1 < trackingGrid.length && opponentGrid[row][col+1] == 'S'){
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
                    if(row + j >= 0 && row + j < trackingGrid.length 
                    && col + i >= 0 && col + i < trackingGrid.length){
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
                    if(row + j >= 0 && row + j < trackingGrid.length 
                    && col + i >= 0 && col + i < trackingGrid.length){
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