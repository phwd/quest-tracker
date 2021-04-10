package defpackage;

import android.app.AppOpsManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: WU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class WU extends HandlerC3356kH1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ YU f9149a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WU(YU yu, Looper looper) {
        super(looper);
        this.f9149a = yu;
    }

    public final void handleMessage(Message message) {
        boolean z;
        Messenger messenger;
        YU yu = this.f9149a;
        int i = message.sendingUid;
        C3969nu0 a2 = C5858yz1.a(yu);
        Objects.requireNonNull(a2);
        try {
            ((AppOpsManager) a2.f10518a.getSystemService("appops")).checkPackage(i, "com.google.android.gms");
            z = true;
        } catch (SecurityException unused) {
            z = false;
        }
        if (!z) {
            Log.e("GcmTaskService", "unable to verify presence of Google Play Services");
            return;
        }
        int i2 = message.what;
        if (i2 == 1) {
            Bundle data = message.getData();
            if (!data.isEmpty() && (messenger = message.replyTo) != null) {
                String string = data.getString("tag");
                ArrayList parcelableArrayList = data.getParcelableArrayList("triggered_uris");
                long j = data.getLong("max_exec_duration", 180);
                if (!this.f9149a.d(string)) {
                    Bundle bundle = data.getBundle("extras");
                    YU yu2 = this.f9149a;
                    XU xu = new XU(yu2, string, messenger, bundle, j, parcelableArrayList);
                    Objects.requireNonNull(yu2);
                    try {
                        yu2.H.execute(xu);
                    } catch (RejectedExecutionException e) {
                        Log.e("GcmTaskService", "Executor is shutdown. onDestroy was called but main looper had an unprocessed start task message. The task will be retried with backoff delay.", e);
                        xu.a(1);
                    }
                }
            }
        } else if (i2 != 2) {
            if (i2 != 4) {
                String valueOf = String.valueOf(message);
                StringBuilder sb = new StringBuilder(valueOf.length() + 31);
                sb.append("Unrecognized message received: ");
                sb.append(valueOf);
                Log.e("GcmTaskService", sb.toString());
                return;
            }
            this.f9149a.a();
        } else if (Log.isLoggable("GcmTaskService", 3)) {
            String.valueOf(message).length();
        }
    }
}
