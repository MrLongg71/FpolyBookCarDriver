package vn.fpoly.fpolybookcardrive.service;

import android.content.Intent;

import androidx.annotation.NonNull;
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


import vn.fpoly.fpolybookcardrive.Constans;

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

        dataDriver.child(Constans.childDriver).child("Car").child(auth.getCurrentUser().getUid()).child("token").setValue(s);


    }

    private void showNotification(String idOrder, String idDriver) {



    }
}
