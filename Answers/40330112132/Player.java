public class Player{
    public Board board;
    public Board trackingBoard;
    String name;
    public Player(int boardSize, String name){
        this.name = name;
        this.board = new Board(boardSize);
        this.trackingBoard = new Board(boardSize);
    }
}