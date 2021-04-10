package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0qg  reason: invalid class name and case insensitive filesystem */
public final class C04570qg<T> extends AbstractC00820Ju<T> {
    @Override // X.AbstractC00820Ju, java.lang.Object
    public final void finalize() throws Throwable {
        boolean z;
        String str;
        try {
            synchronized (this) {
                z = this.A00;
            }
            if (!z) {
                C00860Jy<T> r6 = this.A01;
                T A01 = r6.A01();
                Integer valueOf = Integer.valueOf(System.identityHashCode(this));
                Integer valueOf2 = Integer.valueOf(System.identityHashCode(r6));
                if (A01 == null) {
                    str = null;
                } else {
                    str = A01.getClass().getName();
                }
                AnonymousClass0J5.A05("DefaultCloseableReference", "Finalized without closing: %x %x (type = %s)", valueOf, valueOf2, str);
                this.A02.A9G(r6, this.A03);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    @Override // X.AbstractC00820Ju
    public final AbstractC00820Ju<T> A05() {
        Throwable th;
        C00740Ii.A03(A07());
        C00860Jy<T> r3 = this.A01;
        AbstractC00810Jt r2 = this.A02;
        Throwable th2 = this.A03;
        if (th2 != null) {
            th = new Throwable(th2);
        } else {
            th = null;
        }
        return new C04570qg(r3, r2, th);
    }

    public C04570qg(C00860Jy<T> r1, AbstractC00810Jt r2, @Nullable Throwable th) {
        super(r1, r2, th);
    }

    public C04570qg(T t, AbstractC00840Jw<T> r2, AbstractC00810Jt r3, @Nullable Throwable th) {
        super(t, r2, r3, th);
    }
}
