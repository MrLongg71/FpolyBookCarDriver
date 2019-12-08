package vn.fpoly.fpolybookcardrive.view.splashscreen.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Objects;

import vn.fpoly.fpolybookcardrive.BuildConfig;
import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcardrive.service.FirebaseCloudMessage;


public class FragmentHome extends Fragment implements   com.google.android.gms.location.LocationListener,
        IViewHome,OnMapReadyCallback {
    private LinearLayout linearLayout,linearLayoutPickUpCustomer;
    private ImageButton imageButton;
    private Switch aSwitch;
    private ImageView imageView;
    private Button btnArrive,btnPickUp;
    private boolean checked = false;
    private String placeNameCurrent ;
    private LatLng locationGo,locationCome,locationcurent;
    private PresenterGoogleMap presenterGoogleMap;
    private TextView txtPickUp,txtDestination,txtDeny,txtEstimatePrice,txtKm;
    private RelativeLayout btnAcceppt,btnCall,btnChat;
    private double latitude = 0;
    private double longitude = 0;
    private int clickCount = 0;
    private LocationManager locationManager;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private GoogleMap map;
    private SupportMapFragment mapFragment;

    private AlertDialog alertDialog;
    private String Uid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        Uid = getActivity().getIntent().getStringExtra("Uid");
        getToken();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("myFunction"));
        mapFragment.getMapAsync(this);
        aSwitch.setEnabled(false);
        getLocationDriver();

        return view;
    }

    private void getToken() {
        getActivity().startService(new Intent(getActivity(), FirebaseCloudMessage.class));
    }

    private void initView(View view) {
        linearLayout                = view.findViewById(R.id.lineralayout);
        imageButton                 = view.findViewById(R.id.imgButton);
        aSwitch                     = view.findViewById(R.id.swich);
        imageView                   = view.findViewById(R.id.imgThunder);
        locationManager             = getActivity().getSystemService(LocationManager.class);
        mapFragment                 = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        presenterGoogleMap          = new PresenterGoogleMap(this);
        linearLayoutPickUpCustomer  = view.findViewById(R.id.dialog_pickUpCustomer);
        btnArrive                   = view.findViewById(R.id.btnArrive);
        btnCall                     = view.findViewById(R.id.relativeCall);
        btnPickUp                   = view.findViewById(R.id.btnPickUp);
        btnChat                     = view.findViewById(R.id.relativeChat);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setCompassEnabled(true);
        map.setMyLocationEnabled(true);
        getLocationDriver();
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void addMarkerDriver(LatLng location, String place,int icon) {

       final Marker marker =  map.addMarker(new MarkerOptions().position(location).title(place).icon(BitmapDescriptorFactory.fromResource(icon)));
        marker.setVisible(false);
        final String Uid = getActivity().getIntent().getStringExtra("Uid");

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
                                databaseReference.child("Driver").child("Car").child(Uid).child("status").setValue(true);


                            } else {
                                b = !b;
                                imageView.setBackgroundResource(R.drawable.thunderof);
                                databaseReference.child("Driver").child("Car").child(Uid).child("status").setValue(false);
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
            locationcurent = new LatLng(latitude, longitude);
            addMarkerDriver(locationcurent, placeNameCurrent,R.drawable.iconmarker);
            pus CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(locationcurent, 14);
            map.moveCamera(cameraUpdate);

        }
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String idOrder = intent.getStringExtra("idOrder");
            presenterGoogleMap.getOrderCar(idOrder,Uid);
            dialogPickUpCar();
        }
    };


    @Override
    public void drawPolyline() {
        presenterGoogleMap.getPolyline(getActivity(),map,locationcurent,locationGo);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayOrder(OrderCar orderCar) {
        txtPickUp.setText(orderCar.getPlacenamego());
        txtDestination.setText(orderCar.getPlacenamecome());
        txtEstimatePrice.setText(orderCar.getPrice()+ " K");
        txtKm.setText(orderCar.getDistance()+" Km");
        alertDialog.show();
        locationCome = new LatLng(orderCar.getLatitudecome(), orderCar.getLongitudecome());
        locationGo  = new LatLng(orderCar.getLatitudego(),orderCar.getLongitudego() );

    }

    private  void dialogPickUpCar(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View viewDialogPickUp = layoutInflater.inflate(R.layout.custom_dialog_receive_car, null);
        txtPickUp           = viewDialogPickUp.findViewById(R.id.txtPickUp);
        txtDestination      = viewDialogPickUp.findViewById(R.id.txtDestination);
        btnAcceppt          = viewDialogPickUp.findViewById(R.id.btnAccept);
        txtDeny             = viewDialogPickUp.findViewById(R.id.txtDeny);
        txtEstimatePrice     = viewDialogPickUp.findViewById(R.id.txtEstimatePrice);
        txtKm               = viewDialogPickUp.findViewById(R.id.txtKm);
        builder.setView(viewDialogPickUp);
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btnAcceppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogContactCustomer();

                addMarkerCustomer_LocationCome(locationGo,"Customer",R.drawable.iconraisehand);
                addMarkerCustomer_LocationCome(locationCome,"Destination",R.drawable.iconyellow);

                drawPolyline();

                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(locationGo).include(locationCome);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), 100);
                map.animateCamera(cameraUpdate);
                alertDialog.dismiss();
            }
        });
        txtDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void addMarkerCustomer_LocationCome(LatLng location, String place, int icon) {
        map.addMarker(new MarkerOptions()
                .position(location)
                .title(place)
                .icon(BitmapDescriptorFactory.fromResource(icon)));

    }

    @SuppressLint("MissingPermission")
    private void getLocationDriver() {
        String provider = BuildConfig.DEBUG ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER;
        locationManager.requestLocationUpdates(provider, 5000L
                , 500.0F, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        locationcurent = new LatLng(location.getLatitude(), location.getLongitude());

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                });
    }

    private void dialogContactCustomer() {
        linearLayoutPickUpCustomer.setVisibility(View.VISIBLE);
        linearLayoutPickUpCustomer.setFocusable(true);
        linearLayoutPickUpCustomer.setClickable(true);

        btnArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCount += clickCount;
                if (clickCount == 0) {
                    presenterGoogleMap.getPolyline(getActivity(), map, locationGo, locationCome);

                } else {
                    btnArrive.setEnabled(false);
                }
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Call", Toast.LENGTH_SHORT).show();
            }
        });
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Chat", Toast.LENGTH_SHORT).show();
            }
        });
        btnPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Pickup", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
