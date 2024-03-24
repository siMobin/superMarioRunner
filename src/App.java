import main.GameWindow;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("|-----------------------------------------------|");
        System.out.println("|       Press space, w or up key to jump        |");
        System.out.println("|       Press s or down key to quick fall       |");
        System.out.println("|       Press p or esc key to pause             |");
        System.out.println("|       Press ` key to enter debug mode         |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("\n\n");
        System.out.println("Startup log...");
        System.out.println("-----------------------------------------------------\n");
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
