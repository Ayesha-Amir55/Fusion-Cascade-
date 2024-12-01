import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

class Game2048Fruit extends JFrame {
    private static final int TILE_SIZE = 150;
    private static final int SPACING = 10;
    private static final int PANEL_HEIGHT = 50;

    private final GameBoard gameBoard;
    private Image[] tileImages;
    private boolean gameOver;
    private boolean gameWon;
    private JLabel scoreLabel;

    public Game2048Fruit() {
        setTitle("Fusion Cascade");
        setSize(4 * TILE_SIZE + SPACING * (4 + 1), 4 * TILE_SIZE + SPACING * (4 + 1) + PANEL_HEIGHT + 70);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        gameBoard = new GameBoard();
        loadTileImages();

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        scoreLabel = new JLabel("Score: " + gameBoard.getScore());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scorePanel.add(scoreLabel);
        add(scorePanel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 4, SPACING, SPACING));
        gamePanel.setPreferredSize(new Dimension(4 * TILE_SIZE + SPACING * 5, 4 * TILE_SIZE + SPACING * 5)); // Set preferred size of the grid
        add(gamePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        buttonPanel.add(restartButton);

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(e -> showMainMenu());  // Placeholder action
        buttonPanel.add(mainMenuButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!gameOver && !gameWon) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            gameBoard.moveUp();
                            break;
                        case KeyEvent.VK_DOWN:
                            gameBoard.moveDown();
                            break;
                        case KeyEvent.VK_LEFT:
                            gameBoard.moveLeft();
                            break;
                        case KeyEvent.VK_RIGHT:
                            gameBoard.moveRight();
                            break;
                    }
                    gameBoard.addRandomTile();
                    scoreLabel.setText("Score: " + gameBoard.getScore());
                    checkGameOver();
                    checkGameWon();
                    repaint();
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        initGame();
    }

    private void initGame() {
        gameBoard.initGame();
        gameOver = false;
        gameWon = false;
        scoreLabel.setText("Score: 0");
        repaint();
    }

    private void restartGame() {
        gameBoard.resetGame();
        gameOver = false;
        gameWon = false;
        scoreLabel.setText("Score: 0");
        repaint();
        initGame(); // Reinitialize the game state
        requestFocusInWindow(); // Ensure the focus for key input
    }

    private void showMainMenu() {
        JOptionPane.showMessageDialog(this, "Main Menu button clicked!");  // Placeholder action
    }


    private void checkGameOver() {
        if (!gameBoard.canMove()) {
            gameOver = true;
            int response = JOptionPane.showConfirmDialog(this, "Game Over! Would you like to restart?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                restartGame();  // Reset the game if "Yes" is clicked
            } else {
                System.exit(0);  // Exit if "No" is clicked
            }
        }
    }


    private void checkGameWon() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (gameBoard.getBoard()[r][c] == 2048) {
                    gameWon = true;
                    JOptionPane.showMessageDialog(this, "You Win! Congratulations!");
                    return;
                }
            }
        }
    }

    private void loadTileImages() {
        tileImages = new Image[12];
        try {
            tileImages[0] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic2.jpg"))).getImage();
            tileImages[1] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic4.jpg"))).getImage();
            tileImages[2] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic8.jpg"))).getImage();
            tileImages[3] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic16.jpg"))).getImage();
            tileImages[4] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic32.jpg"))).getImage();
            tileImages[5] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic64.jpg"))).getImage();
            tileImages[6] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic128.jpg"))).getImage();
            tileImages[7] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic256.jpg"))).getImage();
            tileImages[8] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic512.jpg"))).getImage();
            tileImages[9] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic1024.jpg"))).getImage();
            tileImages[10] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pic2048.jpg"))).getImage();
            tileImages[11] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/white.png"))).getImage();
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                int value = gameBoard.getBoard()[r][c];
                drawTile(g, value, r, c);
            }
        }
    }

    private void drawTile(Graphics g, int value, int row, int col) {
        int x = col * TILE_SIZE + SPACING * (col + 1);
        int y = row * TILE_SIZE + SPACING * (row + 1) + PANEL_HEIGHT;
        Image tileImage;

        if (value == 0) {
            tileImage = tileImages[11];
        } else {
            int index = (int) (Math.log(value) / Math.log(2)) - 1;
            tileImage = tileImages[index];
        }

        g.drawImage(tileImage, x, y, TILE_SIZE, TILE_SIZE, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game2048Fruit().setVisible(true));
    }
}
