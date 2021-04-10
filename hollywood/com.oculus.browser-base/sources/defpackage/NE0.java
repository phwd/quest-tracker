package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;

/* renamed from: NE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NE0 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicBoolean f8534a = new AtomicBoolean(false);
    public LE0 b = new LE0();
    public ME0 c = new ME0();

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) && ApplicationStatus.hasVisibleActivities()) {
            ME0 me0 = this.c;
            if (me0.G != 1) {
                me0.G = 1;
                me0.F.postDelayed(me0, 5000);
            }
            if (this.f8534a.getAndSet(false)) {
                ContextUtils.getApplicationContext().unregisterReceiver(this);
            }
        }
    }
}
