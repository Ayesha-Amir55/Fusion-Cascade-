import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Objects;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        openFirstWindow();
    }

    // First Window (Fusion Cascade)
    private static void openFirstWindow() {
        JFrame firstFrame = new JFrame("Fusion Cascade");
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        firstFrame.getContentPane().setBackground(new Color(0xf4c3b5));
        firstFrame.setLayout(null);

        JLabel titleLabel = new JLabel("Fusion Cascade", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Muthiara -Demo Version-", Font.PLAIN, 200));
        titleLabel.setForeground(new Color(0xe94d4d));
        firstFrame.add(titleLabel);

        JButton continueButton1 = new JButton("CONTINUE");
        continueButton1.setFont(new Font("GodOfWar", Font.PLAIN, 40));
        continueButton1.setForeground(new Color(0xe75b75b));
        firstFrame.add(continueButton1);

        continueButton1.addActionListener(e -> {
            firstFrame.dispose();
            openSecondWindow();
        });

        firstFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameWidth = firstFrame.getWidth();
                int frameHeight = firstFrame.getHeight();
                FontMetrics fm = titleLabel.getFontMetrics(titleLabel.getFont());
                int textWidth = fm.stringWidth(titleLabel.getText());
                int textHeight = fm.getHeight();
                int titleX = (frameWidth - textWidth) / 2;
                int titleY = (frameHeight - textHeight) / 4;
                titleLabel.setBounds(titleX, titleY, textWidth, textHeight);

                int buttonWidth = 300;
                int buttonHeight = 80;
                continueButton1.setBounds((frameWidth - buttonWidth) / 2, (frameHeight * 3 / 4), buttonWidth, buttonHeight);
            }
        });

        firstFrame.setVisible(true);
    }

    // Second Window (Welcome)
    private static void openSecondWindow() {
        JFrame secondFrame = new JFrame("Welcome");
        secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        secondFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        secondFrame.getContentPane().setBackground(new Color(0xf4c3b5));
        secondFrame.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Muthiara -Demo Version-", Font.PLAIN, 150));
        welcomeLabel.setForeground(new Color(0xe94d4d));
        secondFrame.add(welcomeLabel);

        JLabel nameLabel = new JLabel("Your Name", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Evil Dead", Font.PLAIN, 30));
        nameLabel.setForeground(new Color(0x38a008));
        secondFrame.add(nameLabel);

        JTextField nameField = new JTextField(SwingConstants.CENTER);
        nameField.setFont(new Font("Montserrat", Font.BOLD, 30));
        nameField.setBackground(new Color(0xffdcd4));
        secondFrame.add(nameField);

        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        messageLabel.setForeground(new Color(0xe94d4d));
        secondFrame.add(messageLabel);

        JButton saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("GodOfWar", Font.PLAIN, 30));
        saveButton.setForeground(new Color(0xe75b75b));
        secondFrame.add(saveButton);

        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("user_data.txt", true), StandardCharsets.UTF_8);
                     BufferedWriter bw = new BufferedWriter(writer)) {
                    bw.write("Name: " + name + "\n");
                    messageLabel.setText("Data saved successfully!");
                    // Open the Main Menu after saving
                    secondFrame.dispose();
                    MainMenuSwing.openMainMenu();
                } catch (IOException ex) {
                    messageLabel.setText("Error saving data.");
                }
            } else {
                messageLabel.setText("Please enter your name.");
            }
        });

        secondFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                int frameWidth = secondFrame.getWidth();
                int frameHeight = secondFrame.getHeight();
                FontMetrics fm = welcomeLabel.getFontMetrics(welcomeLabel.getFont());
                int textWidth = fm.stringWidth(welcomeLabel.getText());
                int textHeight = fm.getHeight();
                int titleX = (frameWidth - textWidth) / 2;
                int titleY = (frameHeight - textHeight) / 4;
                welcomeLabel.setBounds(titleX, titleY, textWidth, textHeight);

                nameLabel.setBounds((frameWidth - 300) / 2, frameHeight / 2 - 100, 300, 40);
                nameField.setBounds((frameWidth - 400) / 2, frameHeight / 2 - 50, 400, 50);
                saveButton.setBounds((frameWidth - 200) / 2, frameHeight / 2 + 100, 200, 50);
                messageLabel.setBounds(0, frameHeight / 2 + 200, frameWidth, 40);
            }
        });

        secondFrame.setVisible(true);
    }
}

// Main Menu Swing Class
class MainMenuSwing {

    public static void openMainMenu() {
        // Create the main frame
        JFrame frame = new JFrame("Main Menu");

        // Set the frame size slightly smaller than the full screen to keep the taskbar visible
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight() - 50; // Leave space for the taskbar
        frame.setBounds(0, 0, width, height);
        frame.setLayout(new BorderLayout());

        // Set window background color
        frame.getContentPane().setBackground(Color.decode("#f4c3b5"));

        // Title label
        JLabel titleLabel = new JLabel("Fusion Cascade", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Muthiara -Demo Version-", Font.PLAIN, 150)); // Changed to "Heydex" (plain)
        titleLabel.setForeground(Color.decode("#e94d4d")); // Changed text color to #FFB7CE
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Make the panel transparent

        // GridBagConstraints for button positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Spacing between buttons

        // Create a custom button style
        Font buttonFont = new Font("GodOfWar", Font.PLAIN, 30);

        // Play Button
        JButton playButton = new JButton("PLAY");
        styleButton(playButton, buttonFont);
        playButton.setForeground(new Color(0xe75b75b));
        gbc.gridy = 0;
        buttonPanel.add(playButton, gbc);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game2048Fruit game = new Game2048Fruit();
                game.setVisible(true);
            }
        });

        // Instructions Button
        JButton instructionsButton = new JButton("INSTRUCTIONS");
        styleButton(instructionsButton, buttonFont);
        instructionsButton.setForeground(new Color(0xe75b75b));
        gbc.gridy = 1;
        buttonPanel.add(instructionsButton, gbc);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInstructionsWindow();
            }
        });

        // Scoreboard Button
        JButton scoreboardButton = new JButton("SCOREBOARD");
        styleButton(scoreboardButton, buttonFont);
        scoreboardButton.setForeground(new Color(0xe75b75b));
        gbc.gridy = 2;
        buttonPanel.add(scoreboardButton, gbc);
        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openScoreboardWindow(); // Open the new Scoreboard window
            }
        });

        // Exit Button
        JButton exitButton = new JButton("EXIT");
        styleButton(exitButton, buttonFont);
        exitButton.setForeground(new Color(0xe75b75b));
        gbc.gridy = 3;
        buttonPanel.add(exitButton, gbc);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });

        // Add button panel to frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Prevent resizing
        frame.setResizable(false);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the frame
        frame.setVisible(true);
    }

    // Helper method to style buttons
    private static void styleButton(JButton button, Font font) {
        button.setFont(font);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(350, 100)); // Set preferred button size
    }
    static class GameBoard {
    private static final int GRID_SIZE = 4;
    private int[][] board;
    private int score;

    public GameBoard() {
        board = new int[GRID_SIZE][GRID_SIZE];
        score = 0;
        initGame();
    }

    public int getScore() {
        return score;
    }

    public void initGame() {
        board = new int[GRID_SIZE][GRID_SIZE];  // Reset the board
        addRandomTile();
        addRandomTile();
    }

    // Add a random tile (2 or 4) at an empty position on the board
    public void addRandomTile() {
        Random random = new Random();
        int row, col;

        if (isFull()) return;

        do {
            row = random.nextInt(GRID_SIZE);
            col = random.nextInt(GRID_SIZE);
        } while (board[row][col] != 0);  // Ensure the spot is empty

        board[row][col] = (random.nextInt(10) < 9) ? 2 : 4;  // 90% chance for 2, 10% for 4
    }

    // Check if the board is full (no empty spaces)
    private boolean isFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    // Move the tiles and handle merges for UP, DOWN, LEFT, RIGHT
    public void moveLeft() {
        for (int r = 0; r < GRID_SIZE; r++) {
            int[] row = board[r];
            mergeTiles(row);
        }
    }

    public void moveRight() {
        for (int r = 0; r < GRID_SIZE; r++) {
            int[] row = reverse(board[r]);
            mergeTiles(row);
            board[r] = reverse(row);
        }
    }

    public void moveUp() {
        for (int c = 0; c < GRID_SIZE; c++) {
            int[] col = new int[GRID_SIZE];
            for (int r = 0; r < GRID_SIZE; r++) {
                col[r] = board[r][c];
            }
            mergeTiles(col);
            for (int r = 0; r < GRID_SIZE; r++) {
                board[r][c] = col[r];
            }
        }
    }

    public void moveDown() {
        for (int c = 0; c < GRID_SIZE; c++) {
            int[] col = new int[GRID_SIZE];
            for (int r = 0; r < GRID_SIZE; r++) {
                col[r] = board[r][c];
            }
            col = reverse(col);
            mergeTiles(col);
            col = reverse(col);
            for (int r = 0; r < GRID_SIZE; r++) {
                board[r][c] = col[r];
            }
        }
    }

    // Merge tiles in the line (row or column)
    private void mergeTiles(int[] line) {
        for (int i = 0; i < GRID_SIZE - 1; i++) {
            if (line[i] == 0) continue;

            for (int j = i + 1; j < GRID_SIZE; j++) {
                if (line[j] == 0) continue;

                if (line[i] == line[j]) {
                    line[i] *= 2;  // Merge tiles
                    line[j] = 0;  // Clear the merged tile
                    score ++;  // Update score
                }
                break;  // Stop after one merge
            }
        }

        // Shift tiles to the left
        int[] newLine = new int[GRID_SIZE];
        int index = 0;
        for (int value : line) {
            if (value != 0) {
                newLine[index++] = value;
            }
        }

        System.arraycopy(newLine, 0, line, 0, GRID_SIZE);  // Update the original line
    }

    // Reverse the order of the array (used for right and down moves)
    private int[] reverse(int[] line) {
        int[] reversed = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            reversed[i] = line[GRID_SIZE - 1 - i];
        }
        return reversed;
    }

    public int[][] getBoard() {
        return board;
    }

    public void resetGame() {
        score = 0;
        initGame();
    }

    public boolean canMove() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (board[r][c] == 0) return true;
                if (r < GRID_SIZE - 1 && board[r][c] == board[r + 1][c]) return true;
                if (c < GRID_SIZE - 1 && board[r][c] == board[r][c + 1]) return true;
            }
        }
        return false;
    }
}

static class Game2048Fruit extends JFrame {
    private static final int TILE_SIZE = 150;
    private static final int SPACING = 10;
    private static final int PANEL_HEIGHT = 70;

    private final GameBoard gameBoard;
    private Image[] tileImages;
    private boolean gameOver;
    private boolean gameWon;
    private JLabel scoreLabel;

    public Game2048Fruit() {
        setTitle("Fusion Cascade");
        setSize(4 * TILE_SIZE + SPACING * (4 + 1), 4 * TILE_SIZE + SPACING * (4 + 1) + PANEL_HEIGHT + 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        gameBoard = new GameBoard();
        loadTileImages();

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        scoreLabel = new JLabel("SCORE: " + gameBoard.getScore());
        scoreLabel.setFont(new Font("GodOfWar", Font.BOLD, 24));
        scoreLabel.setForeground(new Color(0xe94d4d));
        scorePanel.setBackground(new Color(0xf4c3b5));
        scorePanel.add(scoreLabel);
        add(scorePanel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 4, SPACING, SPACING));
        gamePanel.setPreferredSize(new Dimension(4 * TILE_SIZE + SPACING * 5, 4 * TILE_SIZE + SPACING * 5)); // Set preferred size of the grid
        gamePanel.setBackground(new Color(0xf4c3b5));
        add(gamePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0xf4c3b5));
        JButton restartButton = new JButton("RESTART");
        restartButton.setFont(new Font("GodOfWar", Font.PLAIN, 18));
        restartButton.setForeground(new Color(0xe75b75b));
        restartButton.addActionListener(e -> restartGame());
        buttonPanel.add(restartButton);

        JButton mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.setFont(new Font("GodOfWar", Font.PLAIN, 18));
        mainMenuButton.setForeground(new Color(0xe75b75b));
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
        openMainMenu();
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
    public static void openGameWindow() {
        class GameBoard {
            private static final int GRID_SIZE = 4;
            private int[][] board;
            private int score;

            public GameBoard() {
                board = new int[GRID_SIZE][GRID_SIZE];
                score = 0;
                initGame();
            }

            public int getScore() {
                return score;
            }

            public void initGame() {
                board = new int[GRID_SIZE][GRID_SIZE];  // Reset the board
                addRandomTile();
                addRandomTile();
            }

            // Add a random tile (2 or 4) at an empty position on the board
            public void addRandomTile() {
                Random random = new Random();
                int row, col;

                if (isFull()) return;

                do {
                    row = random.nextInt(GRID_SIZE);
                    col = random.nextInt(GRID_SIZE);
                } while (board[row][col] != 0);  // Ensure the spot is empty

                board[row][col] = (random.nextInt(10) < 9) ? 2 : 4;  // 90% chance for 2, 10% for 4
            }

            // Check if the board is full (no empty spaces)
            private boolean isFull() {
                for (int[] row : board) {
                    for (int cell : row) {
                        if (cell == 0) return false;
                    }
                }
                return true;
            }

            // Move the tiles and handle merges for UP, DOWN, LEFT, RIGHT
            public void moveLeft() {
                for (int r = 0; r < GRID_SIZE; r++) {
                    int[] row = board[r];
                    mergeTiles(row);
                }
            }

            public void moveRight() {
                for (int r = 0; r < GRID_SIZE; r++) {
                    int[] row = reverse(board[r]);
                    mergeTiles(row);
                    board[r] = reverse(row);
                }
            }

            public void moveUp() {
                for (int c = 0; c < GRID_SIZE; c++) {
                    int[] col = new int[GRID_SIZE];
                    for (int r = 0; r < GRID_SIZE; r++) {
                        col[r] = board[r][c];
                    }
                    mergeTiles(col);
                    for (int r = 0; r < GRID_SIZE; r++) {
                        board[r][c] = col[r];
                    }
                }
            }

            public void moveDown() {
                for (int c = 0; c < GRID_SIZE; c++) {
                    int[] col = new int[GRID_SIZE];
                    for (int r = 0; r < GRID_SIZE; r++) {
                        col[r] = board[r][c];
                    }
                    col = reverse(col);
                    mergeTiles(col);
                    col = reverse(col);
                    for (int r = 0; r < GRID_SIZE; r++) {
                        board[r][c] = col[r];
                    }
                }
            }

            // Merge tiles in the line (row or column)
            private void mergeTiles(int[] line) {
                for (int i = 0; i < GRID_SIZE - 1; i++) {
                    if (line[i] == 0) continue;

                    for (int j = i + 1; j < GRID_SIZE; j++) {
                        if (line[j] == 0) continue;

                        if (line[i] == line[j]) {
                            line[i] *= 2;  // Merge tiles
                            line[j] = 0;  // Clear the merged tile
                            score ++;  // Update score
                        }
                        break;  // Stop after one merge
                    }
                }

                // Shift tiles to the left
                int[] newLine = new int[GRID_SIZE];
                int index = 0;
                for (int value : line) {
                    if (value != 0) {
                        newLine[index++] = value;
                    }
                }

                System.arraycopy(newLine, 0, line, 0, GRID_SIZE);  // Update the original line
            }

            // Reverse the order of the array (used for right and down moves)
            private int[] reverse(int[] line) {
                int[] reversed = new int[GRID_SIZE];
                for (int i = 0; i < GRID_SIZE; i++) {
                    reversed[i] = line[GRID_SIZE - 1 - i];
                }
                return reversed;
            }

            public int[][] getBoard() {
                return board;
            }

            public void resetGame() {
                score = 0;
                initGame();
            }

            public boolean canMove() {
                for (int r = 0; r < GRID_SIZE; r++) {
                    for (int c = 0; c < GRID_SIZE; c++) {
                        if (board[r][c] == 0) return true;
                        if (r < GRID_SIZE - 1 && board[r][c] == board[r + 1][c]) return true;
                        if (c < GRID_SIZE - 1 && board[r][c] == board[r][c + 1]) return true;
                    }
                }
                return false;
            }
        }

        class Game2048Fruit extends JFrame {
            private static final int TILE_SIZE = 150;
            private static final int SPACING = 10;
            private static final int PANEL_HEIGHT = 70;

            private final GameBoard gameBoard;
            private Image[] tileImages;
            private boolean gameOver;
            private boolean gameWon;
            private JLabel scoreLabel;

            public Game2048Fruit() {
                setTitle("Fusion Cascade");
                setSize(4 * TILE_SIZE + SPACING * (4 + 1), 4 * TILE_SIZE + SPACING * (4 + 1) + PANEL_HEIGHT + 100);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLayout(new BorderLayout());
                setLocationRelativeTo(null);

                gameBoard = new GameBoard();
                loadTileImages();

                JPanel scorePanel = new JPanel();
                scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                scoreLabel = new JLabel("SCORE: " + gameBoard.getScore());
                scoreLabel.setFont(new Font("GodOfWar", Font.BOLD, 24));
                scoreLabel.setForeground(new Color(0xe94d4d));
                scorePanel.setBackground(new Color(0xf4c3b5));
                scorePanel.add(scoreLabel);
                add(scorePanel, BorderLayout.NORTH);

                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new GridLayout(4, 4, SPACING, SPACING));
                gamePanel.setPreferredSize(new Dimension(4 * TILE_SIZE + SPACING * 5, 4 * TILE_SIZE + SPACING * 5)); // Set preferred size of the grid
                gamePanel.setBackground(new Color(0xf4c3b5));
                add(gamePanel, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.setBackground(new Color(0xf4c3b5));
                JButton restartButton = new JButton("RESTART");
                restartButton.setFont(new Font("GodOfWar", Font.PLAIN, 18));
                restartButton.setForeground(new Color(0xe75b75b));
                restartButton.addActionListener(e -> restartGame());
                buttonPanel.add(restartButton);

                JButton mainMenuButton = new JButton("MAIN MENU");
                mainMenuButton.setFont(new Font("GodOfWar", Font.PLAIN, 18));
                mainMenuButton.setForeground(new Color(0xe75b75b));
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
                        dispose();  // Exit if "No" is clicked
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


    }
    private static void openInstructionsWindow() {
        // Create a new frame
        JFrame instructionsFrame = new JFrame("Instructions");

        // Set the frame size slightly smaller than the full screen to keep the taskbar visible
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight() - 50; // Leave space for the taskbar
        instructionsFrame.setBounds(0, 0, width, height);

        // Set layout manager to GridBagLayout for centering
        instructionsFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add space around components

        // Set background color to #CEA2FD
        instructionsFrame.getContentPane().setBackground(Color.decode("#f4c3b5"));

        // Center panel for everything
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Vertical layout
        centerPanel.setOpaque(false); // Make the panel transparent

        // Add heading (How to Play 2048) in a large font
        JLabel headingLabel = new JLabel("How to Play Fusion Cascade", JLabel.CENTER);
        headingLabel.setFont(new Font("Muthiara -Demo Version-", Font.PLAIN, 100)); // Changed font to "Heydex" (plain)
        headingLabel.setForeground(Color.decode("#e94d4d")); // Changed text color to #FFB7CE
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center it horizontally
        centerPanel.add(headingLabel);

        // Add space between heading and instructions
        centerPanel.add(Box.createVerticalStrut(40));

        // Add each instruction as a separate JLabel (no wrapping)
        JLabel instruction1 = new JLabel("1. Swipe or use arrow keys to move the fruits on the board.");
        instruction1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40)); // Changed font to "Monotype Corsiva"
        instruction1.setForeground(new Color(0xe75b75b)); // Changed text color to white
        instruction1.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction1);

        JLabel instruction2 = new JLabel("2. Combine matching fruits (e.g., two apples) to create bigger fruits (e.g., an orange).");
        instruction2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40)); // Changed font to "Monotype Corsiva"
        instruction2.setForeground(new Color(0xe75b75b)); // Changed text color to white
        instruction2.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction2);

        JLabel instruction3 = new JLabel("3. The goal is to reach the ultimate fruit tile for the ultimate fruit salad..");
        instruction3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40)); // Changed font to "Monotype Corsiva"
        instruction3.setForeground(new Color(0xe75b75b)); // Changed text color to white
        instruction3.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction3);

        JLabel instruction4 = new JLabel("4. Strategize to avoid filling the board completely with fruits..");
        instruction4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40)); // Changed font to "Monotype Corsiva"
        instruction4.setForeground(new Color(0xe75b75b)); // Changed text color to white
        instruction4.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction4);

        JLabel instruction5 = new JLabel("Keep merging fruits to earn points and keep playing for a higher score!");
        instruction5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40)); // Changed font to "Monotype Corsiva"
        instruction5.setForeground(new Color(0xe75b75b)); // Changed text color to white
        instruction5.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction5);

        // Add the centerPanel to the frame and center it using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Center the content
        instructionsFrame.add(centerPanel, gbc);

        // Add a close button at the bottom, also centered
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("GodOfWar", Font.PLAIN, 30)); // Font size remains unchanged
        closeButton.setForeground(new Color(0xe75b75b)); // Color remains unchanged

// Increase the button size
        closeButton.setPreferredSize(new Dimension(150, 50)); // Set the desired width and height

// Action listener to close the instructions frame
        closeButton.addActionListener(e -> instructionsFrame.dispose());

        gbc.gridy = 1; // Move close button to the next row
        instructionsFrame.add(closeButton, gbc);

        // Prevent resizing
        instructionsFrame.setResizable(false);

        // Display the instructions window
        instructionsFrame.setVisible(true);
    }

    // Method to open the Scoreboard window
    private static void openScoreboardWindow() {
        // Create a new frame
        JFrame scoreboardFrame = new JFrame("Scoreboard");

        // Set the frame size slightly smaller than the full screen to keep the taskbar visible
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight() - 50; // Leave space for the taskbar
        scoreboardFrame.setBounds(0, 0, width, height);

        // Set background color to #CEA2FD
        scoreboardFrame.getContentPane().setBackground(Color.decode("#f4c3b5"));

        // Create a panel to center everything
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Vertical layout
        centerPanel.setOpaque(false); // Make the panel transparent

        // Add space at the top (to ensure title is not at the very top)
        centerPanel.add(Box.createVerticalStrut(100)); // Adjust space as needed

        // Add a title label for the scoreboard
        JLabel titleLabel = new JLabel("High Scores", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Muthiara -Demo Version-", Font.PLAIN, 100)); // Changed font to "Heydex" (plain)
        titleLabel.setForeground(Color.decode("#e94d4d")); // Changed text color to #FFB7CE
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the title
        centerPanel.add(titleLabel);

        // Add vertical space between title and the scores
        centerPanel.add(Box.createVerticalStrut(40)); // Adjust the value for the desired space

        // Create a JPanel to hold each score as a JLabel
        JPanel scoreboardListPanel = new JPanel();
        scoreboardListPanel.setLayout(new BoxLayout(scoreboardListPanel, BoxLayout.Y_AXIS)); // Vertical layout
        scoreboardListPanel.setOpaque(false); // Make the panel transparent

        // Add the scores as labels (to keep them in one line each)
        String[] scores = {
                "1. Aqsa - 1050",
                "2. Nabiha - 890",
                "3. Bisma - 848",
                "4. Ayesha - 689",
                "5. se - 590"
        };

        for (String score : scores) {
            JLabel scoreLabel = new JLabel(score, SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 50)); // Changed font to "Monotype Corsiva"
            scoreLabel.setForeground(new Color(0xe75b75b)); // Changed text color to white
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center each score
            scoreboardListPanel.add(scoreLabel);
        }

        // Add the scoreboard panel to the center panel
        centerPanel.add(scoreboardListPanel);

        // Add space before the close button
        centerPanel.add(Box.createVerticalStrut(20));

        // Add a close button
        JButton closeButton = new JButton("CLOSE");
        closeButton.setFont(new Font("GodOfWar", Font.PLAIN, 20));
        closeButton.setForeground(new Color(0xe75b75b));
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);// Center the button
        closeButton.setPreferredSize(new Dimension(200, 50));
        closeButton.addActionListener(e -> scoreboardFrame.dispose());
        centerPanel.add(closeButton);

        // Add a filler component to vertically center the panel inside the frame
        centerPanel.add(Box.createVerticalGlue());

        // Add the centered content to the frame
        scoreboardFrame.add(centerPanel, BorderLayout.CENTER);

        // Prevent resizing
        scoreboardFrame.setResizable(false);

        // Display the scoreboard window
        scoreboardFrame.setVisible(true);
    }
}