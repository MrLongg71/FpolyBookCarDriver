package vn.fpoly.fpolybookcardrive.library;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import dmax.dialog.SpotsDialog;

public class Dialog  {
    public static void DialogLoading(Context context, boolean loading) {
//        AlertDialog alertDialog = new SpotsDialog.Builder().setContext(context).build();
//        if(loading){
//            alertDialog.show();
//        }else {
//            alertDialog.dismiss();
//        }
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        if (loading){
            progressDialog.show();
        }else {
            progressDialog.dismiss();
        }
    }
}
