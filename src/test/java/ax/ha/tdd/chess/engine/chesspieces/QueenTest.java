package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.InvalidMovementException;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.Queen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class QueenTest {
    private Chessboard gameboard;
    private Queen blackQueen_1 = null;
    private Queen whiteQueen_1 = null;
    private Pawn blackPawn_1 = null;
    private Pawn blackPawn_2 = null;
    private Pawn blackPawn_3 = null;
    private Pawn blackPawn_4 = null;

    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackPawn_1 = new Pawn(Player.BLACK, new Coordinates("e4"));
        blackPawn_2 = new Pawn(Player.BLACK, new Coordinates("g4"));
        blackPawn_3 = new Pawn(Player.BLACK, new Coordinates("c4"));
        blackPawn_4 = new Pawn(Player.BLACK, new Coordinates("f7"));
        blackQueen_1 = new Queen(Player.BLACK, new Coordinates("e8"));
        whiteQueen_1 = new Queen(Player.WHITE, new Coordinates("e1"));
        gameboard.addPiece(blackQueen_1);
        gameboard.addPiece(blackPawn_1);
        gameboard.addPiece(blackPawn_2);
        gameboard.addPiece(blackPawn_3);
        gameboard.addPiece(blackPawn_4);
        gameboard.addPiece(whiteQueen_1);
    }

    @Test
    void validMovementLikeRook() throws InvalidMovementException {
        gameboard.move("e8-e5", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("e8")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("e5")));
        gameboard.move("e5-b5", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("h5")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("b5")));
        gameboard.move("b5-b1", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("b5")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("b1")));
        assertEquals(whiteQueen_1, gameboard.getPiece(new Coordinates("e1")));
        gameboard.move("b1-e1", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("b1")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("e1")));
    }

    @Test
    void validMovementLikeBishop() throws InvalidMovementException {
        gameboard.move("e8-a4", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("e8")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("a4")));
        gameboard.move("a4-d1", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("a4")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("d1")));
        gameboard.move("d1-f3", Player.BLACK);
        assertEquals(null, gameboard.getPiece(new Coordinates("d1")));
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("f3")));
    }

    @Test
    void moveIllegally() throws InvalidMovementException {
        gameboard.move("e8-g7", Player.BLACK);
        gameboard.move("e8-d6", Player.BLACK);
        gameboard.move("e8-f2", Player.BLACK);
        gameboard.move("e8-b7", Player.BLACK);
        gameboard.move("e8-f5", Player.BLACK);
        assertEquals(blackQueen_1, gameboard.getPiece(new Coordinates("e8")));
        assertEquals(null, gameboard.getPiece(new Coordinates("g7")));
        assertEquals(null, gameboard.getPiece(new Coordinates("d6")));
        assertEquals(null, gameboard.getPiece(new Coordinates("f2")));
        assertEquals(null, gameboard.getPiece(new Coordinates("b7")));
        assertEquals(null, gameboard.getPiece(new Coordinates("f5")));
    }

    @Test
    void queenCapture() throws InvalidMovementException {
        assertEquals(blackPawn_1, gameboard.getPiece(new Coordinates("e4")));
        assertEquals(blackPawn_2, gameboard.getPiece(new Coordinates("g4")));
        assertEquals(blackPawn_3, gameboard.getPiece(new Coordinates("c4")));
        assertEquals(blackPawn_4, gameboard.getPiece(new Coordinates("f7")));
        gameboard.move("e1-e4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("e1")));
        assertEquals(whiteQueen_1, gameboard.getPiece(new Coordinates("e4")));
        gameboard.move("e4-f5", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("e4")));
        assertEquals(whiteQueen_1, gameboard.getPiece(new Coordinates("f5")));
        gameboard.move("f5-g4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("f5")));
        assertEquals(whiteQueen_1, gameboard.getPiece(new Coordinates("g4")));
        gameboard.move("g4-c4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("g4")));
        assertEquals(whiteQueen_1, gameboard.getPiece(new Coordinates("c4")));
        gameboard.move("c4-f7", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("c4")));
        assertEquals(whiteQueen_1, gameboard.getPiece(new Coordinates("f7")));
    }
}
