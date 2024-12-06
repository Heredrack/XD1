import java.util.*;

public class C_SCAN {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número total de discos: ");
        int NroDiscos = scanner.nextInt(); 
        
        System.out.print("Ingrese la posición inicial del cabezal: ");
        int disco_inicial = scanner.nextInt();
        
        System.out.print("Ingrese el número de pistas solicitadas: ");
        int numeroPistas = scanner.nextInt();
        
        System.out.print("Ingrese las pistas solicitadas separadas por espacios: ");
        scanner.nextLine();  
        String[] pistasInput = scanner.nextLine().split(" ");
        List<Integer> pistas = new ArrayList<>();
        for (String p : pistasInput) {
            pistas.add(Integer.parseInt(p));
        }
        
        System.out.print("Ingrese la dirección inicial del cabezal (up/down): ");
        String direccion = scanner.nextLine().trim().toLowerCase();
        
        if (!direccion.equals("up") && !direccion.equals("down")) {
            System.out.println("Dirección no válida. Use 'up' o 'down'.");
            return;
        }
        
        pistas.add(0);
        pistas.add(NroDiscos);
        Collections.sort(pistas);
        
        List<Integer> pistas_ordenadas = new ArrayList<>();
        pistas_ordenadas.add(disco_inicial);  
        
        if (direccion.equals("up")) {
            for (Integer pista : pistas) {
                if (pista >= disco_inicial) {
                    pistas_ordenadas.add(pista);
                }
            }
            if (pistas_ordenadas.get(pistas_ordenadas.size() - 1) == NroDiscos) {
                pistas_ordenadas.add(0); 
            }
            for (Integer pista : pistas) {
                if (pista < disco_inicial) {
                    pistas_ordenadas.add(pista);
                }
            }
        } else {
            for (int i = pistas.size() - 1; i >= 0; i--) {
                Integer pista = pistas.get(i);
                if (pista <= disco_inicial) {
                    pistas_ordenadas.add(pista);
                }
            }
            if (pistas_ordenadas.get(pistas_ordenadas.size() - 1) == 0) {
                pistas_ordenadas.add(NroDiscos); 
            }
            for (int i = pistas.size() - 1; i >= 0; i--) {
                Integer pista = pistas.get(i);
                if (pista > disco_inicial) {
                    pistas_ordenadas.add(pista);
                }
            }
        }
        
        int movimientos = 0;
        for (int i = 1; i < pistas_ordenadas.size(); i++) {
            movimientos += Math.abs(pistas_ordenadas.get(i) - pistas_ordenadas.get(i - 1));
        }
        
        int totalPistas = pistas.size() - 2; 
        double longitudMediaBusqueda = totalPistas > 0 ? (double)movimientos / numeroPistas : 0;
        
        System.out.println("\nResultados del algoritmo C-SCAN:");
        System.out.println("Orden de pistas visitadas: " + pistas_ordenadas);
        System.out.println("Número de movimientos del cabezal: " + movimientos);
        System.out.printf("Longitud media de búsqueda: %.2f\n", longitudMediaBusqueda);
        System.out.println("\nRepresentación gráfica del recorrido del cabezal:");
        for (int pista : pistas_ordenadas) {
            System.out.print(pista + " ");

        
        }
    }
}










