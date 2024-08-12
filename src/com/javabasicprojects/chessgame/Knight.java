package com.javabasicprojects.chessgame;

public class Knight extends Piece {
    public Knight(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if (newPosition.equals(this.position)) {
            return false; // Cannot move to the same position
        }

        int rowDiff = Math.abs(this.position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(this.position.getColumn() - newPosition.getColumn());

        // Check for the 'L' shaped move pattern
        boolean isLShapeMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        if (!isLShapeMove) {
            return false; // Not a valid Knight move
        }

        Piece targetPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (targetPiece == null) {
            return true; // The square is empty, move is valid
        } else {
            return targetPiece.getPieceColor() != this.getPieceColor(); // Can capture if it's an opponent piece
        }
    }
}
