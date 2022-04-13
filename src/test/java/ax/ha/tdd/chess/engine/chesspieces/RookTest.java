package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.InvalidMovementException;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.Rook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class RookTest {
    private Chessboard gameboard;
    private Rook blackRook_1 = null;
    private Rook blackRook_2 = null;
    private Rook blackRook_3 = null;
    private Rook whiteRook_1 = null;
    private Rook whiteRook_2 = null;
    private Rook whiteRook_3 = null;

    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackRook_1 = new Rook(Player.BLACK, new Coordinates("a8"));
        blackRook_2 = new Rook(Player.BLACK, new Coordinates("e8"));
        blackRook_3 = new Rook(Player.BLACK, new Coordinates("g2"));
        whiteRook_1 = new Rook(Player.WHITE, new Coordinates("e2"));
        whiteRook_2 = new Rook(Player.WHITE, new Coordinates("a1"));
        whiteRook_3 = new Rook(Player.WHITE, new Coordinates("h1"));
        gameboard.addPiece(blackRook_1);
        gameboard.addPiece(blackRook_2);
        gameboard.addPiece(blackRook_3);
        gameboard.addPiece(whiteRook_1);
        gameboard.addPiece(whiteRook_2);
        gameboard.addPiece(whiteRook_3);
    }

    @Test
    void moveLegallyForwardAndBackwards() throws InvalidMovementException {
        gameboard.move("h1-h8", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h1")));
        assertEquals(whiteRook_3, gameboard.getPiece(new Coordinates("h8")));
        gameboard.move("h8-h1", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h8")));
        assertEquals(whiteRook_3, gameboard.getPiece(new Coordinates("h1")));
        gameboard.move("h1-h4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h1")));
        assertEquals(whiteRook_3, gameboard.getPiece(new Coordinates("h4")));
    }

    @Test
    void moveLegallyLeftAndRight() throws InvalidMovementException {
        gameboard.move("e2-a2", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("h2")));
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("a2")));
        gameboard.move("a1-f1", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("a1")));
        assertEquals(whiteRook_2, gameboard.getPiece(new Coordinates("f1")));
        gameboard.move("g2-d2", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("g2")));
        assertEquals(blackRook_3, gameboard.getPiece(new Coordinates("d2")));
    }

    @Test
    void moveIllegallyOutsideXYAxis() throws InvalidMovementException {
        gameboard.move("e2-f4", Player.WHITE);
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("e2")));
        assertEquals(null, gameboard.getPiece(new Coordinates("f4")));
        gameboard.move("a8-d7", Player.WHITE);
        assertEquals(blackRook_1, gameboard.getPiece(new Coordinates("a8")));
        assertEquals(null, gameboard.getPiece(new Coordinates("d7")));
    }

    @Test
    void validCollisionMoves() throws InvalidMovementException {
        gameboard.move("e2-h4", Player.WHITE);
        assertEquals(whiteRook_3, gameboard.getPiece(new Coordinates("h1")));
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("e2")));
        assertEquals(null, gameboard.getPiece(new Coordinates("h4")));
    }

    @Test
    void rookCapture() throws InvalidMovementException {
        assertEquals(blackRook_1, gameboard.getPiece(new Coordinates("a8")));
        gameboard.move("a1-a8", Player.WHITE);
        assertEquals(whiteRook_2, gameboard.getPiece(new Coordinates("a8")));
        assertEquals(null, gameboard.getPiece(new Coordinates("a1")));

        assertEquals(blackRook_2, gameboard.getPiece(new Coordinates("e8")));
        gameboard.move("e2-e8", Player.WHITE);
        assertEquals(whiteRook_1, gameboard.getPiece(new Coordinates("e8")));
        assertEquals(null, gameboard.getPiece(new Coordinates("e2")));
    }
}
