import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class FrmJuego extends JFrame {

    private JButton btnRepartir;
    private JButton btnVerificar;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JTabbedPane tpJugadores;

    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();

    public FrmJuego() {
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();

        setSize(600, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlJugador1.setBackground(new Color(238, 138, 248));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raul Vidal", pnlJugador2);

        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

        getContentPane().setLayout(null);
        getContentPane().add(tpJugadores);
        getContentPane().add(btnRepartir);
        getContentPane().add(btnVerificar);

    }

    private void btnRepartirClick(ActionEvent evt) {
        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);
        
        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);
        
    }

    private void btnVerificarClick(ActionEvent evt) {
        int pestañaEscogida = tpJugadores.getSelectedIndex();
        String mensaje;
        switch (pestañaEscogida) {
            case 0:
                mensaje = jugador1.getGrupos() + "\n" + jugador1.getEscaleras() + "\nPuntaje: " + jugador1.calcularPuntaje();
                JOptionPane.showMessageDialog(null, mensaje, "Resultados Jugador 1", JOptionPane.INFORMATION_MESSAGE);
            break;
            case 1:
                mensaje = jugador2.getGrupos() + "\n" + jugador2.getEscaleras() + "\nPuntaje: " + jugador2.calcularPuntaje();
                JOptionPane.showMessageDialog(null, mensaje, "Resultados Jugador 2", JOptionPane.INFORMATION_MESSAGE);
            break;
        }

    }

}
