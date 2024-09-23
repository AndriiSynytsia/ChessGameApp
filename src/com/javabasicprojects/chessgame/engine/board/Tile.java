package com.javabasicprojects.chessgame.engine.board;

import com.google.common.collect.ImmutableMap;
import com.javabasicprojects.chessgame.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

import static com.javabasicprojects.chessgame.engine.board.BoardUtils.NUM_TILES;

public abstract class Tile {

    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    @Override
    public String toString() {
        return "-";
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(int tileCoordinate, Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    public int getTileCoordinate() {
        return this.tileCoordinate;
    }
}
