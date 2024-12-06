import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public class SCAN {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número total de discos: ");
        int NroDiscos = scanner.nextInt();

        System.out.print("Ingrese la posición inicial del cabezal: ");
        int discoInicial = scanner.nextInt();

        System.out.print("Ingrese el número de pistas solicitadas: ");
        int numeroPistas = scanner.nextInt();
        
        System.out.print("Ingrese las pistas solicitadas separadas por espacios: ");
        scanner.nextLine(); 
        String[] pistasInput = scanner.nextLine().split(" ");
        List<Integer> pistas = new ArrayList<>();
        for (String pista : pistasInput) {
            pistas.add(Integer.parseInt(pista));
        }

        System.out.print("Ingrese la dirección inicial del cabezal (up/down): ");
        String direccion = scanner.nextLine().trim().toLowerCase();

        if (!direccion.equals("up") && !direccion.equals("down")) {
            System.out.println("Dirección no válida. Use 'up' o 'down'.");
            scanner.close();
            return;
        }

        pistas.add(0);
        pistas.add(NroDiscos);
        Collections.sort(pistas);

        List<Integer> pistasOrdenadas = new ArrayList<>();
        pistasOrdenadas.add(discoInicial); 

        if (direccion.equals("up")) {
            for (int pista : pistas) {
                if (pista >= discoInicial) {
                    pistasOrdenadas.add(pista);
                }
            }
            for (int i = pistas.size() - 1; i >= 0; i--) {
                if (pistas.get(i) < discoInicial) {
                    pistasOrdenadas.add(pistas.get(i));
                }
            }
        } else {
            for (int i = pistas.size() - 1; i >= 0; i--) {
                if (pistas.get(i) <= discoInicial) {
                    pistasOrdenadas.add(pistas.get(i));
                }
            }
            for (int pista : pistas) {
                if (pista > discoInicial) {
                    pistasOrdenadas.add(pista);
                }
            }
        }

        int movimientos = 0;
        for (int i = 1; i < pistasOrdenadas.size(); i++) {
            movimientos += Math.abs(pistasOrdenadas.get(i) - pistasOrdenadas.get(i - 1));
        }

        double longitudMediaBusqueda = (double) movimientos / numeroPistas;

        System.out.println("\nResultados del algoritmo SCAN:");
        System.out.println("Orden de pistas visitadas: " + pistasOrdenadas);
        System.out.println("Número de movimientos del cabezal: " + movimientos);
        System.out.printf("Longitud media de búsqueda: %.2f\n", longitudMediaBusqueda);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Recorrido del Cabezal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 400);
            frame.add(new GraphPanel(pistasOrdenadas, NroDiscos));
            frame.setVisible(true);
        });

        scanner.close();
    }
}

class GraphPanel extends JPanel {
    private final List<Integer> pistasOrdenadas;
    private final int NroDiscos;

    public GraphPanel(List<Integer> pistasOrdenadas, int NroDiscos) {
        this.pistasOrdenadas = pistasOrdenadas;
        this.NroDiscos = NroDiscos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int padding = 50;

        int graphWidth = width - 2 * padding;
        int graphHeight = height - 2 * padding;

        int pointCount = pistasOrdenadas.size();
        int xStep = graphWidth / (pointCount - 1);
        int yMax = NroDiscos;

        g2d.drawLine(padding, height - padding, width - padding, height - padding);
        g2d.drawLine(padding, padding, padding, height - padding);

        for (int i = 0; i < pointCount - 1; i++) {
            int x1 = padding + i * xStep;
            int y1 = height - padding - (pistasOrdenadas.get(i) * graphHeight / yMax);
            int x2 = padding + (i + 1) * xStep;
            int y2 = height - padding - (pistasOrdenadas.get(i + 1) * graphHeight / yMax);
            g2d.drawLine(x1, y1, x2, y2);
        }

        for (int i = 0; i < pointCount; i++) {
            int x = padding + i * xStep;
            int y = height - padding - (pistasOrdenadas.get(i) * graphHeight / yMax);
            g2d.fillOval(x - 3, y - 3, 6, 6);
        }
    }
}
