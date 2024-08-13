package com.javabasicprojects.chessgame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChessGameGUI extends JFrame {
    private final ChessSquareComponent[][] squares = new ChessSquareComponent[8][8];
    private final ChessGame game = new ChessGame();
    private final Map<Class<? extends Piece>, String> pieceUnicodeMap = new HashMap<>() {
        {
        put(Pawn.class, "\u265F");
        put(Rook.class, "\u265C");
        put(Knight.class, "\u265E");
        put(Bishop.class, "\u265D");
        put(Queen.class, "\u265B");
        put(King.class, "\u265A");
        }
    };

    public ChessGameGUI() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8,8));
        initializeBoard();
        pack(); // Adjust window size to fit the chessboard
        setVisible(true);
    }

    private void initializeBoard() {

    }

    private void refreshBoard() {

    }

    private void handleSquareClick(int row, int column) {

    }

    private void checkGameState() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGameGUI::new);
    }
}
