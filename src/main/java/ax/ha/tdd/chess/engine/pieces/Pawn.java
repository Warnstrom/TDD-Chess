package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Pawn extends ChessPiece {

    public Pawn(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    public boolean move(Chessboard board, Coordinates destination) {
        if (!canMove(board, destination))
            return false;
        location = destination;
        return true;
    }

    @Override
    public String getSymbol() {
        return pieceType.getSymbol();
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        final int targetPosX = destination.getX();
        final int targetPosY = destination.getY();
        final int curretPosX = location.getX();
        final int curretPosY = location.getY();
        if (!pieceHasMoved && chessboard.getPiece(destination) == null || targetPosX == curretPosX
                || targetPosY - curretPosY == 0
                || targetPosY - curretPosY == 1) {
            return true;
        } else if (pieceHasMoved && chessboard.getPiece(destination) == null && targetPosY - curretPosY == 0
                || targetPosX == curretPosX) {
            return true;
        }
        return false;
    }
}
