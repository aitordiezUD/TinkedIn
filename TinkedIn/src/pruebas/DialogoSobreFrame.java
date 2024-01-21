package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoSobreFrame extends JFrame {

    public DialogoSobreFrame() {
        JButton abrirDialogoButton = new JButton("Abrir Diálogo");
        abrirDialogoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDialogo();
            }
        });

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(abrirDialogoButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    private void abrirDialogo() {
        JDialog dialogo = new JDialog(this, "Dialogo Sobre el Frame", true);
        dialogo.setSize(200, 100);

        // Ubica el JDialog relativo al JFrame
        dialogo.setLocationRelativeTo(this);

        dialogo.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DialogoSobreFrame frame = new DialogoSobreFrame();
            frame.setVisible(true);
        });
    }
}
