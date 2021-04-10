package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1PM  reason: invalid class name */
public final class AnonymousClass1PM {
    public Context A00;

    public final AnonymousClass1PC A00(AnonymousClass1PL r5) {
        Context context = this.A00;
        String A07 = AnonymousClass006.A07(AnonymousClass1PL.RTI_PREFIX, r5.getName());
        int i = 0;
        if (r5.isMultiProc()) {
            i = 4;
        }
        return new AnonymousClass1PC(context.getSharedPreferences(A07, i));
    }

    public AnonymousClass1PM(Context context) {
        this.A00 = context;
    }
}
