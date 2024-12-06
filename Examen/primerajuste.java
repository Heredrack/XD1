package Examen;
import java.util.*;
public class primerajuste {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n= sc.nextInt();
        int[][] memoria= new int[n][2];
        for (int i=0; i<n; i++){
            System.out.println("Cantidad del bloque: ");
            memoria[1][i]=sc.nextInt();
            System.out.println("Espacio libre: ");
            memoria[2][i]=sc.nextInt();
        }
        int procesos= sc.nextInt();
        for(int i=0; i<procesos; i++){
            int tamanio= sc.nextInt();
            
        }

    }
}
