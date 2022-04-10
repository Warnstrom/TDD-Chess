package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Rook extends ChessPiece {

    public Rook(Player player, Coordinates location) {
        super(PieceType.PAWN, player, location);
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
        final int targetPosX = destination.getX();
        final int targetPosY = destination.getY();
        final int curretPosX = location.getX();
        final int curretPosY = location.getY();
        return false;
    }
}
/**
 * 
 * if (!pieceHasMoved && targetPosX == curretPosX
 * && targetPosY - curretPosY == 0
 * || targetPosY - curretPosY == 1) {
 * return true;
 * } else if (pieceHasMoved && targetPosY - curretPosY == 0
 * || targetPosX == curretPosX) {
 * return true;
 * }
 * return false;
 * }
 */