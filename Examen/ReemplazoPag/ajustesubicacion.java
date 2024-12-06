package memoria;

import java.util.*;

public class ajustesubicacion {
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

        // Llamar a las diferentes estrategias
        System.out.println("\n[1] Primer Ajuste");
        ejecutarPrimerAjuste(new LinkedList<>(cola), copiarMemoria(memoria), n);

        System.out.println("\n[2] Mejor Ajuste");
        ejecutarMejorAjuste(new LinkedList<>(cola), copiarMemoria(memoria), n);

        System.out.println("\n[3] Peor Ajuste");
        ejecutarPeorAjuste(new LinkedList<>(cola), copiarMemoria(memoria), n);

        System.out.println("\n[4] Siguiente Ajuste");
        ejecutarSiguienteAjuste(new LinkedList<>(cola), copiarMemoria(memoria), n);

        sc.close();
    }
    public static int[][] copiarMemoria(int[][] original) {
        int[][] copia = new int[original.length][2];
        for (int i = 0; i < original.length; i++) {
            copia[i][0] = original[i][0]; // Tamaño total
            copia[i][1] = original[i][1]; // Espacio ocupado
        }
        return copia;
    }
    // Función para Primer Ajuste
    public static void ejecutarPrimerAjuste(Queue<procesoM> cola, int[][] memoria, int n) {
        List<String> asignaciones = new ArrayList<>();
        while (!cola.isEmpty()) {
            procesoM proceso = cola.poll();
            boolean asignado = false;

            for (int i = 0; i < n; i++) {
                int espacioLibre = memoria[i][0] - memoria[i][1];
                if (espacioLibre >= proceso.tamanio) {
                    memoria[i][1] += proceso.tamanio;
                    int espacioSobrante = memoria[i][0] - memoria[i][1];
                    asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\t" + i + "\t\t" + espacioSobrante);
                    asignado = true;
                    break;
                }
            }

            if (!asignado) {
                asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\tNo asignado\t\tN/A");
            }
        }

        imprimirResultados(asignaciones);
    }

    // Función para Mejor Ajuste
    public static void ejecutarMejorAjuste(Queue<procesoM> cola, int[][] memoria, int n) {
        List<String> asignaciones = new ArrayList<>();
        while (!cola.isEmpty()) {
            procesoM proceso = cola.poll();
            int mejorBloque = -1;
            int menorEspacioSobrante = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int espacioLibre = memoria[i][0] - memoria[i][1];
                if (espacioLibre >= proceso.tamanio && espacioLibre < menorEspacioSobrante) {
                    mejorBloque = i;
                    menorEspacioSobrante = espacioLibre;
                }
            }

            if (mejorBloque != -1) {
                memoria[mejorBloque][1] += proceso.tamanio;
                int espacioSobrante = memoria[mejorBloque][0] - memoria[mejorBloque][1];
                asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\t"+ mejorBloque + "\t\t" + espacioSobrante);
            } else {
                asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\tNo asignado\t\tN/A");
            }
        }

        imprimirResultados(asignaciones);
    }

    // Función para Peor Ajuste
    public static void ejecutarPeorAjuste(Queue<procesoM> cola, int[][] memoria, int n) {
        List<String> asignaciones = new ArrayList<>();
        while (!cola.isEmpty()) {
            procesoM proceso = cola.poll();
            int peorBloque = -1;
            int mayorEspacioLibre = -1;

            for (int i = 0; i < n; i++) {
                int espacioLibre = memoria[i][0] - memoria[i][1];
                if (espacioLibre >= proceso.tamanio && espacioLibre > mayorEspacioLibre) {
                    peorBloque = i;
                    mayorEspacioLibre = espacioLibre;
                }
            }

            if (peorBloque != -1) {
                memoria[peorBloque][1] += proceso.tamanio;
                int espacioSobrante = memoria[peorBloque][0] - memoria[peorBloque][1];
                asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\t" + peorBloque + "\t\t" + espacioSobrante);
            } else {
                asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\tNo asignado\t\tN/A");
            }
        }

        imprimirResultados(asignaciones);
    }

    // Función para Siguiente Ajuste
    public static void ejecutarSiguienteAjuste(Queue<procesoM> cola, int[][] memoria, int n) {
        List<String> asignaciones = new ArrayList<>();
        int puntero = 0; // Puntero que guarda el último bloque revisado

        while (!cola.isEmpty()) {
            procesoM proceso = cola.poll();
            boolean asignado = false;

            // Empezamos desde el puntero actual y recorremos todos los bloques
            for (int i = 0; i < n; i++) {
                int bloqueActual = (puntero + i) % n; // Movimiento circular
                int espacioLibre = memoria[bloqueActual][0] - memoria[bloqueActual][1];

                if (espacioLibre >= proceso.tamanio) {
                    memoria[bloqueActual][1] += proceso.tamanio;
                    int espacioSobrante = memoria[bloqueActual][0] - memoria[bloqueActual][1];
                    asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\t" + bloqueActual + "\t\t" + espacioSobrante);
                    asignado = true;
                    puntero = bloqueActual + 1; // Actualizamos el puntero al siguiente bloque
                    break;
                }
            }

            if (!asignado) {
                asignaciones.add(proceso.id + "\t" + proceso.tamanio + "\tNo asignado\t\tN/A");
            }
        }

        imprimirResultados(asignaciones);
    }

    // Función para imprimir resultados
    public static void imprimirResultados(List<String> asignaciones) {
        System.out.println("\nProceso\tTamaño\tBloque asignado\tEspacio sobrante");
        for (String asignacion : asignaciones) {
            System.out.println(asignacion);
        }
    }
}
