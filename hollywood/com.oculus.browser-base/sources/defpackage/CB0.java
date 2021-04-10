package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/* renamed from: CB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CB0 {

    /* renamed from: a  reason: collision with root package name */
    public PendingIntent f7793a;
    public final int b;

    public CB0(PendingIntent pendingIntent, int i) {
        this.f7793a = pendingIntent;
        this.b = i;
    }

    public static CB0 a(Context context, int i, Intent intent, int i2) {
        return new CB0(PendingIntent.getBroadcast(context, i, intent, i2), i2);
    }
}
