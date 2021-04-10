package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo.system.impl.WatcherImpl;

/* renamed from: mM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExecutorC3709mM implements Executor, Cw1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1552Zj0 f10416a;
    public final AbstractC1552Zj0 b;
    public final List c;
    public final Object d = new Object();
    public final WatcherImpl e;

    public ExecutorC3709mM(SA sa) {
        Objects.requireNonNull((CoreImpl) sa);
        WatcherImpl watcherImpl = new WatcherImpl();
        this.e = watcherImpl;
        C1247Uj0 uj0 = C1247Uj0.c;
        CoreImpl coreImpl = (CoreImpl) sa;
        ByteBuffer a2 = coreImpl.a(8);
        a2.putInt(0, 8);
        a2.putInt(4, uj0.f9606a);
        ResultAnd resultAnd = (ResultAnd) N.MZhgS7uU(coreImpl, a2);
        if (resultAnd.f10995a == 0) {
            C1576Zv0 zv0 = new C1576Zv0(new C1709ak0(coreImpl, ((Integer) ((UA) resultAnd.b).f9384a).intValue()), new C1709ak0(coreImpl, ((Integer) ((UA) resultAnd.b).b).intValue()));
            AbstractC1552Zj0 zj0 = (AbstractC1552Zj0) zv0.f9384a;
            this.b = zj0;
            this.f10416a = (AbstractC1552Zj0) zv0.b;
            this.c = new ArrayList();
            watcherImpl.a(zj0, RA.c, this);
            return;
        }
        throw new C5475wl0(resultAnd.f10995a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0014  */
    @Override // defpackage.Cw1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0027
            r6 = 0
            Zj0 r0 = r5.b     // Catch:{ wl0 -> 0x0011 }
            Wj0 r1 = defpackage.C1369Wj0.c     // Catch:{ wl0 -> 0x0011 }
            org.chromium.mojo.system.ResultAnd r0 = r0.W(r1)     // Catch:{ wl0 -> 0x0011 }
            int r0 = r0.f10995a     // Catch:{ wl0 -> 0x0011 }
            if (r0 != 0) goto L_0x0011
            r0 = 1
            goto L_0x0012
        L_0x0011:
            r0 = r6
        L_0x0012:
            if (r0 == 0) goto L_0x0027
            java.lang.Object r0 = r5.d
            monitor-enter(r0)
            java.util.List r1 = r5.c     // Catch:{ all -> 0x0024 }
            java.lang.Object r6 = r1.remove(r6)     // Catch:{ all -> 0x0024 }
            java.lang.Runnable r6 = (java.lang.Runnable) r6     // Catch:{ all -> 0x0024 }
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            r6.run()
            goto L_0x0059
        L_0x0024:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        L_0x0027:
            java.lang.Object r6 = r5.d
            monitor-enter(r6)
            Zj0 r0 = r5.f10416a     // Catch:{ all -> 0x005a }
            r0.close()     // Catch:{ all -> 0x005a }
            java.util.List r0 = r5.c     // Catch:{ all -> 0x005a }
            r0.clear()     // Catch:{ all -> 0x005a }
            monitor-exit(r6)     // Catch:{ all -> 0x005a }
            org.chromium.mojo.system.impl.WatcherImpl r6 = r5.e
            long r0 = r6.f10997a
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0040
            goto L_0x0046
        L_0x0040:
            r4 = 0
            r6.b = r4
            J.N.MPTT407x(r6, r0)
        L_0x0046:
            org.chromium.mojo.system.impl.WatcherImpl r6 = r5.e
            long r0 = r6.f10997a
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x004f
            goto L_0x0054
        L_0x004f:
            J.N.Mi32vqDA(r6, r0)
            r6.f10997a = r2
        L_0x0054:
            Zj0 r6 = r5.b
            r6.close()
        L_0x0059:
            return
        L_0x005a:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ExecutorC3709mM.a(int):void");
    }

    public void execute(Runnable runnable) {
        synchronized (this.d) {
            if (this.f10416a.a()) {
                this.c.add(runnable);
                AbstractC1552Zj0 zj0 = this.f10416a;
                ThreadLocal threadLocal = AbstractC3880nM.f10485a;
                zj0.c(null, null, C1491Yj0.c);
            } else {
                throw new IllegalStateException("Trying to execute an action on a closed executor.");
            }
        }
    }
}
