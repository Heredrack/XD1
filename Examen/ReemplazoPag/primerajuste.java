package memoria;

import java.util.*;

public class primerajuste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Número de bloques de memoria
        System.out.print("Número de bloques de memoria: ");
        int n = sc.nextInt();
        int[][] memoria = new int[n][2]; // Cada bloque tiene: [0] tamaño total, [1] espacio ocupado

        // Configuración de los bloques de memoria
        for (int i = 0; i < n; i++) {
            System.out.print("Tamaño total del bloque [" + i + "]: ");
            memoria[i][0] = sc.nextInt(); // Tamaño total del bloque
            System.out.print("Memoria ocupada inicialmente en el bloque [" + i + "]: ");
            memoria[i][1] = sc.nextInt(); // Memoria ya ocupada

            // Verificamos que el espacio ocupado no exceda el tamaño total
            if (memoria[i][1] > memoria[i][0]) {
                System.out.println("Error: La memoria ocupada no puede exceder el tamaño total del bloque.");
                return;
            }
        }

        // Número de procesos
        System.out.print("Número de procesos: ");
        int procesos = sc.nextInt();

        Queue<procesoM> cola = new LinkedList<>();
        for (int i = 0; i < procesos; i++) {
            System.out.print("Introduce tamaño del proceso [" + i + "]: ");
            int tamanio = sc.nextInt();
            cola.offer(new procesoM(i, tamanio));
        }

        // Lógica del Primer Ajuste
        List<String> asignaciones = new ArrayList<>(); // Para almacenar resultados
        while (!cola.isEmpty()) {
            procesoM proceso = cola.poll(); // Tomamos el primer proceso de la cola
            boolean asignado = false;

            for (int i = 0; i < n; i++) {
                // Verificamos si el bloque tiene suficiente espacio libre
                int espacioLibre = memoria[i][0] - memoria[i][1]; // Espacio disponible en el bloque
                if (espacioLibre >= proceso.tamanio) {
                    memoria[i][1] += proceso.tamanio; // Asignamos el proceso al bloque
                    int espacioSobrante = memoria[i][0] - memoria[i][1]; // Recalculamos espacio libre
                    asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\tBloque " + i + "\t\t" + espacioSobrante);
                    asignado = true;
                    break; // Salimos del bucle una vez asignado
                }
            }

            if (!asignado) {
                asignaciones.add("Proceso " + proceso.id + "\t" + proceso.tamanio + "\tNo asignado\t\tN/A");
            }
        }

        // Mostrar asignaciones
        System.out.println("\nProceso\tTamaño\tBloque asignado\tEspacio sobrante");
        for (String asignacion : asignaciones) {
            System.out.println(asignacion);
        }

        sc.close();
    }
}