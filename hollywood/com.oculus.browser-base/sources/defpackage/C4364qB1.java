package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* renamed from: qB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4364qB1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public Context f11122a;
    public final AbstractC4193pB1 b;

    public C4364qB1(AbstractC4193pB1 pb1) {
        this.b = pb1;
    }

    public final synchronized void a() {
        Context context = this.f11122a;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.f11122a = null;
    }

    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            this.b.a();
            a();
        }
    }
}
