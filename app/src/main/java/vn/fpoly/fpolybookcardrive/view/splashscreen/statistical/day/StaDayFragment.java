package vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.day;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.adapter.OrderCarHistoryAdapter;
import vn.fpoly.fpolybookcardrive.adapter.OrderFoodHistoryAdapter;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.presenter.statiscal.PresenterStatiscal;
import vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.IViewStatistical;

public class StaDayFragment extends Fragment implements IViewStatistical {
    private PresenterStatiscal presenterStatiscal;
    private TextView txtDate,txtTotalMoneySta,txtTotalJobSta;
    private RecyclerView recyclerViewHistoryOrderCar,recyclerViewHistoryOrderFood;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical_day,container,false);
        initView(view);



        return view;
    }

    private void initView(View view) {
        presenterStatiscal = new PresenterStatiscal(this);
        presenterStatiscal.getListOrder(2);
        txtDate = view.findViewById(R.id.txtDate);
        txtTotalJobSta = view.findViewById(R.id.txtTotalJobSta);
        txtTotalMoneySta = view.findViewById(R.id.txtTotalMoneySta);
        recyclerViewHistoryOrderCar = view.findViewById(R.id.recyclerViewHistoryOrderCar);
        recyclerViewHistoryOrderFood = view.findViewById(R.id.recyclerViewHistoryOrderFood);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("dd");
        String day = simpleDateFormatDay.format(calendar.getTime());
        txtDate.setText("Today: " + day);
    }


    @Override
    public void displayListOrderDay(int total_Jobs, int total_money, ArrayList<OrderCar> orderCarArrayList, ArrayList<OrderFood> orderFoodArrayList) {
        txtTotalMoneySta.setText(total_money +"$");
        txtTotalJobSta.setText(total_Jobs+"");
        OrderCarHistoryAdapter orderCarHistoryAdapter = new OrderCarHistoryAdapter(getActivity(),R.layout.custom_item_order_history,orderCarArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerViewHistoryOrderCar.setLayoutManager(layoutManager);
        recyclerViewHistoryOrderCar.setAdapter(orderCarHistoryAdapter);

        OrderFoodHistoryAdapter orderFoodHistoryAdapter = new OrderFoodHistoryAdapter(getActivity(),R.layout.custom_item_order_history,orderFoodArrayList);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerViewHistoryOrderFood.setLayoutManager(manager);
        recyclerViewHistoryOrderFood.setAdapter(orderFoodHistoryAdapter);

        orderCarHistoryAdapter.notifyDataSetChanged();
        orderFoodHistoryAdapter.notifyDataSetChanged();

    }
}
