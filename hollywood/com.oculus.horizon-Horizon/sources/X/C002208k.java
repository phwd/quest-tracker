package X;

import android.content.Context;
import android.content.Intent;
import javax.annotation.Nullable;

/* renamed from: X.08k  reason: invalid class name and case insensitive filesystem */
public final class C002208k extends AbstractC00900Hh {
    public AbstractC02910bj A00;
    public AbstractC02910bj A01;

    @Override // X.AbstractC02910bj
    public final boolean A8w(Context context, Object obj, Intent intent, @Nullable AnonymousClass0b1 r6) {
        if (!this.A00.A8w(context, obj, intent, r6) || !this.A01.A8w(context, obj, intent, r6)) {
            return false;
        }
        return true;
    }

    public C002208k(AbstractC02910bj r1, AbstractC02910bj r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
