public class Player{
    Board board;
    Board trackingBoard;

    public Player(int boardSize){
        this.board = new Board(boardSize);
        this.trackingBoard = new Board(boardSize);
    }
}
