package View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import Model.Tile;
import Model.Walker;

public class ApplicationPanel extends JPanel implements Runnable {
    private Tile[][] tiles;
    private ArrayList<Walker> walkers = new ArrayList<>();
    private Thread GameThread;

    public ApplicationPanel() throws IOException {
        int rows = 50;
        int columns = 50;

        setLayout(new GridLayout(rows, columns));

        tiles = new Tile[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Tile tile = new Tile(i, j, 48, 48, 0, "/assets/images/basicTile.png");
                tiles[i][j] = tile;
                add(tile);
            }
        }
        Walker walker = new Walker(25, 25, tiles, walkers);
        walkers.add(walker);
        GameThread = new Thread(this);
        GameThread.start();
    }
    //Game-loop
    @Override
    public void run() {
        double interval = (double)1000000000 /24;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        do {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;

            if (delta >= 1) {
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
            }
        } while(GameThread != null);
    }

    private void update() throws IOException {
       for (int i = 0; i < walkers.size(); i++) {
           walkers.get(i).walk();
       }
       refresh();
    }
    public void refresh() throws IOException {
        removeAll();
        int rows = 50;
        int columns = 50;

        setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                add(tiles[i][j]);
            }
        }
        revalidate();
        repaint();
    }
}
