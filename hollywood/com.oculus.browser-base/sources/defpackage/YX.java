package defpackage;

import android.accounts.AccountManagerCallback;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/* renamed from: YX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YX extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f9279a;
    public final /* synthetic */ ZX b;

    public YX(ZX zx, Context context) {
        this.b = zx;
        this.f9279a = context;
    }

    public void onReceive(Context context, Intent intent) {
        this.f9279a.unregisterReceiver(this);
        ZX zx = this.b;
        C1674aY aYVar = zx.f9348a;
        aYVar.b.getAuthToken(aYVar.e, aYVar.d, aYVar.c, true, (AccountManagerCallback<Bundle>) new ZX(zx.b, aYVar), (Handler) null);
    }
}
