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
        if(name.isBlank()){
            System.out.println("Name cannot be empty!");
            return false;
        }
        if(name.length() > 13){
            System.out.println("Name cannot be longer than 13 characters!");
            return false;
        }
        for(int i = 0; i < name.length(); i++){
            if(!Character.isLetter(name.charAt(i)) && !Character.isDigit(name.charAt(i))){
                System.out.println("Name can only contain letters and numbers!");
                return false;
            }
        }
        
        return true;
    }

}