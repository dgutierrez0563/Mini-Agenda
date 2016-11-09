/**
 *Sistema de listas de tareas en lenguaje Android, desarrollado
 *con fines didacticos y educativos de libre uso y mejora para
 *cualquiera que desee utilizarlo con fines no lucrativos.
 *10-11-2016 1.0v
 *by wsullivan 2016.
 */
package com.enoqueleal.agenda.activity;
/**
 * Importe de paquetes necesarios en la la clase
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.enoqueleal.agenda.R;
import com.enoqueleal.agenda.conexionSQLite.ConexionTarea;
import com.enoqueleal.agenda.nodoTarea.Tarea;
import java.util.List;

/**
 * Clase que actua como lista
 */
public class ListViewTareas extends AppCompatActivity {
    /**
     * Aributo tipo objeto listView
     */
    private ListView listaTareas;

    /**
     * Metodo que inicia la lista de tareas en su respectivo formulario
     * @param bundle
     */
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_lista_tareas);
        
        listaTareas = (ListView) findViewById(R.id.lista_tareas);

        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
            Tarea tarea = (Tarea)  listaTareas.getItemAtPosition(position);
            Intent intentVaiProFormulario = new Intent(ListViewTareas.this, RegistroTareas.class);
                intentVaiProFormulario.putExtra("tarea", tarea);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoAluno = (Button) findViewById (R.id.btn_nuevoNodo);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intentVaiProFormulario = new Intent(ListViewTareas.this, RegistroTareas.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaTareas);
    }
    /**
     * Metodo que refresca la lista
     */
    @Override
        public void onResume(){
        super.onResume();
        cargaLista();
    }
    /**
     * Metodo que agrega los datos de nodos tarea a la lista.
     */
    private void cargaLista() {
        ConexionTarea tarea = new ConexionTarea(this);
        List<Tarea> tareas = tarea.searchTareas();
        tarea.close();

        ArrayAdapter<Tarea> adapter = new ArrayAdapter<Tarea>(this, android.R.layout.simple_list_item_1, tareas);
        listaTareas.setAdapter(adapter);
    }
    /**
     * Metodo que realiza la decicion de eliminar un nodo si este es seleccionado
     * @param menu
     * @param view
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, final ContextMenu.ContextMenuInfo menuInfo)  {
        MenuItem deletar = menu.add("¿Desea eliminar esta tarea?\nPresione para continuar...");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Tarea tarea = (Tarea) listaTareas.getItemAtPosition(info.position);

                ConexionTarea conexionTarea = new ConexionTarea(ListViewTareas.this);
                conexionTarea.deletTarea(tarea); //enviamos a borrar
                conexionTarea.close(); //se cierra conexion

                Toast.makeText(ListViewTareas.this,"El Evento '"+ tarea.getTitulo() + "'\nha sido eliminado", Toast.LENGTH_SHORT).show();
                cargaLista();
                return false;
            }
        });
    }
}
