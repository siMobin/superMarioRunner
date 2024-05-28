import main.GameWindow;
import javax.swing.*;

import interfaces.GameSettings;

public class App implements GameSettings {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("|-----------------------------------------------|");
        System.out.println("|       Press space, W, or the up key to jump   |");
        System.out.println("|       Press S or the down key to fall faster  |");
        System.out.println("|       Press M to mute the audio               |");
        System.out.println("|       Press P or Esc to pause                 |");
        if (DEBUGGER)
            System.out.println("|       Press ` key to enter debug mode         |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("\n\n");
        System.out.println("Startup log...");
        System.out.println("-----------------------------------------------------\n");
        SwingUtilities.invokeLater(() -> new GameWindow());
    }
}
