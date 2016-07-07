package dspm.dc.ufc.br.cadesaude;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import dspm.dc.ufc.br.cadesaude.models.Posto;

/**
 * Created by arthurbrito on 06/07/16.
 */
public class BD {
    private SQLiteDatabase db;
    //ArrayList<Posto> postoArrayList;

    public BD(Context context){
        BDcore auxBD = new BDcore(context);
        db = auxBD.getWritableDatabase();
    }

    public void inserir (Posto posto){
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
        valores.put("dsc_medicamentos",posto.getDscNedicamentos());
        //demais valores do posto devem ser inseridos aqui

        db.insert("postos",null,valores); //tabela, coluna adicional, informação
    }

    public void atualizar(Posto posto) {
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
        db.update("postos",valores,"posto_id = ?", new String []{""+posto.getId()});
    }

    public void deletar(Posto posto){
        db.delete("posto","posto_id = "+posto.getId(),null);
    }

    public Posto buscar(Integer id){
        Cursor result = db.rawQuery("select * from postos where posto_id = ?",new String[] {id.toString()});
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
        Cursor result = db.rawQuery(("select posto_id from postos"),null);
        return result.getCount();
    }

}
