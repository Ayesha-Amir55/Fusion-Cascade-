import java.nio.charset.StandardCharsets;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

        // Title Label
        JLabel titleLabel = new JLabel("Fusion Cascade", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Muthiara -Demo Version-", Font.PLAIN, 200));
        titleLabel.setForeground(new Color(0xe94d4d));
        firstFrame.add(titleLabel);
        // Continue Button
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

                // Center title label
                FontMetrics fm = titleLabel.getFontMetrics(titleLabel.getFont());
                int textWidth = fm.stringWidth(titleLabel.getText());
                int textHeight = fm.getHeight();
                int titleX = (frameWidth - textWidth) / 2;
                int titleY = (frameHeight - textHeight) / 4; // Adjust Y for better visual positioning
                titleLabel.setBounds(titleX, titleY, textWidth, textHeight);


                int buttonWidth = 300;
                int buttonHeight = 80;
                continueButton1.setBounds((frameWidth - buttonWidth) / 2, (frameHeight * 3 / 4), buttonWidth, buttonHeight);
            }
        });

        firstFrame.setVisible(true);
    }
    // Second Window
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

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("GodOfWar", Font.PLAIN, 30));
        backButton.setForeground(new Color(0xe75b75b));;
        secondFrame.add(backButton);

        JButton saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("GodOfWar", Font.PLAIN, 30));
        saveButton.setForeground(new Color(0xe75b75b));;
        secondFrame.add(saveButton);

        // Save Button Action: Save data to file
        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("user_data.txt", true), StandardCharsets.UTF_8);
                     BufferedWriter bw = new BufferedWriter(writer)) {
                    // Write the name in English to the file
                    bw.write("Name: " + name + "\n");
                    messageLabel.setText("Data saved successfully!");
                } catch (IOException ex) {
                    messageLabel.setText("Error saving data.");
                }
            } else {
                messageLabel.setText("Please enter your name.");
            }
        });

        // Back Button Action
        backButton.addActionListener(e -> {
            secondFrame.dispose();
            openFirstWindow(); // Reopen first window
        });

        secondFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                int frameWidth = secondFrame.getWidth();
                int frameHeight = secondFrame.getHeight();

                // Center welcome label
                FontMetrics fm = welcomeLabel.getFontMetrics(welcomeLabel.getFont());
                int textWidth = fm.stringWidth(welcomeLabel.getText());
                int textHeight = fm.getHeight();
                int titleX = (frameWidth - textWidth) / 2;
                int titleY = (frameHeight - textHeight) / 4;
                welcomeLabel.setBounds(titleX, titleY, textWidth, textHeight);

                // Center name label and text field
                nameLabel.setBounds((frameWidth - 300) / 2, frameHeight / 2 - 100, 300, 40);
                nameField.setBounds((frameWidth - 400) / 2, frameHeight / 2 - 50, 400, 50);

                // Center buttons
                backButton.setBounds((frameWidth - 200) / 2 - 200, frameHeight / 2 + 100, 200, 50);
                saveButton.setBounds((frameWidth - 200) / 2 + 200, frameHeight / 2 + 100, 200, 50);

                // Message label position
                messageLabel.setBounds(0, frameHeight / 2 + 200, frameWidth, 40);
            }
        });

        secondFrame.setVisible(true);
    }
}
