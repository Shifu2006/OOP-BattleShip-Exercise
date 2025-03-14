import java.util.Scanner;

public class Game{

    static Scanner input = new Scanner(System.in);
    static Scanner input2 = new Scanner(System.in);

    public static void main(String[] args) {
        
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
}

