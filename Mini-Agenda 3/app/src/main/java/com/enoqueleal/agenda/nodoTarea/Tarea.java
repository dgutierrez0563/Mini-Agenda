/**
 *Sistema de listas de tareas en lenguaje Android, desarrollado
 *con fines didacticos y educativos de libre uso y mejora para
 *cualquiera que desee utilizarlo con fines no lucrativos.
 *10-11-2016 1.0v
 *by wsullivan 2016.
 */
package com.enoqueleal.agenda.nodoTarea;
import java.io.Serializable;
/**
 *Clase Nodo de la tarea
 */
public class Tarea implements Serializable {
    /**
     *Atributos del nodo Tarea
     */
    private Long id; //Id del nodo
    private String titulo; //titulo para el nodo
    private String hora; //hora de registro del nodo
    private String lugar; //lugar del registro del nodo
    private String descripcion; //decripcion del nodo (tarea)
    /**
     *Metodo get del id
     */
    public Long getId() {
        return id;
    }
    /**
     *Metodo set del id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     *Metodo get del titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     *Metodo set del titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     *Metodo get de la hora
     */
    public String getHora() {
        return hora;
    }
    /**
     *Metodo set de la hora
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
    /**
     *Metodo get del lugar
     */
    public String getLugar() {
        return lugar;
    }
    /**
     *Metodo set del lugar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    /**
     *Metodo get de la descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     *Metodo set de la descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /*
     Metodo toString del nodo (tarea)
    */
    @Override
    public  String toString(){
        return titulo+"\n"+hora+"\n"+lugar+"\n"+descripcion+"\n";

    }
}
