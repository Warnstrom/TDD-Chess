package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Bishop extends ChessPiece {

    public Bishop(Player player, Coordinates location) {
        super(PieceType.BISHOP, player, location);
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
        for (int i = 1; i < Math.abs(curretPosX - targetPosX); i++) {
            if (chessboard.getPiece(new Coordinates(curretPosX + i * (Integer.compare(0, curretPosX - targetPosX)),
            curretPosY + i * (Integer.compare(0, curretPosY - targetPosY)))) != null) {
                return false;
            }
        }

        return (chessboard.getPiece(destination) == null || !chessboard.getPiece(destination).getPlayer().equals(player))
                && Math.abs(curretPosX - targetPosX) == Math.abs(curretPosY - targetPosY);
    }
}