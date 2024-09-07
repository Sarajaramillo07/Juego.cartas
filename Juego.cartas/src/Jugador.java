import java.util.Arrays;
import java.util.Random;
import javax.swing.JPanel;

public class Jugador {
    
    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 50;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];

    private Random r = new Random();

    public void repartir() {
        
        int i = 0;
        for (Carta c : cartas) {
            cartas[i++] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int i = 0;
        for (Carta c : cartas) {
            c.mostrar(pnl, MARGEN + i++ * DISTANCIA, MARGEN);
        }
        pnl.repaint();
        
    }

    public String getGrupos() {
        String mensaje = "No se encontraron grupos";
        int[] contadores = new int[NombreCarta.values().length];

        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        boolean hayGrupos = false;
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                if (!hayGrupos) {
                    hayGrupos = true;
                    mensaje = "Los grupos que se encontraron fueron:\n";
                }
                mensaje += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
            }
        }

        return mensaje;
    }

    public String getEscaleras() {
        StringBuilder mensaje = new StringBuilder("No se encontraron escaleras de la misma pinta"); 
        boolean hayEscaleras = false;
         Arrays.sort(cartas, (c1, c2) -> Integer.compare(c1.getIndice(), c2.getIndice()));

        for (Pinta pinta : Pinta.values()){
            int[] valores = new int[13];
            for (Carta c : cartas) {
                if (c.getPinta() == pinta){
                    valores[c.getNombre().ordinal()] = c.getIndice();
                }
            }

            for (int i = 0; i < valores.length - 2; i++) {
                if (valores[i] != 0 && valores[i + 1] != 0 && valores[i + 2] != 0 ) {
                    if (!hayEscaleras) {
                        hayEscaleras = true;
                        mensaje = new StringBuilder("Las escaleras encontradas son:\n");
                    }
                    mensaje.append("Escalera de ").append(NombreCarta.values()[i]).append(" a ").append(NombreCarta.values()[i + 2]).append(" de ").append(pinta).append("\n");
                }
            }
        }

        return mensaje.toString();
    }


    public int calcularPuntaje() {
        int puntaje = 0;
        int[] contadores = new int[NombreCarta.values().length];

        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] < 2) {
                puntaje += NombreCarta.values()[i].getValor() * contadores[i];
            }
        }

        return puntaje;
    }

}
