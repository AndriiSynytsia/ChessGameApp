package com.javabasicprojects.chessgame.engine.board;

import com.javabasicprojects.chessgame.engine.pieces.Piece;

public class OccupiedTile extends Tile {
    private final Piece pieceOnTile;

    public OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.pieceOnTile;
    }
}
