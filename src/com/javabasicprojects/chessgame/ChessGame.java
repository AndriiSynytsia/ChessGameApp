package com.javabasicprojects.chessgame;

import com.javabasicprojects.chessgame.engine.board.Board;
import com.javabasicprojects.chessgame.gui.Table;

public class ChessGame {
    public static void main(String[] args) {

        Board board = Board.createStandardBoard();

        System.out.println(board);

        Table table = new Table();
    }
}
