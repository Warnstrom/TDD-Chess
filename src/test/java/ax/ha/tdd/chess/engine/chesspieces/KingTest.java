package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.InvalidMovementException;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.Bishop;
import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.Rook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {
    private Chessboard gameboard;
    private King blackKing_1 = null;
    private Rook blackRook_1 = null;
    private Rook blackRook_2 = null;
    private Bishop blackBishop_1 = null;
    private King whiteKing_1 = null;
    private King whiteKing_2 = null;

    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackKing_1 = new King(Player.BLACK, new Coordinates("d8"));
        blackRook_1 = new Rook(Player.BLACK, new Coordinates("g2"));
        blackRook_2 = new Rook(Player.BLACK, new Coordinates("g3"));
        blackBishop_1 = new Bishop(Player.BLACK, new Coordinates("c7"));
        whiteKing_1 = new King(Player.WHITE, new Coordinates("e1"));
        whiteKing_2 = new King(Player.WHITE, new Coordinates("e4"));
        gameboard.addPiece(blackKing_1);
        gameboard.addPiece(blackRook_1);
        gameboard.addPiece(blackBishop_1);
        gameboard.addPiece(blackRook_2);
        gameboard.addPiece(whiteKing_1);
        gameboard.addPiece(whiteKing_2);
    }

    @Test
    void moveLegally() throws InvalidMovementException {
        gameboard.move("d8-d7", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("d8")));
        assertEquals(blackKing_1, gameboard.getPiece(new Coordinates("d7")));
        gameboard.move("d7-e6", Player.BLACK);
        gameboard.move("e6-f5", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("e6")));
        assertEquals(null, gameboard.getPiece(new Coordinates("d7")));
        assertEquals(blackKing_1, gameboard.getPiece(new Coordinates("f5")));
        gameboard.move("f5-e6", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("f5")));
        assertEquals(blackKing_1, gameboard.getPiece(new Coordinates("e6")));
    }

    @Test
    void moveIllegally() throws InvalidMovementException {
        gameboard.move("d8-d6", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("d6")));
        assertEquals(blackKing_1, gameboard.getPiece(new Coordinates("d8")));
        gameboard.move("e1-b2", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("b2")));
        assertEquals(whiteKing_1, gameboard.getPiece(new Coordinates("e1")));
    }

    @Test
    void moveToThreatenedLocation() throws InvalidMovementException {
        gameboard.move("e1-d2", Player.WHITE);
        assertEquals(whiteKing_1, gameboard.getPiece(new Coordinates("e1")));
        assertEquals(null, gameboard.getPiece(new Coordinates("d2")));
        gameboard.move("e4-e5", Player.WHITE);
        assertEquals(whiteKing_1, gameboard.getPiece(new Coordinates("e4")));
        assertEquals(null, gameboard.getPiece(new Coordinates("e5")));
        gameboard.move("e4-e3", Player.WHITE);
        assertEquals(whiteKing_1, gameboard.getPiece(new Coordinates("e4")));
        assertEquals(null, gameboard.getPiece(new Coordinates("e3")));
    }
}
