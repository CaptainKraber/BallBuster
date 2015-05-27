/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//HEEEEEEEEEEEEEEEEEEYYYYYYYYYYYYYYYYYYYYYY PRESS ENTER TO START ANIMATION

/**
 *
 * @author simma1980
 */
// make sure you rename this class if you are doing a copy/paste
public class Face extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 28;
    long desiredTime = (1000) / desiredFPS;
    //frames keeps track of the number of frames
    //subtractor slowly decreases the rate of rising smoke
    //variables with a number and 'rowx' are holding the value for the row specified number, allowing me to move them
    //variables with a number and 'upy' are holding the value for the column specified number, allowing me to move them
    //variables with a number, second number, and 'r, g, or b' holding rgb values of 
    int frames = 0, subtractor = 4, onerowx = 220, tworowx = 290, threerowx = 360, fourrowx = 430, fiverowx = 500, sixrowx = 570, sevenrowx = 640, eightrowx = 710, oneupy = 220, twoupy = 290, threeupy = 360, fourupy = 430, fiveupy = 500, sixupy = 570, sevenupy = 640, eightupy = 710, xy = 70;
    int oneoner = 191, oneoneg = 219, oneoneb = 186, onetwor = 86, onetwog = 156, onetwob = 75, onethreer = 121, onethreeg = 227, onethreeb = 102, onefourr = 76, onefourg = 204, onefourb = 53, onefiver = 141, onefiveg = 245, onefivesub = onefiveg, onefiveb = 122, onesixr = 171, onesixg = 204, onesixb = 165, onesevenr = 214, oneseveng = 230, onesevenb = 204, oneeightr = 71, oneeightg = 168, oneeightb = 72, twooner = 101, twooneg = 219, twooneb = 103, twotwor = 123, twotwog = 156, twotwob = 123, twothreer = 0, twothreeg = 0, twothreeb = 0, twofiver = 81, twofiveg = 214, twofiveb = 47, twosixr = 82, twosixg = 179, twosixb = 85, twosevenr = 125, twoseveng = 189, twosevenb = 127, twoeightr = 160, twoeightg = 219, twoeightb = 162, threeoner = 146, threeoneg = 222, threeoneb = 149, threetwor = 77, threetwog = 165, threetwob = 80, threefiver = 80, threefiveg = 230, threefiveb = 85, fouroner = 167, fouroneg = 212, fouroneb = 169, fourtwor = 70, fourtwog = 212, fourtwob = 86, fourthreer = 175, fourthreeg = 219, fourthreeb = 181, fourfourr = 64, fourfourg = 173, fourfourb = 77, foureightr = 136, foureightg = 247, foureightsub = foureightg, foureightb = 149, fiveoner = 98, fiveoneg = 245, fiveonesub = fiveoneg, fiveoneb = 115, fivetwor = 97, fivetwog = 140, fivetwob = 102, fivethreer = 77, fivethreeg = 227, fivethreeb = 107, fivefourr = 18, fivefourg = 135, fivefourb = 31, fiveeightr = 186, fiveeightg = 232, fiveeightb = 191, sixoner = 92, sixoneg = 237, sixonesub = sixoneg, sixoneb = 109, sixtwor = 83, sixtwog = 245, sixtwosub = sixtwog, sixtwob = 102, sixfiver = 192, sixfiveg = 209, sixfiveb = 190, sevenoner = 169, sevenoneg = 219, sevenoneb = 164, seventwor = 14, seventwog = 138, seventwob = 3, sevenfiver = 105, sevenfiveg = 237, sevenfivesub = sevenfiveg, sevenfiveb = 92, sevensixr = 124, sevensixg = 161, sevensixb = 120, sevensevenr = 158, sevenseveng = 240, sevensevensub = sevenseveng, sevensevenb = 151, seveneightr = 62, seveneightg = 217, seveneightb = 48, eightoner = 69, eightoneg = 156, eightoneb = 61, eighttwor = 214, eighttwog = 214, eighttwob = 214, eightthreer = 219, eightthreeg = 219, eightthreeb = 219, eightfourr = 90, eightfourg = 232, eightfourb = 77, eightfiver = 60, eightfiveg = 209, eightfiveb = 46, eightsixr = 185, eightsixg = 214, eightsixb = 182, eightsevenr = 68, eightseveng = 158, eightsevenb = 60, eighteightr = 120, eighteightg = 222, eighteightb = 111;
    int q, smokeblackonex = (int)(Math.random() * 879) + 1, smokeblacktwox = (int) (Math.random() * 789) + 1, smokeblackoney = (int) (Math.random() * 849) + 1, smokeblacktwoy = (int) (Math.random() * 789) + 1, smokegreyonex = (int)(Math.random() * 879) + 1, smokegreytwox = (int) (Math.random() * 789) + 1, smokegreyoney = (int) (Math.random() * 849) + 1, smokegreytwoy = (int) (Math.random() * 789) + 1, smokewhiteonex = (int) (Math.random() * 879) + 1, smokewhitetwox = (int) (Math.random() * 789) + 1, smokewhiteoney = (int) (Math.random() * 849) + 1, smokewhitetwoy = (int) (Math.random() * 789) + 1;
    int decayone = (int)(Math.random() * 56) + 35, decaytwo = (int)(Math.random() * 56) + 35, decaythree = (int)(Math.random() * 56) + 35, decayfour = (int)(Math.random() * 56) + 35, decayfive = (int)(Math.random() * 56) + 35, decaysix = (int)(Math.random() * 56) + 35, decayseven = (int)(Math.random() * 56) + 35, decayeight = (int)(Math.random() * 56) + 35, decaynine = (int)(Math.random() * 56) + 35, decayten = (int)(Math.random() * 56) + 35, decayeleven = (int)(Math.random() * 56) + 35, decaytwelve = (int)(Math.random() * 56) + 35, decaythirteen = (int)(Math.random() * 56) + 35;
    boolean enter = false;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE  
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (frames < 35) {
            Color oneone = new Color(oneoner, oneoneg, oneoneb), onetwo = new Color(onetwor, onetwog, onetwob), onethree = new Color(onethreer, onethreeg, onethreeb), onefour = new Color(onefourr, onefourg, onefourb);
            if (onefiveg > 255) {
                onefivesub = 255;
            }
            Color onefive = new Color(onefiver, onefivesub, onefiveb), onesix = new Color(onesixr, onesixg, onesixb), oneseven = new Color(onesevenr, oneseveng, onesevenb), oneeight = new Color(oneeightr, oneeightg, oneeightb), twoone = new Color(twooner, twooneg, twooneb), twotwo = new Color(twotwor, twotwog, twotwob), twothree = new Color(twothreer, twothreeg, twothreeb), twofive = new Color(twofiver, twofiveg, twofiveb), twosix = new Color(twosixr, twosixg, twosixb), twoseven = new Color(twosevenr, twoseveng, twosevenb), twoeight = new Color(twoeightr, twoeightg, twoeightb), threeone = new Color(threeoner, threeoneg, threeoneb), threetwo = new Color(threetwor, threetwog, threetwob), threefive = new Color(threefiver, threefiveg, threefiveb), fourone = new Color(fouroner, fouroneg, fouroneb), fourtwo = new Color(fourtwor, fourtwog, fourtwob), fourthree = new Color(fourthreer, fourthreeg, fourthreeb), fourfour = new Color(fourfourr, fourfourg, fourfourb);
            if (foureightg > 255) {
                foureightsub = 255;
            }
            Color foureight = new Color(foureightr, foureightsub, foureightb);
            if (fiveoneg > 255) {
                fiveonesub = 255;
            }
            Color fiveone = new Color(fiveoner, fiveonesub, fiveoneb), fivetwo = new Color(fivetwor, fivetwog, fivetwob), fivethree = new Color(fivethreer, fivethreeg, fivethreeb), fivefour = new Color(fivefourr, fivefourg, fivefourb), fiveeight = new Color(fiveeightr, fiveeightg, fiveeightb);
            if (sixoneg > 255) {
                sixonesub = 255;
            }
            Color sixone = new Color(sixoner, sixonesub, sixoneb);
            if (sixtwog > 255) {
                sixtwosub = 255;
            }
            Color sixtwo = new Color(sixtwor, sixtwosub, sixtwob), sixfive = new Color(sixfiver, sixfiveg, sixfiveb), sevenone = new Color(sevenoner, sevenoneg, sevenoneb), seventwo = new Color(seventwor, seventwog, seventwob);
            if (sevenfiveg > 255) {
                sevenfivesub = 255;
            }
            Color sevenfive = new Color(sevenfiver, sevenfivesub, sevenfiveb), sevensix = new Color(sevensixr, sevensixg, sevensixb);
            if (sevenseveng > 255) {
                sevensevensub = 255;
            }
            Color sevenseven = new Color(sevensevenr, sevensevensub, sevensevenb), seveneight = new Color(seveneightr, eightseveng, seveneightb), eightone = new Color(eightoner, eightoneg, eightoneb), eighttwo = new Color(eighttwor, eighttwog, eighttwob), eightthree = new Color(eightthreer, eightthreeg, eightthreeb), eightfour = new Color(eightfourr, eightfourg, eightfourb), eightfive = new Color(eightfiver, eightfiveg, eightfiveb), eightsix = new Color(eightsixr, eightsixg, eightsixb), eightseven = new Color(eightsevenr, eightseveng, eightsevenb), eighteight = new Color(eighteightr, eighteightg, eighteightb);

            g.setColor(oneone);
            g.fillRect(onerowx, oneupy, xy, xy);
            g.setColor(onetwo);
            g.fillRect(onerowx, twoupy, xy, xy);
            g.setColor(onethree);
            g.fillRect(onerowx, threeupy, xy, xy);
            g.setColor(onefour);
            g.fillRect(onerowx, fourupy, xy, xy);
            g.setColor(onefive);
            g.fillRect(onerowx, fiveupy, xy, xy);
            g.setColor(onesix);
            g.fillRect(onerowx, sixupy, xy, xy);
            g.setColor(oneseven);
            g.fillRect(onerowx, sevenupy, xy, xy);
            g.setColor(oneeight);
            g.fillRect(onerowx, eightupy, xy, xy);
            g.setColor(twoone);
            g.fillRect(tworowx, oneupy, xy, xy);
            g.setColor(twotwo);
            g.fillRect(tworowx, twoupy, xy, xy);
            g.setColor(twothree);
            g.fillRect(tworowx, threeupy, xy, xy);
            g.fillRect(tworowx, fourupy, xy, xy);
            g.setColor(twofive);
            g.fillRect(tworowx, fiveupy, xy, xy);
            g.setColor(twosix);
            g.fillRect(tworowx, sixupy, xy, xy);
            g.setColor(twoseven);
            g.fillRect(tworowx, sevenupy, xy, xy);
            g.setColor(twoeight);
            g.fillRect(tworowx, eightupy, xy, xy);
            g.setColor(threeone);
            g.fillRect(threerowx, oneupy, xy, xy);
            g.setColor(threetwo);
            g.fillRect(threerowx, twoupy, xy, xy);
            g.setColor(twothree);
            g.fillRect(threerowx, threeupy, xy, xy);
            g.fillRect(threerowx, fourupy, xy, xy);
            g.setColor(threefive);
            g.fillRect(threerowx, fiveupy, xy, xy);
            g.setColor(twothree);
            g.fillRect(threerowx, sixupy, xy, xy);
            g.fillRect(threerowx, sevenupy, xy, xy);
            g.fillRect(threerowx, eightupy, xy, xy);
            g.setColor(fourone);
            g.fillRect(fourrowx, oneupy, xy, xy);
            g.setColor(fourtwo);
            g.fillRect(fourrowx, twoupy, xy, xy);
            g.setColor(fourthree);
            g.fillRect(fourrowx, threeupy, xy, xy);
            g.setColor(fourfour);
            g.fillRect(fourrowx, fourupy, xy, xy);
            g.setColor(twothree);
            g.fillRect(fourrowx, fiveupy, xy, xy);
            g.fillRect(fourrowx, sixupy, xy, xy);
            g.fillRect(fourrowx, sevenupy, xy, xy);
            g.setColor(foureight);
            g.fillRect(fourrowx, eightupy, xy, xy);
            g.setColor(fiveone);
            g.fillRect(fiverowx, oneupy, xy, xy);
            g.setColor(fivetwo);
            g.fillRect(fiverowx, twoupy, xy, xy);
            g.setColor(fivethree);
            g.fillRect(fiverowx, threeupy, xy, xy);
            g.setColor(fivefour);
            g.fillRect(fiverowx, fourupy, xy, xy);
            g.setColor(twothree);
            g.fillRect(fiverowx, fiveupy, xy, xy);
            g.fillRect(fiverowx, sixupy, xy, xy);
            g.fillRect(fiverowx, sevenupy, xy, xy);
            g.setColor(fiveeight);
            g.fillRect(fiverowx, eightupy, xy, xy);
            g.setColor(sixone);
            g.fillRect(sixrowx, oneupy, xy, xy);
            g.setColor(sixtwo);
            g.fillRect(sixrowx, twoupy, xy, xy);
            g.setColor(twothree);
            g.fillRect(sixrowx, threeupy, xy, xy);
            g.fillRect(sixrowx, fourupy, xy, xy);
            g.setColor(sixfive);
            g.fillRect(sixrowx, fiveupy, xy, xy);
            g.setColor(twothree);
            g.fillRect(sixrowx, sixupy, xy, xy);
            g.fillRect(sixrowx, sevenupy, xy, xy);
            g.fillRect(sixrowx, eightupy, xy, xy);
            g.setColor(sevenone);
            g.fillRect(sevenrowx, oneupy, xy, xy);
            g.setColor(seventwo);
            g.fillRect(sevenrowx, twoupy, xy, xy);
            g.setColor(twothree);
            g.fillRect(sevenrowx, threeupy, xy, xy);
            g.fillRect(sevenrowx, fourupy, xy, xy);
            g.setColor(sevenfive);
            g.fillRect(sevenrowx, fiveupy, xy, xy);
            g.setColor(sevensix);
            g.fillRect(sevenrowx, sixupy, xy, xy);
            g.setColor(sevenseven);
            g.fillRect(sevenrowx, sevenupy, xy, xy);
            g.setColor(seveneight);
            g.fillRect(sevenrowx, eightupy, xy, xy);
            g.setColor(eightone);
            g.fillRect(eightrowx, oneupy, xy, xy);
            g.setColor(eighttwo);
            g.fillRect(eightrowx, twoupy, xy, xy);
            g.setColor(eightthree);
            g.fillRect(eightrowx, threeupy, xy, xy);
            g.setColor(eightfour);
            g.fillRect(eightrowx, fourupy, xy, xy);
            g.setColor(eightfive);
            g.fillRect(eightrowx, fiveupy, xy, xy);
            g.setColor(eightsix);
            g.fillRect(eightrowx, sixupy, xy, xy);
            g.setColor(eightseven);
            g.fillRect(eightrowx, sevenupy, xy, xy);
            g.setColor(eighteight);
            g.fillRect(eightrowx, eightupy, xy, xy);
        } else {
            g.setColor(Color.BLACK);
            if (decayten > frames) {
            g.fillRect(smokeblackonex, smokeblackoney, 30, 30);
            }
            if (decaysix > frames) {
            g.fillRect(smokeblackonex, smokeblackoney + 30, 30, 30);
            }
            if (decaynine > frames) {
            g.fillRect(smokeblackonex, smokeblackoney + 90, 30, 30);  
            }
            if (decaythirteen > frames) {
            g.fillRect(smokeblackonex + 30, smokeblackoney + 30, 30, 30);
            }
            if (decayfour > frames) {
            g.fillRect(smokeblackonex + 30, smokeblackoney + 60, 30, 30);
            } 
            if (decaysix > frames) {
            g.fillRect(smokeblackonex + 30, smokeblackoney + 90, 30, 30);
            }
            if (decayone > frames) {
            g.fillRect(smokeblackonex + 30, smokeblackoney + 120, 30, 30);
            }
            if (decayfour > frames) {
            g.fillRect(smokeblackonex + 60, smokeblackoney, 30, 30);
            }
            if (decaytwelve > frames) {
            g.fillRect(smokeblackonex + 60, smokeblackoney + 60, 30, 30);
            }
            if (decaythree > frames) {
            g.fillRect(smokeblackonex + 60, smokeblackoney + 90, 30, 30);
            }
            if (decaythree > frames) {
            g.fillRect(smokeblackonex + 90, smokeblackoney + 90, 30, 30);
            }
            if (decayfive > frames) {
            g.fillRect(smokeblacktwox, smokeblacktwoy + 90, 30, 30);     
            }
            if (decaynine > frames) {
            g.fillRect(smokeblacktwox + 30, smokeblacktwoy + 30, 30, 30);
            }
            if (decayone > frames) {
            g.fillRect(smokeblacktwox + 30, smokeblacktwoy + 90, 30, 30);
            }
            if (decayfour > frames) {
            g.fillRect(smokeblacktwox + 30, smokeblacktwoy + 150, 30, 30);
            }
            if (decaythirteen > frames) {
            g.fillRect(smokeblacktwox + 60, smokeblacktwoy + 30, 30, 30);
            }
            if (decayfour > frames) {
            g.fillRect(smokeblacktwox + 60, smokeblacktwoy + 60, 30, 30);
            }
            if (decayfive > frames) {
            g.fillRect(smokeblacktwox + 60, smokeblacktwoy + 120, 30, 30);
            }
            if (decayten > frames) {
            g.fillRect(smokeblacktwox + 60, smokeblacktwoy + 180, 30, 30);
            }
            if (decayseven > frames) {
            g.fillRect(smokeblacktwox + 90, smokeblacktwoy, 30, 30);
            }
            if (decayeight > frames) {
            g.fillRect(smokeblacktwox + 90, smokeblacktwoy + 60, 30, 30);
            }
            if (decaytwelve > frames) {
            g.fillRect(smokeblacktwox + 90, smokeblacktwoy + 90, 30, 30);
            }
            if (decayeight > frames) { 
            g.fillRect(smokeblacktwox + 90, smokeblacktwoy + 120, 30, 30);
            }
            if (decayeleven > frames) {
            g.fillRect(smokeblacktwox + 90, smokeblacktwoy + 150, 30, 30);
            }
            if (decayeleven > frames) {
            g.fillRect(smokeblacktwox + 120, smokeblacktwoy + 30, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokeblacktwox + 120, smokeblacktwoy + 90, 30, 30);
            }
            if (decaysix > frames) {
            g.fillRect(smokeblacktwox + 120, smokeblacktwoy + 120, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokeblacktwox + 150, smokeblacktwoy + 60, 30, 30);
            }
            if (decayfive > frames) {
            g.fillRect(smokeblacktwox + 150, smokeblacktwoy + 120, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokeblacktwox + 150, smokeblacktwoy + 150, 30, 30);
            }
            if (decaythree > frames) {
            g.fillRect(smokeblacktwox + 180, smokeblacktwoy + 60, 30, 30);
            }
            g.setColor(Color.GRAY);
            if (decaythirteen > frames) {
            g.fillRect(smokegreyonex, smokegreyoney, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokegreyonex, smokegreyoney + 30, 30, 30);
            }
            if (decayeight > frames) {
            g.fillRect(smokegreyonex, smokegreyoney + 90, 30, 30);    
            }
            if (decaysix > frames) {
            g.fillRect(smokegreyonex + 30, smokegreyoney + 30, 30, 30);
            }
            if (decayseven > frames) {
            g.fillRect(smokegreyonex + 30, smokegreyoney + 60, 30, 30);
            }
            if (decayten > frames) {
            g.fillRect(smokegreyonex + 30, smokegreyoney + 90, 30, 30);
            }
            if (decaysix > frames) {
            g.fillRect(smokegreyonex + 30, smokegreyoney + 120, 30, 30);
            }
            if (decayone > frames) {
            g.fillRect(smokegreyonex + 60, smokegreyoney, 30, 30);
            }
            if (decaynine > frames) {
            g.fillRect(smokegreyonex + 60, smokegreyoney + 60, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokegreyonex + 60, smokegreyoney + 90, 30, 30);
            }
            if (decayfive > frames) {
            g.fillRect(smokegreyonex + 90, smokegreyoney + 90, 30, 30);
            }
            if (decayone > frames) {
            g.fillRect(smokegreytwox, smokegreytwoy + 90, 30, 30);
            }
            if (decaytwelve > frames) {
            g.fillRect(smokegreytwox + 30, smokegreytwoy + 30, 30, 30);
            }
            if (decaytwelve > frames) {
            g.fillRect(smokegreytwox + 30, smokegreytwoy + 90, 30, 30);
            }
            if (decayseven > frames) {
            g.fillRect(smokegreytwox + 30, smokegreytwoy + 150, 30, 30);
            }
            if (decaythree > frames) {
            g.fillRect(smokegreytwox + 60, smokegreytwoy + 30, 30, 30);
            }
            if (decayfour > frames) {
            g.fillRect(smokegreytwox + 60, smokegreytwoy + 60, 30, 30);
            }
            if (decaythree > frames) {
            g.fillRect(smokegreytwox + 60, smokegreytwoy + 120, 30, 30);
            }
            if (decayseven > frames) {
            g.fillRect(smokegreytwox + 60, smokegreytwoy + 180, 30, 30);
            }
            if (decayfour > frames) {
            g.fillRect(smokegreytwox + 90, smokegreytwoy, 30, 30);
            }
            if (decayeleven > frames) {
            g.fillRect(smokegreytwox + 90, smokegreytwoy + 60, 30, 30);
            }
            if (decaytwelve > frames) {
            g.fillRect(smokegreytwox + 90, smokegreytwoy + 90, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokegreytwox + 90, smokegreytwoy + 120, 30, 30);
            }
            if (decayeight > frames) {
            g.fillRect(smokegreytwox + 90, smokegreytwoy + 150, 30, 30);
            }
            if (decayeight > frames) {
            g.fillRect(smokegreytwox + 120, smokegreytwoy + 30, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokegreytwox + 120, smokegreytwoy + 90, 30, 30);
            }
            if (decayten > frames) {
            g.fillRect(smokegreytwox + 120, smokegreytwoy + 120, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokegreytwox + 150, smokegreytwoy + 60, 30, 30);
            }
            if (decayfour > frames) {
            g.fillRect(smokegreytwox + 150, smokegreytwoy + 120, 30, 30);
            }
            if (decayone > frames) {
            g.fillRect(smokegreytwox + 150, smokegreytwoy + 150, 30, 30);
            }
            if (decayeleven > frames) {
            g.fillRect(smokegreytwox + 180, smokegreytwoy + 60, 30, 30);
            }            
            g.setColor(Color.WHITE);
            if (decaytwelve > frames) {
            g.fillRect(smokewhiteonex, smokewhiteoney, 30, 30);
            }
            if (decayone > frames) {
            g.fillRect(smokewhiteonex, smokewhiteoney + 30, 30, 30);
            }
            if (decaynine > frames) {
            g.fillRect(smokewhiteonex, smokewhiteoney + 90, 30, 30);   
            }
            if (decaythree > frames) {
            g.fillRect(smokewhiteonex + 30, smokewhiteoney + 30, 30, 30);
            }
            if (decayeight > frames) {
            g.fillRect(smokewhiteonex + 30, smokewhiteoney + 60, 30, 30);
            }
            if (decaynine > frames) {
            g.fillRect(smokewhiteonex + 30, smokewhiteoney + 90, 30, 30);
            }
            if (decayseven > frames) {
            g.fillRect(smokewhiteonex + 30, smokewhiteoney + 120, 30, 30);
            }
            if (decaythree > frames) {
            g.fillRect(smokewhiteonex + 60, smokewhiteoney, 30, 30);
            }
            if (decayfour > frames) {
            g.fillRect(smokewhiteonex + 60, smokewhiteoney + 60, 30, 30);
            }
            if (decayfive > frames) {
            g.fillRect(smokewhiteonex + 60, smokewhiteoney + 90, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokewhiteonex + 90, smokewhiteoney + 90, 30, 30);
            }
            if (decayeight > frames) {
            g.fillRect(smokewhitetwox, smokewhitetwoy + 90, 30, 30);       
            }
            if (decayone > frames) {
            g.fillRect(smokewhitetwox + 30, smokewhitetwoy + 30, 30, 30);
            }
            if (decaythree > frames) {
            g.fillRect(smokewhitetwox + 30, smokewhitetwoy + 90, 30, 30);
            }
            if (decaysix > frames) {
            g.fillRect(smokewhitetwox + 30, smokewhitetwoy + 150, 30, 30);
            }
            if (decayeleven > frames) {
            g.fillRect(smokewhitetwox + 60, smokewhitetwoy + 30, 30, 30);
            }
            if (decayseven > frames) {
            g.fillRect(smokewhitetwox + 60, smokewhitetwoy + 60, 30, 30);
            }
            if (decaytwo > frames) {
            g.fillRect(smokewhitetwox + 60, smokewhitetwoy + 120, 30, 30);
            }
            if (decayten > frames) {
            g.fillRect(smokewhitetwox + 60, smokewhitetwoy + 180, 30, 30);
            }
            if (decaysix > frames) {
            g.fillRect(smokewhitetwox + 90, smokewhitetwoy, 30, 30);
            }
            if (decaytwelve > frames) {
            g.fillRect(smokewhitetwox + 90, smokewhitetwoy + 60, 30, 30);
            }
            if (decaysix > frames) {
            g.fillRect(smokewhitetwox + 90, smokewhitetwoy + 90, 30, 30);
            }
            if (decayfive > frames) {
            g.fillRect(smokewhitetwox + 90, smokewhitetwoy + 120, 30, 30);
            }
            if (decaythirteen > frames) {
            g.fillRect(smokewhitetwox + 90, smokewhitetwoy + 150, 30, 30);
            }
            if (decaynine > frames) {
            g.fillRect(smokewhitetwox + 120, smokewhitetwoy + 30, 30, 30);
            }
            if (decayeight > frames) {
            g.fillRect(smokewhitetwox + 120, smokewhitetwoy + 90, 30, 30);
            }
            if (decayeight > frames) {
            g.fillRect(smokewhitetwox + 120, smokewhitetwoy + 120, 30, 30);
            }
            if (decayfive > frames) {
            g.fillRect(smokewhitetwox + 150, smokewhitetwoy + 60, 30, 30);
            }
            if (decaythree > frames) {
            g.fillRect(smokewhitetwox + 150, smokewhitetwoy + 120, 30, 30);
            }
            if (decayeleven > frames) {
            g.fillRect(smokewhitetwox + 150, smokewhitetwoy + 150, 30, 30);
            }
            if (decaynine > frames) {
            g.fillRect(smokewhitetwox + 180, smokewhitetwoy + 60, 30, 30);
            }
        }
    }
    // GAME DRAWING ENDS HERE

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
            if(frames > 34) {               
                if (frames == 49) {
                    subtractor = 3;
                } 
                if (frames == 63) {
                    subtractor = 2;
                }
                if (frames == 77) {
                    subtractor = 1;
                }
                smokeblackoney -= subtractor;
                smokeblacktwoy -= subtractor;
                smokegreyoney -= subtractor;
                smokegreytwoy -= subtractor;
                smokewhiteoney -= subtractor;
                smokewhitetwoy -= subtractor;
            }
            if (enter == true) {
                if (frames > -1 && frames < 35) {
                    if (frames == 0 || frames == 14 || frames == 28) {
                        q = 3;
                    }
                    if (frames == 7 || frames == 21) {
                        q = -3;
                    }
                    if (true) {
                        oneoner += q;
                        oneoneg += q;
                        oneoneb += q;
                        onetwor += q;
                        onetwog += q;
                        onetwob += q;
                        onethreer += q;
                        onethreeg += q;
                        onethreeb += q;
                        onefourr += q;
                        onefourg += q;
                        onefourb += q;
                        onefiver += q;
                        onefiveg += q;
                        onefiveb += q;
                        onefivesub += q;
                        onesixr += q;
                        onesixg += q;
                        onesixb += q;
                        onesevenr += q;
                        oneseveng += q;
                        onesevenb += q;
                        oneeightr += q;
                        oneeightg += q;
                        oneeightb += q;
                        twooner += q;
                        twooneg += q;
                        twooneb += q;
                        twotwor += q;
                        twotwog += q;
                        twotwob += q;
                        twothreer += q;
                        twothreeg += q;
                        twothreeb += q;
                        twofiver += q;
                        twofiveg += q;
                        twofiveb += q;
                        twosixr += q;
                        twosixg += q;
                        twosixb += q;
                        twosevenr += q;
                        twoseveng += q;
                        twosevenb += q;
                        twoeightr += q;
                        twoeightg += q;
                        twoeightb += q;
                        threeoner += q;
                        threeoneg += q;
                        threeoneb += q;
                        threetwor += q;
                        threetwog += q;
                        threetwob += q;
                        threefiver += q;
                        threefiveg += q;
                        threefiveb += q;
                        fouroner += q;
                        fouroneg += q;
                        fouroneb += q;
                        fourtwor += q;
                        fourtwog += q;
                        fourtwob += q;
                        fourthreer += q;
                        fourthreeg += q;
                        fourthreeb += q;
                        fourfourr += q;
                        fourfourg += q;
                        foureightr += q;
                        foureightg += q;
                        foureightb += q;
                        foureightsub += q;
                        fiveoner += q;
                        fiveoneg += q;
                        fiveoneb += q;
                        fiveonesub += q;
                        fivetwor += q;
                        fivetwog += q;
                        fivetwob += q;
                        fivethreer += q;
                        fivethreeg += q;
                        fivethreeb += q;
                        fivefourr += q;
                        fivefourg += q;
                        fivefourb += q;
                        fiveeightr += q;
                        fiveeightg += q;
                        fiveeightb += q;
                        sixoner += q;
                        sixoneg += q;
                        sixoneb += q;
                        sixonesub += q;
                        sixtwor += q;
                        sixtwog += q;
                        sixtwob += q;
                        sixtwosub += q;
                        sixfiver += q;
                        sixfiveg += q;
                        sixfiveb += q;
                        sevenoner += q;
                        sevenoneg += q;
                        sevenoneb += q;
                        seventwor += q;
                        seventwog += q;
                        seventwob += q;
                        sevenfiver += q;
                        sevenfiveg += q;
                        sevenfiveb += q;
                        sevenfivesub += q;
                        sevensixr += q;
                        sevensixg += q;
                        sevensixb += q;
                        sevensevenr += q;
                        sevenseveng += q;
                        sevensevenb += q;
                        sevensevensub += q;
                        seveneightr += q;
                        seveneightg += q;
                        seveneightb += q;
                        eightoner += q;
                        eightoneg += q;
                        eightoneb += q;
                        eighttwor += q;
                        eighttwog += q;
                        eighttwob += q;
                        eightthreer += q;
                        eightthreeg += q;
                        eightthreeb += q;
                        eightfourr += q;
                        eightfourg += q;
                        eightfourb += q;
                        eightfiver += q;
                        eightfiveg += q;
                        eightfiveb += q;
                        eightsixr += q;
                        eightsixg += q;
                        eightsixb += q;
                        eightsevenr += q;
                        eightseveng += q;
                        eightsevenb += q;
                        eighteightr += q;
                        eighteightg += q;
                        eighteightb += q;
                    }
                    xy += 1;
                    onerowx += - 4;
                    tworowx += - 3;
                    threerowx += - 2;
                    fourrowx += - 1;
                    sixrowx += +1;
                    sevenrowx += +2;
                    eightrowx += +3;
                    oneupy += - 4;
                    twoupy += - 3;
                    threeupy += - 2;
                    fourupy += - 1;
                    sixupy += 1;
                    sevenupy += 2;
                    eightupy += 3;
                    frames++;
                }
            }          
            if (frames >= 35) {
                frames++;
                enter = false;
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
        Face game = new Face();
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
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ENTER) {
            enter = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ENTER) {
            enter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}