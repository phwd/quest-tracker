package com.oculus.clay.app;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public final class g {
    private final ClayAppActivity a;
    private final b b;
    private final f c;
    private b d;

    public interface a {
        void a();

        void a(Intent intent);

        boolean e();

        void f();

        void g();
    }

    /* access modifiers changed from: package-private */
    public enum b {
        FULL_SHELL,
        OVERLAY_SHELL,
        FULL_SHELL_FOR_GUARDIAN
    }

    public interface c {
        void a();

        void a(int i);

        void a(boolean z, int i);

        void b();
    }

    public g(ClayAppActivity clayAppActivity, IBinder iBinder) {
        this.a = clayAppActivity;
        this.d = b(clayAppActivity.getIntent());
        Log.i("ShellServiceConnectionManager", String.format("Setting initial connection state to %s", this.d.name()));
        this.b = new b(clayAppActivity, iBinder);
        this.c = new f(clayAppActivity);
    }

    private void a(b bVar) {
        Log.i("ShellServiceConnectionManager", String.format("Connection state is switching from %s to %s", this.d.name(), bVar.name()));
        this.d = bVar;
    }

    private static b b(Intent intent) {
        return (intent == null || !intent.hasExtra("start_in_min_overlay")) ? b.FULL_SHELL : intent.getBooleanExtra("start_in_min_overlay", false) ? b.OVERLAY_SHELL : b.FULL_SHELL;
    }

    private a i() {
        return h.a[this.d.ordinal()] != 1 ? this.b : this.c;
    }

    public final void a() {
        i().a();
    }

    public final void a(Intent intent) {
        b b2 = b(this.a.getIntent());
        new StringBuilder("onNewIntent, connection mode from intent: ").append(b2);
        if (this.d != b.FULL_SHELL_FOR_GUARDIAN) {
            b bVar = this.d;
            if (b2 == bVar) {
                i().a(intent);
                return;
            }
            a(b2);
            if (bVar == b.FULL_SHELL) {
                this.b.b();
                this.c.e();
            } else if (bVar == b.OVERLAY_SHELL) {
                this.b.a();
                this.b.a(intent);
                if (intent != null && intent.hasExtra("forwarded_intent_original_action") && "app_launch".equals(intent.getStringExtra("forwarded_intent_original_action"))) {
                    this.c.b();
                }
            }
        }
    }

    public final void a(c cVar) {
        this.b.a(cVar);
    }

    public final void b() {
        i().e();
    }

    public final void c() {
        i().f();
    }

    public final void d() {
        i().g();
    }

    public final void e() {
        this.b.b();
        this.b.c();
    }

    public final void f() {
        if (this.d != b.FULL_SHELL_FOR_GUARDIAN) {
            a(b.FULL_SHELL_FOR_GUARDIAN);
            this.b.a(false, true);
        }
    }

    public final void g() {
        if (this.d == b.FULL_SHELL_FOR_GUARDIAN) {
            a(b.OVERLAY_SHELL);
            this.b.b();
            this.c.c();
        }
    }

    public final void h() {
        b bVar = this.d;
        a(b.FULL_SHELL);
        if (bVar == b.OVERLAY_SHELL) {
            this.b.a();
        }
    }
}
