import java.util.Scanner;

public class Utils{

    Scanner input = new Scanner(System.in);

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

    
}