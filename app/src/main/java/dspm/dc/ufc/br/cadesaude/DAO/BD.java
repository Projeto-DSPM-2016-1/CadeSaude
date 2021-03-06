package dspm.dc.ufc.br.cadesaude.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Vector;

import dspm.dc.ufc.br.cadesaude.models.Posto;

/**
 * Created by arthurbrito on 06/07/16.
 */
public class BD {
    //private SQLiteDatabase db;
    //ArrayList<Posto> postoArrayList;
    BDcore auxBD;

    public BD(Context context){
        auxBD = new BDcore(context);
        //db = auxBD.getWritableDatabase();
    }

    public void inserir (Posto posto){
        SQLiteDatabase db = auxBD.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("posto_id",posto.getId());
        valores.put("latitude",posto.getLatitude());
        valores.put("longitude",posto.getLongitude());
        valores.put("cod_municipio",posto.getCodMunic());
        valores.put("cod_cnes",posto.getCodCnes());
        valores.put("nome_posto",posto.getName());
        valores.put("endereco_posto",posto.getEndereco());
        valores.put("bairro_posto",posto.getBairro());
        valores.put("cidade_posto",posto.getCidade());
        valores.put("telefone_posto",posto.getTelefone());
        valores.put("dsc_estrut_fisic_ambiencia",posto.getDscEstrutFisicAmbiencia());
        valores.put("dsc_adap_defic_fisic_idosos",posto.getDscAdapDeficFisicIdosos());
        valores.put("dsc_equipamentos",posto.getDscEquipamentos());
        valores.put("dsc_medicamentos", posto.getDscNedicamentos());
        //demais valores do posto devem ser inseridos aqui
        Log.d("BDResponse", "" + posto.getId());

        db.insert("postos", null, valores); //tabela, coluna adicional, informação
    }

    public void atualizar(Posto posto) {
        SQLiteDatabase db = auxBD.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("posto_id",posto.getId());
        valores.put("latitude",posto.getLatitude());
        valores.put("longitude",posto.getLongitude());
        valores.put("cod_municipio",posto.getCodMunic());
        valores.put("cod_cnes",posto.getCodCnes());
        valores.put("nome_posto",posto.getName());
        valores.put("endereco_posto",posto.getEndereco());
        valores.put("bairro_posto",posto.getBairro());
        valores.put("cidade_posto",posto.getCidade());
        valores.put("telefone_posto",posto.getTelefone());
        valores.put("dsc_estrut_fisic_ambiencia",posto.getDscEstrutFisicAmbiencia());
        valores.put("dsc_adap_defic_fisic_idosos",posto.getDscAdapDeficFisicIdosos());
        valores.put("dsc_equipamentos", posto.getDscEquipamentos());
        db.update("postos", valores, "posto_id = ?", new String[]{"" + posto.getId()});
    }

    public void deletar(Posto posto){
        SQLiteDatabase db = auxBD.getWritableDatabase();
        db.delete("postos", "posto_id = " + posto.getId(), null);
    }

    public Posto buscar(Integer id){
        String[] fieldValues = new String[1];
        fieldValues[0] = Integer.toString(id);
        //Cursor result = db.rawQuery("select * from notes where id = ?", fieldValues);
        Log.d("Response" , "Procurando id = " + id);

        SQLiteDatabase db = auxBD.getWritableDatabase();
        Cursor result = db.rawQuery("select * from postos where posto_id = ?", fieldValues);
        result.moveToFirst();
        Posto posto = new Posto();
        posto.setId(result.getInt(0));
        posto.setLatitude(result.getDouble(1));
        posto.setLongitude(result.getDouble(2));
        posto.setCodMunic(result.getInt(3));
        posto.setCodCnes(result.getInt(4));
        posto.setName(result.getString(5));
        posto.setEndereco(result.getString(6));
        posto.setBairro(result.getString(7));
        posto.setCidade(result.getString(8));
        posto.setTelefone(result.getString(9));
        posto.setDscEstrutFisicAmbiencia(result.getString(10));
        posto.setDscAdapDeficFisicIdosos(result.getString(11));
        posto.setDscEquipamentos(result.getString(12));
        posto.setDscNedicamentos(result.getString(13));

        return posto;
    }
    public void preencherBanco(Vector<Posto> postoVector){ // onde essa função deve ficar?
        for (Posto posto: postoVector) {
            inserir(posto);
        }
    }

    public int size(){
        SQLiteDatabase db = auxBD.getWritableDatabase();
        Cursor result = db.rawQuery(("select posto_id from postos"),null);
        return result.getCount();
    }

    public Vector<Posto> getByRadius(Double latitude, Double longitude){
        String[] fieldValues = new String[4];

        Double distance = 0.045;

        fieldValues[0] = Double.toString(latitude - distance); //limite inferior latitude
        fieldValues[1] = Double.toString(latitude + distance); //limite superior latitude
        fieldValues[2] = Double.toString(longitude - distance);  //limite inferior longitude
        fieldValues[3] = Double.toString(longitude + distance);  //limite superior longitude

        SQLiteDatabase db = auxBD.getWritableDatabase();
        Cursor result = db.rawQuery("select * from postos where latitude >= ? AND latitude <= ? AND longitude >= ? AND longitude <= ?", fieldValues);

        Vector<Posto> postos = new Vector<Posto>();

        if (result != null && result.getCount() > 0) {
            result.moveToFirst();
            while (result.isAfterLast() == false) {
                Posto posto = new Posto();
                posto.setId(result.getInt(0));
                posto.setLatitude(result.getDouble(1));
                posto.setLongitude(result.getDouble(2));
                posto.setCodMunic(result.getInt(3));
                posto.setCodCnes(result.getInt(4));
                posto.setName(result.getString(5));
                posto.setEndereco(result.getString(6));
                posto.setBairro(result.getString(7));
                posto.setCidade(result.getString(8));
                posto.setTelefone(result.getString(9));
                posto.setDscEstrutFisicAmbiencia(result.getString(10));
                posto.setDscAdapDeficFisicIdosos(result.getString(11));
                posto.setDscEquipamentos(result.getString(12));
                posto.setDscNedicamentos(result.getString(13));

                postos.add(posto);
                result.moveToNext();
            }
        }
        return postos;
    }

    public void close() {
        auxBD.close();
    }

}
