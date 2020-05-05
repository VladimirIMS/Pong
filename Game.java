package pong2;

import pong2.KeyInput;
import pong2.Ball;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;


//classe für verwaltung von dem spiel, zeichnung und update von physics

public class Game extends Canvas implements Runnable {

private static final long serialVersionUID = 1L;

public final static int WIDTH = 1000;
public final static int HEIGHT = WIDTH * 9 / 16; // 16:9 aspekt ratio

public boolean running = false; // true wenn das spiel lauft
private Thread gameThread;

// ball objekt
private Ball ball;


private Paddle leftPaddle;
private Paddle rightPaddle;

private MainMenu menu; // Main Menu objekt

/**
* constructor
*/
public Game() {

canvasSetup();
new Window("Pong", this);

initialise();

this.addKeyListener(new KeyInput(leftPaddle, rightPaddle));
this.addMouseListener(menu);
this.addMouseMotionListener(menu);
this.setFocusable(true);

	}

/**
* initialisierung von allen gameobjekten
*/
private void initialise() {
// Ballobjekt
ball = new Ball();

// Paddleobjekte
leftPaddle = new Paddle(Color.black, true);
rightPaddle = new Paddle(Color.black, false);

// initialisierung von main menu
menu = new MainMenu(this);
}

/**
* canvas setup für einstellungen und grössen
*/
private void canvasSetup() {
this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}

/**
* Game loop
*/
@Override
public void run() {

this.requestFocus();

// Timer (von Tutorial)
long lastTime = System.nanoTime();
int frames = 0;
double amountOfTicks = 60.0;
double ns = 1000000000 / amountOfTicks;
long timer = System.currentTimeMillis();
double delta = 0;
while (running) {
    long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;
  if (delta >= 1) {
    update();
    delta--;
    draw();
    frames++;
}
}


      
if (System.currentTimeMillis() - timer > 1000) {
timer += 1000;
System.out.println("FPS: " + frames);
frames = 0;
	}
stop();
}

//start von thread und dem spiel
public synchronized void start() {
    
gameThread = new Thread(this);
gameThread.start(); // start thread
running = true;
}

//Thread und game stoppen
public void stop() {
	try {
	gameThread.join();
	running = false;
} catch (InterruptedException e) {
	e.printStackTrace();
	}
}


public void draw() {
// Initialisierungvon Zeichentools

BufferStrategy buffer = this.getBufferStrategy(); // extrahieren von buffer
// ein buffer ist wie ein leeres canvas wo man drauf zeichnen kann

if (buffer == null) { // wenn es nich existiert kann man nicht mit zeichnen anfangen
    
	this.createBufferStrategy(3); //dreifach Buffer
	return;
}

Graphics g = buffer.getDrawGraphics(); // extrahieren von dem drawing
// Graphics ist eine Klasse um Objekte wie Ovale oder Rechtecke zu zeichnen
	 

// Hintergrund zeichnen
drawBackground(g);

//Ball Zeichnen
ball.draw(g);


//Paddles und mit ihnen auch den score zeichnen
leftPaddle.draw(g);
rightPaddle.draw(g);

// Mein Menu zeichnen
if (menu.active)
    menu.draw(g);


// zeichnen
g.dispose(); 
buffer.show();

}

/**
 * Hintergrung zeichnen
 * @param g -Zeichentool
 */
private void drawBackground(Graphics g) {
    // grünes Hintergrund
    g.setColor(Color.green);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    // Punktierte Linie in der Mitte
    g.setColor(Color.black);
    Graphics2D g2d = (Graphics2D) g; //ein komlezierteres Grafic class um Objekte in Parametern zu zeichnen
    //Punktierte Linie erstellen
    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
    g2d.setStroke(dashed);
    g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
}

/**
 * update von Einstelungen
 */
public void update() {

if (!menu.active) {
    
	// update Ball (bewegungen)
	ball.update(leftPaddle, rightPaddle);

	// update paddles (bewegungen)
	leftPaddle.update(ball);
	rightPaddle.update(ball);
	}
}

/**
 * um value zwischen mn und max zu behalten
 * 
 * @param value - integer von value
 * @param min   - minimum integer
 * @param max   - maximum integer
 * @return: the value wenn valuezwischen minimum und maximum ist, minimum wird returned wenn value kleiner als minimum ist, maximum wird returned wenn value isgrösser als maximum ist
 */
public static int ensureRange(int value, int min, int max) {
    return Math.min(Math.max(value, min), max);
}

/**
 * returns the sign (either 1 or -1) of the input
 * @param d - a double for the input
 * @return 1 or -1
 */
public static int sign(double d) {
    if (d <= 0)
    return -1;

return 1;
}

/**
 * start
 */
public static void main(String[] args) {
    new Game();
	
}

}