package vn.fpoly.fpolybookcardrive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;

public class OrderCarHistoryAdapter extends RecyclerView.Adapter<OrderCarHistoryAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private ArrayList<OrderCar> orderCars;

    public OrderCarHistoryAdapter(Context context, int layout, ArrayList<OrderCar> orderCars) {
        this.context = context;
        this.layout = layout;
        this.orderCars = orderCars;
    }

    @NonNull
    @Override
    public OrderCarHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCarHistoryAdapter.ViewHolder holder, int position) {
        OrderCar orderCar = orderCars.get(position);
        holder.txtTime.setText(orderCar.getDate());
        holder.txtPlaceCome.setText(orderCar.getPlacenamecome());
        holder.txtPrice.setText(orderCar.getPrice()+"");

    }

    @Override
    public int getItemCount() {
        return orderCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTime,txtPlaceCome,txtPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.txtTimeOrder);
            txtPlaceCome = itemView.findViewById(R.id.txtPlaceComeOrder);
            txtPrice = itemView.findViewById(R.id.txtPriceOrder);
        }
    }
}
