package com.javabasicprojects.chessgame;

public class King extends Piece {
    public King(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int rowDiff = Math.abs(position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(position.getColumn() - newPosition.getColumn());

        // Kings can move one square in any direction
        boolean inOneSquareMove = rowDiff <= 1 && colDiff <= 1 && !(rowDiff == 0 && colDiff == 0);

        if (!inOneSquareMove) {
            return false; // Move is not within one square
        }

        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        return destinationPiece == null || destinationPiece.getPieceColor() != this.getPieceColor();
    }
}
