import java.util.Scanner;

public class Board{
    static Scanner input = new Scanner(System.in);
    static Scanner inputint = new Scanner(System.in);

    public char[][] grid;
    private int size;
    
    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
    }
    
    public int getSize() {
        return size;
    }

    public void initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = '~';
            }
        }
    }

    public boolean placeShip(Ship ship,int row,int col,boolean horizontal){
        if(canPlaceShip(grid, row, col, ship.size, horizontal)){
            if(horizontal){
                for(int j = 0; j < ship.size; j++){
                    grid[row][col + j] = 'S';
                }
            } else {
                for(int j = 0; j < ship.size; j++){
                    grid[row + j][col] = 'S';
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
    

    public boolean canPlaceShip(char[][] grid, int row, int col, int size, boolean horizontal) {
        if(horizontal){
            if(col + size > this.size){
                return false;
            }
            for (int i = -1; i < size + 1; i++) {
                for (int j = -1; j < 2; j++) {
                    if(row + j >= 0 && row + j < 10 && col + i >= 0 && col + i < 10){
                        if(grid[row + j][col + i] != '~'){
                            return false;
                        }
                    }
                }
            }
        }
        else{
            if(row + size > this.size){
                return false;
            }
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < size + 1; j++) {
                    if(row + j >= 0 && row + j < this.size && col + i >= 0 && col + i < this.size){
                        if(grid[row + j][col + i] != '~'){
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }

    public void printGrid(){
        System.out.print("   A");
        for (int i = 1; i < this.size; i++) {
            System.out.print(" " + ('A' + i));
        }
        System.out.println();
        for (int i = 1; i <= this.size; i++) {
            if(i < 10){
                System.out.print(i + "  ");
            }
            else{
                System.out.print(i + " ");
            }
            for (int j = 0; j < this.size; j++) {
                System.out.print(grid[i-1][j] + " ");
            }
            System.out.println();
        }
    }
}
