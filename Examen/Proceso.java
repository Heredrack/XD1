package Examen;

public class Proceso {
    int id, tLlegada, rCPU, prioridad, tInicio, tFin, tServicio, tEspera, tRestante;
    double penalizacion, respuesta;

    // PR
    public Proceso(int id, int tLlegada, int rCPU, int prioridad) {
        this.id = id;
        this.tLlegada = tLlegada;
        this.rCPU = rCPU;
        this.prioridad = prioridad;
        this.tInicio = 0;
        this.tFin = 0;
        this.tServicio = 0;
        this.tEspera = 0;
        this.penalizacion = 0;
        this.respuesta = 0;
    }

    // SNJ
    public Proceso(int id, int tLlegada, int rCPU) {
        this.id = id;
        this.tLlegada = tLlegada;
        this.rCPU = rCPU;
        this.prioridad = 0;
        this.tInicio = 0;
        this.tFin = 0;
        this.tServicio = 0;
        this.tEspera = 0;
        this.penalizacion = 0;
        this.respuesta = 0;
    }
    // RR
    public Proceso(int id, int tLlegada, int rCPU, String xd) {
        this.id = id;
        this.tLlegada = tLlegada;
        this.rCPU = rCPU;
        this.prioridad = 0;
        this.tInicio = -1;
        this.tFin = 0;
        this.tServicio = 0;
        this.tEspera = 0;
        this.penalizacion = 0;
        this.respuesta = 0;
        this.tRestante = rCPU; // Tiempo restante inicia igual que el tiempo de carga
    }
}
