package X;

import android.app.AppOpsManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;

/* renamed from: X.By  reason: case insensitive filesystem */
public final class HandlerC0127By extends Handler {
    public final ComponentName A00;
    public final /* synthetic */ C0 A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC0127By(C0 c0, Looper looper, ComponentName componentName) {
        super(looper);
        this.A01 = c0;
        this.A00 = componentName;
    }

    public final void handleMessage(Message message) {
        String str;
        String str2;
        if (message != null) {
            C0 c0 = this.A01;
            try {
                ((AppOpsManager) c0.getApplicationContext().getSystemService("appops")).checkPackage(message.sendingUid, "com.google.android.gms");
                int i = message.what;
                if (i == 1) {
                    Bundle data = message.getData();
                    Messenger messenger = message.replyTo;
                    String string = data.getString("tag");
                    if (messenger != null && string != null) {
                        RunnableC0128Bz A002 = C0.A00(c0, string, new C0826j2(messenger, string, this.A00), data.getBundle("extras"));
                        if (A002 != null) {
                            A002.A01();
                            return;
                        }
                        return;
                    } else if (C0139Dd.A01.A3Y(3)) {
                        str = "GcmTaskService";
                        str2 = "Invalid start execution message.";
                    } else {
                        return;
                    }
                } else if (i == 2) {
                    str = "GcmTaskService";
                    str2 = "Ignoring unimplemented stop message";
                } else if (i != 4) {
                    C0139Dd.A0O("GcmTaskService", "Unrecognized message received: %s", message);
                    return;
                } else {
                    return;
                }
                C0139Dd.A09(str, str2);
            } catch (SecurityException e) {
                C0139Dd.A0L("GcmTaskService", "Message was not sent from GCM.", e);
            }
        }
    }
}
