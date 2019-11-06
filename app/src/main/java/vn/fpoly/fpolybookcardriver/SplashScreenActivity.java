package vn.fpoly.fpolybookcardriver;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;
    ImageView imgCarLeft, imgCarRight;
    int dest = 130;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SplashScreenActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ObjectAnimator objectAnimatorLeft = ObjectAnimator.ofFloat(imgCarLeft, "translationX", 0, dest);
        objectAnimatorLeft.setDuration(2000);


        ObjectAnimator objectAnimatorDown = ObjectAnimator.ofFloat(imgCarRight, "translationX", 0, -130);
        objectAnimatorDown.setDuration(2000);

        ObjectAnimator objectAnimatorRight = ObjectAnimator.ofFloat(imgCarLeft, "translationY", 0, 350);
        objectAnimatorRight.setDuration(2000);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorLeft);
        animatorSet.start();

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.play(objectAnimatorRight);
        animatorSet1.start();

    }
    private void initView(){
        imgCarLeft = findViewById(R.id.imgIconCar);
        imgCarRight = findViewById(R.id.imgIconCar1);

    }
}
