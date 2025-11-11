/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy_finalso;

/**
 *
 * @author Leticia
 */
public class BloqueMemoria {
    private int id;
    private int inicio;
    private int tamaño;
    private boolean ocupado;
    private String proceso;

    public BloqueMemoria(int id, int inicio, int tamaño) {
        this.id = id;
        this.inicio = inicio;
        this.tamaño = tamaño;
        this.ocupado = false;
        this.proceso = "";
    }

    public int getId() { return id; }
    public int getInicio() { return inicio; }
    public int getTamaño() { return tamaño; }
    public boolean isOcupado() { return ocupado; }
    public String getProceso() { return proceso; }

    public void ocupar(String proceso) {
        this.ocupado = true;
        this.proceso = proceso;
    }

    public void liberar() {
        this.ocupado = false;
        this.proceso = "";
    }
}

