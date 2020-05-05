package pong2;

import pong2.Game;
import pong2.Paddle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


// In dieser Klasse wir der Ball Programiert
 
public class Ball {

public static final int SIZE = 16;

private int x, y; // Position oben links Ecke
private int xVel, yVel; // weder 1 oder -1
private int speed = 5; // Schnelligket von dem Ball

// constructor
public Ball() {
    reset();
}

/**
* update Position und Kollision
* @param lp: Paddle links
* @param rp: Paddle rechts
*/

public void update(Paddle lp, Paddle rp) {

// update Position
    x += xVel * speed;
    y += yVel * speed;

// Kollision
                   
// mit Wänden

if (x + SIZE >= Game.WIDTH) { // Rechte Wand
	lp.addPoint();
        reset();

}

if (x <= 0) { // Linke Wand
	rp.addPoint();
	reset();
        
}


                
// wenn der Ball mit dem Boden und der Decke Kollidiert
if (y + SIZE >= Game.HEIGHT || y <= 0)
	changeYDir();

}
/**
* Ball zeichnen (ein Quadrat)
* @param g: Ein Objekt um sachen zu Zeichnen
*/
public void draw(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(x, y, SIZE, SIZE);
}
        
public int getY() {
    return y;
}

public int getX() {
    return x;
}

// Die Richtung verändern if ball nach rechts geht, geh nach links, sonst rechts
public void changeXDir() {
    xVel *= -1;
}
      
// Die Richtung verändern, wenn Ball nach oben geht, dann nach unten, sonst nach oben
public void changeYDir() {
    yVel *= -1;
}
        
	
// Ausgangsposition und Geschwindigkeit einstellen
private void reset() {
// Geschwindigkeit 
    yVel = Game.sign(Math.random() * 2.0 - 1);
    xVel = Game.sign(Math.random() * 2.0 - 1);
    
    
// Position 
    y = Game.HEIGHT / 2 - SIZE / 2;
    x = Game.WIDTH / 2 - SIZE / 2;
}

    void collideWithPlayer(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public int getVelocityX() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}