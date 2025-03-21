import java.awt.*;
import javax.swing.*;

public class RPG extends JFrame {
    // Added for testing
    private MainPanel panel;
    
    public RPG() {
        setTitle("RPG");

        panel = new MainPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);

        pack();
    }

    public static void main(String[] args) {
        RPG frame = new RPG();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
