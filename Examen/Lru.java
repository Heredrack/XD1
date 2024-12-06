package Lru;

import java.util.*;

public class Lru {
    public static void main(String[] args) {
        int[] paginas = {1, 3, 5, 2, 3, 7, 1, 3, 8, 4};
        int capacidad = 3;
        List<Integer> memoria = new ArrayList<>();
        List<Integer> reemplazos = new ArrayList<>();
        List<String> detallesReemplazos = new ArrayList<>();
        int fallos = 0;

        for (int pagina : paginas) {
            if (!memoria.contains(pagina)) {
                // Fallo de página
                if (memoria.size() == capacidad) {
                    // Realizar reemplazo
                    int reemplazada = memoria.get(0); // Página más antigua (Least Recently Used)
                    reemplazos.add(reemplazada); // Registrar página reemplazada
                    detallesReemplazos.add(reemplazada + " por " + pagina); // Registrar detalle del reemplazo
                    memoria.remove(0); // Eliminar la página más antigua
                }
                memoria.add(pagina); // Agregar nueva página
                fallos++; // Incrementar contador de fallos
            } else {
                // Página ya está en memoria: actualizar su posición (reciente)
                memoria.remove((Integer) pagina);
                memoria.add(pagina);
            }

            System.out.println("Página: " + pagina + " -> Memoria: " + memoria);
        }

        // Mostrar resultados finales
        System.out.println("\nNúmero total de fallos: " + fallos);
        System.out.println("Secuencia de reemplazos: " + reemplazos);
        System.out.println("Detalles de reemplazos (página por página): ");
        for (String detalle : detallesReemplazos) {
            System.out.println(detalle);
        }
    }
}


/*
 * package Lru;

import java.util.*;

public class Lru {
    public static void main(String[] args) {
        String[] paginas = {"A", "B", "C", "A", "D", "B", "E", "A", "B", "C"};
        int capacidad = 3;
        List<String> memoria = new ArrayList<>();
        List<String> reemplazos = new ArrayList<>();
        List<String> detallesReemplazos = new ArrayList<>();
        int fallos = 0;

        for (String pagina : paginas) {
            if (!memoria.contains(pagina)) {
                // Fallo de página
                if (memoria.size() == capacidad) {
                    // Realizar reemplazo
                    String reemplazada = memoria.get(0); // Página más antigua (Least Recently Used)
                    reemplazos.add(reemplazada); // Registrar página reemplazada
                    detallesReemplazos.add(reemplazada + " por " + pagina); // Registrar detalle del reemplazo
                    memoria.remove(0); // Eliminar la página más antigua
                }
                memoria.add(pagina); // Agregar nueva página
                fallos++; // Incrementar contador de fallos
            } else {
                // Página ya está en memoria: actualizar su posición (reciente)
                memoria.remove(pagina);
                memoria.add(pagina);
            }

            System.out.println("Página: " + pagina + " -> Memoria: " + memoria);
        }

        // Mostrar resultados finales
        System.out.println("\nNúmero total de fallos: " + fallos);
        System.out.println("Secuencia de reemplazos: " + reemplazos);
        System.out.println("Detalles de reemplazos (página por página): ");
        for (String detalle : detallesReemplazos) {
            System.out.println(detalle);
        }
    }
}
*/
