package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Rook extends ChessPiece {

    public Rook(Player player, Coordinates location) {
        super(PieceType.ROOK, player, location);
    }

    public boolean move(Chessboard board, Coordinates destination) {
        if (!canMove(board, destination))
            return false;
        board.removePiece(this);
        location = destination;
        board.addPiece(this);
        return true;
    }

    @Override
    public String getSymbol() {
        return pieceType.getSymbol();
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        System.out.println("Rook called");
        int differenceBetweenTarget = 0;
        final int targetPosX = destination.getX();
        final int targetPosY = destination.getY();
        final int currentPosX = location.getX();
        final int currentPosY = location.getY();
        if (Integer.compare(0, currentPosX - targetPosX) != 0) {
            differenceBetweenTarget = Math.abs(currentPosX - targetPosX);
        } else if (Integer.compare(0, currentPosY - targetPosY) != 0) {
            differenceBetweenTarget = Math.abs(currentPosY - targetPosY);
        }
        for (int i = 1; i < differenceBetweenTarget; i++) {
            if (Integer.compare(0, currentPosX - targetPosX) != 0
                    && chessboard.getPiece(new Coordinates(
                            currentPosX + i * Integer.compare(0, currentPosX - targetPosX), currentPosY)) != null) {
                return false;
            } else if (Integer.compare(0, currentPosY - targetPosY) != 0
                    && chessboard.getPiece(new Coordinates(currentPosX,
                            currentPosY + i * Integer.compare(0, currentPosY - targetPosY))) != null) {
                return false;
            }
        }
        return (currentPosX == targetPosX || currentPosY == targetPosY);
    }

}