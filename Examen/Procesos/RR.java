package Procesos;

import java.util.*;

public class RR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int filas = 4;

        System.out.print("Ingrese el Quantum (Q): ");
        int q = sc.nextInt();

        // Lista para almacenar los procesos
        List<Proceso> procesos = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            System.out.print("Ingrese el tiempo de llegada [" + i + "]: ");
            int tLlegada = sc.nextInt();
            System.out.print("Ingrese el tiempo de carga [" + i + "]: ");
            int rCPU = sc.nextInt();
            procesos.add(new Proceso(i, tLlegada, rCPU, "xd"));
        }

        // Ordenar por tiempo de llegada
        procesos.sort(Comparator.comparingInt(p -> p.tLlegada));

        // Cola para manejar los procesos en ejecución
        Deque<Proceso> colaProcesos = new LinkedList<>();
        int tiempoActual = 0;
        List<Proceso> completados = new ArrayList<>();

        while (!procesos.isEmpty() || !colaProcesos.isEmpty()) {
            // Añadir procesos que han llegado a la cola
            Iterator<Proceso> it = procesos.iterator();
            while (it.hasNext()) {
                Proceso p = it.next();
                System.out.println("INTENTA ENTRAR:: "+p.id);
                if (p.tLlegada <= tiempoActual) {
                    System.out.println("ENTRO: "+p.id);
                    colaProcesos.addLast(p); // Agregar en la cola de procesos
                    it.remove();
                }
            }

            if (!colaProcesos.isEmpty()) {
                // Ejecutar el proceso al frente de la cola
                Proceso actual = colaProcesos.pollFirst();

                // Establecer tiempo de inicio si es la primera ejecución
                if (actual.tInicio == -1) {
                    actual.tInicio = Math.max(tiempoActual, actual.tLlegada);
                }

                // Ejecutar el proceso durante el quantum o el tiempo restante
                System.out.println(actual.id+"   llego con: "+actual.tRestante);
                int tiempoProcesado = Math.min(q, actual.tRestante);
                System.out.println(actual.id+"   AGARRO: "+tiempoProcesado);
                actual.tRestante -= tiempoProcesado;
                System.out.println(actual.id+"   le queda?=????: "+actual.tRestante);
                tiempoActual += tiempoProcesado;
                System.out.println(actual.id+"   TIEMPO ACTUAL: "+tiempoActual);

                // Revisar si llegaron procesos mientras este se ejecutaba
                it = procesos.iterator();
                while (it.hasNext()) {
                    Proceso p = it.next();
                    System.out.println("EN EJECUCION LLEGO: "+p.id);
                    if (p.tLlegada < tiempoActual) {                            // verificar si llega al punto o no
                        System.out.println("EN EJECUCION ENTRO-----: "+p.id);
                        colaProcesos.addLast(p);
                        it.remove();
                    }
                }

                // Si el proceso termina
                if (actual.tRestante == 0) {
                    actual.tFin = tiempoActual;
                    actual.tServicio = actual.tFin - actual.tLlegada;
                    actual.tEspera = actual.tServicio - actual.rCPU;
                    actual.penalizacion = Math.round((double) actual.tServicio / actual.rCPU * 10) / 10.0;
                    actual.respuesta = Math.round((double) actual.rCPU / actual.tServicio * 10) / 10.0;
                    completados.add(actual);
                } else {
                    // Si el proceso no termina, volver a añadirlo a la cola
                    colaProcesos.addLast(actual);
                }
            } else {
                tiempoActual++; // Avanzar el tiempo si no hay procesos listos
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
        // Redondear promedios a 2 decimales
        for (int i = 0; i < promedios.length; i++) {
            promedios[i] = Math.round(promedios[i] / filas * 10) / 10.0;
        }

        // Mostrar resultados
        System.out.println(
                "\n\n*___________________________________________________________________________________________");
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
