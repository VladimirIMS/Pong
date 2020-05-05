package pong2;

import pong2.Paddle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Tastedruck von der Tastatur
public class KeyInput extends KeyAdapter {

private Paddle lp; // linke Paddle
private boolean lup = false; 
private boolean ldown = false;

private Paddle rp; // Rechte Paddle
private boolean rup = false;
private boolean rdown = false;

/**
* constructor
* @param p1 - paddle 1
* @param p2 - paddle 2
*/
public KeyInput(Paddle p1, Paddle p2) {
    lp = p1;
    rp = p2;

}


@Override
public void keyPressed(KeyEvent e) {
    
int key = e.getKeyCode();
//Paddle geht nach unten
if (key == KeyEvent.VK_DOWN) {
    rp.switchDirections(1);
    rdown = true;
}
//Paddle geht nach oben
if (key == KeyEvent.VK_UP) {
    
    rp.switchDirections(-1);
    rup = true;
}
		
// exit
if (key == KeyEvent.VK_ESCAPE) {
    System.exit(1);
}
}

@Override
public void keyReleased(KeyEvent e) {
    
int key = e.getKeyCode();
//Paddle geht nach unten stop
if (key == KeyEvent.VK_DOWN) {
    // rp.stop();
    rdown = false;
}


//Paddle geht nach oben stop
if (key == KeyEvent.VK_UP) {
    // rp.stop();
    rup = false;
}


// haltet den Puddle an (ton Tutorial)
if (!lup && !ldown)
    lp.stop();
    
if (!rup && !rdown)
    rp.stop();
}

}