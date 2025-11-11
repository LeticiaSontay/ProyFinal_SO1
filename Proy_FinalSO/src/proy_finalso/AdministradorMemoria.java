/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy_finalso;

/**
 *
 * @author Leticia
 */

import java.util.ArrayList;

public class AdministradorMemoria {

    private ArrayList<BloqueMemoria> bloques;
    private int tamañoTotal;

    public AdministradorMemoria(int tamañoTotal) {
        this.tamañoTotal = tamañoTotal;
        this.bloques = new ArrayList<>();
        bloques.add(new BloqueMemoria(1, 0, tamañoTotal)); // Toda la memoria libre al inicio
    }

    public void asignarMemoria(int tamaño, String metodo, String proceso) {
        switch (metodo.toLowerCase()) {
            case "primer":
                primerAjuste(tamaño, proceso);
                break;
            case "mejor":
                mejorAjuste(tamaño, proceso);
                break;
            case "peor":
                peorAjuste(tamaño, proceso);
                break;
            default:
                System.out.println("Método no válido");
        }
    }

    private void primerAjuste(int tamaño, String proceso) {
        for (BloqueMemoria b : bloques) {
            if (!b.isOcupado() && b.getTamaño() >= tamaño) {
                dividirBloque(b, tamaño, proceso);
                return;
            }
        }
        System.out.println("No hay suficiente memoria (Primer Ajuste)");
    }

    private void mejorAjuste(int tamaño, String proceso) {
        BloqueMemoria mejor = null;
        for (BloqueMemoria b : bloques) {
            if (!b.isOcupado() && b.getTamaño() >= tamaño) {
                if (mejor == null || b.getTamaño() < mejor.getTamaño()) mejor = b;
            }
        }
        if (mejor != null) dividirBloque(mejor, tamaño, proceso);
        else System.out.println("No hay suficiente memoria (Mejor Ajuste)");
    }

    private void peorAjuste(int tamaño, String proceso) {
        BloqueMemoria peor = null;
        for (BloqueMemoria b : bloques) {
            if (!b.isOcupado() && b.getTamaño() >= tamaño) {
                if (peor == null || b.getTamaño() > peor.getTamaño()) peor = b;
            }
        }
        if (peor != null) dividirBloque(peor, tamaño, proceso);
        else System.out.println("No hay suficiente memoria (Peor Ajuste)");
    }

    private void dividirBloque(BloqueMemoria bloque, int tamaño, String proceso) {
        int idNuevo = bloques.size() + 1;
        int tamañoRestante = bloque.getTamaño() - tamaño;
        int inicioNuevo = bloque.getInicio() + tamaño;

        bloque.ocupar(proceso);
        if (tamañoRestante > 0) {
            bloques.add(new BloqueMemoria(idNuevo, inicioNuevo, tamañoRestante));
        }
    }

    public void liberarMemoria(String proceso) {
        for (BloqueMemoria b : bloques) {
            if (b.isOcupado() && b.getProceso().equals(proceso)) {
                b.liberar();
            }
        }
        garbageCollector();
    }

    public void garbageCollector() {
        // Une los bloques libres contiguos
        for (int i = 0; i < bloques.size() - 1; i++) {
            BloqueMemoria actual = bloques.get(i);
            BloqueMemoria siguiente = bloques.get(i + 1);
            if (!actual.isOcupado() && !siguiente.isOcupado()) {
                int nuevoTamaño = actual.getTamaño() + siguiente.getTamaño();
                bloques.set(i, new BloqueMemoria(actual.getId(), actual.getInicio(), nuevoTamaño));
                bloques.remove(i + 1);
                i--; // Retrocede para volver a revisar
            }
        }
    }

    public void mostrarEstado() {
        System.out.println("=== ESTADO DE LA MEMORIA ===");
        for (BloqueMemoria b : bloques) {
            System.out.println("Bloque " + b.getId() + " | Inicio: " + b.getInicio() +
                    " | Tamaño: " + b.getTamaño() + " | Ocupado: " + b.isOcupado() +
                    " | Proceso: " + b.getProceso());
        }
    }
}

