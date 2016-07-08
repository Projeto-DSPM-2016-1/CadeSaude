package dspm.dc.ufc.br.cadesaude.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dspm.dc.ufc.br.cadesaude.models.Avaliacao;

/**
 * Created by arthurbrito on 07/07/16.
 */
public class avaliacaoDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "bd_avaliacao";
    private static final int VERSAO = 1;
    private static final String TABELA = "avaliacao";


    public avaliacaoDAO(Context context){
        super(context, DATABASE,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA + "(" +
                "id INTEGER PRIMARY KEY,"+
                "nota INTEGER NOT NULL, "+
                "quantidade_avalicao INTEGER NOT NULL"+
                "posto_id INTEGER, "+
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

    public long insert(Avaliacao avaliacao) {

        ContentValues cv = new ContentValues();
        cv.put("id", avaliacao.getId());
        cv.put("nota", avaliacao.getNota());
        cv.put("quantidade_avaliacao",avaliacao.getQuantidade_avaliacao());

        cv.put("posto_id",avaliacao.getPosto_id());
        return getWritableDatabase().insert(TABELA, null, cv);
    }

    public Avaliacao getAvaliacao(Avaliacao avaliacao) {

        String sql = "SELECT * FROM " + TABELA + " WHERE ID = ?;";
        String[] args = {String.valueOf(avaliacao.getId())};
        final Cursor cursor = getReadableDatabase().rawQuery(sql, args);

        while(cursor.moveToNext()){
            avaliacao.setId(cursor.getInt(cursor.getColumnIndex("id")));
            avaliacao.setNota(cursor.getInt(cursor.getColumnIndex("nota")));
            avaliacao.setQuantidade_avaliacao((cursor.getColumnIndex("quantidade_avaliacao")));
            avaliacao.setPosto_id(cursor.getInt(cursor.getColumnIndex("posto_id")));
        }
        cursor.close();
        return avaliacao;
    }
}
