package com.example.m.pra10interprocess_meneger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Messenger messenger;
     boolean isBind = false;
    final int job1=1;
     final int job2 = 2;
     final int job1_response = 3;
    final int job2response = 4;
    TextView tekst;
    Message mag;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   tekst=(TextView)findViewById(R.id.textView);
        Log.d("Uwaga","oncreate");
        Intent intent  = new Intent(getApplicationContext(),MyService.class);
        bindService(intent,sc,BIND_AUTO_CREATE);






    }



    public void First(View view) {
        if(isBind){
            Log.d("Uwaga","firsrtButton");
            mag = Message.obtain(null,job1);
            Log.d("Uwaga","onFirtsButtonReply");
              mag.replyTo= new Messenger(new handlerek2());
            try {
                Log.d("Uwaga","onfirtsButtonsend");
                messenger.send(mag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }




    }

    public void Second(View view) {


        if(isBind){
            Log.d("Uwaga","onsecondButton");
            mag = Message.obtain(null,job2);
            Log.d("Uwaga","onsecondsButtonReply");
            mag.replyTo= new Messenger(new handlerek2());
            try {
                Log.d("Uwaga","onsecondsButtonsend");
                messenger.send(mag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }




    }

    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Uwaga","onconntected");
             messenger = new Messenger(service);
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStop() {
        unbindService(sc);
        isBind=false;
        Log.d("Uwaga","onstop");
        super.onStop();
    }

    public class handlerek2 extends Handler{
        String messageString1;
        String messageString2;

        @Override
        public void handleMessage(Message msg) {
        switch (msg.what){
    case job1_response:
        Log.d("Uwaga","job1_reponse");
        messageString1=msg.getData().getString("reaspoon1");
        tekst.setText(String.valueOf(msg.replyTo));
        break;
    case job2response:
        Log.d("Uwaga","job2_reponse");
        messageString2= msg.getData().getString("reaspoon1");
        tekst.setText(messageString2);
        break;
    default:super.handleMessage(msg);
        break;

}

        }
    }


}
