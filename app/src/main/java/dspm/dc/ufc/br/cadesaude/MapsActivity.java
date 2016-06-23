package dspm.dc.ufc.br.cadesaude;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnInfoWindowClickListener {

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

    private GoogleMap mMap = null;
    LocationManager locationManager;
    MarkerOptions markerMe = null;
    LatLng meLocationLatLong;

    // Create the hash map on the beginning
    HashMap<String, Posto> markerPostoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        markerPostoMap = new HashMap <String, Posto>();

        buildLocationService();

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnInfoWindowClickListener(this);

        if(markerMe != null)
        {
            setCamera();
        }

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

        getLocation();
    }

    private void getLocation(){

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //LatLng locationLatLong = null;
        Log.d("Response", "2");
        Location location = locationManager.getLastKnownLocation(provider);
        meLocationLatLong = new LatLng(location.getLatitude(), location.getLongitude());

        // Adiciona o marcador com a nova posição
        markerMe = new MarkerOptions().position(meLocationLatLong).title("Você está aqui!");

        if(mMap != null){
            setCamera();
        }
    }

    private void setCamera(){
        mMap.addMarker(markerMe);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(meLocationLatLong));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(meLocationLatLong).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // Chama o método responsável por construid os marcadores dos postos
        buildMarkers(meLocationLatLong.latitude, meLocationLatLong.longitude);
    }

    // Esse método é responsável por extrair a informação da base de dados SQLite e criar os
    // marcadores referentes a cada posto
    private void buildMarkers(double latitude, double longitude){
        List<Posto> list;

        // TODO construir a classe do SQLite
        // Aqui o list receberia o list do SQLite com os postos perto da latitude e longitude informados

        // Dados para simulação
        list = new ArrayList<Posto>();
        Posto p1 = new Posto(1, "Herminia Leitão", -3.731192, -38.588053);
        Posto p2 = new Posto(2, "Posto 2", -3.729789, -38.589350);
        Posto p3 = new Posto(3, "Galeto do Gordinho", -3.732298, -38.590135);
        Posto p4 = new Posto(4, "Posto 4", -3.731830, -38.587559);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        Posto pTemp;

        // Inserindo os marcadores de cada posto no mapa
        for (int i = 0; i < list.size(); i++) //(Posto pTemp: list)
        {
            pTemp = list.get(i);
            LatLng locationLatLong = new LatLng(pTemp.getLatitude(), pTemp.getLongitude());

            // Adiciona o marcador com a nova posição
            MarkerOptions markerPosto = new MarkerOptions()
                    .position(locationLatLong)
                    .title(pTemp.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .snippet("Aperte aqui");

            Marker m = mMap.addMarker(markerPosto);

            // Adicionando marcador e posto no hashMap para pegar quando o usuario clicar
            markerPostoMap.put(m.getId(), pTemp);
        }

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        Posto pTemp = markerPostoMap.get(marker.getId());

        Intent intent = new Intent(this, PostoActivity.class);
        intent.setAction("br.ufc.dc.dspm.cadesaude.POSTOACTIVITY");
        intent.putExtra("ID", pTemp.getId());
        intent.putExtra("NOME", pTemp.getName());
        startActivity(intent);

    }
}
