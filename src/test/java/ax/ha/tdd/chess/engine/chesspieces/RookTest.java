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
public class RookTest {
    private Chessboard gameboard;
    private Rook blackRook_1 = null;
    private Rook blackRook_2 = null;
    private Rook whiteRook_1 = null;
    private Rook whiteRook_2 = null;

    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackRook_1 = new Rook(Player.BLACK, new Coordinates("a8"));
        blackRook_2 = new Rook(Player.BLACK, new Coordinates("h8"));
        whiteRook_1 = new Rook(Player.WHITE, new Coordinates("h1"));
        whiteRook_2 = new Rook(Player.WHITE, new Coordinates("a1"));
        gameboard.addPiece(blackRook_1);
        gameboard.addPiece(blackRook_2);
        gameboard.addPiece(whiteRook_1);
        gameboard.addPiece(whiteRook_2);
    }

    @Test
    void moveLegallyForwardAndBackwards() {
        gameboard.move("h1-h8", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h1")));
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("h8")));
        gameboard.move("h8-h1", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h8")));
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("f1")));
    }

    @Test
    void moveLegallyLeftAndRight() {
        gameboard.move("h1-a8", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h1")));
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("a8")));
        gameboard.move("h4-a4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h4")));
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("a4")));
    }

    @Test
    void moveIllegallyOutsideXYAxis() {
        gameboard.move("h1-f4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h1")));
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("f4")));
        gameboard.move("h4-d7", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h4")));
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("d7")));
    }
}
