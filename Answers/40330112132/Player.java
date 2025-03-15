public class Player{
    public Board board;
    public Board trackingBoard;
    public Player(int boardSize){
        this.board = new Board(boardSize);
        this.trackingBoard = new Board(boardSize);
    }
}