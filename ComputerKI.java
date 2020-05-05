import java.awt.*;
import java.util.*;
import java.applet.*;
import pong2.Ball;
import pong2.Paddle;


public class ComputerKI extends Paddle {

// Diese Klasse Implementiert die Bewegungen des Computers und zeichnet auch dieComputerpaddel 

// Deklaration der Variablen
private int y;	// y - Position des Paddels
private int speed = 10;	// Geschwindigkeit in y - Richtung
private int real_y_pos;	// Speichert die wirkliche Paddelposition (Mitte)
private Color color; // Farbe von Paddle

// Konstanten
private static final int x = 15;// x - Position des Paddels
private static final int width = 22;// Grösse des Paddels in x - Richtung
private static final int hight = 85;// Grösse des Paddels in y - Richtung

// Ball
private static Ball ball;

// Construktor
public ComputerKI (int y, Ball ball,Color c)
{
            
    color = c;
    this.y = y;
    speed = 4;
    this.ball = ball;
}

// Diese Methode bewegt den Paddel 
public void move (Ball ball)
{

    // Berechnet die Mitte des Paddels
    real_y_pos = y + (hight / 2);

    /* Wenn sich Ball von Paddel wegbewegt, werden die Paddel in die Mitte zurückbewegt */
    if (ball.getVelocityX() > 0)
    {
        // Paddel oberhalb der Mitte
        if (real_y_pos < 148)
        {
            y += speed;
        }
        // Paddel unterhalb der Mitte
        else if (real_y_pos > 152)
        {
            y -= speed;
        } }
    else if (ball.getVelocityX() < 0)
    {
        // Solange Paddel nicht auf Höhe des Balles ist wird es bewegt
        if ( real_y_pos != ball.getY())
        {
            // Ball oberhalb von Paddel
            if (ball.getY() < real_y_pos)
            {
                y -= speed;
            }
            // Ball unterhalb von Paddel
            else if (ball.getY() > real_y_pos)
            {
                y += speed;
            }
        } } 

} 

public int getX()
{
	return x;
}

public int getY()
{
	return y;
}

public int getWidth()
{
	return width;
}

public int getHight()
{
	return hight;
}
public void run() {
throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}