package dspm.dc.ufc.br.cadesaude.models;

/**
 * Created by Matheus on 07/07/2016.
 */
public class Comentario {

    private int id;
    private String nome;
    private String titulo;
    private String corpo;
    private String horario;

    //private int comentario_id;
    private int posto_id;

    public Comentario(){
    }

    public Comentario(int id, String nome, String titulo, String corpo, String horario, int posto_id) {
        this.id = id;
        this.nome = nome;
        this.titulo = titulo;
        this.corpo = corpo;
        this.horario = horario;
        this.posto_id = posto_id;
    }

    @Deprecated
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

    /*
    public int getComentario_id(){
        return comentario_id;
    }*/

    /*
    public void setComentario_id(int comentario_id){
        this.comentario_id = comentario_id;
    }
    */

    public int getPosto_id(){
        return posto_id;
    }

    public void setPosto_id(int posto_id){
        this.posto_id = posto_id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", titulo='" + titulo + '\'' +
                ", corpo='" + corpo + '\'' +
                ", horario='" + horario + '\'' +
                ", posto_id=" + posto_id +
                '}';
    }
}