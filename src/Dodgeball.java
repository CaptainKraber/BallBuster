
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Dodgeball extends JComponent implements KeyListener {
    // Height and Width of our game
    //WIDTH must be at least 10* FPS
    //keep WIDTH and HEIGHT in aspect ratio of monitor

    static final int WIDTH = 640;
    static final int HEIGHT = 360;
    // sets the framerate and delay for our game
    // you just need to select an appropriate framerate
    //FPS must be multiple of 20
    long FPS = 60;
    long desiredTime = (1000) / FPS;
    //cloakbattery counts how much cloak is left
    //frames counts number of frames
    int cloakbattery = (int) (FPS) * 5, frames = 0, speed = (int) (WIDTH / (FPS * 1.5));
    //w, h, x, and y are player values respectively
    int w = WIDTH / 25, h = w, x = WIDTH / 2 - w / 2, y = HEIGHT / 2 - h / 2;
    //up, down, right, left, and cloak control player changes respectively
    //done controls whether entire game quits
    //gameover controls whether or not to display gameover screen
    //retry resets variables to default when pressed and game is over
    boolean[] spawned = new boolean[10];
    boolean up = false, down = false, right = false, left = false, cloak = false, done = false, retry = false, gameover = false, quit = false, enter = false, backspace = false, playerstart = false;
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
        if (!gameover && playerstart) {
            if (frames >= FPS * 2 && frames < FPS * 4) {
                spawnballs(3, 0, g);
                g.drawString("Level 1", WIDTH / 2 - 18, HEIGHT / 4);
                g.drawString("Let the fun begin!", WIDTH / 2 - 43, HEIGHT / 4 + 25);
            } else if (frames >= FPS * 4 && frames < FPS * 32) {
                spawnballs(0, 3, g);
            } else if (frames >= FPS * 32 && frames < FPS * 34) {
                spawnballs(4, 3, g);
                g.drawString("Level 2", WIDTH / 2 - 18, HEIGHT / 4);
                g.drawString("Better get used to more coming...", WIDTH / 2 - 70, HEIGHT / 4 + 25);
            } else if (frames >= FPS * 34 && frames < FPS * 62) {
                spawnballs(0, 4, g);
            } else if (frames >= FPS * 62 && frames < FPS * 64) {
                spawnballs(5, 4, g);
            } else if (frames >= FPS * 64 && frames < FPS * 92) {
                spawnballs(0, 5, g);
            } else if (frames >= FPS * 92 && frames < FPS * 94) {
                spawnballs(6, 5, g);
            } else if (frames >= FPS * 94 && frames < FPS * 122) {
                spawnballs(0, 6, g);
            } else if (frames >= FPS * 122 && frames < FPS * 124) {
                spawnballs(7, 6, g);
            } else if (frames >= FPS * 124 && frames < FPS * 152) {
                spawnballs(0, 7, g);
            } else if (frames >= FPS * 152 && frames < FPS * 154) {
                spawnballs(8, 7, g);
            } else if (frames >= FPS * 154 && frames < FPS * 182) {
                spawnballs(0, 8, g);
            } else if (frames >= FPS * 182 && frames < FPS * 184) {
                spawnballs(9, 8, g);
            } else if (frames >= FPS * 184 && frames < FPS * 212) {
                spawnballs(0, 9, g);
            } else if (frames >= FPS * 212 && frames < FPS * 214) {
                spawnballs(10, 9, g);
            } else if (frames >= FPS * 214) {
                spawnballs(0, 10, g);
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
        } else if (gameover && playerstart && !retry && !quit) {
            //displays time lasted and which button to use to restart
            g.setColor(Color.DARK_GRAY);
            g.drawString("GAME OVER", WIDTH / 2 - 35, HEIGHT / 2 - 15);
            g.drawString("You lasted " + (int) (frames / FPS) + " seconds", WIDTH / 2 - 59, HEIGHT / 2);
            g.drawString("Press R to restart", WIDTH / 2 - 49, HEIGHT / 2 + 15);
            g.drawString("Press Q to quit", WIDTH / 2 - 41, HEIGHT / 2 + 30);
        } else if (!playerstart) {
            g.drawString("Press ENTER to begin", WIDTH / 2 - 62, HEIGHT / 4);
            g.drawString("Keep your ball", WIDTH / 4 - 40, HEIGHT / 2);
            g.drawString("Away from these balls", WIDTH / 4 - 59, HEIGHT / 2 + h * 9 / 5);
            g.drawString("W", WIDTH * 3 / 4 - w / 3 - w / 4, HEIGHT / 2 - h * 2 / 3 - w - w / 4);
            g.drawString("A", WIDTH * 3 / 4 - w * 4 / 3 - w / 4, HEIGHT / 2 - h / -3 - w);
            g.drawString("S", WIDTH * 3 / 4 - w / 3, HEIGHT / 2 - h / -3 - w);
            g.drawString("D", WIDTH * 3 / 4 + w * 2 / 3 + w / 4, HEIGHT / 2 - h / -3 - w);
            g.drawString("SPACE", WIDTH * 3 / 4 - w * 25 / 9, HEIGHT / 2 - h / -3 + w);
            g.drawString("Go up", WIDTH * 3 / 4 - w / 3 - w / 4, HEIGHT / 2 - w / 6 - w - w / 4);
            g.drawString("Go left", WIDTH * 3 / 4 - w * 4 / 3 - w / 4, HEIGHT / 2 + w * 5 / 6 - w);
            g.drawString("Go down", WIDTH * 3 / 4 - w * 9 / 20, HEIGHT / 2 + w * 5 / 6 - w);
            g.drawString("Go right", WIDTH * 3 / 4 + w * 10 / 16 + w / 4, HEIGHT / 2 + w * 5 / 6 - w);
            g.drawString("Cloak", WIDTH * 3 / 4 - w * 25 / 9, HEIGHT / 2 - h / -3 + w * 3 / 2);
            g.drawRoundRect(WIDTH * 3 / 4 - w / 2 - w / 4, HEIGHT / 2 - h - w - w / 4, w, h, w / 5, w / 5);
            g.drawRoundRect(WIDTH * 3 / 4 - w / 2, HEIGHT / 2 - w, w, h, w / 5, w / 5);
            g.drawRoundRect(WIDTH * 3 / 4 - w * 3 / 2 - w / 4, HEIGHT / 2 - w, w, h, w / 5, w / 5);
            g.drawRoundRect(WIDTH * 3 / 4 + w / 2 + 1 + w / 4, HEIGHT / 2 - w, w, h, w / 5, w / 5);
            g.drawRoundRect(WIDTH * 3 / 4 - w * 3, HEIGHT / 2 + w, w * 6, h, w / 5, w / 5);
            g.setColor(Color.BLUE);
            g.fillOval(WIDTH / 4 - w / 2, HEIGHT / 000002 + h / 4, w, h);
            g.setColor(Color.RED);
            g.fillOval(WIDTH / 4 - w * 11 / 4, HEIGHT / 2 + h * 2, w, h);
            g.setColor(Color.GREEN);
            g.fillOval(WIDTH / 4 - w * 05 / 4, HEIGHT / 2 + h * 2, w, h);
            g.setColor(Color.ORANGE);
            g.fillOval(WIDTH / 4 + w / 4, HEIGHT / 2 + h * 000002, w, h);
            g.setColor(Color.GRAY);
            g.fillOval(WIDTH / 4 + w * 7 / 04, HEIGHT / 2 + h * 2, w, h);
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
            enemyx[i] = (int) (Math.random() * (WIDTH - w - (2 * w))) + w;
        }
        for (int i = 0; i < 10; i++) {
            enemyy[i] = (int) (Math.random() * (HEIGHT - h - (2 * h))) + h;
        }
        for (int i = 0; i < 10; i++) {
            spawned[i] = false;
        }
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE
            if (!gameover && playerstart) {
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
                if (!cloak && cloakbattery < FPS * 5 && frames % 60 == 0) {
                    cloakbattery++;
                }
                if (frames >= 0 && frames < FPS * 2) {
                    movespawn();
                } else if (frames >= FPS * 4 && frames < FPS * 34) {
                    one_method_to_rule_them_all(0, 3);
                } else if (frames >= FPS * 34 && frames < FPS * 64) {
                    one_method_to_rule_them_all(3, 4);
                } else if (frames >= FPS * 64 && frames < FPS * 94) {
                    one_method_to_rule_them_all(4, 5);
                } else if (frames >= FPS * 94 && frames < FPS * 124) {
                    one_method_to_rule_them_all(5, 6);
                } else if (frames >= FPS * 124 && frames < FPS * 154) {
                    one_method_to_rule_them_all(6, 7);
                } else if (frames >= FPS * 154 && frames < FPS * 184) {
                    one_method_to_rule_them_all(7, 8);
                } else if (frames >= FPS * 184 && frames < FPS * 214) {
                    one_method_to_rule_them_all(8, 9);
                } else if (frames >= FPS * 214) {
                    one_method_to_rule_them_all(9, 10);
                }
            } else if (retry && gameover && playerstart) {
                cloakbattery = (int) (FPS) * 5;
                frames = 0;
                w = WIDTH / 25;
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
                for (int i = 0; i < 10; i++) {
                    spawned[i] = false;
                }
            } else if (quit && gameover && playerstart) {
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
        if (k == KeyEvent.VK_SPACE) {
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
            playerstart = true;
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
        if (k == KeyEvent.VK_SPACE) {
            cloak = false;
        }
        if (k == KeyEvent.VK_R) {
            retry = false;
        }
        if (k == KeyEvent.VK_Q) {
            quit = false;
        }
    }

    void spawnballs(int outlines, int enemies, Graphics g) {
        for (int i = enemies; i < outlines; i++) {
            g.setColor(Color.BLACK);
            g.drawOval(enemyx[i], enemyy[i], w, h);
        }
        for (int i = 0; i < enemies; i++) {
            if (frames < FPS * 244) {
                if (colors[i] == 0) {
                    g.setColor(Color.RED);
                } else if (colors[i] == 1) {
                    g.setColor(Color.GREEN);
                } else if (colors[i] == 2) {
                    g.setColor(Color.ORANGE);
                } else if (colors[i] == 3) {
                    g.setColor(Color.GRAY);
                }
            } else {
                if (cloak && cloakbattery > 0) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.BLUE);
                }
            }
            g.fillOval(enemyx[i], enemyy[i], w, h);
        }
    }

    void movespawn() {
        while (((enemyx[0] - enemyx[1]) * (enemyx[0] - enemyx[1])) + ((enemyy[0] - enemyy[1]) * (enemyx[0] - enemyx[1])) < w * w || ((enemyx[0] - enemyx[2]) * (enemyx[0] - enemyx[2])) + ((enemyy[0] - enemyy[2]) * (enemyx[0] - enemyx[2])) < w * w || ((enemyx[1] - enemyx[2]) * (enemyx[1] - enemyx[2])) + ((enemyy[1] - enemyy[2]) * (enemyx[1] - enemyx[2])) < w * w) {
            for (int i = 0; i < 3; i++) {
                enemyx[i] = (int) (Math.random() * (WIDTH - w - 100)) + 50;
            }
            for (int i = 0; i < 3; i++) {
                enemyy[i] = (int) (Math.random() * (HEIGHT - h - 100)) + 50;
            }
        }
    }

    boolean one_method_to_rule_them_all(int start, int end) {
        int[] time = new int[10];
        time[0] = 4;
        time[1] = 4;
        time[2] = 4;
        time[3] = 34;
        time[4] = 64;
        time[5] = 94;
        time[6] = 124;
        time[7] = 154;
        time[8] = 184;
        time[9] = 214;
        for (int i = start; i < end && frames >= FPS * time[i] && !spawned[i]; i++) {
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
            spawned[i] = true;
        }
        for (int diff = 1; diff < end; diff++) {
            for (int i = 0; i < end - diff; i++) {
                if (((enemyx[i] - enemyx[i + diff]) * (enemyx[i] - enemyx[i + diff])) + (((enemyy[i] - enemyy[i + diff]) * (enemyy[i] - enemyy[i + diff]))) < w * w) {
                    dx[i] = dx[i] + dx[i + diff];
                    dx[i + diff] = dx[i] - dx[i + diff];
                    dx[i] = dx[i] - dx[i + diff];
                    dy[i] = dy[i] + dy[i + diff];
                    dy[i + diff] = dy[i] - dy[i + diff];
                    dy[i] = dy[i] - dy[i + diff];
                }
            }
        }
        for (int i = 0; i < end; i++) {
            if (enemyx[i] < 0) {
                dx[i] = Math.abs(dx[i]);
            } else if (enemyx[i] > WIDTH - w) {
                dx[i] = -Math.abs(dx[i]);
            }
            if (enemyy[i] < 0) {
                dy[i] = Math.abs(dy[i]);
            } else if (enemyy[i] > HEIGHT - w) {
                dy[i] = -Math.abs(dy[i]);
            }
        }
        for (int i = 0; i < end; i++) {
            enemyx[i] += dx[i];
            enemyy[i] += dy[i];
        }
        for (int i = 0; i < end; i++) {
            if ((!cloak || cloakbattery == 0 && cloak) && ((x - enemyx[i]) * (x - enemyx[i])) + ((y - enemyy[i]) * (y - enemyy[i])) < w * w) {
                return gameover = true;
            }
        }
        return gameover = false;
    }
}
