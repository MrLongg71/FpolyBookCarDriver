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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.view.splashscreen.fragment.FragmentHome;
import vn.fpoly.fpolybookcardrive.view.splashscreen.fragment.FragmentShowMore;
import vn.fpoly.fpolybookcardrive.view.splashscreen.fragment.FragmentStatical;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        loadFragment(new FragmentHome());
        LocalBroadcastManager.getInstance(HomeActivity.this).registerReceiver(mMessageReceiver,new IntentFilter("myFunction"));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        loadFragment(new FragmentHome());
                        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

                        LayoutInflater layoutInflater = getLayoutInflater();
                        View view1 = layoutInflater.inflate(R.layout.custom_dialog_pickupcustomer, null);
                        builder.setView(view1);

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(HomeActivity.this)
                                .setContentTitle("New Article: " + "a")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentText("By " + "a")
                                .setAutoCancel(true)
                                .setSound(defaultSoundUri);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

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

//        FirebaseMessaging.getInstance().subscribeToTopic(firebaseAuth.getCurrentUser().getUid());

    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom);
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_home, fragment).commit();
        }
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            String t = intent.getStringExtra("value1");
            String t1 = intent.getStringExtra("value2");
            //alert data here
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle(t);
            builder.setMessage(t1);
            builder.show();
        }
    };



}
