package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* renamed from: hD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2832hD1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ YC1 f10056a;

    public C2832hD1(YC1 yc1) {
        this.f10056a = yc1;
    }

    public final void onReceive(Context context, Intent intent) {
        Log.isLoggable("InstanceID", 3);
        this.f10056a.f(intent);
    }
}
