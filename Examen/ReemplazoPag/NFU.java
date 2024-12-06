import java.util.*;

public class NFU {
    public static void main(String[] args) {
        int[] paginas = {2,3,2,1,5,2,4,5,3,2,5,2};
        int capacidad = 3;
        int[] memoria = new int[capacidad];
        int[] contador = new int[capacidad]; 
        List<Integer> reemplazos = new ArrayList<>(); 
        Arrays.fill(memoria, -1);

        int fallos = 0;

        for (int pagina : paginas) {
            boolean encontrado = false;

            for (int i = 0; i < capacidad; i++) {
                if (memoria[i] == pagina) {
                    contador[i]++;
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
       
                int reemplazoIndex = -1;
                int minContador = Integer.MAX_VALUE;

                for (int i = 0; i < capacidad; i++) {
                    if (memoria[i] == -1) {
                        reemplazoIndex = i;
                        break;
                    }
                    if (contador[i] < minContador) {
                        minContador = contador[i];
                        reemplazoIndex = i;
                    }
                }

                if (memoria[reemplazoIndex] != -1) {
                    reemplazos.add(memoria[reemplazoIndex]); 
                }

                memoria[reemplazoIndex] = pagina;
                contador[reemplazoIndex] = 1; 
                fallos++;
            }

            System.out.print("Página: " + pagina + " -> Memoria: ");
            System.out.println(Arrays.toString(memoria));
        }

        System.out.println("Número total de fallos: " + fallos);
        System.out.println("Secuencia de reemplazos: " + reemplazos);
    }
}
