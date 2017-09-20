package com.example.android.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by dfreer on 9/20/2017.
 */

public class TheService extends Service {
    final class TheThread implements Runnable{
        int serviceId;
        TheThread(int serviceId){
            this.serviceId = serviceId;
        }
        public void run(){
            synchronized (this) {
                try {
                    wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stopSelf(this.serviceId);
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(TheService.this, "service started", Toast.LENGTH_LONG).show();
        //Block the service for some time!
        Thread thread = new Thread(new TheThread(startId));
        thread.start(); //cool!!!!
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(TheService.this, "service destroyed", Toast.LENGTH_LONG).show();

    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
