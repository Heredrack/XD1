package ReemplazoPag;

import java.util.*;

public class Lru2 {
    public static void main(String[] args) {
        int[] paginas = { 2, 3, 2, 1, 5, 2, 4, 5, 3, 2, 5, 2 };
        int capacidad = 3;
        List<Integer> memoria = new ArrayList<>();
        List<Integer> reemplazos = new ArrayList<>();
        List<String> detallesReemplazos = new ArrayList<>();
        int fallos = 0;

        // Encabezado para las páginas
        System.out.println("*_____________________________________________");
        for (int pagina : paginas) {
            System.out.print(pagina + " ");
        }
        System.out.println("\n*_____________________________________________");

        // Matriz de estados de memoria
        int[][] matriz = new int[capacidad][paginas.length];

        for (int i = 0; i < capacidad; i++) {
            Arrays.fill(matriz[i], -1); // Inicializar matriz con -1 (marcos vacíos)
        }

        for (int i = 0; i < paginas.length; i++) {
            int pagina = paginas[i];
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

            // Actualizar matriz con el estado de la memoria
            for (int j = 0; j < capacidad; j++) {
                if (j < memoria.size()) {
                    matriz[j][i] = memoria.get(j);
                } else {
                    matriz[j][i] = -1; // Espacio vacío
                }
            }
        }

        // Mostrar matriz
        for (int i = 0; i < capacidad; i++) {
            for (int j = 0; j < paginas.length; j++) {
                System.out.print((matriz[i][j] == -1 ? "-" : matriz[i][j]) + " ");
            }
            System.out.println();
        }

        // Mostrar resultados finales
        System.out.println("*_____________________________________________");
        System.out.println("\nNúmero total de fallos: " + fallos);
        System.out.println("\nDetalles de reemplazos (página por página): ");
        for (String detalle : detallesReemplazos) {
            System.out.println(detalle);
        }
    }
}
