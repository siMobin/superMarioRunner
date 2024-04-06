import main.GameWindow;
import javax.swing.*;

import interfaces.GameSettings;

public class App implements GameSettings {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("|-----------------------------------------------|");
        System.out.println("|       Press space, w or up key to jump        |");
        System.out.println("|       Press s or down key to quick fall       |");
        System.out.println("|       Press m to mute the audio               |");
        System.out.println("|       Press p or esc to pause                 |");
        if (DEBUGGER)
            System.out.println("|       Press ` key to enter debug mode         |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("\n\n");
        System.out.println("Startup log...");
        System.out.println("-----------------------------------------------------\n");
        SwingUtilities.invokeLater(() -> new GameWindow());
    }
}
