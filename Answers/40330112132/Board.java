import java.util.Scanner;

public class Board{
    Scanner input = new Scanner(System.in);
    Scanner inputint = new Scanner(System.in);

    private char[][] grid;
    private int size;
    
    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
    }
    
    public int getSize() {
        return size;
    }

}
