package com.javabasicprojects.chessgame;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor, Position position) {
        super(pieceColor, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int rowDiff = Math.abs(position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(position.getColumn() - newPosition.getColumn());

        if (rowDiff != colDiff) {
            return false; //Move is not diagonal
        }

        int rowStep = newPosition.getRow() > position.getRow() ? 1 : -1;
        int colStep = newPosition.getColumn() > position.getColumn() ? 1 : -1;
        int steps = rowDiff - 1; // Number of steps to check for obstruction

        // Check for obstructions along the path
        for (int i = 1; i <= steps; i++) {
            if (board[position.getRow() + i * rowStep][position.getColumn() + i * colStep] != null) {
                return false; // There's a piece in the way
            }
        }

        // Check the destination square for capturing or moving to an empty square
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (destinationPiece == null) {
            return true; // The destination is empty, move is valid
        } else if (destinationPiece.getPieceColor() != this.getPieceColor()){
            return true; // The destination square has enemy piece, move is valid with capturing opponent's piece
        }
        return false; // The destination has a piece of the same color, move is invalid
    }
}
