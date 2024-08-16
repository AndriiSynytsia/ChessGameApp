package com.javabasicprojects.chessgame.engine.board;

import com.javabasicprojects.chessgame.engine.pieces.Piece;

public class EmptyTile extends Tile {
    EmptyTile(int coordinate) {
        super(coordinate);
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}