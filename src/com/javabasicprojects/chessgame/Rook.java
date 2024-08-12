package com.javabasicprojects.chessgame;

public class Rook extends Piece {
    public Rook(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Rooks can move vertically and horizontally any number of squares.
        // They cannot jump over pieces.
        if (position.getRow() == newPosition.getRow()) {
            int columnStart = Math.min(position.getColumn(), newPosition.getColumn()) + 1;
            int columnEnd = Math.max(position.getColumn(), newPosition.getColumn());

            for (int column = columnStart; column < columnEnd; column++) {
                if (board[position.getRow()][column] != null) {
                    return false; // There is a piece in the way
                }
            }
        } else if (position.getColumn() == newPosition.getColumn()) {
            int rowStart = Math.min(position.getRow(), newPosition.getRow()) + 1;
            int rowEnd = Math.max(position.getRow(), newPosition.getRow());

            for (int row = rowStart; row < rowEnd; row++) {
                if (board[row][position.getColumn()] != null) {
                    return false; // There is a piece in the way
                }
            }
        } else {
            return false; // Not a valid rook move (not straight line)
        }

        // Check the destination square for capturing
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (destinationPiece == null) {
            return true; // The destination piece is empty, move is valid
        } else if (destinationPiece.getPieceColor() != this.color) {
            return true;
        }
        return false;
    }
}
