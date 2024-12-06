package Memoria;

import java.util.ArrayList;
import java.util.List;

public class SiguienteAjuste {
    static class Proceso {
        String nombre;
        int tamanio;

        public Proceso(String nombre, int tamanio) {
            this.nombre = nombre;
            this.tamanio = tamanio;
        }
    }

    public static void main(String[] args) {
        // Tamaños iniciales de los bloques de memoria
        int[] memoria = { 20, 20, 40, 60, 20, 10, 60, 40, 20, 30, 40, 40 };

        // Configuración de bloques ocupados
        int[] ocupado = { 20, 0, 40, 0, 20, 0, 60, 0, 20, 0, 40, 0 };
        for (int i = 0; i < memoria.length; i++) {
            memoria[i] -= ocupado[i]; // Reducir el espacio disponible según lo ocupado
        }

        // Lista de procesos a asignar
        List<Proceso> procesos = new ArrayList<>();
        procesos.add(new Proceso("A", 40));
        procesos.add(new Proceso("B", 20));
        procesos.add(new Proceso("C", 10));
     

        // **Variable para controlar la última ubicación**
        int ultimaUbicacion = 1; // Cambia este valor para empezar desde otro índice (0 = inicio, 5 = posición 6, etc.)

        // Encabezado de la tabla
        System.out.printf("%-10s %-15s %-20s %-10s%n", "Proceso", "Tamaño", "Bloque Asignado", "Espacio Libre");

        for (Proceso proceso : procesos) {
            boolean asignado = false;
            int inicio = ultimaUbicacion; // Guardar la posición inicial

            // Empezar desde la última ubicación
            do {
                if (memoria[ultimaUbicacion] >= proceso.tamanio) {
                    int espacioLibre = memoria[ultimaUbicacion] - proceso.tamanio;
                    System.out.printf("%-10s %-15d %-20d %-10d%n", proceso.nombre, proceso.tamanio,
                            ultimaUbicacion + 1, espacioLibre);
                    memoria[ultimaUbicacion] -= proceso.tamanio;
                    asignado = true;

                    // Actualizar la última ubicación al índice actual
                    ultimaUbicacion = (ultimaUbicacion + 1) % memoria.length; // Mover al siguiente bloque
                    break;
                }

                // Avanzar al siguiente bloque de forma circular
                ultimaUbicacion = (ultimaUbicacion + 1) % memoria.length;
            } while (ultimaUbicacion != inicio);

            if (!asignado) {
                System.out.printf("%-10s %-15d %-20s %-10s%n", proceso.nombre, proceso.tamanio, "No asignado", "-");
            }
        }
    }
}
