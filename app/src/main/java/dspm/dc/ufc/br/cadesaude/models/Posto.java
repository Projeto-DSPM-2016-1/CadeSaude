package dspm.dc.ufc.br.cadesaude.models;

/**
 * Created by Thiago on 20/06/2016.
 */
public class Posto {
    // TODO implementar a classe conforme os dados estraidos do servidor

    private int id; //
    private double latitude; //
    private double longitude; //
    private String name; //
    private int codMunic;
    private int codCnes;
    private String endereco;
    private String bairro;
    private String cidade;
    private String telefone;
    private String dscEstrutFisicAmbiencia;
    private String dscAdapDeficFisicIdosos;
    private String dscEquipamentos;
    private String dscNedicamentos;




    public Posto() {
    }

    @Deprecated
    public Posto(int id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Posto(int id, double latitude, double longitude, String name, int codMunic, int codCnes, String endereco, String bairro, String cidade, String telefone, String dscEstrutFisicAmbiencia, String dscAdapDeficFisicIdosos, String dscEquipamentos, String dscNedicamentos) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.codMunic = codMunic;
        this.codCnes = codCnes;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.telefone = telefone;
        this.dscEstrutFisicAmbiencia = dscEstrutFisicAmbiencia;
        this.dscAdapDeficFisicIdosos = dscAdapDeficFisicIdosos;
        this.dscEquipamentos = dscEquipamentos;
        this.dscNedicamentos = dscNedicamentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCodMunic() {
        return codMunic;
    }

    public void setCodMunic(int codMunic) {
        this.codMunic = codMunic;
    }

    public int getCodCnes() {
        return codCnes;
    }

    public void setCodCnes(int codCnes) {
        this.codCnes = codCnes;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDscEstrutFisicAmbiencia() {
        return dscEstrutFisicAmbiencia;
    }

    public void setDscEstrutFisicAmbiencia(String dscEstrutFisicAmbiencia) {
        this.dscEstrutFisicAmbiencia = dscEstrutFisicAmbiencia;
    }

    public String getDscAdapDeficFisicIdosos() {
        return dscAdapDeficFisicIdosos;
    }

    public void setDscAdapDeficFisicIdosos(String dscAdapDeficFisicIdosos) {
        this.dscAdapDeficFisicIdosos = dscAdapDeficFisicIdosos;
    }

    public String getDscEquipamentos() {
        return dscEquipamentos;
    }

    public void setDscEquipamentos(String dscEquipamentos) {
        this.dscEquipamentos = dscEquipamentos;
    }

    public String getDscNedicamentos() {
        return dscNedicamentos;
    }

    public void setDscNedicamentos(String dscNedicamentos) {
        this.dscNedicamentos = dscNedicamentos;
    }
}
