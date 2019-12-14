package vn.fpoly.fpolybookcardrive.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;

public class OrderFoodHistoryAdapter extends RecyclerView.Adapter<OrderFoodHistoryAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private ArrayList<OrderFood> orderFoods;

    public OrderFoodHistoryAdapter(Context context, int layout, ArrayList<OrderFood> orderFoods) {
        this.context = context;
        this.layout = layout;
        this.orderFoods = orderFoods;
    }

    @NonNull
    @Override
    public OrderFoodHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderFood orderFood = orderFoods.get(position);
        holder.txtTime.setText(orderFood.getDate());
        holder.txtPlaceCome.setText(orderFood.getKeyRestaurant());
        holder.txtPrice.setText(orderFood.getPrice() + "");
    }


    @Override
    public int getItemCount() {
        return orderFoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTime, txtPlaceCome, txtPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.txtTimeOrder);
            txtPlaceCome = itemView.findViewById(R.id.txtPlaceComeOrder);
            txtPrice = itemView.findViewById(R.id.txtPriceOrder);
        }
    }
}
