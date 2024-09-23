package com.javabasicprojects.chessgame.tests;

import com.javabasicprojects.chessgame.engine.board.Board;
import com.javabasicprojects.chessgame.engine.board.Move;
import com.javabasicprojects.chessgame.engine.board.Move.PawnMove;
import com.javabasicprojects.chessgame.engine.pieces.Piece;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void testPawnSingleMove() {
        Board board = Board.createStandardBoard();
        Piece whitePawn = board.getPiece(52);
        Collection<Move> legalMoves = whitePawn.calculateLegalMoves(board);

        assertTrue(legalMoves.contains(new PawnMove(board, whitePawn, 44)));
    }

}