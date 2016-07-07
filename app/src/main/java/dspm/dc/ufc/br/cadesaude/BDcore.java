package dspm.dc.ufc.br.cadesaude;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arthurbrito on 06/07/16.
 */
public class BDcore extends SQLiteOpenHelper{
    private static final String NOME_BD = "postos";
    private static final int VERSAO_BD = 1;

    public BDcore(Context context){
        super(context,NOME_BD,null,VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(posto_id integer primary key autoincrement,latitude double not null, longitude double not null, cod_municipio integer not null, cod_cnes int not null, nome_posto text not null, bairro_posto text, cidade_posto text, telefone_posto text, dsc_estrut_fisic_ambiencia text, dsc_adap_defic_fisic_idosos text, dsc_equipamentos text, dsc_medicamentos text");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //aqui fará a comparação se houve diferença entre as entrelas de um posto
        db.execSQL("drop table postos");
        onCreate(db);
    }
}
