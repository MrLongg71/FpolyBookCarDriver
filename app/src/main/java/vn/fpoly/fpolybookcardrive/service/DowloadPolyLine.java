package vn.fpoly.fpolybookcardrive.service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DowloadPolyLine extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        try {

            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String dataJSON) {
        super.onPostExecute(dataJSON);
    }
}
