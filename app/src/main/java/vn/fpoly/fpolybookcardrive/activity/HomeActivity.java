package vn.fpoly.fpolybookcardrive.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.view.splashscreen.home.FragmentHome;
import vn.fpoly.fpolybookcardrive.view.splashscreen.account.FragmentAccount;
import vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.FragmentStatical;

public class HomeActivity extends AppCompatActivity  {
    public static BottomNavigationView bottomNavigationView;
    private int number = 0;
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
                        loadFragment(new FragmentAccount());
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

    @Override
    public void onBackPressed() {
        if (number == 0) {
            number = 1;
            Toast.makeText(this, "Back Again", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            finish();
        }


    }


}
