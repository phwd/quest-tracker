package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vp  reason: invalid class name and case insensitive filesystem */
public final class C07710vp {
    public Context A00;

    public final AnonymousClass0ux A00(EnumC07690vn r5) {
        Context context = this.A00;
        String A05 = AnonymousClass006.A05(EnumC07690vn.RTI_PREFIX, r5.getName());
        int i = 0;
        if (r5.isMultiProc()) {
            i = 4;
        }
        return new C07730vr(context.getSharedPreferences(A05, i));
    }

    public C07710vp(Context context) {
        this.A00 = context;
    }
}
