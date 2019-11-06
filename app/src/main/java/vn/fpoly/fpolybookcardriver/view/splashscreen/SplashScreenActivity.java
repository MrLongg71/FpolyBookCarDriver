package vn.fpoly.fpolybookcardriver.view.splashscreen;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import vn.fpoly.fpolybookcardriver.R;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;
    LinearLayout layoutCarSlash;
    ImageView imgCarLeft, imgCarRight;
    int destLeft = 260;
    int destRight = -260;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initView();
        SplashScreenActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            FirebaseMessaging.getInstance().subscribeToTopic(driver.getKeydriver());

        ObjectAnimator objectAnimatorLeft = ObjectAnimator.ofFloat(imgCarLeft, "translationX", 0, destLeft);
        objectAnimatorLeft.setDuration(2000);

        final ObjectAnimator objectAnimatorRight = ObjectAnimator.ofFloat(imgCarRight, "translationX", 0, destRight);
        objectAnimatorRight.setDuration(2000);


        AnimatorSet animatorSetLeft = new AnimatorSet();
        animatorSetLeft.play(objectAnimatorLeft);
        animatorSetLeft.start();

        AnimatorSet animatorSetRight = new AnimatorSet();
        animatorSetRight.play(objectAnimatorRight);
        animatorSetRight.start();

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

            }
        },3500);


    }

    private void initView() {
        imgCarLeft = findViewById(R.id.imgIconCarLeft);
        imgCarRight = findViewById(R.id.imgIconCarRight);

    }
}