package vn.fpoly.fpolybookcardrive.service;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParsePolyline {
    public static List<LatLng> getListLocation(String dataJSON){
        List<LatLng> latLngs = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray routes = jsonObject.getJSONArray("routes");
            for (int i = 0 ; i<routes.length(); i++){
                JSONObject jsonObjectAll = routes.getJSONObject(i);
                JSONObject overviewPolyline = jsonObjectAll.getJSONObject("overview_polyline");
                String point = overviewPolyline.getString("points");
                latLngs = PolyUtil.decode(point);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return latLngs;
    };

}
