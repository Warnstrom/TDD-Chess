package ax.ha.tdd.chess.engine.pieces;

import java.util.ArrayList;
import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class King extends ChessPiece {
    public King(Player player, Coordinates location) {
        super(PieceType.KING, player, location);
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

    private boolean locationNotThreatened(final Chessboard board, final Coordinates destination,
            final Coordinates location) {
        ArrayList<ChessPiece> list = new ArrayList<ChessPiece>();

        board.activePieces.forEach(v -> {
            if (!v.getPieceType().equals(PieceType.KING)) {
                if (v.canMove(board, destination))
                    list.add(v);
            }
        });
        return list.isEmpty();
    }

    @Override
    public boolean canMove(final Chessboard chessboard, final Coordinates destination) {
        final int targetPosX = destination.getX();
        final int targetPosY = destination.getY();
        final int curretPosX = location.getX();
        final int curretPosY = location.getY();
        if (locationNotThreatened(chessboard, destination, location)) {
            if (targetPosY > curretPosY && targetPosX == curretPosX && targetPosY - 1 == curretPosY
                    || targetPosY < curretPosY && targetPosX == curretPosX && targetPosY + 1 == curretPosY) {
                return true;
            } else if (targetPosY < curretPosY && targetPosX < curretPosX
                    && Math.abs((targetPosY + 1) - curretPosY) == 0 && Math.abs((targetPosX + 1) - curretPosX) == 0) {
                return true;
            } else if (targetPosX < curretPosX && targetPosY == curretPosY
                    && Math.abs((targetPosY + 1) - curretPosY) == 0
                    || targetPosX > curretPosX && targetPosY == curretPosY
                            && Math.abs((targetPosY - 1) - curretPosY) == 0) {
                return true;
            } else if (targetPosY > curretPosY && targetPosX < curretPosX
                    && Math.abs((targetPosY - 1) - curretPosY) == 0 && Math.abs((targetPosX + 1) - curretPosX) == 0) {
                return true;
            } else if (targetPosY > curretPosY && targetPosX > curretPosX
                    && Math.abs((targetPosY - 1) - curretPosY) == 0 && Math.abs((targetPosX - 1) - curretPosX) == 0) {
                return true;
            } else if (targetPosY < curretPosY && targetPosX > curretPosX
                    && Math.abs((targetPosY + 1) - curretPosY) == 0 && Math.abs((targetPosX - 1) - curretPosX) == 0) {
                return true;
            }
            return false;
        }
        return false;
    }
}