/**
 *Sistema de listas de tareas en lenguaje Android, desarrollado
 *con fines didacticos y educativos de libre uso y mejora para
 *cualquiera que desee utilizarlo con fines no lucrativos.
 *10-11-2016 1.0v
 *by wsullivan 2016.
 */
package com.enoqueleal.agenda.operacionLogica;
import android.widget.EditText;

import com.enoqueleal.agenda.R;
import com.enoqueleal.agenda.activity.RegistroTareas;
import com.enoqueleal.agenda.nodoTarea.Tarea;
/**
 * Clase Formulario de operaciones logicas
 */
public class FormularioInteraccion {
    /**
     * Atributos de la clase del formulario para registros
     */
    private Tarea tarea;
    private EditText idTitulo;
    private EditText idHora;
    private EditText idLugar;
    private EditText idDescripcion;
    /**
     * Metodo que realiza la optencion de los parametros de los textFields
     * @param activity
     */
    public FormularioInteraccion(RegistroTareas activity){
        idTitulo =      (EditText) activity.findViewById(R.id.txt_Titulo);
        idHora = (EditText) activity.findViewById(R.id.txt_Hora);
        idLugar=(EditText) activity.findViewById(R.id.txt_Lugar);
        idDescripcion=(EditText) activity.findViewById(R.id.txt_Descripcion);
        tarea = new Tarea();
    }
    /**
     * Método que realiza el seteo de los setters del Nodo Tarea
     * @return
     */
    public Tarea setTarea() {
        tarea.setTitulo(idTitulo.getText().toString());
        tarea.setHora(idHora.getText().toString());
        tarea.setLugar(idLugar.getText().toString());
        tarea.setDescripcion(idDescripcion.getText().toString());
        return tarea;
    }
    /**
     * Método que realiza el ingreso en textFields de la informacion del los nodos
     * @param tarea
     */
    public void putFormulario(Tarea tarea)  {
        idTitulo.setText(tarea.getTitulo());
        idHora.setText(tarea.getHora());
        idLugar.setText(tarea.getLugar());
        idDescripcion.setText(tarea.getDescripcion());
        this.tarea = tarea;
    }
}
