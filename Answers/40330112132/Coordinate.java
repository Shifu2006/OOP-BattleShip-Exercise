import java.util.Scanner;

public class Coordinate{
    public int row;
    public int col;

    static Scanner input = new Scanner(System.in);

    static Utils utils = new Utils();

    public void getCoordinate(int boardSize){
        System.out.print("Enter a location to place (for example A7): ");
        String inputS = input.nextLine();
        while(!utils.isValidCoordinate(inputS, boardSize)){
            System.out.println("wrong input!");
            System.out.print("Enter a location to place (for example A7): ");
            inputS = input.nextLine();
        }
        this.col = inputS.charAt(0) - 'A';
        this.row = 0;
        for(int i = 1; i < inputS.length(); i++){
            this.row = this.row * 10 + (inputS.charAt(i) - '0');
        }
        this.row -= 1;
    }
}