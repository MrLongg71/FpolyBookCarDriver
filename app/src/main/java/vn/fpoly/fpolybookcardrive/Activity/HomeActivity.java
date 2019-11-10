package vn.fpoly.fpolybookcardrive.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
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

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        loadFragment(new FragmentHome());
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
}
