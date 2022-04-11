package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Queen extends ChessPiece {
    final Bishop BISHOP_MOVE = new Bishop(this.player, this.location);
    final Rook ROOK_MOVE = new Rook(this.player, this.location);
    public Queen(Player player, Coordinates location) {
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
        return (BISHOP_MOVE.canMove(chessboard, destination) || ROOK_MOVE.canMove(chessboard, destination));
    }
}