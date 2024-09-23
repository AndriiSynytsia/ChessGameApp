package com.javabasicprojects.chessgame.engine.player;

import com.javabasicprojects.chessgame.engine.board.Board;
import com.javabasicprojects.chessgame.engine.board.Move;

public class MoveTransition {

    private final Board fromBoard;
    private final Board toBoard;
    private final Move transitionMove;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board fromBoard, Board toBoard, final Move transitionMove, final MoveStatus moveStatus) {
        this.fromBoard = fromBoard;
        this.toBoard = toBoard;
        this.transitionMove = transitionMove;
        this.moveStatus = moveStatus;
    }

    public Board getFromBoard() {
        return this.fromBoard;
    }

    public Board getToBoard() {
        return toBoard;
    }

    public Move getTransitionMove() {
        return transitionMove;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }
}
