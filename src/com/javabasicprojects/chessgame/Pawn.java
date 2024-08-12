package com.javabasicprojects.chessgame;

public class Pawn extends Piece {
    public Pawn(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int forwardDirection = color == PieceColor.WHITE ? -1 : 1;
        int rowDiff = (newPosition.getRow() - position.getRow()) * forwardDirection;
        int colDiff = newPosition.getColumn() - position.getColumn();

        if (// Forward move
            isForwardMove(colDiff, rowDiff, board, newPosition) ||
                    // Initial two-square move
                    isTwoSquareMove(colDiff, rowDiff, forwardDirection, board, newPosition) ||
                        // Diagonal capture
                        isDiagonalCapture(colDiff, rowDiff, board, newPosition)) {
            return true;
        }
        return false;
    }

    private boolean isForwardMove(int colDiff, int rowDiff, Piece[][] board, Position newPosition) {
        return colDiff == 0 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] == null;
    }

    private boolean isStartingPosition() {
        return (color == PieceColor.WHITE && position.getRow() == 6) || (color == PieceColor.BLACK && position.getRow() == 1);
    }

    private boolean isTwoSquareMove(int colDiff, int rowDiff, int forwardDirection, Piece[][] board, Position newPosition) {
        if (colDiff == 0 && rowDiff == 2 && isStartingPosition() && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            // Check the square in between for blocking pieces
            int middleRow = position.getRow() + forwardDirection;
            if (board[middleRow][position.getColumn()] == null) {
                return true; // Move forward two squares
            }
        }
        return false;
    }

    private boolean isDiagonalCapture(int colDiff, int rowDiff, Piece[][] board, Position newPosition) {
        return  Math.abs(colDiff) == 1 &&
                rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] != null &&
                board[newPosition.getRow()][newPosition.getColumn()].color != this.color;
    }
}
