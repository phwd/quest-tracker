package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import java.util.concurrent.Executor;
import org.chromium.base.TraceEvent;

/* renamed from: dp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceConnectionC2244dp implements AbstractC2074cp, ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9809a;
    public final Intent b;
    public final int c;
    public final Handler d;
    public final Executor e;
    public C4633ro f;
    public final String g;
    public boolean h;

    public ServiceConnectionC2244dp(Context context, Intent intent, int i, Handler handler, Executor executor, C4633ro roVar, String str) {
        this.f9809a = context;
        this.b = intent;
        this.c = i;
        this.d = handler;
        this.e = executor;
        this.f = roVar;
        this.g = str;
    }

    /* JADX INFO: finally extract failed */
    public boolean a() {
        try {
            TraceEvent.Y("ChildServiceConnectionImpl.bindServiceConnection", null);
            this.h = AbstractC4952th.b(this.f9809a, this.b, this, this.c, this.d, this.e, this.g);
            TraceEvent.f0("ChildServiceConnectionImpl.bindServiceConnection");
            return this.h;
        } catch (Throwable th) {
            TraceEvent.f0("ChildServiceConnectionImpl.bindServiceConnection");
            throw th;
        }
    }

    public void b() {
        this.f = null;
        c();
    }

    public void c() {
        if (this.h) {
            this.f9809a.unbindService(this);
            this.h = false;
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C4633ro roVar = this.f;
        if (roVar == null) {
            AbstractC1220Ua0.f("ChildServiceConn", "onServiceConnected after timeout " + componentName, new Object[0]);
        } else if (roVar.f11224a.e.getLooper() == Looper.myLooper()) {
            roVar.f11224a.j(iBinder);
        } else {
            roVar.f11224a.e.post(new RunnableC4292po(roVar, iBinder));
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        C4633ro roVar = this.f;
        if (roVar == null) {
            return;
        }
        if (roVar.f11224a.e.getLooper() == Looper.myLooper()) {
            roVar.f11224a.k();
        } else {
            roVar.f11224a.e.post(new RunnableC4463qo(roVar));
        }
    }
}
