package vn.fpoly.fpolybookcardrive.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.Activity.SplashScreenActivity;
import vn.fpoly.fpolybookcardrive.presenter.maps.PresenterGoogleMap;

public class FirebaseCloudMessage extends FirebaseMessagingService {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    DatabaseReference dataDriver = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Intent intent = new Intent("myFunction");
            // add data
            intent.putExtra("idOrder", remoteMessage.getData().get("idOrder"));
            intent.putExtra("idDriver", remoteMessage.getData().get("idDriver"));
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            showNotification(remoteMessage.getData().get("idOrder"), remoteMessage.getData().get("idDriver"));

        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        getToken();

    }

    private void getToken() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            onNewToken(task.getResult().getToken());
                            Log.d("kiemtratokent",task.getResult().getToken());
                        }
                    }
                });
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        sendTokenToServer(s);
    }

    private void sendTokenToServer(String s) {

        dataDriver.child("Driver").child("Car").child(auth.getCurrentUser().getUid()).child("token").setValue(s);


    }

    private void showNotification(String idOrder, String idDriver) {


        //xu li dialog,call du lieu, goi sang 1 file khac, dung viet trong service nha leader

//        Intent intent = new Intent(this, SplashScreenActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setContentTitle("New Article: " + title)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentText("By " + author)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//


    }
}
