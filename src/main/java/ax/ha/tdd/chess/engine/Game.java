package ax.ha.tdd.chess.engine;

public class Game {

    Chessboard board = Chessboard.startingBoard();

    // Feel free to delete this stuff. Just for initial testing.
    boolean isNewGame = true;
    int playerTurn = 0;

    public Player getPlayerToMove() {
        return playerTurn % 2 == 0 ? Player.WHITE : Player.BLACK;
    }

    public Chessboard getBoard() {
        return board;
    }

    public String getLastMoveResult() {
        // TODO this should be used to show the player what happened
        // Illegal move, correct move, e2 moved to e4 etc.
        if (isNewGame) {
            return "Game hasn't begun";
        }
        return "Last move was successful (default reply, change this)";
    }

    public void move(String move) throws InvalidMovementException {
        board.move(move, getPlayerToMove());
        isNewGame = false;
        System.out.println("Player tried to perform move: " + move);
    }
}
