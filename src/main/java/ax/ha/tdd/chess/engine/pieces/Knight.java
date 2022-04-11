package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Knight extends ChessPiece {

    public Knight(Player player, Coordinates location) {
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

        if (curretPosY + 2 == targetPosY && (curretPosX + 1 == targetPosX || curretPosX - 1 == targetPosX)) {
            return true;
        } else if (curretPosY - 2 == targetPosY && (curretPosX + 1 == targetPosX || curretPosX - 1 == targetPosX)) {
            return true;
        } else if (curretPosX + 2 == targetPosX && (curretPosY + 1 == targetPosY || curretPosY - 1 == targetPosY)) {
            return true;
        }
        return (curretPosX + 2 == targetPosX && (curretPosY + 1 == targetPosY || curretPosY - 1 == targetPosY));
    }
}