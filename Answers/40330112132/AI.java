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
                        }
                        randhorizontal = rand.nextBoolean();
                        isPlus = rand.nextBoolean();
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
                        }
                        randhorizontal = rand.nextBoolean();
                        isPlus = rand.nextBoolean();
                    }
                }
            }
            else{
                int n;
                boolean isPlus = rand.nextBoolean();
                while(true){
                    if(this.horizontal){
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
            }
        } else {
            int randrow = rand.nextInt(opponentGrid.length);
            int randcol = rand.nextInt(opponentGrid.length);
            while(trackingGrid[randrow][randcol] != '~'){
                randrow = rand.nextInt(opponentGrid.length);
                randcol = rand.nextInt(opponentGrid.length);
            }
            hitOrMiss(opponentGrid, trackingGrid, randrow, randcol);
            this.isOneHit = true;
        }
    }

    public void hitOrMiss(char[][] opponentGrid, char[][] trackingGrid, int row2, int col2) {
        if(opponentGrid[row2][col2] == 'S'){
            trackingGrid[row2][col2] = 'X';
            if(utils.shipSunk(opponentGrid, trackingGrid, row2, col2)){
                System.out.println("AI chose " + (char)(col2 + 'A') + (row2 + 1) + " and sunk a ship!");
                this.lookingForShip = false;
            }
            else{
                System.out.println("AI chose " + (char)(col2 + 'A') + (row2 + 1) + " and hit!");
                this.lookingForShip = true;
                this.row = row2;
                this.col = col2;
                this.isOneHit = false;
            }
        }
        else{
            trackingGrid[row2][col2] = 'O';
            System.out.println("AI chose " + (char)(col2 + 'A') + (row2 + 1) + " and missed!");
        }
    }
    
}
