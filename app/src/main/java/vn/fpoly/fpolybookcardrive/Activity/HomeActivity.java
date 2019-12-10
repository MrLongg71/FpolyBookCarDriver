package vn.fpoly.fpolybookcardrive.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcardrive.view.splashscreen.home.FragmentHome;
import vn.fpoly.fpolybookcardrive.view.splashscreen.showmore.FragmentShowMore;
import vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.FragmentStatical;

public class HomeActivity extends AppCompatActivity  {
    public static BottomNavigationView bottomNavigationView;


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
