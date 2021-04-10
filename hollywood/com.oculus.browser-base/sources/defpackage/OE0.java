package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.PowerMonitor;

/* renamed from: OE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OE0 extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        PowerMonitor.f10593a.b = intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED");
        N.MCImhGql();
    }
}
