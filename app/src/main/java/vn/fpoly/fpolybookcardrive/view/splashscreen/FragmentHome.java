package vn.fpoly.fpolybookcardrive.view.splashscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import vn.fpoly.fpolybookcardrive.R;


public class FragmentHome extends Fragment implements View.OnClickListener,OnMapReadyCallback {
    private LinearLayout linearLayout;
    private ImageButton imageButton;
    private Switch aSwitch;
    private ImageView imageView;
    private boolean checked = false;
    private GoogleMap googleMap;
    private  SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        aSwitch.setEnabled(false);
        imageButton.setOnClickListener(this);
        return view;
    }
    private void initView(View view){
        linearLayout    = view.findViewById(R.id.lineralayout);
        imageButton     = view.findViewById(R.id.imgButton);
        aSwitch         = view.findViewById(R.id.swich);
        imageView       = view.findViewById(R.id.imgThunder);
        mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgButton:
                if (checked) {
                    checked = !checked;
                    linearLayout.setBackgroundResource(R.drawable.custom_unchecked);
                    aSwitch.setEnabled(false);
                    aSwitch.setChecked(false);
                } else {
                    checked = !checked;
                    linearLayout.setBackgroundResource(R.drawable.custom_checked);
                    aSwitch.setEnabled(true);
                    aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                b = !b;
                                imageView.setBackgroundResource(R.drawable.thunderon);
                            } else {
                                b = !b;
                                imageView.setBackgroundResource(R.drawable.thunderof);
                            }
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapFragment.getMapAsync(this);
    }
}
