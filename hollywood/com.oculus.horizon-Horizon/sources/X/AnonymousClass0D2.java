package X;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: X.0D2  reason: invalid class name */
public final class AnonymousClass0D2 implements Closeable {
    public boolean A00;
    public boolean A01;
    public final Object A02 = new Object();
    public final List<AnonymousClass0D0> A03 = new ArrayList();
    public final ScheduledExecutorService A04 = AnonymousClass0Cy.A03.A02;

    public static void A00(AnonymousClass0D2 r1) {
        if (r1.A01) {
            throw new IllegalStateException("Object already closed");
        }
    }

    public final AnonymousClass0Cz A01() {
        AnonymousClass0Cz r0;
        synchronized (this.A02) {
            A00(this);
            r0 = new AnonymousClass0Cz(this);
        }
        return r0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r1.hasNext() == false) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        ((X.AnonymousClass0D0) r1.next()).A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r1 = r0.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A02() {
        /*
            r3 = this;
            java.lang.Object r2 = r3.A02
            monitor-enter(r2)
            A00(r3)     // Catch:{ all -> 0x002c }
            boolean r0 = r3.A00     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r2)     // Catch:{ all -> 0x002c }
            return
        L_0x000c:
            r0 = 1
            r3.A00 = r0     // Catch:{ all -> 0x002c }
            java.util.List<X.0D0> r1 = r3.A03     // Catch:{ all -> 0x002c }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x002c }
            r0.<init>(r1)     // Catch:{ all -> 0x002c }
            monitor-exit(r2)     // Catch:{ all -> 0x002c }
            java.util.Iterator r1 = r0.iterator()
        L_0x001b:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r1.next()
            X.0D0 r0 = (X.AnonymousClass0D0) r0
            r0.A00()
            goto L_0x001b
        L_0x002b:
            return
        L_0x002c:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0D2.A02():void");
    }

    public final boolean A03() {
        boolean z;
        synchronized (this.A02) {
            A00(this);
            z = this.A00;
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.A02) {
            if (!this.A01) {
                List<AnonymousClass0D0> list = this.A03;
                for (AnonymousClass0D0 r0 : list) {
                    r0.close();
                }
                list.clear();
                this.A01 = true;
            }
        }
    }

    public final String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(A03()));
    }
}
