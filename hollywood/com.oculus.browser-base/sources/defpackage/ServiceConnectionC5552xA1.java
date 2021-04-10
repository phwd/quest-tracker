package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* renamed from: xA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ServiceConnectionC5552xA1 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4531rA1 f11597a;

    public ServiceConnectionC5552xA1(C4531rA1 ra1, byte b) {
        this.f11597a = ra1;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f11597a.c.a(4, "ServiceConnectionImpl.onServiceConnected(%s)", new Object[]{componentName});
        this.f11597a.d(new AA1(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f11597a.c.a(4, "ServiceConnectionImpl.onServiceDisconnected(%s)", new Object[]{componentName});
        this.f11597a.d(new C5892zA1(this));
    }
}
