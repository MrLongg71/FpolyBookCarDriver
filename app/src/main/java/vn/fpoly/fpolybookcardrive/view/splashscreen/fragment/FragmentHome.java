package vn.fpoly.fpolybookcardrive.view.splashscreen.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import vn.fpoly.fpolybookcardrive.BuildConfig;
import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.service.FirebaseCloudMessage;


public class FragmentHome extends Fragment implements  OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private LinearLayout linearLayout;
    private ImageButton imageButton;
    private Switch aSwitch;
    private ImageView imageView;
    private boolean checked = false;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private String placeNameCurrent ;
    private LatLng locationcurrent;
    private double latitude = 0;
    private double longitude = 0;
    private GoogleMap map;
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);

        getToken();


        mapFragment.getMapAsync(this);
        aSwitch.setEnabled(false);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);



        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        return view;
    }

    private void getToken() {
        getActivity().startService(new Intent(getActivity(), FirebaseCloudMessage.class));
    }

    private void initView(View view) {
        linearLayout    = view.findViewById(R.id.lineralayout);
        imageButton     = view.findViewById(R.id.imgButton);
        aSwitch         = view.findViewById(R.id.swich);
        imageView       = view.findViewById(R.id.imgThunder);
        mapFragment     = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.getUiSettings().setCompassEnabled(true);

        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setTrafficEnabled(true);

    }

    private void addMarker(LatLng location, String place,int icon) {
//        new Handler().postDelayed(new Runnable() {
////            @Override
////            public void run() {
////
////            }
////        },10000);
        map.clear();
       final Marker marker =  map.addMarker(new MarkerOptions().position(location).title(place).icon(BitmapDescriptorFactory.fromResource(icon)));
        marker.setVisible(false);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked) {
                    marker.setVisible(false);
                    checked = !checked;
                    linearLayout.setBackgroundResource(R.drawable.custom_unchecked);

                    aSwitch.setEnabled(false);
                    aSwitch.setChecked(false);
                    map.setMyLocationEnabled(true);
                } else {
                    marker.setVisible(true);
                    checked = !checked;
                    linearLayout.setBackgroundResource(R.drawable.custom_checked);
                    aSwitch.setEnabled(true);
                    aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                b = !b;
                                imageView.setBackgroundResource(R.drawable.thunderon);
                            } else {
                                b = !b;
                                imageView.setBackgroundResource(R.drawable.thunderof);
                            }
                        }
                    });
                    map.setMyLocationEnabled(false);

                }
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();
            placeNameCurrent = "You are here!";

            locationcurrent = new LatLng(latitude, longitude);

            addMarker(locationcurrent, placeNameCurrent,R.drawable.iconmarker);

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(locationcurrent, 14);
            map.moveCamera(cameraUpdate);

        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,  this);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,  this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();

        super.onStop();
    }
    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }
}
