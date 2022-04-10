package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.InvalidMovementException;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.Pawn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    private Chessboard gameboard;
    private Pawn blackPawn_1 = null;
    private Pawn blackPawn_2 = null;
    private Pawn blackPawn_3 = null;
    private Pawn whitePawn_1 = null;
    private Pawn whitePawn_2 = null;
    private Pawn whitePawn_3 = null;
    private Pawn whitePawn_4 = null;
    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
        blackPawn_1 = new Pawn(Player.BLACK, new Coordinates("f3"));
        blackPawn_2 = new Pawn(Player.BLACK, new Coordinates("d3"));
        blackPawn_3 = new Pawn(Player.BLACK, new Coordinates("f3"));
        whitePawn_1 = new Pawn(Player.WHITE, new Coordinates("c2"));
        whitePawn_2 = new Pawn(Player.WHITE, new Coordinates("a2"));
        whitePawn_3 = new Pawn(Player.WHITE, new Coordinates("g2"));
        whitePawn_4 = new Pawn(Player.WHITE, new Coordinates("c6"));
        gameboard.addPiece(whitePawn_1);
        gameboard.addPiece(whitePawn_2);
        gameboard.addPiece(whitePawn_3);
        gameboard.addPiece(whitePawn_4);
        gameboard.addPiece(blackPawn_1);
        gameboard.addPiece(blackPawn_2);
    }

    @Test // Create two pawns and check that they created correctly
    void createTwoPawns() {
        ChessPiece actualWhitePiece = gameboard.getPiece(whitePawn_1.getLocation());
        assertEquals(whitePawn_1, actualWhitePiece);
        ChessPiece actualBlackPiece = gameboard.getPiece(blackPawn_1.getLocation());
        assertEquals(blackPawn_1, actualBlackPiece);
    }

    @Test // Test for legal pawn movements
    void movePawnLegally() throws InvalidMovementException {
        gameboard.move("c2-c4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("c2")));
        assertEquals(whitePawn_1, gameboard.getPiece(new Coordinates("c4")));
        gameboard.move("c4-c5", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("c4")));
        assertEquals(whitePawn_1, gameboard.getPiece(new Coordinates("c5")));
        gameboard.move("c5-c6", Player.WHITE);
        assertEquals(whitePawn_1, gameboard.getPiece(new Coordinates("c5")));
        assertEquals(whitePawn_1, gameboard.getPiece(new Coordinates("c6")));
        gameboard.move("a2-a3", Player.WHITE);
        gameboard.move("g2-g4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("a2")));
        assertEquals(whitePawn_2, gameboard.getPiece(new Coordinates("a3")));
        assertEquals(null, gameboard.getPiece(new Coordinates("g2")));
        assertEquals(whitePawn_3, gameboard.getPiece(new Coordinates("g4")));

    }

    @Test 
    // Test for illegal pawn movements, the piece should just not move
    void movePawnIllegally() throws InvalidMovementException {
        gameboard.move("c2-b2", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("b2")));
        assertEquals(whitePawn_1, gameboard.getPiece(new Coordinates("c2")));
        gameboard.move("c2-d2", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("d2")));
        assertEquals(whitePawn_1, gameboard.getPiece(new Coordinates("c2")));
        gameboard.move("c2-c1", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("c1")));
        assertEquals(whitePawn_1, gameboard.getPiece(new Coordinates("c2")));
        gameboard.move("a2-b4", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("b4")));
        assertEquals(whitePawn_2, gameboard.getPiece(new Coordinates("a2")));
        gameboard.move("a2-a5", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("a5")));
        assertEquals(whitePawn_2, gameboard.getPiece(new Coordinates("a2")));
    }

    @Test
    void validCapture() throws InvalidMovementException {
        assertEquals(blackPawn_2, gameboard.getPiece(new Coordinates("d3")));
        gameboard.move("c2-d3", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("c2")));
        assertEquals(whitePawn_1, gameboard.getPiece(new Coordinates("d3")));
        assertEquals(blackPawn_3, gameboard.getPiece(new Coordinates("f3")));
        gameboard.move("g2-f3", Player.WHITE);
        assertEquals(null, gameboard.getPiece(new Coordinates("g2")));
        assertEquals(whitePawn_3, gameboard.getPiece(new Coordinates("f3")));
    }
}
