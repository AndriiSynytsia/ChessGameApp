package com.javabasicprojects.chessgame.engine.board;

import com.javabasicprojects.chessgame.engine.board.Move.NullMove;

import java.util.ArrayList;
import java.util.List;

public class MoveUtils {
    private static BoardUtils INSTANCE;

    public static final Move NULL_MOVE = new NullMove();

    public static int exchangeScore(final Move move) {
        if(move == Move.MoveFactory.getNullMove()) {
            return 1;
        }
        return move.isAttack() ?
                5 * exchangeScore(move.getBoard().getTransitionMove()) :
                exchangeScore(move.getBoard().getTransitionMove());

    }

    public static class Line {
        private final List<Integer> coordinates;

        public Line() {
            this.coordinates = new ArrayList<>();
        }

        public void addCoordinate(int coordinate) {
            this.coordinates.add(coordinate);
        }

        public List<Integer> getLineCoordinates() {
            return this.coordinates;
        }

        public boolean isEmpty() {
            return this.coordinates.isEmpty();
        }
    }
}
