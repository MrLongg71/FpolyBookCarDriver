package vn.fpoly.fpolybookcardrive.adapter;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.BillFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.FoodMenu;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.view.splashscreen.other.CallBack;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.ViewHolder> {
    private List<BillFood> arrBillFood;
    private int layout;
    private Context context;
    private List<FoodMenu>arrFoodMenu;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private CallBack setTotal;

    public BillDetailAdapter(List<BillFood> arrBillFood, int layout, Context context, List<FoodMenu> arrFoodMenu,CallBack setTotal) {
        this.arrBillFood = arrBillFood;
        this.layout = layout;
        this.context = context;
        this.arrFoodMenu = arrFoodMenu;
        this.setTotal = setTotal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillFood billFood = arrBillFood.get(position);
        FoodMenu foodMenu = arrFoodMenu.get(position);
        holder.txtTitleItem.setText(foodMenu.getName());
        setTotal.initSetTotal(position,holder.txtTotalItem,holder.txtAmoutItem);
        setImage(foodMenu,holder.imgTitle);
    }
    private void setImage(final FoodMenu foodMenu, final RoundedImageView imageView){
        storageReference.child("ImageRestaurantMenuFood").child(foodMenu.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                foodMenu.setImage(uri.toString());
                Glide.with(context).load(foodMenu.getImage()).into(imageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrBillFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imgTitle;
        TextView txtTitleItem,txtTotalItem,txtAmoutItem,txtTotalAmount,txtTotalBill;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTitle        = itemView.findViewById(R.id.imgItemDetail);
            txtAmoutItem    = itemView.findViewById(R.id.txtAmoutItemCart);
            txtTotalItem    = itemView.findViewById(R.id.txtTotalItem);
            txtTitleItem    = itemView.findViewById(R.id.txtTitleItemDetail);

        }
    }
}
