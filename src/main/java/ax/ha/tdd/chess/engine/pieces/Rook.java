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

    private boolean validLocation(Chessboard board, final int x, final int y) {
        //System.out.println(board.getPiece(new Coordinates(x, y)) == null);
        //System.out.println(board.getPiece(new Coordinates(x, y)));
        return board.getPiece(new Coordinates(x, y)) == null;
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
        if (curretPosY < targetPosY && curretPosX == targetPosX) {
            System.out.println("Test 1");
            for (int i = curretPosY; i <= targetPosY; i++) {
                if (validLocation(chessboard, curretPosX, i)) {
                    return true;
                }
            }
        } else if (targetPosY < curretPosY && curretPosX == targetPosX) {
            System.out.println("Test 2");
            for (int i = curretPosY; i >= targetPosY; i--) {
                System.out.println(validLocation(chessboard, i, curretPosY));
                if (validLocation(chessboard, curretPosX, i)) {
                    return true;
                }
            }
        } else if (targetPosX < curretPosX && curretPosY == targetPosY) {
            System.out.println("Test 3");
            for (int i = curretPosX; i >= targetPosX; i--) {
                if (validLocation(chessboard, i, curretPosY)) {
                    return true;
                }
            }
        } else if (curretPosX < targetPosX && curretPosY == targetPosY) {
            System.out.println("Test 4");
            for (int i = curretPosX; i <= targetPosX; i++) {
                if (validLocation(chessboard, i, curretPosY)) {
                    return true;
                }
            }
        }
        return false;
    }
}