package com.javabasicprojects.chessgame.engine.board;

import com.javabasicprojects.chessgame.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer,  EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public Tile(int tileCoordinate) {
        this. tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();
}
