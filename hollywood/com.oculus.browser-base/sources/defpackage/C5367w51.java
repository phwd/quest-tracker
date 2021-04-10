package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Iterator;

/* renamed from: w51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5367w51 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5537x51 f11518a;

    public C5367w51(C5537x51 x51) {
        this.f11518a = x51;
    }

    public void onReceive(Context context, Intent intent) {
        Iterator it = this.f11518a.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((W1) uq0.next()).e();
            } else {
                return;
            }
        }
    }
}
