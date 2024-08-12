package com.javabasicprojects.chessgame;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        this.board = new Piece[8][8];
        setupPieces();
    }

    private void setupPieces() {
        // Initial setup on board for Rooks
        board[0][0] = new Rook(PieceColor.BLACK, new Position(0,0));
        board[0][7] = new Rook(PieceColor.BLACK, new Position(0,7));
        board[7][0] = new Rook(PieceColor.WHITE, new Position(7,0));
        board[7][7] = new Rook(PieceColor.WHITE, new Position(7,7));

        // Initial setup on board for Knights
        board[0][1] = new Knight(PieceColor.BLACK, new Position(0,1));
        board[0][6] = new Knight(PieceColor.BLACK, new Position(0,6));
        board[7][1] = new Knight(PieceColor.WHITE, new Position(7,1));
        board[7][6] = new Knight(PieceColor.WHITE, new Position(7,6));

        // Initial setup on board for Bishops
        board[0][2] = new Bishop(PieceColor.BLACK, new Position(0,2));
        board[0][5] = new Bishop(PieceColor.BLACK, new Position(0,5));
        board[7][2] = new Bishop(PieceColor.WHITE, new Position(7,2));
        board[7][5] = new Bishop(PieceColor.WHITE, new Position(7,5));

        // Initial setup on board for Queens
        board[0][3] = new Queen(PieceColor.BLACK, new Position(7,3));
        board[7][3] = new Queen(PieceColor.WHITE, new Position(0,3));

        // Initial setup on board for Kings
        board[0][4] = new King(PieceColor.BLACK, new Position(0,4));
        board[7][4] = new King(PieceColor.WHITE, new Position(7,4));

        // Initial setup on board for Pawns
        for (int i = 0; i < board[0].length; i++) {
            board[1][i] = new Pawn(PieceColor.BLACK, new Position(1,i));
            board[6][i] = new Pawn(PieceColor.WHITE, new Position(6,i));
        }
    }

    public void movePiece(Position start, Position end) {
        // Check if there is a piece at the start position and if the move is valid
        if (board[start.getRow()][start.getColumn()] != null &&
        board[start.getRow()][start.getColumn()].isValidMove(end, board)) {
            // Set the end position
            board[end.getRow()][end.getColumn()] = board[start.getRow()][start.getColumn()];
            // Update the position
            board[end.getRow()][end.getColumn()].setPosition(end);
            // Reset the start position
            board[start.getRow()][start.getColumn()] = null;
        }
    }
}
