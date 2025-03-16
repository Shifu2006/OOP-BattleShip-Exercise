import java.util.ArrayList;
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

    public void rules(ArrayList<Ship> ships){
        System.out.print("RULES: there are " + ships.size() + " ships with these size: ");
        boolean isTrue = true;
        for (Ship ship : ships) {
            if(isTrue){
                System.out.print(ship.size);
                isTrue = !isTrue;
            }
            else{
                System.out.print(", " + ship.size);
            }
        }
        System.out.println(".");
    }

    public int getChoice(){
        System.out.print("Enter your choice: ");
        String inputS = input.nextLine();
        while(true){
            if(inputS.length() != 1 || inputS.charAt(0) < '1' || inputS.charAt(0) > '9'){
                System.out.println("wrong input!");
                System.out.print("Enter your choice: ");
                inputS = input.nextLine();
            }
            else{
                return inputS.charAt(0) - '0';
            }
        }
    }
}