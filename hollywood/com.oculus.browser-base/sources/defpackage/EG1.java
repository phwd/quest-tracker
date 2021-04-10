package defpackage;

import android.content.BroadcastReceiver;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: EG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class EG1 {

    /* renamed from: a  reason: collision with root package name */
    public final Intent f7950a;
    public final BroadcastReceiver.PendingResult b;
    public boolean c = false;
    public final ScheduledFuture d;

    public EG1(Intent intent, BroadcastReceiver.PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.f7950a = intent;
        this.b = pendingResult;
        this.d = scheduledExecutorService.schedule(new NG1(this, intent), 9500, TimeUnit.MILLISECONDS);
    }

    public final synchronized void a() {
        if (!this.c) {
            this.b.finish();
            this.d.cancel(false);
            this.c = true;
        }
    }
}
