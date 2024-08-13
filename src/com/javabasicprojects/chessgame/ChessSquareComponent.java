package com.javabasicprojects.chessgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class ChessSquareComponent extends JButton {
    private int row;
    private int column;

    public ChessSquareComponent(int row, int column) {
        this.row = row;
        this.column = column;
        initButton();
    }

    private void initButton() {
        setPreferredSize(new Dimension(64,64));

        if ((row + column) % 2 == 0) {
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(new Color(205, 133, 63));
        }

        setHorizontalAlignment(SwingUtilities.CENTER);
        setVerticalAlignment(SwingUtilities.CENTER);

        setFont(new Font("Serif", Font.BOLD, 36));
    }

    public void addMouseListener(MouseAdapter mouseAdapter) {
    }

    public void setPieceSymbol(String symbol, Color color) {
        this.setText(symbol);
        this.setForeground(color);
    }

    public void clearPieceSymbol() {
        this.setText("");
    }
}
