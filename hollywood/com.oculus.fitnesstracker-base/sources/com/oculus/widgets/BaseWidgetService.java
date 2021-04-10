package com.oculus.widgets;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import java.lang.ref.WeakReference;

public abstract class BaseWidgetService extends Service {
    public static final String INTENT_KEY_WIDGET_BITMAP = "_oc_shell_widget_bitmap";
    public static final String INTENT_KEY_WIDGET_ID = "_oc_shell_widget_id";
    public static final String INTENT_KEY_WIDGET_OFFSET_PITCH = "_oc_shell_widget_offset_pitch";
    public static final String INTENT_KEY_WIDGET_OFFSET_X = "_oc_shell_widget_offset_x";
    public static final String INTENT_KEY_WIDGET_OFFSET_Y = "_oc_shell_widget_offset_y";
    public static final String INTENT_KEY_WIDGET_OFFSET_YAW = "_oc_shell_widget_offset_yaw";
    public static final String INTENT_KEY_WIDGET_POSITION = "_oc_shell_widget_position";
    public static final String INTENT_KEY_WIDGET_REPLY_CHANNEL = "_oc_shell_widget_reply_channel";
    public static final int MSG_CREATE = 1;
    public static final int MSG_DESTROY = 3;
    public static final int MSG_REFRESH = 2;
    private static final String TAG = "BaseWidgetService";
    private Messenger mMessenger = null;
    private BaseWidget mWidget = null;
    private String mWidgetId = null;

    public abstract BaseWidget createWidgetInstance();

    /* access modifiers changed from: package-private */
    public class IncomingHandler extends Handler {
        WeakReference<BaseWidgetService> serviceWeakRef;

        IncomingHandler(BaseWidgetService baseWidgetService) {
            this.serviceWeakRef = new WeakReference<>(baseWidgetService);
        }

        public final void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                Log.d(BaseWidgetService.TAG, "Initializing widget via MSG_CREATE.");
                Bundle data = message.getData();
                BaseWidgetService.this.mWidgetId = (String) data.get(BaseWidgetService.INTENT_KEY_WIDGET_ID);
                BaseWidgetService.this.mWidget = this.serviceWeakRef.get().createWidgetInstance();
            } else if (i == 2) {
                Bundle data2 = message.getData();
                IBinder binder = data2.getBinder(BaseWidgetService.INTENT_KEY_WIDGET_REPLY_CHANNEL);
                this.serviceWeakRef.get().refreshWidget((String) data2.get(BaseWidgetService.INTENT_KEY_WIDGET_ID), binder);
            } else if (i != 3) {
                super.handleMessage(message);
            } else {
                this.serviceWeakRef.get().destroyWidget((String) message.getData().get(BaseWidgetService.INTENT_KEY_WIDGET_ID));
            }
        }
    }

    public BaseWidgetService() {
        Log.d(TAG, "constructor");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(TAG, "onStartCommand()");
        return 2;
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return this.mMessenger.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.mMessenger = new Messenger(new IncomingHandler(this));
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
    }

    public void refreshWidget(String str, IBinder iBinder) {
        if (this.mWidget != null && this.mWidgetId.equals(str)) {
            Bitmap refresh = this.mWidget.refresh();
            Message obtain = Message.obtain((Handler) null, 2);
            Bundle bundle = new Bundle();
            bundle.putString(INTENT_KEY_WIDGET_ID, this.mWidgetId);
            bundle.putParcelable(INTENT_KEY_WIDGET_BITMAP, refresh);
            bundle.putFloat(INTENT_KEY_WIDGET_OFFSET_X, this.mWidget.getOffsetX());
            bundle.putFloat(INTENT_KEY_WIDGET_OFFSET_Y, this.mWidget.getOffsetY());
            bundle.putFloat(INTENT_KEY_WIDGET_OFFSET_PITCH, this.mWidget.getOffsetPitch());
            bundle.putFloat(INTENT_KEY_WIDGET_OFFSET_YAW, this.mWidget.getOffsetYaw());
            bundle.putInt(INTENT_KEY_WIDGET_POSITION, this.mWidget.getPosition().ordinal());
            obtain.setData(bundle);
            try {
                new Messenger(iBinder).send(obtain);
            } catch (Exception e) {
                Log.e(TAG, "Error replying shell.", e);
            }
        }
    }

    public void destroyWidget(String str) {
        if (this.mWidget != null && this.mWidgetId.equals(str)) {
            this.mWidget.destroy();
        }
        this.mWidget = null;
        this.mWidgetId = null;
    }
}
