package X;

import com.facebook.infer.annotation.FalseOnNull;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.PropagatesNullable;
import java.io.Closeable;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Ju  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00820Ju<T> implements Cloneable, Closeable {
    public static final AbstractC00840Jw<Closeable> A04 = new C04840rO();
    public static final AbstractC00810Jt A05 = new AnonymousClass0rN();
    @GuardedBy("this")
    public boolean A00 = false;
    public final C00860Jy<T> A01;
    public final AbstractC00810Jt A02;
    @Nullable
    public final Throwable A03;

    /* renamed from: A05 */
    public abstract AbstractC00820Ju<T> clone();

    public final synchronized T A06() {
        T A012;
        boolean z = false;
        if (!this.A00) {
            z = true;
        }
        C00740Ii.A03(z);
        A012 = this.A01.A01();
        if (A012 == null) {
            throw null;
        }
        return A012;
    }

    public final synchronized boolean A07() {
        return !this.A00;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        X.C00860Jy.A00(r3);
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        if (r3.A00 <= 0) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        X.C00740Ii.A01(java.lang.Boolean.valueOf(r0));
        r0 = r3.A00 - 1;
        r3.A00 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        if (r0 != 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4 = r3.A01;
        r3.A01 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002d, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        if (r4 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        r3.A02.A8y(r4);
        r3 = X.C00860Jy.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0037, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r0 = r3.get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003e, code lost:
        if (r0 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0040, code lost:
        X.AnonymousClass0J5.A06("SharedReference", "No entry in sLiveObjects for value of type %s", r4.getClass());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004f, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0051, code lost:
        r0 = r0.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0055, code lost:
        if (r0 != 1) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0057, code lost:
        r3.remove(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005b, code lost:
        r3.put(r4, java.lang.Integer.valueOf(r0 - 1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0064, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000b, code lost:
        r3 = r5.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        monitor-enter(r3);
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC00820Ju.close():void");
    }

    @Override // java.lang.Object
    public void finalize() throws Throwable {
        boolean z;
        try {
            synchronized (this) {
                z = this.A00;
            }
            if (!z) {
                this.A02.A9G(this.A01, this.A03);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    @Nullable
    public static <T> AbstractC00820Ju<T> A00(@Nullable AbstractC00820Ju<T> r1) {
        AbstractC00820Ju<T> r0;
        if (r1 == null) {
            return null;
        }
        synchronized (r1) {
            if (r1.A07()) {
                r0 = r1.clone();
            } else {
                r0 = null;
            }
        }
        return r0;
    }

    public static <T> AbstractC00820Ju<T> A01(@PropagatesNullable T t, AbstractC00840Jw<T> r4) {
        AbstractC00810Jt r2 = A05;
        Throwable th = null;
        if (t == null) {
            return null;
        }
        if (r2.A9K()) {
            th = new Throwable();
        }
        return A02(t, r4, r2, th);
    }

    public static <T> AbstractC00820Ju<T> A02(@PropagatesNullable T t, AbstractC00840Jw<T> r2, AbstractC00810Jt r3, @Nullable Throwable th) {
        return new C04570qg(t, r2, r3, th);
    }

    public static void A03(@Nullable AbstractC00820Ju<?> r0) {
        if (r0 != null) {
            r0.close();
        }
    }

    @FalseOnNull
    public static boolean A04(@Nullable AbstractC00820Ju<?> r1) {
        if (r1 == null || !r1.A07()) {
            return false;
        }
        return true;
    }

    public AbstractC00820Ju(C00860Jy<T> r2, AbstractC00810Jt r3, @Nullable Throwable th) {
        if (r2 != null) {
            this.A01 = r2;
            synchronized (r2) {
                C00860Jy.A00(r2);
                r2.A00++;
            }
            this.A02 = r3;
            this.A03 = th;
            return;
        }
        throw null;
    }

    public AbstractC00820Ju(T t, AbstractC00840Jw<T> r3, AbstractC00810Jt r4, @Nullable Throwable th) {
        this.A01 = new C00860Jy<>(t, r3);
        this.A02 = r4;
        this.A03 = th;
    }
}
