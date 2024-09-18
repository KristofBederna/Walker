package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Walker {
    private int x;
    private int y;
    private ArrayList<Walker> walkers = new ArrayList<>();
    private Tile[][] tiles;
    public Walker(int x, int y, Tile[][] tiles, ArrayList<Walker> walkers) throws IOException {
        this.x = x;
        this.y = y;
        this.tiles = tiles;
        this.walkers = walkers;
    }
    public void walk() throws IOException {
        if (getFilledPercentage() > 20) {
            walkers.removeAll(walkers);
            System.out.println("end");
        }
        System.out.println(getFilledPercentage());
        if (tiles[x][y].getId() == 1) {
            changeDirection();
            return;
        }
        placeTile();
        Random r = new Random();
        if (r.nextInt(10) % 3 == 0) {
            multiply();
        }
        else if (r.nextInt(10) % 4 == 0) {
            die();
        }
        else if (r.nextInt(10) % 9 == 0) {
            teleport();
        }
        changeDirection();
    }

    private void teleport() {
        Random r = new Random();
        this.x = r.nextInt(25) + 5;  // Random number between 5 and 29
        this.y = r.nextInt(25) + 5;  // Random number between 5 and 29
        System.out.println("teleport");
    }

    public int getFilledPercentage() {
        int filledTiles = 0;
        int totalTiles = 50 * 50; // Total number of tiles

        // Count the filled tiles (1s)
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (tiles[i][j].getId() == 1) {
                    filledTiles++;
                }
            }
        }

        // Calculate the percentage of filled tiles
        return (filledTiles * 100) / totalTiles;
    }
    private void changeDirection() {
        Random random = new Random();
        int direction = random.nextInt(4); // Random number between 0 and 3
        if (getFilledPercentage() > 10) {
            switch (direction) {
                case 0: // Move Up

                        y++;

                    break;
                case 1: // Move Down

                        y--;

                    break;
                case 2: // Move Left

                        x--;

                    break;
                case 3: // Move Right

                        x++;
                    }

            }
        else

    {
        switch (direction) {
            case 0: // Move Up
                if (y < 39) { // Ensure y doesn't go above 29 (map height - 1)
                    y++;
                }
                break;
            case 1: // Move Down
                if (y > 5) { // Ensure y doesn't go below 0
                    y--;
                }
                break;
            case 2: // Move Left
                if (x > 5) { // Ensure x doesn't go below 0
                    x--;
                }
                break;
            case 3: // Move Right
                if (x < 39) { // Ensure x doesn't go above 29 (map width - 1)
                    x++;
                }
                break;
    }
        }
        System.out.println("I'm currently at " + x + "," + y);
    }

    private void placeTile() throws IOException {
        this.tiles[x][y] = new Tile(x, y, 48, 48, 1, "/assets/images/smallLabTile.png");
    }
    private void multiply() throws IOException {
        if (walkers.size() > 3) {
            return;
        }
        Walker walker = new Walker(this.x, this.y, this.tiles, this.walkers);
        walkers.add(walker);
        System.out.println("multiply");
    }
    private void die() {
        if (walkers.size() == 1) {
            return;
        }
        walkers.remove(walkers.size() - 1);
        System.out.println("die");
    }
}
