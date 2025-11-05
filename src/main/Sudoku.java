package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sudoku {
   
   private final int boardWidth = 600;
   private final int boardHeight = 650;
    
    JFrame frame = new JFrame("Sudoku");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    JButton numSelected = null;
    private int errors = 0;
    
    // TODO: Générer une solution aléatoirement,
    // puis assigner des "-" à des endroits différents
    // Assigner une police (pour le titre, par le contenu de la grille) qui sera modifiable par l'utilisateur
    // Implémenter les cases à moitié transparentes, et placer un fond d'écran
    

    String[] puzzle = {
        "--74916-5",
        "2---6-3-9",
        "-----7-1-",
        "-586----4",
        "--3----9-",
        "--62--187",
        "9-4-7---2",
        "67-83----",
        "81--45---"
    };

    String[] solution = {
        "387491625",
        "241568379",
        "569327418",
        "758619234",
        "123784596",
        "496253187",
        "934176852",
        "675832941",
        "812945763"
    };

    

    public Sudoku() {
        // frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Sudoku: 0");

        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(9, 9));
        setupTiles();
        frame.add(boardPanel, BorderLayout.CENTER);

        buttonsPanel.setLayout(new GridLayout(1, 9));
        setupButtons();
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    void setupTiles() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Tile tile = new Tile(r, c);
                char tileChar = puzzle[r].charAt(c);
                
                
                if (tileChar != '-') {
                    tile.setFont(new Font("Arial", Font.BOLD, 20));
                    tile.setText(String.valueOf(tileChar));
                    tile.setBackground(Color.lightGray);
                }
                // Emptying the dash buttons
                else {
                    tile.setFont(new Font("Arial", Font.PLAIN, 20));
                    tile.setBackground(Color.white);
                }
                // on met des bordures pour séparer la grande grille en 9 grilles de 9 cases
                if ((r == 2 && c == 2) || (r == 2 && c == 5) || (r == 5 && c == 2) || (r == 5 && c == 5)) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.black));
                }
                else if (r == 2 || r == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.black));
                }
                else if (c == 2 || c == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 5, Color.black));
                }
                else {
                    tile.setBorder(BorderFactory.createLineBorder(Color.black));
                }
                tile.setFocusable(false);
                boardPanel.add(tile);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Tile tile = (Tile) e.getSource();
                        int r = tile.r;
                        int c = tile.c;
                        if (numSelected != null) {
                            if (tile.getText() != "") {
                                return;
                            }
                            String numSelectedText = numSelected.getText();
                            String tileSolution = String.valueOf(solution[r].charAt(c));
                            if (tileSolution.equals(numSelectedText)) {
                                tile.setText(numSelectedText);
                            }
                            else {
                                errors += 1;
                                textLabel.setText("Sudoku: " + String.valueOf(errors));
                            }

                        }
                    }
                });
            }
        }
    }

    
    
    
    void setupButtons() {
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setText(String.valueOf(i));
            button.setFocusable(false);
            button.setBackground(Color.white);
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (numSelected != null) {
                        numSelected.setBackground(Color.white);
                    }
                    numSelected = button;
                    numSelected.setBackground(Color.lightGray);
                }
            });
        }
    }
    
    
    
    class Tile extends JButton {
        int r;
        int c;
        Tile(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}