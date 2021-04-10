package com.oculus.clay.app;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;

final class d implements ServiceConnection {
    private /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        b.b(this.a).g();
        b.d(this.a).removeCallbacks(b.c(this.a));
        b.a(this.a, new Messenger(iBinder));
        b.e(this.a);
        if (b.f(this.a) != null) {
            b.f(this.a).a();
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        b.a(this.a, (Messenger) null);
        b.a(this.a, false);
        boolean z = b.a(this.a).get();
        b.b(this.a).a(z);
        if (z) {
            this.a.d();
            if (b.f(this.a) != null) {
                b.f(this.a).b();
            }
        }
        Log.e("ShellFullOverlayConnectionManager", "Shell service disconnected");
    }
}
