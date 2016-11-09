/**
 *Sistema de listas de tareas en lenguaje Android, desarrollado
 *con fines didacticos y educativos de libre uso y mejora para
 *cualquiera que desee utilizarlo con fines no lucrativos.
 *10-11-2016 1.0v
 *by wsullivan 2016.
 */
package com.enoqueleal.agenda.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.enoqueleal.agenda.conexionSQLite.ConexionTarea;
import com.enoqueleal.agenda.operacionLogica.FormularioInteraccion;
import com.enoqueleal.agenda.R;
import com.enoqueleal.agenda.nodoTarea.Tarea;

/**
 * Clase de registro de tareas y su validadcion
 */
public class RegistroTareas extends AppCompatActivity {
    /**
     * Atributo tipo objeto formulario
     */
    private FormularioInteraccion formulario;

    /**
     * Metodo que inicia el formulario de registro de tareas y su aplicacion
     * @param bundle
     */
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_registro_tareas);
        formulario = new FormularioInteraccion(this);

        Intent intent = getIntent();
        Tarea tarea = (Tarea) intent.getSerializableExtra("tarea");
        if (tarea != null) {
            formulario.putFormulario(tarea);
        }
    }
    /**
     * Metodo creador de menu de barras.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_registro_tareas, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * Metodo que valida el registro del formulario.
     * Se muestra mensaje de guardado.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_guardarDatos: //caso de interaccion con el boton guadarDatos
                Tarea tarea = formulario.setTarea();
                ConexionTarea conexionTarea = new ConexionTarea(this);
                if (!tarea.getTitulo().equals("") & !tarea.getHora().equals("") & !tarea.getLugar().equals("") & !tarea.getDescripcion().equals("")){
                    if (tarea.getId() != null){
                        conexionTarea.update(tarea);
                    } else {
                        conexionTarea.insert(tarea);
                    }
                    conexionTarea.close();
                    Toast.makeText(RegistroTareas.this, "Evento '" + tarea.getTitulo() + "'\n Registrado con exito", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }else{
                    Toast.makeText(RegistroTareas.this, "Error de Registro\n\nComplete todos los campos", Toast.LENGTH_SHORT).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
