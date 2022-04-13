package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class King extends ChessPiece {
    public King(Player player, Coordinates location) {
        super(PieceType.QUEEN, player, location);
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

    private boolean locationThreatened() {
        return false;
    }

    // targetPosY < curretPosY && targetPosX == curretPosX && targetPosY + 1 ==
    // curretPosY
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        final int targetPosX = destination.getX();
        final int targetPosY = destination.getY();
        final int curretPosX = location.getX();
        final int curretPosY = location.getY();
        if (!locationThreatened()) {
            if (targetPosY > curretPosY && targetPosX == curretPosX && targetPosY - 1 == curretPosY
                    || targetPosY < curretPosY && targetPosX == curretPosX && targetPosY + 1 == curretPosY) {
                System.out.println("Top or bottom");
                return true;
            } else if (targetPosY < curretPosY && targetPosX < curretPosX
                    && Math.abs((targetPosY + 1) - curretPosY) == 0 && Math.abs((targetPosX + 1) - curretPosX) == 0) {
                System.out.println("Top left");
                return true;
            } else if (targetPosX < curretPosX && targetPosY == curretPosY
                    && Math.abs((targetPosY + 1) - curretPosY) == 0
                    || targetPosX > curretPosX && targetPosY == curretPosY
                            && Math.abs((targetPosY - 1) - curretPosY) == 0) {
                System.out.println("Left or right");
                return true;
            } else if (targetPosY > curretPosY && targetPosX < curretPosX
                    && Math.abs((targetPosY - 1) - curretPosY) == 0 && Math.abs((targetPosX + 1) - curretPosX) == 0) {
                System.out.println("Bottom left");
                return true;
            } else if (targetPosY > curretPosY && targetPosX > curretPosX
                    && Math.abs((targetPosY - 1) - curretPosY) == 0 && Math.abs((targetPosX - 1) - curretPosX) == 0) {
                System.out.println("Bottom right");
                return true;
            } else if (targetPosY < curretPosY && targetPosX > curretPosX
                    && Math.abs((targetPosY + 1) - curretPosY) == 0 && Math.abs((targetPosX - 1) - curretPosX) == 0) {
                System.out.println("Top right");
                return true;
            }
            System.out.println("Invalid movement");
            return false;
        }
        System.out.println("Invalid movement");
        return false;
    }
}