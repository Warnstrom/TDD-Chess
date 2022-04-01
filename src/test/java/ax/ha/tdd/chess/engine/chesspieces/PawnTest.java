package ax.ha.tdd.chess.engine.chesspieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.InvalidMovementException;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.ChessPieceStub;
import ax.ha.tdd.chess.engine.pieces.PieceType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    private Chessboard gameboard;

    @BeforeEach
    void initializeBoard() {
        gameboard = new Chessboard();
    }

    
    @Test // Create two pawns and check that they created correctly
    void createTwoPawns() {
        ChessPiece whitePawn = new ChessPieceStub(PieceType.PAWN, Player.WHITE, new Coordinates("d2"));
        ChessPiece blackPawn = new ChessPieceStub(PieceType.PAWN, Player.WHITE, new Coordinates("d7"));
        gameboard.addPiece(whitePawn);
        gameboard.addPiece(blackPawn);
        ChessPiece actualWhitePiece = gameboard.getPiece(whitePawn.getLocation());
        assertEquals(whitePawn, actualWhitePiece);
        ChessPiece actualBlackPiece = gameboard.getPiece(blackPawn.getLocation());
        assertEquals(blackPawn, actualBlackPiece);
    }

    @Test // Test for pawn movement, both legal and illegal movements
    void movePawnForward() {
        ChessPiece whitePawn = new ChessPieceStub(PieceType.PAWN, Player.WHITE, new Coordinates("c2"));
        gameboard.addPiece(whitePawn);
        gameboard.move("c2-c3", Player.WHITE);
        assertEquals(new Coordinates("c3"), whitePawn.getLocation());
        assertThrows(InvalidMovementException.class, gameboard.move("c3-b3", Player.WHITE));
        assertThrows(InvalidMovementException.class, gameboard.move("c3-d3", Player.WHITE));
        assertThrows(InvalidMovementException.class, gameboard.move("c3-c5", Player.WHITE));
    }
}
