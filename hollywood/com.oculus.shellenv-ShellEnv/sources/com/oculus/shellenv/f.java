package com.oculus.shellenv;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import com.oculus.clay.app.g;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public final class f implements g.c {
    private static f a;
    private final Context b;
    private final UnifiedTelemetryLogger c;
    private final Handler d = new Handler();
    private a e;
    private long f = 0;
    private boolean g = true;
    private long h;

    /* access modifiers changed from: package-private */
    public class a implements Runnable {
        private final boolean a;
        private final int b;

        public a(boolean z, int i) {
            this.a = z;
            this.b = i;
        }

        public final boolean a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final void run() {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("shellenv_overlay_service_timeout");
            analyticsEvent.setExtra("was_reconnect", Boolean.valueOf(this.a));
            analyticsEvent.setExtra("reconnect_count", Integer.valueOf(this.b));
            f.this.c.reportEvent(analyticsEvent, false);
        }
    }

    private f(Context context) {
        this.b = context;
        this.c = UnifiedTelemetryLogger.getInstance(this.b);
    }

    public static void a(Context context) {
        if (a == null) {
            a = new f(context);
        }
    }

    public static f c() {
        return a;
    }

    private void h() {
        a aVar = this.e;
        if (aVar != null) {
            this.d.removeCallbacks(aVar);
            this.e = null;
        }
    }

    @Override // com.oculus.clay.app.g.c
    public final void a() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.h;
        this.h = 0;
        a aVar = this.e;
        if (aVar != null) {
            boolean a2 = aVar.a();
            int b2 = this.e.b();
            h();
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("shellenv_overlay_service_connected");
            analyticsEvent.setExtra("time_to_connect_ms", Long.valueOf(elapsedRealtime));
            analyticsEvent.setExtra("was_reconnect", Boolean.valueOf(a2));
            analyticsEvent.setExtra("reconnect_count", Integer.valueOf(b2));
            this.c.reportEvent(analyticsEvent, false);
        }
    }

    @Override // com.oculus.clay.app.g.c
    public final void a(int i) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("shellenv_overlay_service_connect_retry_exceed");
        analyticsEvent.setExtra("reconnect_count", Integer.valueOf(i));
        this.c.reportEvent(analyticsEvent, false);
    }

    @Override // com.oculus.clay.app.g.c
    public final void a(boolean z, int i) {
        this.h = SystemClock.elapsedRealtime();
        this.e = new a(z, i);
        this.d.postDelayed(this.e, 10000);
    }

    @Override // com.oculus.clay.app.g.c
    public final void b() {
        this.c.reportEvent(new AnalyticsEvent("shellenv_overlay_service_unexpected_disconnect"), false);
    }

    public final void d() {
        this.g = true;
    }

    public final void e() {
        this.f = SystemClock.elapsedRealtime();
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("shellenv_session_started_event");
        analyticsEvent.setExtra("is_cold_start", Boolean.valueOf(this.g));
        this.c.reportEvent(analyticsEvent, false);
        this.g = false;
    }

    public final void f() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f;
        this.f = 0;
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("shellenv_session_ended_event");
        analyticsEvent.setExtra("active_time_ms", Long.valueOf(elapsedRealtime));
        this.c.reportEvent(analyticsEvent, false);
    }

    public final void g() {
        this.g = true;
        h();
    }
}
