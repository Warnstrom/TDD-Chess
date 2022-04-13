package ax.ha.tdd.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private boolean locationThreatened(final Chessboard board, final Coordinates destination,
            final Coordinates location) {
        ArrayList<ChessPiece> threateningPieces = new ArrayList<ChessPiece>();
        //System.out.println(board.activePieces.size() + " " + board.activePieces);
        Boolean test = board.activePieces.get(new Coordinates("g2")).canMove(board, destination);
        System.out.println(test);
        List<ChessPiece> list = board.activePieces.entrySet()
                .stream()
                .filter(p -> p.getValue().getPieceType().equals(PieceType.KING) && p.getValue().canMove(board, destination))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        System.out.println(list.size());
        /*
         * board.activePieces.forEach((k, v) -> {
         * // System.out.println(k + " " + v);
         * if (v != this && v.canMove(board, destination)) {
         * System.out.println(v);
         * return;
         * // threateningPieces.add(v);
         * }
         * System.out.println(board.activePieces + " " + board.activePieces.size() +
         * "\n" + threateningPieces);
         * });
         */
        return threateningPieces.isEmpty();
    }

    @Override
    public boolean canMove(final Chessboard chessboard, final Coordinates destination) {
        System.out.println("King called" + this.player);
        final int targetPosX = destination.getX();
        final int targetPosY = destination.getY();
        final int curretPosX = location.getX();
        final int curretPosY = location.getY();
        if (!locationThreatened(chessboard, destination, location)) {
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