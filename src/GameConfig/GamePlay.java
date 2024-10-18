package GameConfig;

import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private int delay = 8;
    private Timer time;
    private int PlayerX = 310; // player X position 
    private int ballXpos = 120; // Ball x position 
    private int ballYpos = 350;
    private int ballXdir = -1;
    private int ballYdir = -2; 
    private ArrayList<Brick> bricks;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
        createBricks();
    } 

    private void createBricks() {
        bricks = new ArrayList<>();
        int row = 3;
        int col = 7;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bricks.add(new Brick(j * 100 + 20, i * 30 + 50));
            }
        }
    }

    public void paint(Graphics g) {
        // background 
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592); 
        // borders 
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592); 
        // Paddle 
        g.setColor(Color.GREEN);
        g.fillRect(PlayerX, 550, 100, 8);
        // the ball 
        g.setColor(Color.GREEN);
        g.fillOval(ballXpos, ballYpos, 20, 20);
        
        // Draw bricks
        for (Brick brick : bricks) {
            brick.draw(g);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start(); 
        if (play) { 
            // Check paddle collision
            if (new Rectangle(ballXpos, ballYpos, 20, 20).intersects(new Rectangle(PlayerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }
            // Check brick collisions
            for (Brick brick : bricks) {
                if (brick.isVisible && new Rectangle(ballXpos, ballYpos, 20, 20).intersects(brick.getBounds())) {
                    ballYdir = -ballYdir;
                    brick.isVisible = false;
                    score++;
                }
            }
            ballXpos += ballXdir;
            ballYpos += ballYdir;

            if (ballXpos < 0) {
                ballXdir = -ballXdir;
            }
            if (ballYpos < 0) {
                ballYdir = -ballYdir;
            }  
            if (ballXpos > 670) {
                ballXdir = -ballXdir;
            }
            // Game over if ball falls below the paddle
            if (ballYpos > 570) {
                play = false;
                // Add game over logic here (reset game, show message, etc.)
            }
        }
        repaint(); // recall the paint method
    }

    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (PlayerX >= 600) {
                PlayerX = 600;
            } else {
                moveRight();
            }
        } 
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (PlayerX < 10) {
                PlayerX = 10;
            } else {
                moveLeft();
            }
        }
    } 

    public void moveRight() {
        play = true;
        PlayerX += 20; // if right move 20 pixels to the right 
    }

    public void moveLeft() {
        play = true;
        PlayerX -= 20; // if left move 20 pixels to the left 
    }
}
