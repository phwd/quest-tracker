package X;

import androidx.annotation.RestrictTo;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0Fx  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01280Fx {
    public final AnonymousClass0Fr mDatabase;
    public final AtomicBoolean mLock = new AtomicBoolean(false);
    public volatile AbstractC03360cA mStmt;

    public abstract String A01();

    public final AbstractC03360cA A00() {
        this.mDatabase.A03();
        if (this.mLock.compareAndSet(false, true)) {
            if (this.mStmt == null) {
                String A01 = A01();
                AnonymousClass0Fr r0 = this.mDatabase;
                r0.A03();
                r0.A04();
                this.mStmt = r0.mOpenHelper.A4y().A1l(A01);
            }
            return this.mStmt;
        }
        String A012 = A01();
        AnonymousClass0Fr r02 = this.mDatabase;
        r02.A03();
        r02.A04();
        return r02.mOpenHelper.A4y().A1l(A012);
    }

    public final void A02(AbstractC03360cA r3) {
        if (r3 == this.mStmt) {
            this.mLock.set(false);
        }
    }

    public AbstractC01280Fx(AnonymousClass0Fr r3) {
        this.mDatabase = r3;
    }
}
