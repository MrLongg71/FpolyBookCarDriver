package vn.fpoly.fpolybookcardrive.view.splashscreen.splashscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.view.splashscreen.driver.SigninFragment;


public class FragmentSplash extends Fragment {
    private Button btnLogin;
    private ImageView imgLogo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.fadeinwithtranslate);

        btnLogin = view.findViewById(R.id.btnlogin);
        imgLogo = view.findViewById(R.id.imgIconSplashScreen);
        imgLogo.startAnimation(animation);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SigninFragment signinFragment = new SigninFragment();
                loadFragment(signinFragment);
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.frame_driver, fragment).addToBackStack(null).commit();

        }
    }
}
