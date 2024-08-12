package com.javabasicprojects.chessgame;

public class ChessGame {
    private ChessBoard board;
    private  boolean whiteTurn = true;

    public ChessGame() {
        this.board = new ChessBoard();
    }
}
