package componentes;



import javax.swing.*;
import java.awt.*;

public class SquareLabel extends JLabel {
    private int number;

    public SquareLabel( int number ) {
        this.number = number;
        setupUI();
    }

    private void setupUI() {
        setOpaque(true);
        setBackground(new Color(70, 130, 180)); // Puedes ajustar el color de fondo según tus preferencias
        setForeground(Color.WHITE);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.BOLD, 20)); // Puedes ajustar la fuente y el tamaño
        setPreferredSize(new Dimension(30, 30)); // Ajusta el tamaño del cuadrado según tus necesidades
        setText(String.valueOf(number));
    }
}
