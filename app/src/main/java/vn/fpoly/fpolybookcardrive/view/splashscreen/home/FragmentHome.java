package vn.fpoly.fpolybookcardrive.view.splashscreen.home;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Objects;

import vn.fpoly.fpolybookcardrive.BuildConfig;
import vn.fpoly.fpolybookcardrive.Constans;
import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.Driver;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcardrive.service.FirebaseCloudMessage;
import vn.fpoly.fpolybookcardrive.view.splashscreen.other.FragmentPayCar;


public class FragmentHome extends Fragment implements
        IViewHome,OnMapReadyCallback {
    private LinearLayout linearLayout,linearLayoutPickUpCustomer,linearLayoutDropOffCustomer;
    private ImageButton imageButton;
    private Switch aSwitch;
    private ImageView imageView;
    private Button btnArrive,btnPickUp,btnDropOff;
    private boolean checked = false;
    private LatLng locationGo,locationCome,locationcurent;
    private PresenterGoogleMap presenterGoogleMap;
    private TextView txtPickUp,txtDestination,txtEstimatePrice,txtKm;
    private RelativeLayout btnCall,btnChat;
    private int clickCount = 0;
    private LocationManager locationManager;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private String keyOrder,Uid,namecustomer;
    private AlertDialog alertDialog;
    private OrderCar ordercar = new OrderCar();
    private Driver driverr = new Driver();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        Uid = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("Uid");
        getToken();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("myFunction"));
        mapFragment.getMapAsync(this);
        aSwitch.setEnabled(false);
        getLocationDriver();
        return view;
    }

    private void getToken() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot nodeDriver = dataSnapshot.child(Constans.childDriver).child("Car").child(Uid);
                Driver driver = nodeDriver.getValue(Driver.class);
               driverr = driver;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
        Objects.requireNonNull(getActivity()).startService(new Intent(getActivity(), FirebaseCloudMessage.class));
    }

    private void initView(View view) {
        linearLayout                = view.findViewById(R.id.lineralayout);
        imageButton                 = view.findViewById(R.id.imgButton);
        aSwitch                     = view.findViewById(R.id.swich);
        imageView                   = view.findViewById(R.id.imgThunder);
        locationManager             = Objects.requireNonNull(getActivity()).getSystemService(LocationManager.class);
        mapFragment                 = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        presenterGoogleMap          = new PresenterGoogleMap(this);
        linearLayoutPickUpCustomer  = view.findViewById(R.id.dialog_pickUpCustomer);
        btnArrive                   = view.findViewById(R.id.btnArrive);
        btnCall                     = view.findViewById(R.id.relativeCall);
        btnPickUp                   = view.findViewById(R.id.btnPickUp);
        btnDropOff                  = view.findViewById(R.id.btnDropOff);
        btnChat                     = view.findViewById(R.id.relativeChat);
        linearLayoutDropOffCustomer = view.findViewById(R.id.dialog_dropoffCustomer);

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

    private void addMarkerDriver(LatLng location, String place) {

       final Marker marker =  map.addMarker(new MarkerOptions().position(location).title(place).icon(BitmapDescriptorFactory.fromResource(R.drawable.iconmarker)));
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

                                imageView.setBackgroundResource(R.drawable.thunderon);
                                databaseReference.child(Constans.childDriver).child("Car").child(Objects.requireNonNull(Uid)).child("status").setValue(true);

                            } else {
                                imageView.setBackgroundResource(R.drawable.thunderof);
                                databaseReference.child(Constans.childDriver).child("Car").child(Objects.requireNonNull(Uid)).child("working").setValue(false);
                                databaseReference.child(Constans.childDriver).child("Car").child(Objects.requireNonNull(Uid)).child("status").setValue(false);
                            }
                        }
                    });
                    map.setMyLocationEnabled(false);

                }
            }
        });

    }


    @Override
    public void drawPolyline() {
        presenterGoogleMap.getPolyline(getActivity(),map,locationcurent,locationGo);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayOrder(OrderCar orderCar, String nameCustomer) {
        txtPickUp.setText(orderCar.getPlacenamego());
        txtDestination.setText(orderCar.getPlacenamecome());
        txtEstimatePrice.setText(orderCar.getPrice()+ " K");
        txtKm.setText(orderCar.getDistance()+" Km");
        alertDialog.show();
        locationCome = new LatLng(orderCar.getLatitudecome(), orderCar.getLongitudecome());
        locationGo  = new LatLng(orderCar.getLatitudego(),orderCar.getLongitudego() );
        keyOrder = orderCar.getKeyOrder();
        ordercar = orderCar;
        namecustomer = nameCustomer;

    }

    private  void dialogPickUpCar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View viewDialogPickUp = layoutInflater.inflate(R.layout.custom_dialog_receive_car, null);
        txtPickUp                   = viewDialogPickUp.findViewById(R.id.txtPickUp);
        txtDestination              = viewDialogPickUp.findViewById(R.id.txtDestination);
        RelativeLayout btnAccept    = viewDialogPickUp.findViewById(R.id.btnAccept);
        TextView txtDeny            = viewDialogPickUp.findViewById(R.id.txtDeny);
        txtEstimatePrice            = viewDialogPickUp.findViewById(R.id.txtEstimatePrice);
        txtKm                       = viewDialogPickUp.findViewById(R.id.txtKm);
        builder.setView(viewDialogPickUp);
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(Constans.childDriver).child("Car").child(Uid).child("working").setValue(true);
                if (driverr.isWorking()){
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
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        String placeNameCurrent = "You are here!";
                        locationcurent = new LatLng(latitude, longitude);
                        addMarkerDriver(locationcurent, placeNameCurrent);
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(locationcurent, 14);
                        map.moveCamera(cameraUpdate);
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
                    map.clear();
                    presenterGoogleMap.getPolyline(getActivity(), map, locationGo, locationCome);
                    addMarkerCustomer_LocationCome(locationCome,"Destination",R.drawable.iconyellow);
                    addMarkerCustomer_LocationCome(locationGo,"Customer",R.drawable.iconraisehand);

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
                dialogDropOffCustomer();
                linearLayoutPickUpCustomer.setVisibility(View.GONE);
                alertDialog.dismiss();

            }
        });
    }

    private void dialogDropOffCustomer(){
        linearLayoutDropOffCustomer.setVisibility(View.VISIBLE);
        linearLayoutDropOffCustomer.setFocusable(true);
        linearLayoutDropOffCustomer.setClickable(true);
        btnDropOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(Constans.childOrder).child(Uid).child(keyOrder).child("fisnish").setValue(true);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constans.KEY_BUNDEL_ORDER,ordercar);
                bundle.putString(Constans.KEY_BUNDEL_NAMECUSTOMER,namecustomer);
                FragmentPayCar fragmentPayCar = new FragmentPayCar();
                fragmentPayCar.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, fragmentPayCar).commit();
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseReference.child(Constans.childDriver).child("Car").child(Uid).child("working").setValue(false);
        databaseReference.child(Constans.childDriver).child("Car").child(Uid).child("status").setValue(false);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String event = intent.getStringExtra("event");
            
            if (driverr.isStatus() && !driverr.isWorking() && event.equals("1")) {
                Log.d("LONgKUTE", "onReceive: Car");
                String idOrder = intent.getStringExtra("idOrder");
                presenterGoogleMap.getOrderCar(idOrder, Uid);
                dialogPickUpCar();
            }
            
            if(event.equals("2")){
                //logic food
                Log.d("LONgKUTE", "onReceive: Food");
                
            }
        }
    };
}
