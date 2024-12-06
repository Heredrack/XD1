package ReemplazoPag;

import java.util.*;

public class Reloj {
    public static void main(String[] args) {
        int[] paginas = {2, 3, 2, 1, 5, 2, 4, 5, 3, 2, 5, 2};
        int capacidad = 3;
        int[] memoria = new int[capacidad];
        boolean[] referencia = new boolean[capacidad]; // Bit de referencia
        List<Integer> reemplazos = new ArrayList<>(); // Para registrar las páginas reemplazadas
        List<String> reemplazosDetalles = new ArrayList<>(); // Para registrar detalles de reemplazo
        Arrays.fill(memoria, -1);

        int puntero = 0;
        int fallos = 0;

        for (int pagina : paginas) {
            boolean encontrado = false;

            // Buscar si la página ya está en memoria
            for (int i = 0; i < capacidad; i++) {
                if (memoria[i] == pagina) {
                    referencia[i] = true; // Actualizar bit de referencia
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                // Fallo de página, aplicar reemplazo reloj
                while (referencia[puntero]) {
                    referencia[puntero] = false; // Quitar segunda oportunidad
                    puntero = (puntero + 1) % capacidad;
                }

                if (memoria[puntero] != -1) {
                    reemplazos.add(memoria[puntero]); // Registrar el reemplazo
                    reemplazosDetalles.add("Página " + memoria[puntero] + " reemplazada por " + pagina);
                }
                memoria[puntero] = pagina;
                referencia[puntero] = true;
                puntero = (puntero + 1) % capacidad;
                fallos++;
            }

            // Mostrar el estado de la memoria
            System.out.print("Página: " + pagina + " -> Memoria: ");
            System.out.println(Arrays.toString(memoria));
        }

        // Mostrar resultados finales
        System.out.println("Número total de fallos: " + fallos);
        System.out.println("Secuencia de reemplazos: " + reemplazos);
        System.out.println("Detalles de reemplazos:");
        for (String detalle : reemplazosDetalles) {
            System.out.println(detalle);
        }
    }
}

/*
 * package Reloj;

import java.util.*;

public class Reloj {
    public static void main(String[] args) {
        String[] paginas = {"A", "B", "C", "A", "D", "B", "E", "A", "B", "C"};
        int capacidad = 3;
        String[] memoria = new String[capacidad];
        boolean[] referencia = new boolean[capacidad]; // Bit de referencia
        List<String> reemplazos = new ArrayList<>(); // Para registrar las páginas reemplazadas
        List<String> reemplazosDetalles = new ArrayList<>(); // Para registrar detalles de reemplazo
        Arrays.fill(memoria, null);

        int puntero = 0;
        int fallos = 0;

        for (String pagina : paginas) {
            boolean encontrado = false;

            // Buscar si la página ya está en memoria
            for (int i = 0; i < capacidad; i++) {
                if (pagina.equals(memoria[i])) {
                    referencia[i] = true; // Actualizar bit de referencia
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                // Fallo de página, aplicar reemplazo reloj
                while (referencia[puntero]) {
                    referencia[puntero] = false; // Quitar segunda oportunidad
                    puntero = (puntero + 1) % capacidad;
                }

                if (memoria[puntero] != null) {
                    reemplazos.add(memoria[puntero]); // Registrar el reemplazo
                    reemplazosDetalles.add("Página " + memoria[puntero] + " reemplazada por " + pagina);
                }
                memoria[puntero] = pagina;
                referencia[puntero] = true;
                puntero = (puntero + 1) % capacidad;
                fallos++;
            }

            // Mostrar el estado de la memoria
            System.out.print("Página: " + pagina + " -> Memoria: ");
            System.out.println(Arrays.toString(memoria));
        }

        // Mostrar resultados finales
        System.out.println("Número total de fallos: " + fallos);
        System.out.println("Secuencia de reemplazos: " + reemplazos);
        System.out.println("Detalles de reemplazos:");
        for (String detalle : reemplazosDetalles) {
            System.out.println(detalle);
        }
    }
}

 * */
