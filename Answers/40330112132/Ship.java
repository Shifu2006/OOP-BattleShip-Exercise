public class Ship{
    public int size;
    public Ship(int size){
        this.size = size;
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