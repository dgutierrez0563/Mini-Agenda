/**
 *Sistema de listas de tareas en lenguaje Android, desarrollado
 *con fines didacticos y educativos de libre uso y mejora para
 *cualquiera que desee utilizarlo con fines no lucrativos.
 *10-11-2016 1.0v
 *by wsullivan 2016.
 */
package com.enoqueleal.agenda.conexionSQLite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import com.enoqueleal.agenda.nodoTarea.Tarea;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase conexcion de la base de datos SQLite
 */
public class ConexionTarea extends SQLiteOpenHelper{
    /**
     *Metodo contructor de la clase conexcionSQLite
     */
    public ConexionTarea(Context context) {
        super(context, "Agenda", null, 1);
    }
    /**
     * Metodo que crea la tabla de tareas
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        //String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, sobrenome TEXT);";
        String sql = "CREATE TABLE tareas (id INTEGER PRIMARY KEY, titulo TEXT NOT NULL, hora TEXT NOT NULL, lugar TEXT NOT NULL, descripcion TEXT);";
        database.execSQL(sql);
    }

    /**
     * Metodo que borra la tabla de tareas o actualizacion total
     * @param database
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tareas";
        database.execSQL(sql);
        onCreate(database);
    }

    /**
     * Metodo que inserta el nodo en la tabla de tareas
     * @param tarea
     */
    public void insert(Tarea tarea)  {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = pegaDadosDoAluno(tarea);
        database.insert("tareas", null, values );
    }

    /**
     * Metodo que realiza la busqueda en la tabla de tareas de todos los campos
     * @return tareas
     */
    public List<Tarea> searchTareas()  {
        String sql = "SELECT * FROM tareas;";
        SQLiteDatabase database = getReadableDatabase();
        Cursor query = database.rawQuery(sql, null);

        List<Tarea> tareas = new ArrayList<Tarea>(); //creacion de una lista para insertar las tareas
        while (query.moveToNext()) { //recorre hasta el final
            Tarea tarea = new Tarea();
            tarea.setId(query.getLong(query.getColumnIndex("id")));
            tarea.setTitulo(query.getString(query.getColumnIndex("titulo")));
            tarea.setHora(query.getString(query.getColumnIndex("hora")));
            tarea.setLugar(query.getString(query.getColumnIndex("lugar")));
            tarea.setDescripcion(query.getString(query.getColumnIndex("descripcion")));
            tareas.add(tarea); //add a cada nodo a la lista
        }
        query.close();
        return tareas;
    }

    /**
     * Metodo que borra el nodo de la tabla tareas mediante el id como referencia
     * @param tarea
     */
    public void deletTarea(Tarea tarea) {
        SQLiteDatabase database = getWritableDatabase();
        String [] params = {tarea.getId().toString()};
        database.delete("tareas", "id = ?", params);
    }

    /**
     * Metodo que actualiza la tabla tareas mediante el id como referencia
     * @param tarea
     */
    public void update(Tarea tarea) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = pegaDadosDoAluno(tarea);
        String[] params = {tarea.getId().toString()};
        database.update("tareas", values, "id = ?", params);
    }

    /**
     * Metodo que setea los nodos en un ContentValues
     * @param tarea
     * @return
     */
    @NonNull
    private ContentValues pegaDadosDoAluno(Tarea tarea) {
        ContentValues values = new ContentValues();
        values.put("titulo", tarea.getTitulo());
        values.put("hora", tarea.getHora());
        values.put("lugar",tarea.getLugar());
        values.put("descripcion",tarea.getDescripcion());
        return values;
    }
}
