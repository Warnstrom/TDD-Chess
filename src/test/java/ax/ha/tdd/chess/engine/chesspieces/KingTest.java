package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.PieceType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {
    private Chessboard gameboard;
    private King blackKing_1 = null;
    private King whiteKing_1 = null;

    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackKing_1 = new King(Player.BLACK, new Coordinates("d8"));
        whiteKing_1 = new King(Player.WHITE, new Coordinates("d1"));
        gameboard.addPiece(blackKing_1);
        gameboard.addPiece(whiteKing_1);
    }

    @Test
    void moveLegally() {
        gameboard.move("d8-d7", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("d8")));
        assertEquals(blackKing_1, gameboard.getPiece(new Coordinates("d7")));
        gameboard.move("d7-e6", Player.BLACK);
        gameboard.move("e6-f5", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("e6")));
        assertEquals(null, gameboard.getPiece(new Coordinates("d7")));
        assertEquals(blackKing_1, gameboard.getPiece(new Coordinates("f5")));
    }

    @Test
    void moveIllegally() {
        gameboard.move("d8-d6", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("d6")));
        assertEquals(blackKing_1, gameboard.getPiece(new Coordinates("d8")));
        gameboard.move("d1-b2", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("b2")));
        assertEquals(whiteKing_1, gameboard.getPiece(new Coordinates("d1")));
    }
}