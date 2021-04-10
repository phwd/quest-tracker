package X;

import android.content.Context;
import android.content.Intent;
import javax.annotation.Nullable;

/* renamed from: X.0Hh  reason: invalid class name */
public final class AnonymousClass0Hh extends AnonymousClass0TP {
    public AnonymousClass0kS A00;
    public AnonymousClass0kS A01;

    @Override // X.AnonymousClass0kS
    public final boolean AAL(Context context, Object obj, Intent intent, @Nullable AbstractC02660jW r6) {
        if (!this.A00.AAL(context, obj, intent, r6) || !this.A01.AAL(context, obj, intent, r6)) {
            return false;
        }
        return true;
    }

    public AnonymousClass0Hh(AnonymousClass0kS r1, AnonymousClass0kS r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
