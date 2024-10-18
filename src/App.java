import javax.swing.JFrame;
import GameConfig.GamePlay;

public class App {
    public static void main(String[] args) {
        // Creating the JFrame
        JFrame obj = new JFrame();
        GamePlay game = new GamePlay();
        
        // Set the bounds for the JFrame
        obj.setBounds(10, 10, 700, 600); // Adjusted dimensions for game area
        obj.setTitle("Breakout Ball");
        obj.setResizable(false); // Prevent resizing
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(game); // Add the game panel to the JFrame
        obj.setVisible(true); // Make the frame visible

        // Optional: Set the game panel to request focus for key events
        game.requestFocusInWindow();
    }
}
