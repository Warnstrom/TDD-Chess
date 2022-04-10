package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.PieceType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class QueenTest {
    private Chessboard gameboard;
    private Queen blackQueen_1 = null;
    private Queen whiteQueen_1 = null;

    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackQueen_1 = new Queen(Player.BLACK, new Coordinates("e8"));
        whiteQueen_1 = new Queen(Player.WHITE, new Coordinates("e1"));
        gameboard.addPiece(blackQueen_1);
        gameboard.addPiece(whiteQueen_1);
    }

    @Test
    void validMovementLikeRook() {
        gameboard.move("e8-e4", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("e8")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("e4")));
        gameboard.move("e4-b4", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("e4")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("b4")));
    }

    @Test
    void validMovementLikeBishop() {
        gameboard.move("e8-a4", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("e8")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("a4")));
    }

    @Test
    void moveIllegally() {
        gameboard.move("e8-g6", Player.BLACK);
        gameboard.move("e8-d6", Player.BLACK);
        gameboard.move("e8-f2", Player.BLACK);
        gameboard.move("e8-b7", Player.BLACK);
        gameboard.move("e8-f5", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("e8")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("a4")));
    }
}
