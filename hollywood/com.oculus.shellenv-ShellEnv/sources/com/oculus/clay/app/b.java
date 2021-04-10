package com.oculus.clay.app;

import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.facebook.soloader.SysUtil;
import com.oculus.clay.app.g;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public final class b implements g.a {
    private final ClayAppActivity a;
    private Messenger b = null;
    private IBinder c = null;
    private boolean d = false;
    private final String e;
    private final Handler f = new Handler();
    private final Runnable g = new c(this);
    private g.c h;
    private final SysUtil i;
    private AtomicBoolean j = new AtomicBoolean(false);
    private ServiceConnection k = new d(this);
    private int l = 0;
    private final Runnable m = new e(this);

    public b(ClayAppActivity clayAppActivity, IBinder iBinder) {
        this.a = clayAppActivity;
        this.c = iBinder;
        this.e = UUID.randomUUID().toString();
        this.i = SysUtil.c();
    }

    private boolean a(int i2) {
        boolean z = true;
        try {
            this.b.send(Message.obtain((Handler) null, i2));
        } catch (RemoteException e2) {
            Log.e("ShellFullOverlayConnectionManager", String.format("Failed to send event %s (event id: %d)", b(i2), Integer.valueOf(i2)), e2);
            z = false;
        }
        this.i.a(b(i2), z);
        return z;
    }

    private static Intent b(Intent intent) {
        Intent intent2;
        if (intent == null) {
            intent2 = new Intent();
        }
        intent2.setClassName("com.oculus.vrshell", "com.oculus.vrshell.ShellEnvOverlayService");
        return intent2;
    }

    private static String b(int i2) {
        return i2 != 2 ? i2 != 3 ? "unknown" : "onPause" : "onResume";
    }

    static /* synthetic */ void e(b bVar) {
        boolean h2 = bVar.h();
        boolean z = bVar.j.get();
        if (!h2) {
            if (z) {
                bVar.d();
            }
        } else if (bVar.a.b() && !bVar.e() && z) {
            bVar.d();
        }
    }

    private boolean h() {
        Bundle bundle = new Bundle();
        IBinder iBinder = this.c;
        if (iBinder != null) {
            bundle.putBinder("ShellEnvIPCWindowToken", iBinder);
        }
        boolean z = true;
        Message obtain = Message.obtain(null, 1, 0, 0);
        obtain.setData(bundle);
        try {
            this.b.send(obtain);
        } catch (RemoteException e2) {
            Log.e("ShellFullOverlayConnectionManager", "Failed to send setup message", e2);
            z = false;
        }
        this.i.a("setup", z);
        return z;
    }

    @Override // com.oculus.clay.app.g.a
    public final void a() {
        a(false, false);
    }

    @Override // com.oculus.clay.app.g.a
    public final void a(Intent intent) {
        Intent b2 = b(intent);
        b2.setAction("com.oculus.vrshell.SHELL_ENV_NEW_INTENT");
        b2.setClassName("com.oculus.vrshell", "com.oculus.vrshell.ShellEnvOverlayService");
        this.a.startService(b2);
    }

    public final void a(g.c cVar) {
        this.h = cVar;
    }

    public final void a(boolean z) {
        a(true, false);
    }

    public final void a(boolean z, boolean z2) {
        g.c cVar;
        ClayAppActivity clayAppActivity;
        if (this.d) {
            Log.w("ShellFullOverlayConnectionManager", "ShellIPCManager has already established a service connection");
            return;
        }
        this.i.f();
        this.j.set(true);
        Intent b2 = b((z2 || (clayAppActivity = this.a) == null || clayAppActivity.getIntent() == null) ? null : this.a.getIntent());
        b2.setAction("com.oculus.vrshell.SHELL_ENV_START_SERVICE");
        b2.putExtra("extra_instance_session_id", this.e);
        try {
            this.a.startService(b2);
            Intent intent = new Intent(b2);
            intent.setData(new Uri.Builder().scheme("shellenv").authority("bind").appendQueryParameter("extra_instance_session_id", this.e).build());
            int i2 = this.l;
            this.l = 0;
            this.f.postDelayed(this.g, 3000);
            this.d = this.a.bindService(intent, this.k, 4096);
            if (this.d && (cVar = this.h) != null) {
                cVar.a(z, i2);
            }
        } catch (SecurityException unused) {
            int i3 = this.l;
            if (i3 < 5) {
                Log.e("ShellFullOverlayConnectionManager", String.format("Unable to connect to VrShell - Attempting a reconnect after %d ms", 5000L));
                this.l++;
                this.f.postDelayed(this.m, 5000);
                return;
            }
            Log.e("ShellFullOverlayConnectionManager", String.format("Failed to reconnect to VrShell after %d tries - hard quitting ShellEnv", Integer.valueOf(i3)));
            g.c cVar2 = this.h;
            if (cVar2 != null) {
                cVar2.a(this.l);
            }
            ClayAppActivity.a();
        }
    }

    public final void b() {
        this.j.set(false);
        this.i.h();
        if (this.d) {
            this.a.unbindService(this.k);
            this.d = false;
            this.b = null;
        }
    }

    public final void c() {
        this.i.i();
        this.a.stopService(b((Intent) null));
    }

    public final void d() {
        b();
        a(true, false);
    }

    @Override // com.oculus.clay.app.g.a
    public final boolean e() {
        if (this.b != null) {
            return a(2);
        }
        return false;
    }

    @Override // com.oculus.clay.app.g.a
    public final void f() {
    }

    @Override // com.oculus.clay.app.g.a
    public final void g() {
        if (this.b != null) {
            a(3);
        }
    }
}
