public class Player{
    public Board board;
    public Board trackingBoard;
    String name;
    public Player(int boardSize, String name){
        this.name = name;
        this.board = new Board(boardSize);
        this.trackingBoard = new Board(boardSize);
    }

    public static boolean isValidName(String name){
        if(name.length() < 0){
            return false;
        }
        if(name.isBlank()){
            return false;
        }
        return true;
    }
}