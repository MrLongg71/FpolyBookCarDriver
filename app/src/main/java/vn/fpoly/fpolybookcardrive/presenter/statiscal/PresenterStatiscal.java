package vn.fpoly.fpolybookcardrive.presenter.statiscal;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.model.statiscal.ModelStatiscal;
import vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.IViewStatistical;

public class PresenterStatiscal implements IPresenterStatiscal {
    private IViewStatistical iViewStatistical;
    private ModelStatiscal modelStatiscal;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("dd");
    private SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("MM");
    int yesterday = (Integer.parseInt(simpleDateFormatDay.format(calendar.getTime()))) - 1;
    int day = Integer.parseInt(simpleDateFormatDay.format(calendar.getTime()));
    int month = Integer.parseInt(simpleDateFormatMonth.format(calendar.getTime()));

    public PresenterStatiscal(IViewStatistical iViewStatistical) {
        this.iViewStatistical = iViewStatistical;
        modelStatiscal = new ModelStatiscal();
    }

    @Override
    public void getListOrder(int i) {
        modelStatiscal.DowloadListOrderCar(this,i);
    }

    @Override
    public void resultListOrder(boolean success, ArrayList<OrderCar> orderCars, ArrayList<OrderFood> orderFoods,int i) {
        ArrayList<OrderCar> orderCarArrayList = new ArrayList<>();
        ArrayList<OrderFood> orderFoodArrayList = new ArrayList<>();

        switch (i){
            case 1:
                if (orderCars != null  && orderFoods != null) {
                    int totalJobs = 0;
                    int totalMoney = 0;
                    for (OrderCar orderCar :orderCars) {
                        String[] date = orderCar.getDate().split("/");
                        if (Integer.parseInt(date[0]) == yesterday) {
                            totalMoney += (int) orderCar.getPrice();
                            totalJobs++;
                            orderCarArrayList.add(orderCar);
                        }
                    }
                    for (OrderFood orderFood :orderFoods) {
                        String[] date = orderFood.getDate().split("/");
                        if (Integer.parseInt(date[0]) == yesterday) {
                            totalMoney += (int) orderFood.getPrice();
                            totalJobs++;
                            orderFoodArrayList.add(orderFood);
                        }
                    }
                    iViewStatistical.displayListOrderDay(totalJobs,totalMoney,orderCarArrayList,orderFoodArrayList);

                }
                break;

            case 2:
                if (orderCars != null  && orderFoods != null) {
                    int totalJobs = 0;
                    int totalMoney = 0;
                    for (OrderCar orderCar :orderCars) {
                        String[] date = orderCar.getDate().split("/");
                        if (Integer.parseInt(date[0]) == day) {
                            totalMoney = +(int) orderCar.getPrice();
                            totalJobs++;
                        }
                    }
                    for (OrderFood orderFood :orderFoods) {
                        String[] date = orderFood.getDate().split("/");
                        if (Integer.parseInt(date[0]) == day) {
                            totalMoney += (int) orderFood.getPrice();
                            totalJobs++;
                            orderFoodArrayList.add(orderFood);
                        }
                    }
                    iViewStatistical.displayListOrderDay(totalJobs,totalMoney,orderCarArrayList,orderFoodArrayList);
                }
                break;
            case 3:
                if (orderCars != null  && orderFoods != null) {
                    int totalJobs = 0;
                    int totalMoney = 0;
                    for (OrderCar orderCar :orderCars) {
                        String[] date = orderCar.getDate().split("/");
                        if (Integer.parseInt(date[1]) == month) {
                            totalMoney = +(int) orderCar.getPrice();
                            totalJobs++;
                            orderCarArrayList.add(orderCar);
                        }

                    }
                    for (OrderFood orderFood :orderFoods) {
                        String[] date = orderFood.getDate().split("/");
                        if (Integer.parseInt(date[1]) == month) {
                            totalMoney += (int) orderFood.getPrice();
                            totalJobs++;
                            orderFoodArrayList.add(orderFood);
                        }
                    }
                    iViewStatistical.displayListOrderDay(totalJobs,totalMoney,orderCarArrayList,orderFoodArrayList);
                }
                break;
        }


    }

}
