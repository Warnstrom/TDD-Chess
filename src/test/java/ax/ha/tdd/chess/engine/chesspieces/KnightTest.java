package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.InvalidMovementException;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.Knight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {
    private Chessboard gameboard;
    private Knight blackKnight_1 = null;
    private Knight blackKnight_2 = null;
    private Knight blackKnight_3 = null;
    private Knight blackKnight_4 = null;
    private Knight whiteKnight_1 = null;
    private Knight whiteKnight_2 = null;

    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackKnight_1 = new Knight(Player.BLACK, new Coordinates("b8"));
        blackKnight_2 = new Knight(Player.BLACK, new Coordinates("g8"));
        blackKnight_3 = new Knight(Player.BLACK, new Coordinates("a3"));
        blackKnight_4 = new Knight(Player.BLACK, new Coordinates("f3"));
        whiteKnight_1 = new Knight(Player.WHITE, new Coordinates("b1"));
        whiteKnight_2 = new Knight(Player.WHITE, new Coordinates("g1"));
        gameboard.addPiece(blackKnight_1);
        gameboard.addPiece(blackKnight_2);
        gameboard.addPiece(blackKnight_3);
        gameboard.addPiece(blackKnight_4);
        gameboard.addPiece(whiteKnight_1);
        gameboard.addPiece(whiteKnight_2);
    }
    
    @Test
    void moveLegally() throws InvalidMovementException {
        gameboard.move("b1-c3",Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("b1")));
        assertEquals(whiteKnight_1, gameboard.getPiece(new Coordinates("c3")));
        gameboard.move("g8-f6",Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("g8")));
        assertEquals(blackKnight_2, gameboard.getPiece(new Coordinates("f6")));

    }

    @Test
    void moveIllegally() throws InvalidMovementException {
        gameboard.move("b1-b3",Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("b3")));
        assertEquals(whiteKnight_1, gameboard.getPiece(new Coordinates("b1")));
        gameboard.move("g8-g6",Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("g6")));
        assertEquals(blackKnight_2, gameboard.getPiece(new Coordinates("g8")));
    }
    @Test 
    void knightCapture() throws InvalidMovementException {
        assertEquals(blackKnight_3, gameboard.getPiece(new Coordinates("a3")));
        gameboard.move("b1-a3",Player.WHITE);
        assertEquals(whiteKnight_1, gameboard.getPiece(new Coordinates("a3")));
        assertEquals(null, gameboard.getPiece(new Coordinates("b1")));
        assertEquals(blackKnight_4, gameboard.getPiece(new Coordinates("f3")));
        gameboard.move("g1-f3",Player.WHITE);
        assertEquals(whiteKnight_2, gameboard.getPiece(new Coordinates("f3")));
        assertEquals(null, gameboard.getPiece(new Coordinates("g1")));
    }
}
