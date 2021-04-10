package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;
import java.util.concurrent.Executor;

/* renamed from: kh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3416kh extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3929nh f10295a;

    public C3416kh(C3929nh nhVar) {
        this.f10295a = nhVar;
    }

    public void onReceive(Context context, Intent intent) {
        C3929nh nhVar = this.f10295a;
        Objects.requireNonNull(nhVar);
        if (!intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
            AbstractC1220Ua0.a("BatteryStatusManager", "Unexpected intent.", new Object[0]);
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("present", false);
        int intExtra = intent.getIntExtra("plugged", -1);
        if (!booleanExtra || intExtra == -1) {
            nhVar.f10508a.a(new C3245jh(0));
            return;
        }
        double intExtra2 = ((double) intent.getIntExtra("level", -1)) / ((double) intent.getIntExtra("scale", -1));
        double d = 0.0d;
        if (intExtra2 < 0.0d || intExtra2 > 1.0d) {
            intExtra2 = 1.0d;
        }
        boolean z = true;
        boolean z2 = intExtra != 0;
        if (intent.getIntExtra("status", -1) != 5) {
            z = false;
        }
        if (!z2 || !z) {
            d = Double.POSITIVE_INFINITY;
        }
        C3245jh jhVar = new C3245jh(0);
        jhVar.d = z2;
        jhVar.e = d;
        jhVar.f = Double.POSITIVE_INFINITY;
        jhVar.g = intExtra2;
        if (nhVar.d != null) {
            C3587lh lhVar = new C3587lh(nhVar, jhVar);
            Executor executor = AbstractC2032cb.f9616a;
            lhVar.f();
            ((ExecutorC1463Ya) executor).execute(lhVar.e);
            return;
        }
        nhVar.f10508a.a(jhVar);
    }
}
