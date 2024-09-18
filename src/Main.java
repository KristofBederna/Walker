import View.ApplicationFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            ApplicationFrame app = null;
            try {
                app = new ApplicationFrame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            app.setVisible(true);
        });
    }
}