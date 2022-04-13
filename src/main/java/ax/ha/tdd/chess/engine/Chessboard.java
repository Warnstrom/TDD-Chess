package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.ChessPieceStub;
import ax.ha.tdd.chess.engine.pieces.PieceType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Chessboard implements Iterable<ChessPiece[]> {
    // This could just as easily be replaced with a List or Set,
    // since the ChessPieces right now keep track of their own location.
    // Feel free to change this however you like
    // [y][x]
    private final ChessPiece[][] board = new ChessPiece[8][8];
    public Map<Coordinates, ChessPiece> activePieces = new HashMap<Coordinates, ChessPiece>(); 

    public static Chessboard startingBoard() {
        final Chessboard chessboard = new Chessboard();
        chessboard.withMirroredPiece(PieceType.PAWN, List.of(0, 1, 2, 3, 4, 5, 6, 7), 1)
                .withMirroredPiece(PieceType.ROOK, List.of(0, 7), 0)
                .withMirroredPiece(PieceType.KNIGHT, List.of(1, 6), 0)
                .withMirroredPiece(PieceType.BISHOP, List.of(2, 5), 0)
                .withMirroredPiece(PieceType.QUEEN, List.of(3), 0)
                .withMirroredPiece(PieceType.KING, List.of(4), 0);
        return chessboard;
    }

    public ChessPiece getPiece(final Coordinates coordinates) {
        return board[coordinates.getY()][coordinates.getX()];
    }

    public void addPiece(final ChessPiece chessPiece) {
        this.activePieces.put(new Coordinates(chessPiece.getLocation().getX(), chessPiece.getLocation().getY()), chessPiece);
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = chessPiece;
    }

    public void removePiece(final ChessPiece chessPiece) {
        this.activePieces.replace(new Coordinates(chessPiece.getLocation().getX(), chessPiece.getLocation().getY()), null);
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = null;
    }

    /*
     * private boolean isLocationOccupied(final ChessPiece chessPiece) {
     * return
     * board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] ==
     * null ? true : false;
     * }
     */

    public boolean move(final String move, final Player player) throws InvalidMovementException {
        if (move.matches("([a-h][1-8]-[a-h][1-8])")) {

            String[] piecemoves = move.split("-");
            String currentPosCoord = piecemoves[0];
            String targetPosCord = piecemoves[1];
            Coordinates targetPos = new Coordinates(targetPosCord);
            ChessPiece targetPiece = getPiece(new Coordinates(targetPosCord));
            ChessPiece currentPiece = getPiece(new Coordinates(currentPosCoord));
            System.out.println(targetPos + " " + new Coordinates(currentPosCoord));
            if (targetPiece != null && targetPiece.getPlayer() != player && targetPiece.getPieceType() != PieceType.KING) {
                removePiece(targetPiece);
                currentPiece.move(this, targetPos);
            } else if (targetPiece == null) {
                currentPiece.move(this, targetPos);
            }
            return false;
        }
        throw new InvalidMovementException("Movement was outside the board");
    }

    // targetPiece.getPlayer() != currentPiece.getPlayer() &&
    // !targetPiece.getPieceType().equals(PieceType.KING)
    /**
     * Helper method to initialize chessboard with {@link ChessPieceStub}.
     * Basically mirrors all added pieces for both players.
     * When all pieces has been implemented, this should be replaced with the proper
     * implementations.
     *
     * @param pieceType    pieceType
     * @param xCoordinates xCoordinates
     * @param yCoordinate  yCoordinateOffset
     * @return itself, like a builder pattern
     */
    private Chessboard withMirroredPiece(final PieceType pieceType, final List<Integer> xCoordinates,
            final int yCoordinate) {
        xCoordinates.forEach(xCoordinate -> {
            addPiece(new ChessPieceStub(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
            addPiece(new ChessPieceStub(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
        });
        return this;
    }

    @Override
    public Iterator<ChessPiece[]> iterator() {
        return List.of(board).iterator();
    }
}
