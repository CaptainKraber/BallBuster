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
public class Dodgeball extends JComponent implements KeyListener {

    // Height and Width of our game
    //WIDTH must be at least 10* FPS
    //keep WIDTH and HEIGHT in aspect ratio of monitor
    static final int WIDTH = 853;
    static final int HEIGHT = 480;
    // sets the framerate and delay for our game
    // you just need to select an appropriate framerate
    //FPS must be multiple of 20
    long FPS = 60;
    long desiredTime = (1000) / FPS;
    //cloakbattery counts how much cloak is left
    //frames counts number of frames
    int cloakbattery = (int) (FPS) * 5, frames = 0, speed = (int) (WIDTH / (FPS * 1.5));
    //w, h, x, and y are player values respectively
    int w = WIDTH / 16, h = w, x = WIDTH / 2 - w / 2, y = HEIGHT / 2 - h / 2;
    //up, down, right, left, and cloak control player changes respectively
    //done controls whether entire game quits
    //gameover controls whether or not to display gameover screen
    //retry resets variables to default when pressed and game is over
    boolean up = false, down = false, right = false, left = false, cloak = false, done = false, retry = false, gameover = false, quit = false, enter = false, backspace = false;
    //colors holds 10 random numbers dictating each balls color for 10 different balls each game
    //directions holds 10 random numbers dictating each balls direction for 10 different balls each game
    //enemyx holds 10 random numbers dictating each balls x value for 10 different balls each game
    //enemyy holds 10 random numbers dictating each balls y value for 10 different balls each game
    int[] colors = new int[10], directions = new int[10], enemyx = new int[10], enemyy = new int[10], dx = new int[10], dy = new int[10];
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)

    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        // GAME DRAWING GOES HERE
        //if the game hasn't ended draw player, enemies, cloakbar, and cloakbattery
        if (!gameover) {
            if (frames >= FPS * 2 && frames < FPS * 4) {
                spawnballs(0, 1, 0, g);
            } else if (frames >= FPS * 4 && frames < FPS * 22) {
                spawnballs(0, 0, 1, g);
            } else if (frames >= FPS * 22 && frames < FPS * 24) {
                spawnballs(3, 4, 3, g);
            } else if (frames >= FPS * 24 && frames < FPS * 42) {
                spawnballs(0, 0, 4, g);
            } else if (frames >= FPS * 42 && frames < FPS * 44) {
                spawnballs(4, 5, 4, g);
            } else if (frames >= FPS * 44 && frames < FPS * 62) {
                spawnballs(0, 0, 5, g);
            } else if (frames >= FPS * 62 && frames < FPS * 64) {
                spawnballs(5, 6, 5, g);
            } else if (frames >= FPS * 64 && frames < FPS * 82) {
                spawnballs(0, 0, 6, g);
            } else if (frames >= FPS * 82 && frames < FPS * 84) {
                spawnballs(6, 7, 6, g);
            } else if (frames >= FPS * 84 && frames < FPS * 102) {
                spawnballs(0, 0, 7, g);
            } else if (frames >= FPS * 102 && frames < FPS * 104) {
                spawnballs(7, 8, 7, g);
            } else if (frames >= FPS * 104 && frames < FPS * 122) {
                spawnballs(0, 0, 8, g);
            } else if (frames >= FPS * 122 && frames < FPS * 124) {
                spawnballs(8, 9, 8, g);
            } else if (frames >= FPS * 124 && frames < FPS * 142) {
                spawnballs(0, 0, 9, g);
            } else if (frames >= FPS * 142 && frames < FPS * 144) {
                spawnballs(9, 10, 9, g);
            } else if (frames >= FPS * 144) {
                spawnballs(0, 0, 10, g);
            }
            //changes players colour when cloak is active and isn't empty   
            if (cloak && cloakbattery > 0) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.BLUE);
            }
            //player
            g.fillOval(x, y, w, h);
            //diplays percentage of battery remaining and charge meter 
            g.setColor(Color.DARK_GRAY);
            g.drawString("Cloak Battery: " + cloakbattery / (FPS / 20) + "%", WIDTH / 2 - 55, HEIGHT - 20);
            g.fillRect((WIDTH - ((int) (FPS) * 10)) / 2, HEIGHT - 15, cloakbattery * 2, 10);
            //
            //if the game has ended display GAMEOVER, time lasted, and the button used to restart
        } else {
            //displays time lasted and which button to use to restart
            g.setColor(Color.DARK_GRAY);
            g.drawString("GAME OVER", WIDTH / 2 - 35, HEIGHT / 2 - 15);
            g.drawString("You lasted " + frames / FPS + " seconds", WIDTH / 2 - 59, HEIGHT / 2);
            g.drawString("Press R to restart", WIDTH / 2 - 49, HEIGHT / 2 + 15);
            g.drawString("Press Q to quit", WIDTH / 2 - 41, HEIGHT / 2 + 30);
        }
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
        //for loops make random integers to determine colors, directions, xs, and ys of the enemies
        for (int i = 0; i < 10; i++) {
            colors[i] = (int) (Math.random() * 4);
        }
        for (int i = 0; i < 10; i++) {
            directions[i] = (int) (Math.random() * speed * 4);
        }
        for (int i = 0; i < 10; i++) {
            enemyx[i] = (int) (Math.random() * (WIDTH - w - 100)) + 50;
        }
        for (int i = 0; i < 10; i++) {
            enemyy[i] = (int) (Math.random() * (HEIGHT - h - 100)) + 50;
        }
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE
            if (!gameover) {
                frames++;
                if (up) {
                    if (cloak) {
                        y -= (int) (WIDTH / (FPS * 3));
                    } else {
                        y -= (int) (WIDTH / (FPS * 1.5));
                    }
                }
                if (down) {
                    if (cloak) {
                        y += (int) (WIDTH / (FPS * 3));
                    } else {
                        y += (int) (WIDTH / (FPS * 1.5));
                    }
                }
                if (right) {
                    if (cloak) {
                        x += (int) (WIDTH / (FPS * 3));
                    } else {
                        x += (int) (WIDTH / (FPS * 1.5));
                    }
                }
                if (left) {
                    if (cloak) {
                        x -= (int) (WIDTH / (FPS * 3));
                    } else {
                        x -= (int) (WIDTH / (FPS * 1.5));
                    }
                }
                if (enter && backspace) {
                    cloak = true;
                } else if (cloak && cloakbattery > 0) {
                    cloakbattery--;
                }
                if (x < 0) {
                    x = 0;
                }
                if (x + w > WIDTH) {
                    x = WIDTH - w;
                }
                if (y < 0) {
                    y = 0;
                }
                if (y + h > HEIGHT) {
                    y = HEIGHT - h;
                }
                if (!cloak && cloakbattery < FPS * 5 && frames % 18 == 0) {
                    cloakbattery++;
                }
                if (frames >= FPS * 4 && frames < FPS * 24) {
                    ballmovement(0, 1, frames);
                } else if (frames >= FPS * 24 && frames < FPS * 44) {
                    ballmovement(3, 4, frames);
                } else if (frames >= FPS * 44 && frames < FPS * 64) {
                    ballmovement(4, 5, frames);
                } else if (frames >= FPS * 64 && frames < FPS * 84) {
                    ballmovement(5, 6, frames);
                } else if (frames >= FPS * 84 && frames < FPS * 104) {
                    ballmovement(6, 7, frames);
                } else if (frames >= FPS * 104 && frames < FPS * 124) {
                    ballmovement(7, 8, frames);
                } else if (frames >= FPS * 124 && frames < FPS * 144) {
                    ballmovement(8, 9, frames);
                } else if (frames >= FPS * 144) {
                    ballmovement(9, 10, frames);
                }
                //for loop stops game when player touches enemy balls one, two, or three without cloak
                if (frames >= FPS * 4 && frames < FPS * 24) {
                    playerhitenemy(1);
                } else if (frames >= FPS * 24 && frames < FPS * 44) {
                    playerhitenemy(4);
                } else if (frames >= FPS * 44 && frames < FPS * 64) {
                    playerhitenemy(5);
                } else if (frames >= FPS * 64 && frames < FPS * 84) {
                    playerhitenemy(6);
                } else if (frames >= FPS * 84 && frames < FPS * 104) {
                    playerhitenemy(7);
                } else if (frames >= FPS * 104 && frames < FPS * 124) {
                    playerhitenemy(8);
                } else if (frames >= FPS * 124 && frames < FPS * 144) {
                    playerhitenemy(9);
                } else if (frames >= FPS * 144) {
                    playerhitenemy(10);
                }
                if (frames >= FPS * 4 && frames < FPS * 24) {
                    enemyhitenemy(3);
                } else if (frames >= FPS * 24 && frames < FPS * 44) {
                } else if (frames >= FPS * 44 && frames < FPS * 64) {
                } else if (frames >= FPS * 64 && frames < FPS * 84) {
                } else if (frames >= FPS * 84 && frames < FPS * 104) {
                } else if (frames >= FPS * 104 && frames < FPS * 124) {
                } else if (frames >= FPS * 144) {
                }
            } else if (retry && gameover) {
                cloakbattery = (int) (FPS) * 5;
                frames = 0;
                w = WIDTH / 16;
                h = w;
                x = WIDTH / 2 - w / 2;
                y = HEIGHT / 2 - h / 2;
                gameover = false;
                for (int i = 0; i < 10; i++) {
                    colors[i] = (int) (Math.random() * 4);
                }
                for (int i = 0; i < 10; i++) {
                    directions[i] = (int) (Math.random() * speed * 4);
                }
                for (int i = 0; i < 10; i++) {
                    enemyx[i] = (int) (Math.random() * (WIDTH - w - 100)) + 50;
                }
                for (int i = 0; i < 10; i++) {
                    enemyy[i] = (int) (Math.random() * (HEIGHT - h - 100)) + 50;
                }
            } else if (quit && gameover) {
                System.exit(0);
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
        Dodgeball game = new Dodgeball();
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
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_W) {
            up = true;
        } else if (k == KeyEvent.VK_S) {
            down = true;
        }
        if (k == KeyEvent.VK_A) {
            left = true;
        } else if (k == KeyEvent.VK_D) {
            right = true;
        }
        if (k == KeyEvent.VK_SHIFT) {
            cloak = true;
            enter = false;
            backspace = false;
        }
        if (k == KeyEvent.VK_R) {
            retry = true;
        }
        if (k == KeyEvent.VK_Q) {
            quit = true;
        }
        if (k == KeyEvent.VK_ENTER) {
            enter = true;
        }
        if (k == KeyEvent.VK_BACK_SPACE) {
            backspace = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_W) {
            up = false;
        } else if (k == KeyEvent.VK_S) {
            down = false;
        }
        if (k == KeyEvent.VK_A) {
            left = false;
        } else if (k == KeyEvent.VK_D) {
            right = false;
        }
        if (k == KeyEvent.VK_SHIFT) {
            cloak = false;
        }
        if (k == KeyEvent.VK_R) {
            retry = false;
        }
        if (k == KeyEvent.VK_Q) {
            quit = false;
        }
    }

    static int whxy(int w, int h, int x, int y) {

        return 0;
    }

    void spawnballs(int start, int outlines, int enemies, Graphics g) {
        for (int i = start; i < outlines; i++) {
            g.setColor(Color.BLACK);
            g.drawOval(enemyx[i], enemyy[i], w, h);
        }
        for (int i = 0; i < enemies; i++) {
            if (colors[i] == 0) {
                g.setColor(Color.RED);
            } else if (colors[i] == 1) {
                g.setColor(Color.GREEN);
            } else if (colors[i] == 2) {
                g.setColor(Color.ORANGE);
            } else {
                g.setColor(Color.GRAY);
            }
            g.fillOval(enemyx[i], enemyy[i], w, h);
        }
    }

    void ballmovement(int start, int enemies, int frames) {
        int starttime = 0;
        if (frames == FPS * 4) {
            starttime = 4;
        } else if (frames == FPS * 24) {
            starttime = 24;
        } else if (frames == FPS * 44) {
            starttime = 44;
        } else if (frames == FPS * 64) {
            starttime = 64;
        } else if (frames == FPS * 84) {
            starttime = 84;
        } else if (frames == FPS * 104) {
            starttime = 104;
        } else if (frames == FPS * 124) {
            starttime = 124;
        } else if (frames == FPS * 144) {
            starttime = 144;
        }
        for (int i = start; i < enemies && frames == FPS * starttime; i++) {
            for (int j = 0; j < speed; j++) {
                if (directions[i] == j) {
                    dx[i] = ((int) (WIDTH / (FPS * 1.5))) - j;
                    dy[i] = j;
                }
            }
            for (int j = 0; j < speed; j++) {
                if (directions[i] == j + (speed)) {
                    dx[i] = -j;
                    dy[i] = ((int) (WIDTH / (FPS * 1.5))) - j;
                }
            }
            for (int j = 0; j < speed; j++) {
                if (directions[i] == j + (speed * 2)) {
                    dx[i] = (-1 * ((int) (WIDTH / (FPS * 1.5)))) + j;
                    dy[i] = -j;
                }
            }
            for (int j = 0; j < speed; j++) {
                if (directions[i] == j + (speed * 3)) {
                    dx[i] = j;
                    dy[i] = (-1 * ((int) (WIDTH / (FPS * 1.5)))) + j;
                }
            }
        }
        for (int i = 0; i < enemies; i++) {
            if (enemyx[i] < 0 || enemyx[i] > WIDTH - w) {
                directions[i] += speed * 2 + Math.abs(dy[i]) * 2;
                if (directions[i] >= speed * 4) {
                    directions[i] -= speed * 4;
                }
                dx[i] *= -1;
            }
            if (enemyy[i] < 0 || enemyy[i] > HEIGHT - w) {
                directions[i] += speed * 2 + Math.abs(dx[i]) * 2;
                if (directions[i] >= speed * 4) {
                    directions[i] -= speed * 4;
                }
                dy[i] *= -1;
            }
        }
        for (int i = 0; i < enemies; i++) {
            enemyx[i] += dx[i];
            enemyy[i] += dy[i];
        }
        System.out.println(directions[0]);
    }

    boolean playerhitenemy(int enemies) {
        for (int i = 0; i < enemies; i++) {
            if ((!cloak || cloakbattery == 0 && cloak) && ((x - enemyx[i]) * (x - enemyx[i])) + ((y - enemyy[i]) * (y - enemyy[i])) < w * w) {
                return gameover = true;
            }
        }
        return gameover = false;
    }

    void enemyhitenemy(int balls) {
        for (int diff = 1; diff < balls; diff++) {
            for (int i = 0; i < balls - diff; i++) {
                if (((enemyx[i] - enemyx[i + 1]) * (enemyx[i] - enemyx[i + 1])) + (((enemyy[i] - enemyy[i + 1]) * (enemyy[i] - enemyy[i + 1]))) < w * w) {
                }
            }
        }
    }
}
