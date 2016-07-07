package dspm.dc.ufc.br.cadesaude.DAO;

/**
 * Created by jonas on 07/07/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dspm.dc.ufc.br.cadesaude.models.Comentario;

/** "Many to One" - Vários ids de comentario tem apenas um de posto*/

public class ComentarioDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "bd_comentarios";
    private static final int VERSAO = 1;
    private static final String TABELA = "comentarios";

    public ComentarioDAO(Context context){
        super(context, DATABASE,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA + "(" +
                "id INTEGER PRIMARY KEY REFERENCES postos(id),"+
                //"id INTEGER PRIMARY KEY, "+
                "name TEXT NOT NULL, "+
                "titulo TEXT, "+
                "corpo TEXT, "+
                ");";
        try {
            db.execSQL(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }
     public long insert(Comentario comentario) {

        ContentValues cv = new ContentValues();
        cv.put("id", comentario.getId());
        cv.put("name", comentario.getNome());
        cv.put("titulo",comentario.getTitulo());
        cv.put("corpo",comentario.getCorpo());
        return getWritableDatabase().insert(TABELA, null, cv);
     }

    public void delete(int id) {
        String args[] = {String.valueOf(id)};
        getWritableDatabase().delete(TABELA, "id=?", args);
    }
     //Necessário?
     public Comentario getComentario(Comentario comentario) {

        String sql = "SELECT * FROM " + TABELA + " WHERE ID = ?;";
        String[] args = {String.valueOf(comentario.getId())};
        final Cursor cursor = getReadableDatabase().rawQuery(sql, args);

        while(cursor.moveToNext()){
            comentario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            comentario.setNome(cursor.getString(cursor.getColumnIndex("name")));
            comentario.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            comentario.setCorpo(cursor.getString(cursor.getColumnIndex("corpo")));
        }
        cursor.close();
        return comentario;
    }

}
