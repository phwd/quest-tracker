package defpackage;

import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import org.chromium.base.TraceEvent;

/* renamed from: zm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5989zm1 extends C5819ym1 implements MessageQueue.IdleHandler {
    public long b;
    public long c;
    public int d;
    public int e;
    public int f;
    public boolean g;

    public C5989zm1(AbstractC5139um1 um1) {
        super(null);
    }

    @Override // defpackage.C5819ym1
    public final void a(String str) {
        if (this.f == 0) {
            TraceEvent.f0("Looper.queueIdle");
        }
        this.c = SystemClock.elapsedRealtime();
        c();
        super.a(str);
    }

    @Override // defpackage.C5819ym1
    public final void b(String str) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.c;
        if (elapsedRealtime > 16) {
            String str2 = "observed a task that took " + elapsedRealtime + "ms: " + str;
            TraceEvent.i0("TraceEvent.LooperMonitor:IdleStats", str2);
            Log.println(5, "TraceEvent_LooperMonitor", str2);
        }
        super.b(str);
        c();
        this.d++;
        this.f++;
    }

    public final void c() {
        if (TraceEvent.F && !this.g) {
            this.b = SystemClock.elapsedRealtime();
            Looper.myQueue().addIdleHandler(this);
            this.g = true;
        } else if (this.g && !TraceEvent.F) {
            Looper.myQueue().removeIdleHandler(this);
            this.g = false;
        }
    }

    public final boolean queueIdle() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.b == 0) {
            this.b = elapsedRealtime;
        }
        long j = elapsedRealtime - this.b;
        this.e++;
        TraceEvent.Y("Looper.queueIdle", this.f + " tasks since last idle.");
        if (j > 48) {
            String str = this.d + " tasks and " + this.e + " idles processed so far, " + this.f + " tasks bursted and " + j + "ms elapsed since last idle";
            TraceEvent.i0("TraceEvent.LooperMonitor:IdleStats", str);
            Log.println(3, "TraceEvent_LooperMonitor", str);
        }
        this.b = elapsedRealtime;
        this.f = 0;
        return true;
    }
}
