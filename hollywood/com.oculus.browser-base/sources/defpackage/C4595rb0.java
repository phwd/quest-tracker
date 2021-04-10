package defpackage;

import java.util.LinkedHashMap;
import java.util.Locale;

/* renamed from: rb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4595rb0 {

    /* renamed from: a  reason: collision with root package name */
    public final LinkedHashMap f11205a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;

    public C4595rb0(int i) {
        if (i > 0) {
            this.c = i;
            this.f11205a = new LinkedHashMap(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public Object a(Object obj) {
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        r0 = a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        if (r0 != null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r3.e++;
        r1 = r3.f11205a.put(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        if (r1 == null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        r3.f11205a.put(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        r3.b += d(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        if (r1 == null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        f(r3.c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object b(java.lang.Object r4) {
        /*
            r3 = this;
            java.lang.String r0 = "key == null"
            java.util.Objects.requireNonNull(r4, r0)
            monitor-enter(r3)
            java.util.LinkedHashMap r0 = r3.f11205a     // Catch:{ all -> 0x0050 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x0016
            int r4 = r3.g     // Catch:{ all -> 0x0050 }
            int r4 = r4 + 1
            r3.g = r4     // Catch:{ all -> 0x0050 }
            monitor-exit(r3)     // Catch:{ all -> 0x0050 }
            return r0
        L_0x0016:
            int r0 = r3.h     // Catch:{ all -> 0x0050 }
            int r0 = r0 + 1
            r3.h = r0     // Catch:{ all -> 0x0050 }
            monitor-exit(r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r0 = r3.a(r4)
            if (r0 != 0) goto L_0x0025
            r4 = 0
            return r4
        L_0x0025:
            monitor-enter(r3)
            int r1 = r3.e     // Catch:{ all -> 0x004d }
            int r1 = r1 + 1
            r3.e = r1     // Catch:{ all -> 0x004d }
            java.util.LinkedHashMap r1 = r3.f11205a     // Catch:{ all -> 0x004d }
            java.lang.Object r1 = r1.put(r4, r0)     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x003a
            java.util.LinkedHashMap r2 = r3.f11205a     // Catch:{ all -> 0x004d }
            r2.put(r4, r1)     // Catch:{ all -> 0x004d }
            goto L_0x0043
        L_0x003a:
            int r2 = r3.b     // Catch:{ all -> 0x004d }
            int r4 = r3.d(r4, r0)     // Catch:{ all -> 0x004d }
            int r2 = r2 + r4
            r3.b = r2     // Catch:{ all -> 0x004d }
        L_0x0043:
            monitor-exit(r3)     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x0047
            return r1
        L_0x0047:
            int r4 = r3.c
            r3.f(r4)
            return r0
        L_0x004d:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x0050:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4595rb0.b(java.lang.Object):java.lang.Object");
    }

    public final Object c(Object obj, Object obj2) {
        Object put;
        if (obj == null || obj2 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.d++;
            this.b += d(obj, obj2);
            put = this.f11205a.put(obj, obj2);
            if (put != null) {
                this.b -= d(obj, put);
            }
        }
        f(this.c);
        return put;
    }

    public final int d(Object obj, Object obj2) {
        int e2 = e(obj, obj2);
        if (e2 >= 0) {
            return e2;
        }
        throw new IllegalStateException("Negative size: " + obj + "=" + obj2);
    }

    public int e(Object obj, Object obj2) {
        return 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006c, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void f(int r4) {
        /*
        // Method dump skipped, instructions count: 112
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4595rb0.f(int):void");
    }

    public final synchronized String toString() {
        int i;
        int i2;
        i = this.g;
        i2 = this.h + i;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.c), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(i2 != 0 ? (i * 100) / i2 : 0));
    }
}
