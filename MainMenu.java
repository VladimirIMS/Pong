package pong2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//main menu beim start
public class MainMenu extends MouseAdapter {

	public boolean active; 

	// 1 Ball Knopf
	private Rectangle playBtn; // Play Button
	private String playTxt = "1";
	private boolean pHighlight = false; 

	//2 Bälle Knopf
	private Rectangle quitBtn; // Quit Button
	private String quitTxt = "2";
	private boolean qHighlight = false; 

	private Font font;

	/**
	* constructor
	* @param game - Game objekt
	*/
	public MainMenu(Game game) {

		active = true;
		game.start();

		// position und dimension von jedem Knopf
		int x, y, w, h;

		w = 300;
		h = 150;

		y = Game.HEIGHT / 2 - h / 2;

		x = Game.WIDTH / 4 - w / 2;
		playBtn = new Rectangle(x, y, w, h);

		x = Game.WIDTH * 3 / 4 - w / 2;
		quitBtn = new Rectangle(x, y, w, h);

		font = new Font("wah", Font.PLAIN, 100);
	}

	/**
	 * Buttons und Text in main menu zeichnen
	 * @param g - Graphikobjekt um alles zu zeichnen
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(font);

		// buttons zeichnen
		g.setColor(Color.black);
		if (pHighlight)
		g.setColor(Color.white);
		g2d.fill(playBtn);

		g.setColor(Color.black);
		if (qHighlight)
		g.setColor(Color.white);
		g2d.fill(quitBtn);

		// rände von buttons
		g.setColor(Color.white);
		g2d.draw(playBtn);
		g2d.draw(quitBtn);

		// text in buttons

		int strWidth, strHeight;

		// 1 ball Button text
		strWidth = g.getFontMetrics(font).stringWidth(playTxt);
		strHeight = g.getFontMetrics(font).getHeight();

		g.setColor(Color.green);
		g.drawString(playTxt, (int) (playBtn.getX() + playBtn.getWidth() / 2 - strWidth / 2),
			(int) (playBtn.getY() + playBtn.getHeight() / 2 + strHeight / 4));

		// 2 bälle Button text
		strWidth = g.getFontMetrics(font).stringWidth(quitTxt);
		strHeight = g.getFontMetrics(font).getHeight();

		g.setColor(Color.red);
		g.drawString(quitTxt, (int) (quitBtn.getX() + quitBtn.getWidth() / 2 - strWidth / 2),
			(int) (quitBtn.getY() + quitBtn.getHeight() / 2 + strHeight / 4));

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Point p = e.getPoint();

		if (playBtn.contains(p))
			active = false;
		else if (quitBtn.contains(p))
			System.exit(0);

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		Point p = e.getPoint();

		// wenn maus drüber ist
		pHighlight = playBtn.contains(p);
		qHighlight = quitBtn.contains(p);

	}

}