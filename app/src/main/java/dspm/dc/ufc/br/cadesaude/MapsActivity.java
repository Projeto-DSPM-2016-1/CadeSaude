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

    private GoogleMap mMap = null;
    LocationManager locationManager;
    MarkerOptions markerMe = null;
    LatLng meLocationLatLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        buildLocationService();
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

        if(markerMe != null)
        {
            setCamera();
        }

        //buildLocationService();
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

            //askGPS();
            Log.d("Response", "1");
        }

        getLocation();
    }

    private void getLocation(){

        //buildLocationService();

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
        list.add(p1);

        Posto pTemp;

        for (int i = 0; i < list.size(); i++) //(Posto pTemp: list)
        {
            pTemp = list.get(i);
            LatLng locationLatLong = new LatLng(pTemp.getLatitude(), pTemp.getLongitude());

            // Adiciona o marcador com a nova posição
            MarkerOptions markerPosto = new MarkerOptions()
                    .position(locationLatLong)
                    .title(pTemp.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .snippet("Population: 4,137,400");
            mMap.addMarker(markerPosto);
        }

    }

    private void askGPS(){

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.build();
    }



    /*
    private void askGPSPermission(){
        GoogleApiClient googleApiClient = null;

        if (googleApiClient == null)
        {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            //**************************
            builder.setAlwaysShow(true); //this is the key ingredient
            //**************************

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result.getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can initialize location
                            // requests here.
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(((Activity)this), 1000);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });
        }

    }
    */
}
