package com.javabasicprojects.chessgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                final int finalRow = row;
                final int finalColumn = col;
                ChessSquareComponent square = new ChessSquareComponent(row, col);
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(finalRow, finalColumn);
                    }
                });
                add(square);
                squares[row][col] = square;
            }
        }
        refreshBoard();
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
