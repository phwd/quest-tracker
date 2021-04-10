package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public abstract class PlatformServiceClient implements ServiceConnection {
    public final String applicationId;
    public final Context context;
    public final Handler handler;
    public CompletedListener listener;
    public final int protocolVersion;
    public int replyMessage;
    public int requestMessage;
    public boolean running;
    public Messenger sender;

    public interface CompletedListener {
        void completed(Bundle bundle);
    }

    public void cancel() {
        this.running = false;
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.sender = null;
        try {
            this.context.unbindService(this);
        } catch (IllegalArgumentException unused) {
        }
        callback(null);
    }

    public abstract void populateRequestBundle(Bundle bundle);

    private void callback(Bundle bundle) {
        if (this.running) {
            this.running = false;
            CompletedListener completedListener = this.listener;
            if (completedListener != null) {
                completedListener.completed(bundle);
            }
        }
    }

    private void sendMessage() {
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
        Message obtain = Message.obtain((Handler) null, this.requestMessage);
        obtain.arg1 = this.protocolVersion;
        obtain.setData(bundle);
        obtain.replyTo = new Messenger(this.handler);
        try {
            this.sender.send(obtain);
        } catch (RemoteException unused) {
            callback(null);
        }
    }

    public void handleMessage(Message message) {
        if (message.what == this.replyMessage) {
            Bundle data = message.getData();
            if (data.getString(NativeProtocol.STATUS_ERROR_TYPE) != null) {
                data = null;
            }
            callback(data);
            this.context.unbindService(this);
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.sender = new Messenger(iBinder);
        sendMessage();
    }

    public boolean start() {
        Intent createPlatformServiceIntent;
        if (this.running || NativeProtocol.getLatestAvailableProtocolVersionForService(this.protocolVersion) == -1 || (createPlatformServiceIntent = NativeProtocol.createPlatformServiceIntent(this.context)) == null) {
            return false;
        }
        this.running = true;
        this.context.bindService(createPlatformServiceIntent, this, 1);
        return true;
    }

    public PlatformServiceClient(Context context2, int i, int i2, int i3, String str) {
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext != null ? applicationContext : context2;
        this.requestMessage = i;
        this.replyMessage = i2;
        this.applicationId = str;
        this.protocolVersion = i3;
        this.handler = new Handler() {
            /* class com.facebook.internal.PlatformServiceClient.AnonymousClass1 */

            public void handleMessage(Message message) {
                PlatformServiceClient.this.handleMessage(message);
            }
        };
    }

    public Context getContext() {
        return this.context;
    }

    public void setCompletedListener(CompletedListener completedListener) {
        this.listener = completedListener;
    }
}
