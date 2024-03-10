import main.GameWindow;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        System.out.println("\nGame Is Running...");
        System.out.println("------------------\n");
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
