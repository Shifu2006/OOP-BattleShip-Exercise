import java.util.Scanner;

public class Coordinate{
    public int row;
    public int col;

    static Scanner input = new Scanner(System.in);
    static Utils utils = new Utils();

    public void getCoordinateForPlace(int boardSize){
        System.out.print("Enter a location to place (for example A6): ");
        String inputS = input.nextLine();
        inputS = inputS.toUpperCase();
        while(!utils.isValidCoordinate(inputS, boardSize)){
            System.out.println("wrong input!");
            System.out.print("Enter a location to place (for example A6): ");
            inputS = input.nextLine();
        }
        this.col = inputS.charAt(0) - 'A';
        this.row = 0;
        for(int i = 1; i < inputS.length(); i++){
            this.row = this.row * 10 + (inputS.charAt(i) - '0');
        }
        this.row -= 1;
    }

    public boolean getCoordinateForGame(int boardSize){
        System.out.print("Enter a target location (for example A6): ");
        String inputS = input.nextLine();
        inputS = inputS.toUpperCase();
        if(!utils.isValidCoordinate(inputS, boardSize)){
            return false;
        }
        else{
            this.col = inputS.charAt(0) - 'A';
            this.row = 0;
            for(int i = 1; i < inputS.length(); i++){
                this.row = this.row * 10 + (inputS.charAt(i) - '0');
            }
            this.row -= 1;
        }
        
        return true;
    }

}