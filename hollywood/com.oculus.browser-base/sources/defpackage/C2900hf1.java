package defpackage;

import J.N;
import android.util.Pair;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.chromium.base.task.PostTask;

/* renamed from: hf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2900hf1 implements AbstractC2387ef1 {

    /* renamed from: a  reason: collision with root package name */
    public static final ReferenceQueue f10091a = new ReferenceQueue();
    public static final Set b = new HashSet();
    public final C3070if1 c;
    public final String d;
    public final int e;
    public volatile long f;
    public final Runnable g = new RunnableC2558ff1(this);
    public final Object h = new Object();
    public boolean i;
    public LinkedList j;
    public List k;

    public C2900hf1(C3070if1 if1, String str, int i2) {
        this.c = if1.d();
        this.d = AbstractC2531fV.f(str, ".PreNativeTask.run");
        this.e = i2;
    }

    public static void d() {
        while (true) {
            C2729gf1 gf1 = (C2729gf1) f10091a.poll();
            if (gf1 != null) {
                N.MERCiIV8(gf1.f10012a);
                Set set = b;
                synchronized (set) {
                    set.remove(gf1);
                }
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC2387ef1
    public void a(Runnable runnable, long j2) {
        if (this.f != 0) {
            N.MGnQU$47(this.f, runnable, j2, runnable.getClass().getName());
            return;
        }
        synchronized (this.h) {
            f();
            if (this.f != 0) {
                N.MGnQU$47(this.f, runnable, j2, runnable.getClass().getName());
                return;
            }
            if (j2 == 0) {
                this.j.add(runnable);
                h();
            } else {
                this.k.add(new Pair(runnable, Long.valueOf(j2)));
            }
        }
    }

    @Override // defpackage.AbstractC2387ef1
    public void b(Runnable runnable) {
        a(runnable, 0);
    }

    public void e() {
        int i2 = this.e;
        C3070if1 if1 = this.c;
        long M5_IQXaH = N.M5_IQXaH(i2, if1.j, if1.k, if1.l, if1.m, if1.n);
        synchronized (this.h) {
            LinkedList linkedList = this.j;
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    Runnable runnable = (Runnable) it.next();
                    N.MGnQU$47(M5_IQXaH, runnable, 0, runnable.getClass().getName());
                }
                this.j = null;
            }
            List<Pair> list = this.k;
            if (list != null) {
                for (Pair pair : list) {
                    N.MGnQU$47(M5_IQXaH, (Runnable) pair.first, ((Long) pair.second).longValue(), pair.getClass().getName());
                }
                this.k = null;
            }
            this.f = M5_IQXaH;
        }
        Set set = b;
        synchronized (set) {
            set.add(new C2729gf1(this));
        }
        d();
    }

    public final void f() {
        if (!this.i) {
            boolean z = true;
            this.i = true;
            synchronized (PostTask.f10598a) {
                List list = PostTask.b;
                if (list == null) {
                    z = false;
                } else {
                    list.add(this);
                }
            }
            if (!z) {
                e();
                return;
            }
            this.j = new LinkedList();
            this.k = new ArrayList();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r1 = r4.c.j;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        if (r1 == 1) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        if (r1 == 2) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        android.os.Process.setThreadPriority(10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        android.os.Process.setThreadPriority(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        android.os.Process.setThreadPriority(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r2.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void g() {
        /*
            r4 = this;
            java.lang.String r0 = r4.d
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.j0(r0)
            java.lang.Object r1 = r4.h     // Catch:{ all -> 0x0040 }
            monitor-enter(r1)     // Catch:{ all -> 0x0040 }
            java.util.LinkedList r2 = r4.j     // Catch:{ all -> 0x003d }
            if (r2 != 0) goto L_0x0014
            monitor-exit(r1)     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0013
            r0.close()
        L_0x0013:
            return
        L_0x0014:
            java.lang.Object r2 = r2.poll()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            monitor-exit(r1)
            if1 r1 = r4.c
            int r1 = r1.j
            r3 = 1
            if (r1 == r3) goto L_0x0030
            r3 = 2
            if (r1 == r3) goto L_0x002b
            r1 = 10
            android.os.Process.setThreadPriority(r1)
            goto L_0x0034
        L_0x002b:
            r1 = -1
            android.os.Process.setThreadPriority(r1)
            goto L_0x0034
        L_0x0030:
            r1 = 0
            android.os.Process.setThreadPriority(r1)
        L_0x0034:
            r2.run()
            if (r0 == 0) goto L_0x003c
            r0.close()
        L_0x003c:
            return
        L_0x003d:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L_0x0040:
            r1 = move-exception
            if (r0 == 0) goto L_0x004d
            r0.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004d
        L_0x0047:
            r0 = move-exception
            Gh1 r2 = defpackage.AbstractC0754Mh1.f8495a
            r2.a(r1, r0)
        L_0x004d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2900hf1.g():void");
    }

    public void h() {
        PostTask.d.execute(this.g);
    }
}
