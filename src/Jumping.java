/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author simma1980
 */
// make sure you rename this class if you are doing a copy/paste
public class Jumping extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    boolean right = false, left = false, jump = false, inair = false;
    Rectangle player = new Rectangle(100, 500, 50, 50);
    Rectangle block = new Rectangle(300, 400, 100, 50);
    Rectangle block2 = new Rectangle(400, 395, 100, 50);
    int gravity = 1, dy = 0;
    int camx = 0;
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        g.setColor(Color.RED);
        g.fillRect(player.x - camx, player.y, player.width, player.height);
        g.setColor(Color.BLACK);
        g.fillRect(block.x - camx, block.y, block.width, block.height);
        g.fillRect(block2.x - camx, block2.y, block.width, block.height);
        
        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE

            dy = dy + gravity;
            if(right) {
                player.x += 10;
            } else if (left) {
                player.x -= 10;
            }
            if (jump && !inair) {
                dy = -20;
                inair = true;
            }
            player.y += dy;
            if(player.y > 500) {
                player.y = 500;
                dy = 0;
                inair = false;
            }      
            if (player.x < WIDTH/2) {
                camx = 0;
            } else {
                camx = player.x - WIDTH/2;
            }
            if (player.intersects(block)) {
                handlecollision(player, block);                             
            }
            if (player.intersects(block2)) {
                handlecollision(player, block2);
            } else if (player.y > 500){
                inair = true;
            }
            // GAME LOGIC ENDS HERE

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        Jumping game = new Jumping();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_D) {
            right = true;
        } 
        if (keycode == KeyEvent.VK_A) {
            left = true;
        }
        if (keycode == KeyEvent.VK_SPACE) {
            jump = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_D) {
            right = false;
        } 
        if (keycode == KeyEvent.VK_A) {
            left = false;
        }
        if (keycode == KeyEvent.VK_SPACE) {
            jump = false;
        }
    }
    public void handlecollision(Rectangle player, Rectangle block) {
        Rectangle overlap = player.intersection(block);
        if (overlap.height > overlap.width) {
            if (player.x < block.x) {
                player.x -= overlap.width;
            } else if (player.x + player.width > block.x + block.width) {
                player.x += overlap.width;
            }
        } else {
            dy = 0;
            if (player.y < block.y) {
                player.y -= overlap.height;
                inair = false;
            } else if (player.y + player.height > block.y + block.height) {
                player.y += overlap.height;
            }
        }
    }
}