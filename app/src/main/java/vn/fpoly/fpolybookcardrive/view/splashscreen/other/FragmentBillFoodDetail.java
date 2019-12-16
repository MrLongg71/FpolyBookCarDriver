package vn.fpoly.fpolybookcardrive.view.splashscreen.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.Objects;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.activity.HomeActivity;
import vn.fpoly.fpolybookcardrive.adapter.BillDetailAdapter;
import vn.fpoly.fpolybookcardrive.library.Dialog;
import vn.fpoly.fpolybookcardrive.model.objectclass.BillFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.FoodMenu;
import vn.fpoly.fpolybookcardrive.presenter.billdetail.PresenterBillDetail;

public class FragmentBillFoodDetail extends Fragment implements IViewBillDetail,CallBack {
    private RecyclerView recyclerView;
    private PresenterBillDetail presenterBillDetail;
    private BillDetailAdapter billDetailAdapter;
    private SpinKitView progressbarItem;
    private TextView txtTotalAmount,txtTotalBill;
    private ArrayList<FoodMenu> arrFoodMenuu = new ArrayList<>();
    private Button btnFood;
    private ArrayList<BillFood>arrBillFood = new ArrayList<>();
    private int total = 0;
    private int totalBill = 0;
    String idOrder,Uid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_billfooddetail, container, false);
        initView(view);

        Bundle bundle = getArguments();
        if (bundle !=null){
            idOrder =   bundle.getString("idOrder");
             Uid = bundle.getString("Uid");
            presenterBillDetail.getOderBillDetail(Uid,idOrder);
        }

        return view;

    }
    private void initView(View view){
        HomeActivity.bottomNavigationView.setVisibility(View.GONE);
        recyclerView            = view.findViewById(R.id.recyclerViewBillDetail);
        presenterBillDetail     = new PresenterBillDetail(this);
        progressbarItem         = view.findViewById(R.id.progressbarItem);
        txtTotalAmount          = view.findViewById(R.id.txtTotalAmount);
        btnFood                 = view.findViewById(R.id.btnFood);
        txtTotalBill            = view.findViewById(R.id.txtTotalBill);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayBillDetail(ArrayList<BillFood> arrBillDetail, ArrayList<FoodMenu> arrFoodMenu) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressbarItem.setVisibility(View.GONE);
        billDetailAdapter = new BillDetailAdapter(arrBillDetail, R.layout.custom_row_itemcart, getActivity(),arrFoodMenu,this);
        txtTotalAmount.setText(arrBillDetail.size()+"");
        recyclerView.setAdapter(billDetailAdapter);
        billDetailAdapter.notifyDataSetChanged();
        arrBillFood = arrBillDetail;
        arrFoodMenuu = arrFoodMenu;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initSetTotal(int i, TextView txtTotal, TextView txtAmount) {

        BillFood billFood = arrBillFood.get(i);
        FoodMenu foodMenu = arrFoodMenuu.get(i);
        txtAmount.setText(billFood.getAmountBuy() + " x " + foodMenu.getPrice());
        total = Integer.parseInt(foodMenu.getPrice()) * billFood.getAmountBuy();
        txtTotal.setText(total +"K");
        totalBill +=total;
        txtTotalBill.setText(totalBill + "K");
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("amountGoods",arrBillFood.size()+"");
                bundle.putString("total",totalBill+"");
                bundle.putString("Uid",Uid);
                bundle.putString("idOrder",idOrder);
                FragmentPayFood fragmentPayFood = new FragmentPayFood();
                fragmentPayFood.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragmentPayFood).commit();
            }
        });
    }
}
