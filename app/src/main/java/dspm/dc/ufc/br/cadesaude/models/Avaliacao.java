package dspm.dc.ufc.br.cadesaude.models;

/**
 * Created by arthurbrito on 07/07/16.
 */
public class Avaliacao {
    private int id;
    private int nota;
    private int quantidade_avaliacao;

    //private int comentario_id;
    private int posto_id;

    public Avaliacao(){
    }

    public Avaliacao(int id, int nota,int quantidade_avaliacao, int posto_id) {
        this.id = id;
        this.nota = nota;
        this.quantidade_avaliacao = quantidade_avaliacao;
        this.posto_id = posto_id;
    }


    public int getId() {return id;}

    public void setId(int id){this.id=id;}

    public int getNota() {return nota;}

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setQuantidade_avaliacao(int quantidade_avaliacao){this.quantidade_avaliacao = quantidade_avaliacao;}

    public int getQuantidade_avaliacao(){return quantidade_avaliacao;}

    public int getPosto_id(){
        return posto_id;
    }

    public void setPosto_id(int posto_id){
        this.posto_id = posto_id;
    }

}
