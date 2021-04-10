package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* renamed from: AG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AG1 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final Map f7664a = new HashMap();
    public int b = 2;
    public boolean c;
    public IBinder d;
    public final NV e;
    public ComponentName f;
    public final /* synthetic */ ZF1 g;

    public AG1(ZF1 zf1, NV nv) {
        this.g = zf1;
        this.e = nv;
    }

    public final void a(String str) {
        this.b = 3;
        ZF1 zf1 = this.g;
        C1030Qx qx = zf1.K;
        Context context = zf1.I;
        boolean b2 = qx.b(context, this.e.a(context), this, this.e.e);
        this.c = b2;
        if (b2) {
            Message obtainMessage = this.g.f9333J.obtainMessage(1, this.e);
            ZF1 zf12 = this.g;
            zf12.f9333J.sendMessageDelayed(obtainMessage, zf12.M);
            return;
        }
        this.b = 2;
        try {
            ZF1 zf13 = this.g;
            C1030Qx qx2 = zf13.K;
            Context context2 = zf13.I;
            Objects.requireNonNull(qx2);
            context2.unbindService(this);
        } catch (IllegalArgumentException unused) {
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.g.H) {
            this.g.f9333J.removeMessages(1, this.e);
            this.d = iBinder;
            this.f = componentName;
            for (ServiceConnection serviceConnection : this.f7664a.values()) {
                serviceConnection.onServiceConnected(componentName, iBinder);
            }
            this.b = 1;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.g.H) {
            this.g.f9333J.removeMessages(1, this.e);
            this.d = null;
            this.f = componentName;
            for (ServiceConnection serviceConnection : this.f7664a.values()) {
                serviceConnection.onServiceDisconnected(componentName);
            }
            this.b = 2;
        }
    }
}
