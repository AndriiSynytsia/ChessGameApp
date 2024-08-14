package com.javabasicprojects.chessgame;

public class ChessGame {
    private ChessBoard board;
    private boolean whiteTurn = true;
    private Position selectedPosition; // Track the currently selected piece's position

    public ChessGame() {
        this.board = new ChessBoard();
    }

    public boolean makeMove(Position start, Position end) {
        Piece movingPiece = board.getPiece(start.getRow(), start.getColumn());
        if (movingPiece == null || movingPiece.getPieceColor() != (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
            return false; // No piece at the start position or not the player's turn
        }

        if (movingPiece.isValidMove(end, board.getBoard())) {
            // Execute the move
            board.movePiece(start, end);
            whiteTurn = !whiteTurn; // Switch turns
            return true;
        }
        return false;
    }

    public boolean isInCheck(PieceColor kingColor) {
        Position kingPosition = findKingPosition(kingColor);
        for (int row = 0; row < board.getBoard().length; row++) {
            for (int col = 0; col < board.getBoard()[row].length; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getPieceColor() != kingColor) {
                    if (piece.isValidMove(kingPosition, board.getBoard())) {
                        return true; // An opposing piece can capture the king
                    }
                }
            }
        }
        return false;
    }

    private Position findKingPosition(PieceColor kingColor) {
        for (int row = 0; row < board.getBoard().length; row++) {
            for (int col = 0; col < board.getBoard()[row].length; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece instanceof King && piece.getPieceColor() == kingColor) {
                    return new Position(row, col);
                }
            }
        }
        throw new RuntimeException("King not found, which should never happen.");
    }

    public boolean isCheckMate(PieceColor kingColor) {
        if (!isInCheck(kingColor)) {
            return false;
        }

        Position kingPosition = findKingPosition(kingColor);
        King king = (King) board.getPiece(kingPosition.getRow(), kingPosition.getColumn());

        // Attempt to find a move that gets the king out of check
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue; // Skip the current position of the king
                }
                Position newPosition = new Position(kingPosition.getRow() + rowOffset, kingPosition.getColumn() + colOffset);

                // Check if moving the king to the new position is a legal move and does not result is a check
                if (isPositionOnBoard(newPosition) && king.isValidMove(newPosition, board.getBoard()) && !wouldBeInCheckAfterMove(kingColor, kingPosition, newPosition)) {
                    return false; // Found a move that gets the king out of check, so it's not checkmate
                }
            }
        }
        return true; // No legal moves available to escape check, so it's checkmate
    }

    private boolean wouldBeInCheckAfterMove(PieceColor kingColor, Position from, Position to) {
        // Simulate the move temporarily
        Piece temp = board.getPiece(to.getRow(), to.getColumn());
        board.setPiece(to.getRow(), to.getColumn(), board.getPiece(from.getRow(), from.getColumn()));
        board.setPiece(from.getRow(), from.getColumn(), null);

        boolean inCheck = isInCheck(kingColor);

        // Undo the move
        board.setPiece(from.getRow(), from.getColumn(),  board.getPiece(to.getRow(), to.getColumn()));
        board.setPiece(to.getRow(), to.getColumn(), temp);
        return inCheck;
    }

    private boolean isPositionOnBoard(Position position) {
        return position.getRow() >= 0 && position.getRow() < board.getBoard().length &&
                position.getColumn() >= 0 && position.getColumn() < board.getBoard()[0].length;
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    public boolean handleSquareSelection(int row, int column) {
        if (selectedPosition == null) {
            // Attempt to select a piece
            Piece selectedPiece = board.getPiece(row, column);
            if (selectedPiece != null && selectedPiece.getPieceColor() == (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
                selectedPosition = new Position(row, column);
                return false;
            }
        } else {
            // Attempt to move the selected piece
            boolean moveMade = makeMove(selectedPosition, new Position(row, column));
            selectedPosition = null;
            return moveMade;
        }
        return false;
    }

    public PieceColor getCurrentPlayerColor() {
        return whiteTurn ? PieceColor.WHITE : PieceColor.BLACK;
    }

    public void resetGame() {
        this.board = new ChessBoard(); // Re-initialize the board
        this.whiteTurn = true; // Reset turn to white
    }
}
