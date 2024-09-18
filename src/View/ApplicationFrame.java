package View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() throws IOException {
        setTitle("Tile Map Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        ApplicationPanel panel = new ApplicationPanel();
        add(panel, BorderLayout.CENTER);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
}
