package Examen;

import java.util.*;

public class SNJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int filas = 4;
        // Lista para almacenar los procesos
        List<Proceso> procesos = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            System.out.print("Ingrese el tiempo de llegada [" + i + "]: ");
            int tLlegada = sc.nextInt();
            System.out.print("Ingrese el tiempo de carga [" + i + "]: ");
            int rCPU = sc.nextInt();
            procesos.add(new Proceso(i, tLlegada, rCPU));
        }
        // Ordenar por tiempo de llegada inicial
        procesos.sort(Comparator.comparingInt(p -> p.tLlegada));
        PriorityQueue<Proceso> colaRafagasCPU = new PriorityQueue<>(
                Comparator.comparingInt(p -> p.rCPU) // Menor rafaga = mayor importancia
        );
        int tiempoActual = 0;
        List<Proceso> completados = new ArrayList<>();
        while (!procesos.isEmpty() || !colaRafagasCPU.isEmpty()) {
            // Añadir a la cola los procesos que han llegado
            Iterator<Proceso> it = procesos.iterator(); // Crear un iterador para la lista "procesos" para recorrerla
            while (it.hasNext()) { // Verifica si quedan elementos por recorrer en la lista
                Proceso p = it.next(); // Obtiene el siguiente elemento de la lista (Proceso) y avanza el iterador al
                                       // siguiente elemento.
                if (p.tLlegada <= tiempoActual) {
                    colaRafagasCPU.add(p); // Mueve el proceso a la cola de prioridad.
                    it.remove(); // Elimina el proceso de la lista original.
                }
            }

            if (!colaRafagasCPU.isEmpty()) {
                // Sacar el proceso con mayor prioridad de la cola
                Proceso actual = colaRafagasCPU.poll();
                actual.tInicio = Math.max(tiempoActual, actual.tLlegada);
                actual.tFin = actual.tInicio + actual.rCPU;
                actual.tServicio = actual.tFin - actual.tLlegada;
                actual.tEspera = actual.tServicio - actual.rCPU;
                actual.penalizacion = Math.round((double) actual.tServicio / actual.rCPU * 10) / 10.0;
                actual.respuesta = Math.round((double) actual.rCPU / actual.tServicio * 10) / 10.0;
                tiempoActual = actual.tFin; // Avanzar el tiempo
                completados.add(actual);
            } else {
                tiempoActual++; // Si no hay procesos, avanzar el tiempo
            }
        }

        // Calcular promedios
        double[] promedios = new double[7];
        for (Proceso p : completados) {
            promedios[0] += p.rCPU;
            promedios[1] += p.tInicio;
            promedios[2] += p.tFin;
            promedios[3] += p.tServicio;
            promedios[4] += p.tEspera;
            promedios[5] += p.penalizacion;
            promedios[6] += p.respuesta;
        }
        // convertir a 2 decimales
        for (int i = 0; i < promedios.length; i++) {
            promedios[i] = Math.round(promedios[i] / filas * 100) / 100.0; // para 3 decimales seria 1000 y 1000.0
        }
        System.out.println(
                "\n\n*___________________________________________________________________________________________");
        // Mostrar resultados
        System.out.println("Pro#\tT_LL\tR_cpu\tT_ini\tT_fin\tT\tE\tP\tR");
        for (Proceso p : completados) {
            System.out.println(p.id + "\t" + p.tLlegada + "\t" + p.rCPU + "\t" +
                    p.tInicio + "\t" + p.tFin + "\t" + p.tServicio + "\t" + p.tEspera + "\t" +
                    p.penalizacion + "\t" + p.respuesta);
        }
        System.out.println(
                "*___________________________________________________________________________________________");
        System.out.println();
        System.out.println("Promedios:\t" + promedios[0] + "\t " + promedios[1] + "\t " + promedios[2] +
                "\t " + promedios[3] + "\t " + promedios[4] + "\t " + promedios[5] + "\t " + promedios[6]);
        System.out.println(
                "*___________________________________________________________________________________________");

        sc.close();
    }
}
