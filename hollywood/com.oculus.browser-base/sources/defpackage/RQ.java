package defpackage;

import android.os.SystemClock;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* renamed from: RQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RQ {

    /* renamed from: a  reason: collision with root package name */
    public static RQ f8829a;
    public boolean b;
    public boolean c;
    public long d;
    public Queue e = new LinkedList();
    public Queue f = new LinkedList();
    public AbstractC2032cb g;

    public RQ() {
        Object obj = ThreadUtils.f10596a;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (AbstractC1575Zv.e().g("policy")) {
            a(true, elapsedRealtime);
            return;
        }
        try {
            QQ qq = new QQ(this, ContextUtils.getApplicationContext(), elapsedRealtime);
            this.g = qq;
            C3070if1 if1 = C3070if1.f;
            qq.f();
            PostTask.b(if1, qq.e, 0);
        } catch (RejectedExecutionException unused) {
            a(false, elapsedRealtime);
        }
    }

    public final void a(boolean z, long j) {
        this.c = z;
        this.b = true;
        if (j > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.d = elapsedRealtime;
            long j2 = elapsedRealtime - j;
            AbstractC3364kK0.k("Enterprise.FirstRun.AppRestrictionLoadTime", j2);
            AbstractC3364kK0.j("Enterprise.FirstRun.AppRestrictionLoadTime.Medium", j2);
            String.format(Locale.US, "Policy received. Runtime: [%d], result: [%s]", Long.valueOf(j2), Boolean.valueOf(z));
        }
        while (!this.e.isEmpty()) {
            ((Callback) this.e.remove()).onResult(Boolean.valueOf(this.c));
        }
        while (!this.f.isEmpty()) {
            ((Callback) this.f.remove()).onResult(Long.valueOf(this.d));
        }
    }
}
