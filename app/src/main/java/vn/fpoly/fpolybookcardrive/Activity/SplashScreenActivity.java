package vn.fpoly.fpolybookcardrive.Activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import vn.fpoly.fpolybookcardrive.R;


public class SplashScreenActivity extends AppCompatActivity {
    private ImageView imgCarLeft, imgCarRight;
    private int destLeft = 260;
    private int destRight = -260;
    private TextView txtDriver;
    private SharedPreferences sharedPreferences;
    private Location locationcurrent;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private int REQUEST_CODE_LOCATION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initView();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String longitude = sharedPreferences.getString("longitude","");
        String latitude = sharedPreferences.getString("latitude","");


        if (longitude.length() == 0 && latitude.length() == 0 ){
            dialogNotificationPermission();
        }else {
            loadSplashScreen();
        }
    }

    private void initView() {
        imgCarLeft  = findViewById(R.id.imgIconCarLeft);
        imgCarRight = findViewById(R.id.imgIconCarRight);

    }

    private void dialogNotificationPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);
        builder.setMessage("We need to access your location and device state to continue using BookCarDriver ");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                locationCurrent();

            }
        });
        builder.setNegativeButton("DONT'T ALLOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            locationCurrent();
            loadSplashScreen();
        } else {
            finish();
        }

    }

    private void loadSplashScreen() {
        ObjectAnimator objectAnimatorLeft = ObjectAnimator.ofFloat(imgCarLeft, "translationX", 0, destLeft);
        objectAnimatorLeft.setDuration(2000);

        final ObjectAnimator objectAnimatorRight = ObjectAnimator.ofFloat(imgCarRight, "translationX", 0, destRight);
        objectAnimatorRight.setDuration(2000);


        final AnimatorSet animatorSetLeft = new AnimatorSet();
        animatorSetLeft.play(objectAnimatorLeft);
        animatorSetLeft.start();

        AnimatorSet animatorSetRight = new AnimatorSet();
        animatorSetRight.play(objectAnimatorRight);
        animatorSetRight.start();

        Animation animation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fadein);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgCarLeft.setImageResource(R.drawable.iconcar1down);
                imgCarRight.setImageResource(R.drawable.iconcar1down);


                final ObjectAnimator objectAnimatorDownLeft = ObjectAnimator.ofFloat(imgCarLeft, "translationY", 0, 400);
                objectAnimatorDownLeft.setDuration(2000);

                final ObjectAnimator objectAnimatorDownRight = ObjectAnimator.ofFloat(imgCarRight, "translationY", 0, 400);
                objectAnimatorDownRight.setDuration(2000);

                AnimatorSet animatorSetDowLeft = new AnimatorSet();
                animatorSetDowLeft.play(objectAnimatorDownLeft);
                animatorSetDowLeft.start();

                AnimatorSet animatorSetDowRight = new AnimatorSet();
                animatorSetDowRight.play(objectAnimatorDownRight);
                animatorSetDowRight.start();


            }
        }, 2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();

            }
        }, 3500);

    }

    private void locationCurrent() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    locationcurrent = location;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("latitude", String.valueOf(location.getLatitude()));
                    editor.putString("longitude", String.valueOf(location.getLongitude()));
                    editor.commit();
                }
            }
        });
    }
}