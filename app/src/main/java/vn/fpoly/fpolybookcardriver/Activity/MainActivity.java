package vn.fpoly.fpolybookcardriver.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import vn.fpoly.fpolybookcardriver.R;
import vn.fpoly.fpolybookcardriver.view.splashscreen.FragmentSplash;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        initView();
    }
    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_driver, new FragmentSplash()).commit();
    }
}
