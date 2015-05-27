/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author simma1980
 */
// make sure you rename this class if you are doing a copy/paste
public class Pong extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int onew = 20;
    int oneh = 100;
    int onex = 0;
    int oney = HEIGHT / 2 - oneh / 2;
    int onespeed = 10;
    int twow = 20;
    int twoh = 100;
    int twox = WIDTH - 20;
    int twoy = HEIGHT / 2 - oneh / 2;
    int twospeed = 10;
    int ballw = 20;
    int ballh = 20;
    int ballx = WIDTH / 2 - ballw / 2;
    int bally = HEIGHT / 2 - ballh / 2;
    int balldx = 1;
    int balldy = 1;
    int ballspeed = 5;
    int onescore = 0;
    int twoscore = 0;
    int scoreone = 0, scoretwo = 0;
    boolean oneup = false;
    boolean onedown = false;
    boolean twoup = false;
    boolean twodown = false;
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)

    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.fillRect(ballx, bally, ballw, ballh);
        g.fillRect(onex, oney, onew, oneh);
        g.fillRect(twox, twoy, twow, twoh);

        g.drawString("Player 1: " + scoreone, 320, 50);
        g.drawString("Player 2: " + scoretwo, 400, 50);
        
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
            if (oneup) {
                oney -= onespeed;
            } else if (onedown) {
                oney += onespeed;
            }
            if (twoup) {
                twoy -= twospeed;
            } else if (twodown) {
                twoy += twospeed;
            }
            ballx += balldx * ballspeed;
            bally += balldy * ballspeed;
            if (ballx < 0) {
                scoretwo++;
                ballx = WIDTH/2 - ballw/2;
                bally = HEIGHT/2 - ballh/2;
            }
            if (bally < 0) {
                balldy = 1;
            }
            if (ballx > WIDTH - ballw) {
                scoreone++;
                ballx = WIDTH/2 - ballw/2;
                bally = HEIGHT/2 - ballh/2;
            }
            if (bally > HEIGHT - ballh) {
                balldy = -1;
            }

            if (ballx < 0) {
                ballx = 0;
            }
            if (ballx > WIDTH - ballw) {
                ballx = WIDTH - ballw;
            }
            if (bally < 0) {
                bally = 0;
            }
            if (bally > HEIGHT - ballh) {
                bally = HEIGHT - ballh;
            }
            if (!(ballx + ballw < onex || ballx > onex + onew || bally > oney + oneh || bally + ballh < oney)) {
                balldx = 1;
            }
            if (!(ballx + ballw < twox || ballx > twox + twow || bally > twoy + twoh || bally + ballh < twoy)) {
                balldx = -1;
            }
            if(scoreone == 10) {
                done = true;
            }
            if(scoreone == 10) {
                done = true;
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
        Pong game = new Pong();
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
        if (keycode == KeyEvent.VK_W) {
            oneup = true;
        } else if (keycode == KeyEvent.VK_S) {
            onedown = true;
        } else if (keycode == KeyEvent.VK_UP) {
            twoup = true;
        } else if (keycode == KeyEvent.VK_DOWN) {
            twodown = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_W) {
            oneup = false;
        } else if (keycode == KeyEvent.VK_S) {
            onedown = false;
        } else if (keycode == KeyEvent.VK_UP) {
            twoup = false;
        } else if (keycode == KeyEvent.VK_DOWN) {
            twodown = false;
        }

    }
}