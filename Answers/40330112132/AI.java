import java.util.Random;

public class AI extends Player {
    private boolean lookingForShip = false;
    private int row;
    private int col;
    private boolean isOneHit = true;
    private boolean horizontal;

    static Random rand = new Random();
    static Utils utils = new Utils();
    
    public AI(int size) {
        super(size);
    }

    public void turn(char[][] opponentGrid, char[][] trackingGrid) {
        if (lookingForShip) {
            if(isOneHit){
                int n;
                boolean randhorizontal = rand.nextBoolean();
                boolean isPlus = rand.nextBoolean();
                while(true){
                    if(randhorizontal){
                        if(isPlus){
                            n = 1;
                            while(true){
                                if(col + n >= trackingGrid.length || trackingGrid[row][col + n] == 'O'){
                                    break;
                                }
                                if(col + n < trackingGrid.length && trackingGrid[row][col + n] == '~'){
                                    hitOrMiss(opponentGrid, trackingGrid, row, col + n);
                                    this.horizontal = true;
                                    return;
                                }
                                n++;
                            }
                            randhorizontal = rand.nextBoolean();
                            isPlus = rand.nextBoolean();
                        }
                        else{
                            n = -1;
                            while(true){
                                if(col + n < 0 || trackingGrid[row][col + n] == 'O'){
                                    break;
                                }
                                if(col + n > 0 && trackingGrid[row][col + n] == '~'){
                                    hitOrMiss(opponentGrid, trackingGrid, row, col + n);
                                    this.horizontal = true;
                                    return;
                                }
                                n--;
                            }
                            randhorizontal = rand.nextBoolean();
                            isPlus = rand.nextBoolean();
                        }
                    }
                    else{
                        if(isPlus){
                            n = 1;
                            while(true){
                                if(row + n >= trackingGrid.length || trackingGrid[row + n][col] == 'O'){
                                    break;
                                }
                                if(row + n < trackingGrid.length && trackingGrid[row + n][col] == '~'){
                                    hitOrMiss(opponentGrid, trackingGrid, row + n, col);
                                    this.horizontal = false;
                                    return;
                                }
                                n++;
                            }
                            randhorizontal = rand.nextBoolean();
                            isPlus = rand.nextBoolean();
                        }
                        else{
                            n = -1;
                            while(true){
                                if(row + n < 0 || trackingGrid[row + n][col] == 'O'){
                                    break;
                                }
                                if(row + n > 0 && trackingGrid[row + n][col] == '~'){
                                    hitOrMiss(opponentGrid, trackingGrid, row + n, col);
                                    this.horizontal = false;
                                    return;
                                }
                                n--;
                            }
                            randhorizontal = rand.nextBoolean();
                            isPlus = rand.nextBoolean();
                        }
                    }
                }
            }
            else{
                int n;
                boolean isPlus = rand.nextBoolean();
                if(horizontal){
                    if(isPlus){
                        n = 1;
                        while(true){
                            if(col + n >= trackingGrid.length || trackingGrid[row][col + n] == 'O'){
                                break;
                            }
                            if(col + n < trackingGrid.length && trackingGrid[row][col + n] == '~'){
                                hitOrMiss(opponentGrid, trackingGrid, row, col + n);
                                this.horizontal = true;
                                return;
                            }
                            n++;
                        }
                        isPlus = !isPlus;
                    }
                    else{
                        n = -1;
                        while(true){
                            if(col + n < 0 || trackingGrid[row][col + n] == 'O'){
                                break;
                            }
                            if(col + n > 0 && trackingGrid[row][col + n] == '~'){
                                hitOrMiss(opponentGrid, trackingGrid, row, col + n);
                                this.horizontal = true;
                                return;
                            }
                            n--;
                        }
                        isPlus = !isPlus;
                    }
                }
                else{
                    if(isPlus){
                        n = 1;
                        while(true){
                            if(row + n >= trackingGrid.length || trackingGrid[row + n][col] == 'O'){
                                break;
                            }
                            if(row + n < trackingGrid.length && trackingGrid[row + n][col] == '~'){
                                hitOrMiss(opponentGrid, trackingGrid, row + n, col);
                                this.horizontal = false;
                                return;
                            }
                            n++;
                        }
                        isPlus = !isPlus;
                    }
                    else{
                        n = -1;
                        while(true){
                            if(row + n < 0 || trackingGrid[row + n][col] == 'O'){
                                break;
                            }
                            if(row + n > 0 && trackingGrid[row + n][col] == '~'){
                                hitOrMiss(opponentGrid, trackingGrid, row + n, col);
                                this.horizontal = false;
                                return;
                            }
                            n--;
                        }
                        isPlus = !isPlus;
                    }
                }
            }
        } else {
            int row2 = rand.nextInt(opponentGrid.length);
            int col2 = rand.nextInt(opponentGrid.length);
            while(trackingGrid[row2][col2] != '~'){
                row2 = rand.nextInt(opponentGrid.length);
                col2 = rand.nextInt(opponentGrid.length);
            }
            hitOrMiss(opponentGrid, trackingGrid, row2, col2);
            this.isOneHit = true;
        }
    }

    public void hitOrMiss(char[][] opponentGrid, char[][] trackingGrid, int row, int col) {
        if(opponentGrid[row][col] == 'S'){
            trackingGrid[row][col] = 'X';
            if(utils.shipSunk(opponentGrid, trackingGrid, row, col)){
                System.out.println("AI chose " + (char)(col + 'A') + (row + 1) + " and sunk a ship!");
                this.lookingForShip = false;
            }
            else{
                System.out.println("AI chose " + (char)(col + 'A') + (row + 1) + " and hit!");
                this.lookingForShip = true;
                this.row = row;
                this.col = col;
                this.isOneHit = false;
            }
        }
        else{
            trackingGrid[row][col] = 'O';
            System.out.println("AI chose " + (char)(col + 'A') + (row + 1) + " and missed!");
        }
    }
    
}
