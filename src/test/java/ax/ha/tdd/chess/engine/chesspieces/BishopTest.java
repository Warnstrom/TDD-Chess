package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.Bishop;
import ax.ha.tdd.chess.engine.pieces.PieceType;

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
    private Bishop whiteBishop_1 = null;
    private Bishop whiteBishop_2 = null;
    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackBishop_1 = new Bishop(Player.BLACK, new Coordinates("c8"));
        blackBishop_2 = new Bishop(Player.BLACK, new Coordinates("f8"));
        whiteBishop_1 = new Bishop(Player.WHITE, new Coordinates("c1"));
        whiteBishop_2 = new Bishop(Player.WHITE, new Coordinates("f1"));
        gameboard.addPiece(blackBishop_1);
        gameboard.addPiece(blackBishop_2);
        gameboard.addPiece(whiteBishop_1);
        gameboard.addPiece(whiteBishop_2);
    }

    @Test
    void createTwoBishops() {
        ChessPiece actualWhitePiece = gameboard.getPiece(whiteBishop_1.getLocation());
        assertEquals(whiteBishop_1, actualWhitePiece);
        ChessPiece actualBlackPiece = gameboard.getPiece(blackBishop_1.getLocation());
        assertEquals(blackBishop_1, actualBlackPiece);
    }

    @Test
    void moveLegally() {
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
    }

    @Test
    void moveIllegally() {
        gameboard.move("c1-e8", Player.WHITE);
        assertEquals(whiteBishop_1, gameboard.getPiece(new Coordinates("c1")));
        assertEquals(null, gameboard.getPiece(new Coordinates("g8")));
    }
}
