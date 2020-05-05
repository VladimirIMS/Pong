package pong2;

import javax.swing.JFrame;

//window class

public class Window {

/**
* Frame erstellen
* @param title - Titel vom Game
* @param game  - das Spiel
*/
public Window(String title, Game game) {
JFrame frame = new JFrame(title);

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
frame.add(game); // damit es in JFrame geht
frame.pack();
frame.setLocationRelativeTo(null); // Fenster Zentrieren

frame.setVisible(true);
}

}