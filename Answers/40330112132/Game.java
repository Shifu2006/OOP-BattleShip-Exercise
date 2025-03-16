import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game{
    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();
    static Utils utils = new Utils();
    static ShipPlacer shipPlacer = new ShipPlacer();
    static Coordinate coordinate = new Coordinate();
    static Ship ship0 = new Ship(0);
    
    static int boardSize;
    static String player1Name;
    static String player2Name;
    static String aiName;
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
        player1Name = "Player1";
        player2Name = "Player2";
        aiName = "AI";
        mainMenu();
        System.out.println("bye bye...");
    }

    static void playTwoPlayer(){
        Player player1 = new Player(boardSize, player1Name);
        Player player2 = new Player(boardSize, player2Name);
        player1.board.initializeGrid();
        player2.board.initializeGrid();
        player1.trackingBoard.initializeGrid();
        player2.trackingBoard.initializeGrid();
        shipPlacer.setShips(ships, boardSize);
        utils.rules(ships);
        System.out.println(player1.name + ": ");
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
        System.out.println(player2.name + ": ");
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
                System.out.println(player1.name + "'s turn:");
                player1.trackingBoard.printGrid();
                playerTurn(player2.board.grid, player1.trackingBoard.grid);
            } else {
                System.out.println(player2.name + "'s turn:");
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
            System.out.println();
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
                if(ship0.shipSunk(opponentGrid, trackingGrid, row, col)){
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
        if(ship0.allShipsSunk(player1.trackingBoard.grid, player2.board.grid)){
            System.out.println(player1.name + " grid:");
            player1.board.printGrid();
            System.out.println(player2.name + " grid:");
            player2.board.printGrid();
            System.out.println(player1.name + " wins!");
            return true;
        }
        else if(ship0.allShipsSunk(player2.trackingBoard.grid, player1.board.grid)){
            System.out.println(player1.name + " grid:");
            player1.board.printGrid();
            System.out.println(player2.name + " grid:");
            player2.board.printGrid();
            System.out.println(player2.name + " wins!");
            return true;
        }
        return false;
    }

    static boolean isGameOverAI(Player player1 , AI ai) {
        if(ship0.allShipsSunk(player1.trackingBoard.grid, ai.board.grid)){
            System.out.println(player1.name + " grid:");
            player1.board.printGrid();
            System.out.println(ai.name + " grid:");
            ai.board.printGrid();
            System.out.println(player1.name + " wins!");
            return true;
        }
        else if(ship0.allShipsSunk(ai.trackingBoard.grid, player1.board.grid)){
            System.out.println(player1.name + " grid:");
            player1.board.printGrid();
            System.out.println(ai.name + " grid:");
            ai.board.printGrid();
            System.out.println(ai.name + " wins!");
            return true;
        }
        return false;
    }

    public static void playWithAI(){
        Player player1 = new Player(boardSize, player1Name);
        AI ai = new AI(boardSize, aiName);
        player1.board.initializeGrid();
        ai.board.initializeGrid();
        player1.trackingBoard.initializeGrid();
        ai.trackingBoard.initializeGrid();
        shipPlacer.setShips(ships, boardSize);
        utils.rules(ships);
        System.out.println(player1.name + ": ");
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
        for(Ship ship : ships){
            shipPlacer.placeShipRandomly(ai.board, ship);
        }

        boolean player1Turn = true;
        while (!isGameOverAI(player1, ai)) {
            if (player1Turn) {
                System.out.println(player1.name + "'s turn:");
                player1.trackingBoard.printGrid();
                playerTurn(ai.board.grid, player1.trackingBoard.grid);
            } else {
                System.out.println(ai.name + "'s turn:");
                ai.turn(player1.board.grid, ai.trackingBoard.grid);
                ai.trackingBoard.printGrid();
            }
            player1Turn = !player1Turn;
        }

        System.out.println("Game Over!");
    }

    static void mainMenu(){
        System.out.println("--- Battle Ship OOP ---");
        System.out.println("1. Play");
        System.out.println("2. Options");
        System.out.println("3. Quit game");

        int n = utils.getChoice();

        switch(n){
            case 1:
                playMenu();
                return;
            case 2:
                optionsMenu();
                return;
            case 3:
                return;
            default:
                System.out.println("wrong input...");
                System.out.println();
                mainMenu();
        }
    }

    static void playMenu(){
        System.out.println();
        System.out.println("--- Play ---");
        System.out.println("1. Play with a friend");
        System.out.println("2. Play with AI");
        System.out.println("3. Back to main menu");

        int n = utils.getChoice();

        switch(n){
            case 1:
                playTwoPlayer();
                playMenu();
                return;
            case 2:
                playWithAI();
                playMenu();
                return;
            case 3:
                System.out.println();
                mainMenu();
                return;
            default:
                System.out.println("wrong input...");
                playMenu();
        }
    }

    public static void optionsMenu(){
        System.out.println();
        System.out.println("--- Options ---");
        System.out.println("1. Size of the board, current: " + boardSize);
        System.out.println("2. Names");
        System.out.println("3. back to main menu");

        int n = utils.getChoice();

        switch(n){
            case 1:
                boardSize = utils.askSize();
                optionsMenu();
                return;
            case 2:
                namesMenu();
                return;
            case 3:
                System.out.println();
                mainMenu();
                return;
            default:
                System.out.println("wrong input...");
                optionsMenu();
        }
    }

    public static void namesMenu(){
        System.out.println();
        System.out.println("--- Names ---");
        System.out.println("1. Player 1, current: " + player1Name);
        System.out.println("2. Player 2, current: " + player2Name);
        System.out.println("3. AI, current: " + aiName);
        System.out.println("4. Back to options");

        int n = utils.getChoice();

        switch(n){
            case 1:
                System.out.print("Enter new name for Player 1: ");
                String holdName = input.nextLine();
                while(!Player.isValidName(holdName)){
                    System.out.println("Invalid name. Try again.");
                    System.out.print("Enter new name for Player 1: ");
                    holdName = input.nextLine();
                }
                player1Name = holdName;
                namesMenu();
                return;
            case 2:
                System.out.print("Enter new name for Player 2: ");
                holdName = input.nextLine();
                while(!Player.isValidName(holdName)){
                    System.out.println("Invalid name. Try again.");
                    System.out.print("Enter new name for Player 2: ");
                    holdName = input.nextLine();
                }
                player2Name = holdName;
                namesMenu();
                return;
            case 3:
                System.out.print("Enter new name for AI: ");
                holdName = input.nextLine();
                while(!Player.isValidName(holdName)){
                    System.out.println("Invalid name. Try again.");
                    System.out.print("Enter new name for AI: ");
                    holdName = input.nextLine();
                }
                aiName = holdName;
                namesMenu();
                return;
            case 4:
                optionsMenu();
                return;
            default:
                System.out.println("wrong input...");
                namesMenu();
        }
    }

}
