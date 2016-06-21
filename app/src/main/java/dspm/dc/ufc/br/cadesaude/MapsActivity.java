package dspm.dc.ufc.br.cadesaude;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    /*
     * Definindo Fluxo
     *
     * o onCreate chama o getMapAsync, quando o mapa estiver pronto ele chama o onMapReady
     * responsável por configurar o mapa.
     *
     * Após configurar o mapa é pego a localização atual do usuário pelo método getLocation
     *
     * A partir da localização do usuário é pego da base de dados SQLite os postos de saúde mais proximos
     *
     * O resultado da pesquisa no banco é montado no mapa onde cada posto representa um marcador
     *
     */

    private GoogleMap mMap;
    LocationManager locationManager;
    MarkerOptions markerMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        markerMe = null;

        getLocation();
    }

    // Esse método é responsável por extrair a informação da base de dados SQLite e criar os
    // marcadores referentes a cada posto
    private void buildMarkers(double latitude, double longitude){
        List<Posto> list;

        // TODO construir a classe do SQLite
        // Aqui o list receberia o list do SQLite com os postos perto da latitude e longitude informados

    }

    private void buildLocationService() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

    }

    private void getLocation(){

        buildLocationService();

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LatLng locationLatLong = null;
        Location location = locationManager.getLastKnownLocation(provider);
        locationLatLong = new LatLng(location.getLatitude(), location.getLongitude());

        // Apaga os marcadores que tem no mapa
        mMap.clear();

        // Adiciona o marcador com a nova posição
        markerMe = new MarkerOptions().position(locationLatLong).title("Você está aqui!");
        mMap.addMarker(markerMe);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationLatLong));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(locationLatLong).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // Chama o método responsável por construid os marcadores dos postos
        buildMarkers(location.getLatitude(), location.getLongitude());
    }
}
