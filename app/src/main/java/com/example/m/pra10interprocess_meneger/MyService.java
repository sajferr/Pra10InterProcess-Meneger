package com.example.m.pra10interprocess_meneger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by m on 2016-07-05.
 */
public class MyService extends Service {
    private final int job1=1;
    private final int job2 = 2;
    private final int job1_response = 3;
    Messenger messenger = new Messenger(new handlerek());

    private final int job2response = 4;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
public class handlerek extends Handler {
    String stringMess ;
    Message MSG;
    Bundle bundle = new Bundle();



    @Override
    public void handleMessage(Message msg) {
switch (msg.what){
    case job1:
        Log.d("Uwaga","job1");
        stringMess = "This is first message";
        MSG = Message.obtain(null,job1_response);
        bundle.putString("reaspoon1",stringMess);
        MSG.setData(bundle);
        try {
            Log.d("Uwaga","job1send");
            msg.replyTo.send(MSG);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        break;

    case job2:
        Log.d("Uwaga","job2");
        stringMess = "This is second message";
        MSG = Message.obtain(null,job2response);
        bundle.putString("reaspoon1",stringMess);
        MSG.setData(bundle);
        try {
            Log.d("Uwaga","job2send");
            msg.replyTo.send(MSG);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        default:  super.handleMessage(msg);
            break;















}


    }
}


}

