package dspm.dc.ufc.br.cadesaude;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;


import android.app.NotificationManager;
import android.app.PendingIntent;

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
import java.util.Vector;

import dspm.dc.ufc.br.cadesaude.DAO.BD;
import dspm.dc.ufc.br.cadesaude.models.Posto;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,
        GoogleMap.OnInfoWindowClickListener {

    /**
     * o onCreate chama o getMapAsync, quando o mapa estiver pronto ele chama o onMapReady
     * responsável por configurar o mapa.
     * Após configurar o mapa é pego a localização atual do usuário pelo método getLocation
     * A partir da localização do usuário é pego da base de dados SQLite os postos de saúde mais proximos
     * O resultado da pesquisa no banco é montado no mapa onde cada posto representa um marcador
     * */
    Context context = this;
    BD banco;
    private GoogleMap mMap = null;
    LocationManager locationManager;
    MarkerOptions markerMe = null;
    LatLng meLocationLatLong;
    public static final float RADIUS = 10000; // raio dos postos que aparecerá na aplicação
    public static final Integer RADIUS_KM = 10;

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

        banco = new BD(context);
        //Posto p = new Posto(30000, -3.731628, -38.588563, "Posto de Teste", 30000, 30000, );

        buildLocationService(); //algum objeto nulo está sendo chamado aqui por isso dá erro, depois ver isso

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap = googleMap;

        /** Exemplo setando latitudes...
         LatLng pici = new LatLng(-3.7446337, -38.5727);
         mMap.addMarker(new MarkerOptions().position(pici).title("UFC PICI"));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(pici));
         mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
         */


         mMap.setOnInfoWindowClickListener(this);

         if(markerMe != null) {
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

    private Location getLocation(){

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //return;
        }

        //LatLng locationLatLong = null;
        Log.d("Response", "Entrou getLocation");
        Location location = locationManager.getLastKnownLocation(provider);
        if(location == null)
        {
            meLocationLatLong = new LatLng(-3.731628, -38.588563);
        }
        else
        {
            meLocationLatLong = new LatLng(location.getLatitude(), location.getLongitude());
        }

        // Adiciona o marcador com a nova posição
        markerMe = new MarkerOptions().position(meLocationLatLong).title("Você está aqui!");

        if(mMap != null){
            setCamera();
        }
        return location;
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
        List<Posto> list = new ArrayList<Posto>();

        // TODO construir a classe do SQLite
        // Aqui o list receberia o list do SQLite com os postos perto da latitude e longitude informados

        Location location = new Location("eu"); //= getLocation(); //pega a localização do usuário
        location.setLatitude(-3.731628);
        location.setLongitude(-38.588563);
        Location location2 = new Location("posto"); // localização de algum posto
        Posto pTemp;


        Vector<Posto> pTempRadius = banco.getByRadius(location.getLatitude(), location.getLongitude());
        list.addAll(pTempRadius);

        // Dados para simulação

        /*
        Posto p1 = new Posto(1, "Herminia Leitão", -3.731192, -38.588053);
        Posto p2 = new Posto(2, "Posto 2", -3.729789, -38.589350);
        Posto p3 = new Posto(3, "Galeto do Gordinho", -3.732298, -38.590135);
        Posto p4 = new Posto(4, "Posto 4", -3.731830, -38.587559);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        */

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
