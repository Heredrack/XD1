import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class cscan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada de datos
        System.out.print("Introduce la posición inicial del disco: ");
        int discoinicial = scanner.nextInt();

        System.out.print("Introduce las pistas (separadas por espacio): ");
        scanner.nextLine(); // Limpiar buffer
        String[] pistasInput = scanner.nextLine().split(" ");
        List<Integer> pistas = new ArrayList<>();
        for (String pista : pistasInput) {
            pistas.add(Integer.parseInt(pista));
        }

        System.out.print("Modo (arriba/abajo): ");
        String modalidad = scanner.nextLine();

        System.out.print("Tope del disco: ");
        int tope = scanner.nextInt();

        System.out.print("Piso del disco: ");
        int piso = scanner.nextInt();

        // Construir y ordenar la lista de pistas a visitar
        List<Integer> pistasOrdenadas = new ArrayList<>();
        pistasOrdenadas.add(discoinicial);
        pistasOrdenadas.addAll(pistas);
        pistasOrdenadas.add(piso);
        pistasOrdenadas.add(tope);
        Collections.sort(pistasOrdenadas);

        // Identificar el índice del disco inicial
        int indice = pistasOrdenadas.indexOf(discoinicial);

        // Listas para recorrer y cálculo de distancia
        List<Integer> recorrido = new ArrayList<>();
        int sumaDistancias = 0;

        if (modalidad.equals("arriba")) {
            // Ir hacia el tope y volver al piso (C-SCAN)
            recorrido.addAll(pistasOrdenadas.subList(indice, pistasOrdenadas.size())); // Subir desde el disco inicial
            recorrido.addAll(pistasOrdenadas.subList(0, indice)); // Volver al inicio circularmente
        } else {
            // Ir hacia el piso y luego al tope (C-SCAN inverso)
            List<Integer> subLista1 = pistasOrdenadas.subList(0, indice + 1);
            Collections.reverse(subLista1);
            recorrido.addAll(subLista1); // Bajar desde el disco inicial
            List<Integer> subLista2 = pistasOrdenadas.subList(indice + 1, pistasOrdenadas.size());
            Collections.reverse(subLista2);
            recorrido.addAll(subLista2); // Volver al tope circularmente
        }

        // Calcular suma de distancias
        for (int i = 0; i < recorrido.size() - 1; i++) {
            sumaDistancias += Math.abs(recorrido.get(i) - recorrido.get(i + 1));
        }

        // Calcular la longitud media de búsqueda
        double media = Math.round((double) sumaDistancias / pistas.size() * 100.0) / 100.0;

        // Mostrar los resultados
        System.out.println("Recorrido del disco: " + recorrido);
        System.out.println("Suma total de distancias: " + sumaDistancias);
        System.out.println("Longitud media de búsqueda: " + media);

        // Generar gráfico
        Frame frame = new Frame("Recorrido del Disco");
        frame.setSize(800, 600);
        frame.add(new Canvas() {
            @Override
            public void paint(Graphics g) {
                // Configuración del gráfico
                int width = getWidth();
                int height = getHeight();
                int margin = 50;
                int graphWidth = width - 2 * margin;
                int graphHeight = height - 2 * margin;

                // Dibujar ejes
                g.drawLine(margin, height - margin, width - margin, height - margin); // Eje X
                g.drawLine(margin, margin, margin, height - margin); // Eje Y

                // Etiquetas
                g.drawString("Orden de acceso", width / 2, height - 10);
                g.drawString("Número de pista", 10, height / 2);

                // Dibujar puntos y líneas
                int maxPista = Collections.max(recorrido);
                int minPista = Collections.min(recorrido);
                int pointCount = recorrido.size();

                for (int i = 0; i < pointCount - 1; i++) {
                    int x1 = margin + (i * graphWidth / (pointCount - 1));
                    int y1 = height - margin - ((recorrido.get(i) - minPista) * graphHeight / (maxPista - minPista));

                    int x2 = margin + ((i + 1) * graphWidth / (pointCount - 1));
                    int y2 = height - margin - ((recorrido.get(i + 1) - minPista) * graphHeight / (maxPista - minPista));

                    g.drawLine(x1, y1, x2, y2); // Línea entre puntos
                    g.fillOval(x1 - 3, y1 - 3, 6, 6); // Punto actual
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
