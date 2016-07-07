package dspm.dc.ufc.br.cadesaude.models;

/**
 * Created by Matheus on 07/07/2016.
 */
public class Comentario {

    private String nome;
    private String titulo;
    private String corpo;
    private int comentario_id;
    private int posto_id;
    private String data;
    private String horario;

    public Comentario(){
    }

    public Comentario(String nome, String titulo, String corpo){
        this.nome = nome;
        this.titulo = titulo;
        this.corpo = corpo;
    }

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

    public int getComentario_id(){
        return comentario_id;
    }

    public void setComentario_id(int comentario_id){
        this.comentario_id = comentario_id;
    }

    public int getPosto_id(){
        return posto_id;
    }

    public void setPosto_id(int posto_id){
        this.posto_id = posto_id;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getHorario(){
        return horario;
    }

    public void setHorario(String horario){
        this.horario = horario;
    }

}