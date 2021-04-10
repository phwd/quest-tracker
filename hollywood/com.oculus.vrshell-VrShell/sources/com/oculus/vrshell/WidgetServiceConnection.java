package com.oculus.vrshell;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.oculus.android.exoplayer2.C;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelVerifier;
import com.oculus.vrshell.panelservice.SigCertVerifier;
import java.lang.ref.WeakReference;
import java.util.Collections;

public class WidgetServiceConnection implements ServiceConnection {
    private static final String INTENT_KEY_WIDGET_BITMAP = "_oc_shell_widget_bitmap";
    private static final String INTENT_KEY_WIDGET_ID = "_oc_shell_widget_id";
    private static final String INTENT_KEY_WIDGET_OFFSET_PITCH = "_oc_shell_widget_offset_pitch";
    private static final String INTENT_KEY_WIDGET_OFFSET_X = "_oc_shell_widget_offset_x";
    private static final String INTENT_KEY_WIDGET_OFFSET_Y = "_oc_shell_widget_offset_y";
    private static final String INTENT_KEY_WIDGET_OFFSET_YAW = "_oc_shell_widget_offset_yaw";
    private static final String INTENT_KEY_WIDGET_POSITION = "_oc_shell_widget_position";
    private static final String INTENT_KEY_WIDGET_REPLY_CHANNEL = "_oc_shell_widget_reply_channel";
    private static final int MSG_CREATE = 1;
    private static final int MSG_DESTROY = 3;
    private static final int MSG_REFRESH = 2;
    private static final String TAG = LoggingUtil.tag(WidgetServiceConnection.class);
    private final PanelVerifier mFirstPartyVerifier;
    private boolean mIsServiceBound = false;
    private final WidgetServiceConnectionListener mListener;
    private final PackageManager mPackageManager;
    private Messenger mServiceMessenger = null;
    private Messenger mServiceReplyChannel = null;
    private final int mWidgetId;

    public enum WidgetPosition {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public interface WidgetServiceConnectionListener {
        void onWidgetRefresh(int i, Bitmap bitmap, float f, float f2, float f3, float f4, WidgetPosition widgetPosition);

        void onWidgetServiceConnected(ComponentName componentName);

        void onWidgetServiceDisconnected(int i);
    }

    class IncomingHandler extends Handler {
        final WeakReference<WidgetServiceConnection> connectionWeakRef;

        IncomingHandler(WidgetServiceConnection widgetServiceConnection) {
            this.connectionWeakRef = new WeakReference<>(widgetServiceConnection);
        }

        public void handleMessage(Message message) {
            if (message.what != 2) {
                super.handleMessage(message);
            } else {
                handleRefreshResponse(message);
            }
        }

        private void handleRefreshResponse(Message message) {
            try {
                Bundle data = message.getData();
                Bitmap bitmap = (Bitmap) data.getParcelable(WidgetServiceConnection.INTENT_KEY_WIDGET_BITMAP);
                WidgetServiceConnection.this.mListener.onWidgetRefresh(Integer.parseInt(data.getString(WidgetServiceConnection.INTENT_KEY_WIDGET_ID)), bitmap, data.getFloat(WidgetServiceConnection.INTENT_KEY_WIDGET_OFFSET_X, 0.0f), data.getFloat(WidgetServiceConnection.INTENT_KEY_WIDGET_OFFSET_Y, 0.0f), data.getFloat(WidgetServiceConnection.INTENT_KEY_WIDGET_OFFSET_PITCH, 0.0f), data.getFloat(WidgetServiceConnection.INTENT_KEY_WIDGET_OFFSET_YAW, 0.0f), WidgetPosition.values()[data.getInt(WidgetServiceConnection.INTENT_KEY_WIDGET_POSITION)]);
            } catch (Exception e) {
                String str = WidgetServiceConnection.TAG;
                Log.e(str, "Error handling widget refresh:  " + e.toString());
            }
        }
    }

    WidgetServiceConnection(int i, WidgetServiceConnectionListener widgetServiceConnectionListener, PanelVerifier panelVerifier, PackageManager packageManager) {
        this.mWidgetId = i;
        this.mListener = widgetServiceConnectionListener;
        this.mServiceReplyChannel = new Messenger(new IncomingHandler(this));
        this.mFirstPartyVerifier = panelVerifier;
        this.mPackageManager = packageManager;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        boolean z = false;
        Log.d(TAG, String.format("onServiceConnected - component name (%s), widget id (%d)", String.valueOf(componentName), Integer.valueOf(this.mWidgetId)));
        WidgetServiceConnectionListener widgetServiceConnectionListener = this.mListener;
        if (widgetServiceConnectionListener != null) {
            widgetServiceConnectionListener.onWidgetServiceConnected(componentName);
        }
        try {
            String verifyFirstPartyUID = verifyFirstPartyUID(this.mPackageManager.getPackageInfo(componentName.getPackageName(), 132).applicationInfo.uid);
            if (verifyFirstPartyUID != null) {
                Log.i(TAG, "verifyFirstPartyUID Failure:  " + verifyFirstPartyUID);
            } else {
                z = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(TAG, "verifyFirstPartyUID Failure:  " + e);
        }
        if (z) {
            this.mServiceMessenger = new Messenger(iBinder);
            this.mIsServiceBound = true;
            sendWidgetConnectionMessage();
            sendWidgetRefreshMessage();
            return;
        }
        Log.e(TAG, "Disconnecting unverified widget service.");
        onServiceDisconnected(componentName);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(TAG, String.format("onServiceDisconnected - component name (%s), widget id (%d)", String.valueOf(componentName), Integer.valueOf(this.mWidgetId)));
        this.mServiceMessenger = null;
        this.mIsServiceBound = false;
        WidgetServiceConnectionListener widgetServiceConnectionListener = this.mListener;
        if (widgetServiceConnectionListener != null) {
            widgetServiceConnectionListener.onWidgetServiceDisconnected(this.mWidgetId);
        }
    }

    public void sendWidgetRefreshMessage() {
        if (this.mIsServiceBound) {
            Message obtain = Message.obtain((Handler) null, 2);
            Bundle bundle = new Bundle();
            bundle.putString(INTENT_KEY_WIDGET_ID, String.valueOf(this.mWidgetId));
            bundle.putBinder(INTENT_KEY_WIDGET_REPLY_CHANNEL, this.mServiceReplyChannel.getBinder());
            obtain.setData(bundle);
            try {
                this.mServiceMessenger.send(obtain);
            } catch (Exception e) {
                Log.e(TAG, "Error sending refresh message to service.", e);
            }
        }
    }

    public String verifyFirstPartyUID(int i) {
        long nanoTime = System.nanoTime();
        SigCertVerifier.SigCertInfo checkApkSignature = this.mFirstPartyVerifier.checkApkSignature(i);
        String str = (String) Collections.min(checkApkSignature.packageNames);
        long nanoTime2 = System.nanoTime();
        Log.i(TAG, String.format("verifyFirstPartyUID(Debug Signatures=%s, uid=%d) => apkSignatureResult=%s, time=%d ms", Boolean.toString(this.mFirstPartyVerifier.areDebugSignaturesEnabled()), Integer.valueOf(i), checkApkSignature, Long.valueOf((nanoTime2 - nanoTime) / C.MICROS_PER_SECOND)));
        if (checkApkSignature.isTrusted || this.mFirstPartyVerifier.areDebugSignaturesEnabled()) {
            return null;
        }
        return String.format("Expected APK '%s' to be signed with trusted certificate; failing authentication.", str);
    }

    private void sendWidgetConnectionMessage() {
        if (this.mIsServiceBound) {
            Message obtain = Message.obtain((Handler) null, 1);
            Bundle bundle = new Bundle();
            bundle.putString(INTENT_KEY_WIDGET_ID, String.valueOf(this.mWidgetId));
            obtain.setData(bundle);
            try {
                this.mServiceMessenger.send(obtain);
            } catch (Exception e) {
                Log.e(TAG, "Error sending create message to service.", e);
            }
        }
    }
}
