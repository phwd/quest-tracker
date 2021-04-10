package X;

import java.util.LinkedList;
import java.util.List;

/* renamed from: X.1x9  reason: invalid class name and case insensitive filesystem */
public final class C12251x9 implements AbstractC12271xB, AbstractC12231x7 {
    public List<AbstractC12271xB> A00;
    public volatile boolean A01;

    @Override // X.AbstractC12231x7
    public final boolean A1D(AbstractC12271xB r2) {
        AnonymousClass219.A01(r2, "d is null");
        if (!this.A01) {
            synchronized (this) {
                if (!this.A01) {
                    List list = this.A00;
                    if (list == null) {
                        list = new LinkedList();
                        this.A00 = list;
                    }
                    list.add(r2);
                    return true;
                }
            }
        }
        r2.dispose();
        return false;
    }

    @Override // X.AbstractC12231x7
    public final boolean A2Z(AbstractC12271xB r3) {
        List<AbstractC12271xB> list;
        AnonymousClass219.A01(r3, "Disposable item is null");
        if (this.A01) {
            return false;
        }
        synchronized (this) {
            if (this.A01 || (list = this.A00) == null || !list.remove(r3)) {
                return false;
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        r2 = null;
        r1 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r1.hasNext() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.next().dispose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        X.C12261xA.A00(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        if (r2 == null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
        r2 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        r2.add(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        if (r2.size() != 1) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        throw X.C12301xE.A00((java.lang.Throwable) r2.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0054, code lost:
        throw new X.AnonymousClass1Ox(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    @Override // X.AbstractC12271xB
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void dispose() {
        /*
            r3 = this;
            boolean r0 = r3.A01
            if (r0 != 0) goto L_0x0058
            monitor-enter(r3)
            boolean r0 = r3.A01     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r3)     // Catch:{ all -> 0x0055 }
            return
        L_0x000b:
            r0 = 1
            r3.A01 = r0     // Catch:{ all -> 0x0055 }
            java.util.List<X.1xB> r1 = r3.A00     // Catch:{ all -> 0x0055 }
            r0 = 0
            r3.A00 = r0     // Catch:{ all -> 0x0055 }
            monitor-exit(r3)     // Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x0058
            r2 = 0
            java.util.Iterator r1 = r1.iterator()
        L_0x001b:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x003a
            java.lang.Object r0 = r1.next()
            X.1xB r0 = (X.AbstractC12271xB) r0
            r0.dispose()     // Catch:{ all -> 0x002b }
            goto L_0x001b
        L_0x002b:
            r0 = move-exception
            X.C12261xA.A00(r0)
            if (r2 != 0) goto L_0x0036
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L_0x0036:
            r2.add(r0)
            goto L_0x001b
        L_0x003a:
            if (r2 == 0) goto L_0x0058
            int r1 = r2.size()
            r0 = 1
            if (r1 != r0) goto L_0x004f
            r0 = 0
            java.lang.Object r0 = r2.get(r0)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.RuntimeException r0 = X.C12301xE.A00(r0)
            throw r0
        L_0x004f:
            X.1Ox r0 = new X.1Ox
            r0.<init>(r2)
            throw r0
        L_0x0055:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0058:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C12251x9.dispose():void");
    }

    @Override // X.AbstractC12231x7
    public final boolean A95(AbstractC12271xB r2) {
        if (!A2Z(r2)) {
            return false;
        }
        r2.dispose();
        return true;
    }
}
