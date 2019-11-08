package vn.fpoly.fpolybookcardrive.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.view.splashscreen.FragmentSplash;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        initView();

    }
    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_driver,new FragmentSplash()).commit();

    }
}
