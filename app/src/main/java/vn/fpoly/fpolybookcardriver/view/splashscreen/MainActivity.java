package vn.fpoly.fpolybookcardriver.view.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vn.fpoly.fpolybookcardriver.R;

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