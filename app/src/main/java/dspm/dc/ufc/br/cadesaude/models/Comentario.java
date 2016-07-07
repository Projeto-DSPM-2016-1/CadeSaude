package dspm.dc.ufc.br.cadesaude.models;

/**
 * Created by Matheus on 07/07/2016.
 */
public class Comentario {

    private int id;
    private String nome;
    private String titulo;
    private String corpo;
    public Comentario(){
    }

    public Comentario(int id,String nome, String titulo, String corpo){
        this.id=id;
        this.nome = nome;
        this.titulo = titulo;
        this.corpo = corpo;
    }
    public int getId() {return id;}
    public void setId(int id){this.id=id;}
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo){
        this.corpo = corpo;
    }


}