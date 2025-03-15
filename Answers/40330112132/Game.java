import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Game{
    static Scanner input = new Scanner(System.in);
    static Scanner input2 = new Scanner(System.in);
    static Random rand = new Random();
    static Utils utils = new Utils();
    static ShipPlacer shipPlacer = new ShipPlacer();
    static Coordinate coordinate = new Coordinate();
    static int boardSize;
    public static ArrayList<Ship> ships = new ArrayList<>();

    static String[] text = {
        "Are you blind or something?",
        "LOOK AT THIS GUY! XD",
        "Ain't no way you just did that.",
        "Some advice: this not gonna work.",
        "SKILL ISSUE!",
        "Bro really did it twice, unbelievable!",
        "the square is talking: what did I do to deserve this? dont hit me again.",
        "Is this your master plan? gg."
    };

    public static void main(String[] args) {
        boardSize = 10;
        mainMenu();

    }

    public static void playTwoPlayer(){
        Player player1 = new Player(boardSize);
        Player player2 = new Player(boardSize);
        player1.board.initializeGrid();
        player2.board.initializeGrid();
        player1.trackingBoard.initializeGrid();
        player2.trackingBoard.initializeGrid();
        shipPlacer.setShips(ships, boardSize);
        utils.rules(ships);
        System.out.println("Player 1: ");
        if(!shipPlacer.askIfRandom()){
            for(Ship ship : ships){
                shipPlacer.placeShipRandomly(player1.board, ship);
            }
        }
        else{
            player1.board.printGrid();
            for(Ship ship : ships){
                shipPlacer.userPlaceShip(player1.board, ship);
            }
        }
        System.out.println("Player 2:");
        if(!shipPlacer.askIfRandom()){
            for(Ship ship : ships){
                shipPlacer.placeShipRandomly(player2.board, ship);
            }
        }
        else{
            player2.board.printGrid();
            for(Ship ship : ships){
                shipPlacer.userPlaceShip(player2.board, ship);
            }
        }

        boolean player1Turn = true;
        while (!isGameOver(player1, player2)) {
            if (player1Turn) {
                System.out.println("Player 1's turn:");
                player1.trackingBoard.printGrid();
                playerTurn(player2.board.grid, player1.trackingBoard.grid);
            } else {
                System.out.println("Player 2's turn:");
                player2.trackingBoard.printGrid();
                playerTurn(player1.board.grid, player2.trackingBoard.grid);
            }
            player1Turn = !player1Turn;
        }

        System.out.println("Game Over!");
    }

    static void playerTurn(char[][] opponentGrid, char[][] trackingGrid) {
        if(!coordinate.getCoordinateForGame(opponentGrid.length)) {
            System.out.println("Invalid input. Don't do this again.");
            return;
        }
        else{
            int col = coordinate.col;
            int row = coordinate.row;
            if(opponentGrid[row][col] == 'S'){
                if(trackingGrid[row][col] == 'X'){
                    System.out.println("You already hit that ship. " + text[rand.nextInt(text.length)]);
                    return;
                }
                trackingGrid[row][col] = 'X';
                if(utils.shipSunk(opponentGrid, trackingGrid, row, col)){
                    System.out.println("You sunk a ship!");
                }
                else{
                    System.out.println("Hit!");
                }
            }
            else{
                if(trackingGrid[row][col] == 'O'){
                    System.out.println("You already hit that square. " + text[rand.nextInt(text.length)]);
                    return;
                }
                trackingGrid[row][col] = 'O';
                System.out.println("Miss!");
            }
        }
    }

    static boolean isGameOver(Player player1 , Player player2) {
        if(utils.allShipsSunk(player1.trackingBoard.grid, player2.board.grid)){
            System.out.println("Player 1 grid:");
            player1.board.printGrid();
            System.out.println("Player 2 grid:");
            player2.board.printGrid();
            System.out.println("Player 1 wins!");
            return true;
        }
        else if(utils.allShipsSunk(player2.trackingBoard.grid, player1.board.grid)){
            System.out.println("Player 1 grid:");
            player1.board.printGrid();
            System.out.println("Player 2 grid:");
            player2.board.printGrid();
            System.out.println("Player 2 wins!");
            return true;
        }
        return false;
    }

    public void playAI(){

    }

    public static void mainMenu(){
        System.out.println("--- Battle Ship OOP ---");
        System.out.println("1. Play");
        System.out.println("2. Options");
        System.out.println("3. Quit game");
        System.out.print("Enter your choice: ");

        int n = input2.nextInt();

        switch(n){
            case 1:
                playMenu();
                return;
            case 2:
                optionMenu();
                mainMenu();
                return;
            case 3:
                return;
            default:
                System.out.println("wrong input...");
                mainMenu();
                return;
        }
    }

    public static void playMenu(){
        System.out.println("--- Play ---");
        System.out.println("1. Play with a friend");
        System.out.println("2. Play with AI");
        System.out.println("3. Main menu");
        System.out.print("Enter your choice: ");

        int n = input2.nextInt();

        switch(n){
            case 1:
                playTwoPlayer();
                return;
            case 2:
                return;
            case 3:
                mainMenu();
                return;
            default:
                System.out.println("wrong input...");
                playMenu();
                return;
        }
    }

    public static void optionMenu(){
        System.out.println("--- Option ---");
        System.out.println("1. Size of the board");
        System.out.println("2. Main menu");
        System.out.print("Enter your choice: ");

        int n = input2.nextInt();

        switch(n){
            case 1:
                boardSize = utils.askSize();
                return;
            case 2:
                mainMenu();
                return;
            default:
                System.out.println("wrong input...");
                optionMenu();
                return;
        }
    }

    
}
