package cn.irains.pushdemo.app.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PushService extends Service{
    private static boolean STOP_PUSH = false;
    public PushService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(0, new Notification());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if(STOP_PUSH) {
            super.onDestroy();
        } else {
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
