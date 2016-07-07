package dspm.dc.ufc.br.cadesaude;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void preencherBanco(ArrayList<Posto> postoArrayList){ // onde essa função deve ficar?
        for (int i = 0; i <= postoArrayList.size() ; i++) {
        }
    }
}
