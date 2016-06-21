package dspm.dc.ufc.br.cadesaude;

/**
 * Created by Thiago on 20/06/2016.
 */
public class Posto {
    // TODO implementar a classe conforme os dados estraidos do servidor

    private int id;
    private String name;
    private double latitude;
    private double longitude;

    public Posto() {
    }

    public Posto(int id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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
}
