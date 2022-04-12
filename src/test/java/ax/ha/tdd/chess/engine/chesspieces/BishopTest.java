package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.InvalidMovementException;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.Bishop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * 
 * We use null to check other locations where other pieces should be
 * but we only add specific pieces to the gameboard
 * so the rest of the board will be empty or null
 */
public class BishopTest {
    private Chessboard gameboard;
    private Bishop blackBishop_1 = null;
    private Bishop blackBishop_2 = null;
    private Bishop blackBishop_3 = null;
    private Bishop blackBishop_4 = null;
    private Bishop blackBishop_5 = null;
    private Bishop whiteBishop_1 = null;
    private Bishop whiteBishop_2 = null;
    private Bishop whiteBishop_3 = null;
    private Bishop whiteBishop_4 = null;
    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackBishop_1 = new Bishop(Player.BLACK, new Coordinates("c8"));
        blackBishop_2 = new Bishop(Player.BLACK, new Coordinates("f8"));
        blackBishop_3 = new Bishop(Player.BLACK, new Coordinates("f4"));
        blackBishop_4 = new Bishop(Player.BLACK, new Coordinates("a6"));
        blackBishop_5 = new Bishop(Player.BLACK, new Coordinates("a7"));
        whiteBishop_1 = new Bishop(Player.WHITE, new Coordinates("c1"));
        whiteBishop_2 = new Bishop(Player.WHITE, new Coordinates("f1"));
        whiteBishop_3 = new Bishop(Player.WHITE, new Coordinates("e2"));
        whiteBishop_4 = new Bishop(Player.WHITE, new Coordinates("g1"));
        gameboard.addPiece(blackBishop_1);
        gameboard.addPiece(blackBishop_2);
        gameboard.addPiece(blackBishop_3);
        gameboard.addPiece(blackBishop_4);
        gameboard.addPiece(blackBishop_5);
        gameboard.addPiece(whiteBishop_1);
        gameboard.addPiece(whiteBishop_2);
        gameboard.addPiece(whiteBishop_3);
        gameboard.addPiece(whiteBishop_4);
    }

    @Test
    void createTwoBishops() {
        ChessPiece actualWhitePiece = gameboard.getPiece(whiteBishop_1.getLocation());
        assertEquals(whiteBishop_1, actualWhitePiece);
        ChessPiece actualBlackPiece = gameboard.getPiece(blackBishop_1.getLocation());
        assertEquals(blackBishop_1, actualBlackPiece);
    }

    @Test
    void moveLegally() throws InvalidMovementException {
        gameboard.move("c1-f4", Player.WHITE);
        gameboard.move("f1-h3", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("c1")));
        assertEquals(whiteBishop_1, gameboard.getPiece(new Coordinates("f4")));
        assertEquals(null, gameboard.getPiece(new Coordinates("f1")));
        assertEquals(whiteBishop_2, gameboard.getPiece(new Coordinates("h3")));
        gameboard.move("f4-d6", Player.WHITE);
        gameboard.move("h3-f5", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("f4")));
        assertEquals(whiteBishop_1, gameboard.getPiece(new Coordinates("d6")));
        assertEquals(null, gameboard.getPiece(new Coordinates("h3")));
        assertEquals(whiteBishop_2, gameboard.getPiece(new Coordinates("f5")));
        gameboard.move("e2-g4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("e2")));
        assertEquals(whiteBishop_3, gameboard.getPiece(new Coordinates("g4")));
    }

    @Test
    void moveIllegally() throws InvalidMovementException {
        gameboard.move("c1-e8", Player.WHITE);
        assertEquals(whiteBishop_1, gameboard.getPiece(new Coordinates("c1")));
        assertEquals(null, gameboard.getPiece(new Coordinates("g8")));
        gameboard.move("c1-c8", Player.WHITE);
        assertEquals(whiteBishop_1, gameboard.getPiece(new Coordinates("c1")));
        assertEquals(null, gameboard.getPiece(new Coordinates("c8")));
        gameboard.move("c1-f5", Player.WHITE);
        assertEquals(whiteBishop_1, gameboard.getPiece(new Coordinates("c1")));
        assertEquals(null, gameboard.getPiece(new Coordinates("f5")));
    }

    @Test
    void bishopCapture() throws InvalidMovementException {
        assertEquals(blackBishop_3, gameboard.getPiece(new Coordinates("f4")));
        gameboard.move("c1-f4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("c1")));
        assertEquals(whiteBishop_1, gameboard.getPiece(new Coordinates("f4")));
        assertEquals(blackBishop_5, gameboard.getPiece(new Coordinates("a7")));
        gameboard.move("g1-a7", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("g1")));
        assertEquals(whiteBishop_4, gameboard.getPiece(new Coordinates("a7")));
    }
}
