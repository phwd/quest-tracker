package X;

import com.facebook.infer.annotation.FalseOnNull;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.PropagatesNullable;
import java.io.Closeable;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qa  reason: invalid class name */
public abstract class AnonymousClass1qa<T> implements Cloneable, Closeable {
    public static final AnonymousClass1ou<Closeable> A04 = new AnonymousClass1sh();
    public static final AbstractC10391sj A05 = new C09771ox();
    @GuardedBy("this")
    public boolean A00 = false;
    public final AbstractC10391sj A01;
    public final C09961qf<T> A02;
    @Nullable
    public final Throwable A03;

    public final synchronized T A04() {
        T A012;
        boolean z = false;
        if (!this.A00) {
            z = true;
        }
        AnonymousClass0KU.A03(z);
        A012 = this.A02.A01();
        if (A012 == null) {
            throw null;
        }
        return A012;
    }

    public final synchronized boolean A05() {
        return !this.A00;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        X.C09961qf.A00(r3);
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        if (r3.A00 <= 0) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        X.AnonymousClass0KU.A01(java.lang.Boolean.valueOf(r0));
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
        r6 = r3.A01;
        r3.A01 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002d, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        if (r6 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        r3.A02.A86(r6);
        r5 = X.C09961qf.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0037, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r0 = r5.get(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003e, code lost:
        if (r0 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0040, code lost:
        X.C01080Kb.A06("SharedReference", "No entry in sLiveObjects for value of type %s", r6.getClass());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0050, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0052, code lost:
        r0 = r0.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0056, code lost:
        if (r0 != 1) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0058, code lost:
        r5.remove(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005c, code lost:
        r5.put(r6, java.lang.Integer.valueOf(r0 - 1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0065, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000b, code lost:
        r3 = r7.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        monitor-enter(r3);
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qa.close():void");
    }

    @Override // java.lang.Object
    public void finalize() throws Throwable {
        boolean z;
        try {
            synchronized (this) {
                z = this.A00;
            }
            if (!z) {
                this.A01.A8G(this.A02, this.A03);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    @Nullable
    public static <T> AnonymousClass1qa<T> A00(@Nullable AnonymousClass1qa<T> r1) {
        AnonymousClass1qa<T> r0;
        if (r1 == null) {
            return null;
        }
        synchronized (r1) {
            if (r1.A05()) {
                r0 = r1.clone();
            } else {
                r0 = null;
            }
        }
        return r0;
    }

    public static <T> AnonymousClass1qa<T> A01(@PropagatesNullable T t, AnonymousClass1ou<T> r4) {
        AbstractC10391sj r2 = A05;
        Throwable th = null;
        if (t == null) {
            return null;
        }
        if (r2.A8M()) {
            th = new Throwable();
        }
        return new C09941qc(t, r4, r2, th);
    }

    @FalseOnNull
    public static boolean A02(@Nullable AnonymousClass1qa<?> r1) {
        if (r1 == null || !r1.A05()) {
            return false;
        }
        return true;
    }

    /* renamed from: A03 */
    public final AnonymousClass1qa<T> clone() {
        Throwable th;
        AnonymousClass0KU.A03(A05());
        C09961qf<T> r3 = this.A02;
        AbstractC10391sj r2 = this.A01;
        Throwable th2 = this.A03;
        if (th2 != null) {
            th = new Throwable(th2);
        } else {
            th = null;
        }
        return new C09941qc(r3, r2, th);
    }

    public AnonymousClass1qa(C09961qf<T> r2, AbstractC10391sj r3, @Nullable Throwable th) {
        if (r2 != null) {
            this.A02 = r2;
            synchronized (r2) {
                C09961qf.A00(r2);
                r2.A00++;
            }
            this.A01 = r3;
            this.A03 = th;
            return;
        }
        throw null;
    }

    public AnonymousClass1qa(T t, AnonymousClass1ou<T> r3, AbstractC10391sj r4, @Nullable Throwable th) {
        this.A02 = new C09961qf<>(t, r3);
        this.A01 = r4;
        this.A03 = th;
    }
}
