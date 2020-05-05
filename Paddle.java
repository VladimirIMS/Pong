package pong2;

import pong2.Ball;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


//class für Paddle

public class Paddle {

private int x, y; //Position
private int vel = 0; // Geschwindigkeit und Richtung von Paddle
private int speed = 10; // Bewegungsgeschwindigkeit von Paddle
private int width = 22, height = 85; // Dimensionen
private int score = 0; // score für den Spieler
private Color color; // Farbe von Paddle
private boolean left; // true wenn das der Linke Paddle ist


/**
 * Anfangsigenschaften von Paddle erstellen
 * @param c
 * @param color -Farbe von Paddle
 * @param left  - boolean um zu wissen ob das der linke Paddle is
 */
public Paddle(){}
public Paddle(Color c, boolean left) 
{
    // Anfangseigenschaften
    
    color = c;
    this.left = left;

if (left) //Verschiedene X-Werte bei linken und rechten Paddle
    x = 0;
else
    x = Game.WIDTH - width;
    y = Game.HEIGHT / 2 - height / 2;

}

/*
* Anzeige von Paddle und Score
* @param g - Graphisches Objekt um  alles zu zeichnen
*/
public void draw(Graphics g) {

// Paddle anzeigen
g.setColor(color);
g.fillRect(x, y, width, height);

// Punktzahl anzeigen
int sx; //x Position von String
int padding = 25; // Abstand zwischen der punktierten Linie und Punktzahl
String scoreText = Integer.toString(score);
Font font = new Font("wah", Font.PLAIN, 50);

if (left) {
    int strWidth = g.getFontMetrics(font).stringWidth(scoreText); // WIDTH um es richtig zu zentrieren
    sx = Game.WIDTH / 2 - padding - strWidth;
} 

else {
    
    sx = Game.WIDTH / 2 + padding;
}

g.setFont(font);
g.drawString(scoreText, sx, 50);
}


// Richtung ändern @param direction - -1 für oben and 1 für unten
public void switchDirections(int direction) {
	vel = speed * direction;
}



//1 Punkt für den Spieler addieren
public void addPoint() {
score++;
    int gameStatus = 0;

         if(score == 5){//score ersetzen
            JLabel kek = new JLabel("Spieler hat gewonnen");
            kek.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, kek);
            System.exit(0);
        }
}

//  Paddlebewegung anhalten
public void stop() {
    vel = 0;
}

/*
* update Position und Kollision 
* @param b -Ball
*/
public void update(Ball b) {

// update Position
y = Game.ensureRange(y + vel, 0, Game.HEIGHT - height);

// Kollisionen
int ballY = b.getY();
int ballX = b.getX();


if (left) {

    if (ballX <= width + x && ballY + Ball.SIZE >= y && ballY <= y + height)
    b.changeXDir();

} 
else {

    if (ballX + Ball.SIZE >= x && ballY + Ball.SIZE >= y && ballY <= y + height)
    b.changeXDir();

        }

    }

}//addPoint