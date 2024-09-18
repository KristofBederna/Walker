package Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tile extends JPanel {
    private int x, y;
    private int width, height;
    private int id;
    private BufferedImage image;

    public Tile(int x, int y, int width, int height, int id, String imagePath) throws IOException {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.image = setImage(imagePath);

        // Set the preferred size of the JPanel
        setPreferredSize(new Dimension(width, height));
    }

    protected BufferedImage setImage(String path) throws IOException {
        if (path != null) {
            return image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the rectangular shape with the actual size of the component
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), getHeight());

        // If you have an image, you can draw it as well
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public int getId() {
        return this.id;
    }
}
