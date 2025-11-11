/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proy_finalso;

/**
 *
 * @author Leticia
*/
import java.util.Scanner;

public class Proy_FinalSO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner sc = new Scanner(System.in);
        AdministradorMemoria admin = new AdministradorMemoria(1000);

        while (true) {
            System.out.println("\n===== SIMULADOR DE ADMINISTRADOR DE MEMORIA =====");
            System.out.println("1. Asignar bloque de memoria");
            System.out.println("2. Liberar bloque (Garbage Collector)");
            System.out.println("3. Mostrar estado de la memoria");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del proceso: ");
                    String proceso = sc.next();
                    System.out.print("Tamaño a asignar: ");
                    int tamaño = sc.nextInt();
                    System.out.print("Método (primer/mejor/peor): ");
                    String metodo = sc.next();
                    admin.asignarMemoria(tamaño, metodo, proceso);
                    break;

                case 2:
                    System.out.print("Nombre del proceso a liberar: ");
                    String p = sc.next();
                    admin.liberarMemoria(p);
                    break;

                case 3:
                    admin.mostrarEstado();
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}


