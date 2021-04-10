package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qc  reason: invalid class name and case insensitive filesystem */
public final class C09941qc<T> extends AnonymousClass1qa<T> {
    @Override // X.AnonymousClass1qa, java.lang.Object
    public final void finalize() throws Throwable {
        boolean z;
        String str;
        try {
            synchronized (this) {
                z = this.A00;
            }
            if (!z) {
                C09961qf<T> r4 = this.A02;
                T A01 = r4.A01();
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(System.identityHashCode(this));
                objArr[1] = Integer.valueOf(System.identityHashCode(r4));
                if (A01 == null) {
                    str = null;
                } else {
                    str = A01.getClass().getName();
                }
                objArr[2] = str;
                C01080Kb.A05("DefaultCloseableReference", "Finalized without closing: %x %x (type = %s)", objArr);
                this.A01.A8G(r4, this.A03);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    public C09941qc(C09961qf<T> r1, AbstractC10391sj r2, @Nullable Throwable th) {
        super(r1, r2, th);
    }

    public C09941qc(T t, AnonymousClass1ou<T> r2, AbstractC10391sj r3, @Nullable Throwable th) {
        super(t, r2, r3, th);
    }
}
