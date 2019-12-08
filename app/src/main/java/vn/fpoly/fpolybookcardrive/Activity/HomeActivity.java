package vn.fpoly.fpolybookcardrive.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcardrive.view.splashscreen.fragment.FragmentHome;
import vn.fpoly.fpolybookcardrive.view.splashscreen.fragment.FragmentShowMore;
import vn.fpoly.fpolybookcardrive.view.splashscreen.fragment.FragmentStatical;
import vn.fpoly.fpolybookcardrive.view.splashscreen.fragment.IViewHome;

public class HomeActivity extends AppCompatActivity  {
    BottomNavigationView bottomNavigationView;


    private PresenterGoogleMap presenterGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

        loadFragment(new FragmentHome());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        loadFragment(new FragmentHome());
                        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(HomeActivity.this)
                                .setContentTitle("New Article: " + "a")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentText("By " + "a")
                                .setAutoCancel(true)
                                .setSound(defaultSoundUri);


                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                        notificationManager.notify(0 , notificationBuilder.build());

                        break;
                    case R.id.statistical:
                        loadFragment(new FragmentStatical());

                        break;
                    case R.id.showMore:
                        loadFragment(new FragmentShowMore());
                        break;
                }
                return false;
            }
        });

    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom);

    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, fragment).commit();
        }
    }



}
