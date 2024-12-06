import java.util.*;

public class LOOK {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número total de discos: ");
        int NroDiscos = scanner.nextInt();
        
        System.out.print("Ingrese la posición inicial del cabezal: ");
        int disco_inicial = scanner.nextInt();
        
        System.out.print("Ingrese las pistas solicitadas separadas por espacios: ");
        scanner.nextLine();
        String inputPistas = scanner.nextLine();
        String[] pistasArray = inputPistas.split(" ");
        List<Integer> pistas = new ArrayList<>();

        for (String pista : pistasArray) {
            pistas.add(Integer.parseInt(pista));
        }

        System.out.print("Ingrese la dirección inicial del cabezal (up/down): ");
        String direccion = scanner.nextLine().toLowerCase();

        if (!direccion.equals("up") && !direccion.equals("down")) {
            System.out.println("Dirección no válida. Use 'up' o 'down'.");
            return;
        }

        Collections.sort(pistas);

        List<Integer> pistas_ordenadas = new ArrayList<>();
        pistas_ordenadas.add(disco_inicial); 

        if (direccion.equals("up")) {
            for (int pista : pistas) {
                if (pista >= disco_inicial) {
                    pistas_ordenadas.add(pista);
                }
            }
            for (int i = pistas.size() - 1; i >= 0; i--) {
                if (pistas.get(i) < disco_inicial) {
                    pistas_ordenadas.add(pistas.get(i));
                }
            }
        } else {
            for (int i = pistas.size() - 1; i >= 0; i--) {
                if (pistas.get(i) <= disco_inicial) {
                    pistas_ordenadas.add(pistas.get(i));
                }
            }
            for (int pista : pistas) {
                if (pista > disco_inicial) {
                    pistas_ordenadas.add(pista);
                }
            }
        }

        int movimientos = 0;
        for (int i = 1; i < pistas_ordenadas.size(); i++) {
            movimientos += Math.abs(pistas_ordenadas.get(i) - pistas_ordenadas.get(i - 1));
        }

        double longitud_media_busqueda = pistas.size() > 0 ? (double)movimientos / pistas.size() : 0;

        System.out.println("\nResultados del algoritmo LOOK:");
        System.out.println("Orden de pistas visitadas: " + pistas_ordenadas);
        System.out.println("Número de movimientos del cabezal: " + movimientos);
        System.out.println(String.format("Longitud media de búsqueda: %.2f", longitud_media_busqueda));
    }
}
