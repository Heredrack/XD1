package PrimerAjuste;

import java.util.ArrayList;
import java.util.List;

public class PeorAjuste {
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
            memoria[i] -= ocupado[i];
        }

        // Lista de procesos a asignar
        List<Proceso> procesos = new ArrayList<>();
        procesos.add(new Proceso("A", 40));
        procesos.add(new Proceso("B", 20));
        procesos.add(new Proceso("C", 10));
   

        // Encabezado de la tabla
        System.out.printf("%-10s %-15s %-20s %-10s%n", "Proceso", "Tamaño", "Bloque Asignado", "Espacio Libre");

        for (Proceso proceso : procesos) {
            boolean asignado = false;
            int peorBloque = -1;
            int mayorEspacio = -1;

            for (int i = 0; i < memoria.length; i++) {
                if (memoria[i] >= proceso.tamanio && memoria[i] > mayorEspacio) {
                    mayorEspacio = memoria[i];
                    peorBloque = i;
                }
            }

            if (peorBloque != -1) {
                int espacioLibre = memoria[peorBloque] - proceso.tamanio;
                System.out.printf("%-10s %-15d %-20d %-10d%n", proceso.nombre, proceso.tamanio, peorBloque + 1,
                        espacioLibre);
                memoria[peorBloque] -= proceso.tamanio;
                asignado = true;
            }

            if (!asignado) {
                System.out.printf("%-10s %-15d %-20s %-10s%n", proceso.nombre, proceso.tamanio, "No asignado", "-");
            }
        }
    }
}
