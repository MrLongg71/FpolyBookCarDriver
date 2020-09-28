package vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.month;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.presenter.statiscal.PresenterStatiscal;
import vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.IViewStatistical;

public class StaMonthFragment extends Fragment implements IViewStatistical, OnChartValueSelectedListener {
    private PresenterStatiscal presenterStatiscal;
    private TextView txtDate,txtTotalMoneySta,txtTotalJobSta;
    private LinearLayout layoutHistoryOrder,layoutChart;
    private  PieChart mChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical_day,container,false);
       initView(view);
        return view;
    }


    private void initView(View view) {
        layoutChart                 = view.findViewById(R.id.lineChart);
        layoutChart.setVisibility(View.VISIBLE);
        presenterStatiscal          = new PresenterStatiscal(this);
        presenterStatiscal.getListOrder(3);
        txtDate =    view.findViewById(R.id.txtDate);
        txtTotalJobSta = view.findViewById(R.id.txtTotalJobSta);
        txtTotalMoneySta = view.findViewById(R.id.txtTotalMoneySta);
        layoutHistoryOrder = view.findViewById(R.id.layoutHistoryOrder);
        mChart =  view.findViewById(R.id.piechart);
        layoutHistoryOrder.setVisibility(View.GONE);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("dd");
        String day = simpleDateFormatDay.format(calendar.getTime());
        txtDate.setText("Today: " + day);
    }


    @Override
    public void displayListOrderDay(int total_Jobs, int total_money, ArrayList<OrderCar> orderCarArrayList, ArrayList<OrderFood> orderFoodArrayList) {
        txtTotalMoneySta.setText(total_money +"$");
        txtTotalJobSta.setText(total_Jobs+"");
        addDataSet(mChart,orderCarArrayList.size(),orderFoodArrayList.size());
        mChart.setOnChartValueSelectedListener(this);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
    private static void addDataSet(PieChart pieChart,int totalJobCar,int totalJobFood) {
        pieChart.setRotationEnabled(true);
        pieChart.setDescription(new Description());
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterTextSize(30);
        pieChart.setDrawEntryLabels(true);
        pieChart.setHoleColor(Color.WHITE);
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.clear();
        entries.add(new PieEntry(totalJobCar, "Job Car"));
        entries.add(new PieEntry(totalJobFood, "Job Food"));


        PieDataSet pieDataSet = new PieDataSet(entries, " ");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);


        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.invalidate();
    }
}
