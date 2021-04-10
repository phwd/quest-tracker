package X;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;

/* renamed from: X.0GN  reason: invalid class name */
public final class AnonymousClass0GN extends AnonymousClass0LY {
    public final C05280ix A00;

    @Override // X.AnonymousClass0LY
    public final boolean A07(Context context, ComponentInfo componentInfo) {
        boolean z;
        ApplicationInfo applicationInfo = componentInfo.applicationInfo;
        if (applicationInfo == null) {
            super.A00.A7Q("ThirdPartyIntentScope", "Null application info.", null);
            return false;
        }
        try {
            z = this.A00.A02(applicationInfo.uid, context);
        } catch (SecurityException e) {
            super.A00.A7Q("ThirdPartyIntentScope", AnonymousClass006.A05("Unexpected exception in checking trusted app for ", componentInfo.packageName), e);
            z = false;
            if (AbstractC02840au.A01(this) == AnonymousClass007.A0D) {
                z = true;
            }
        }
        return !z;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0GN(AnonymousClass0i0 r2, AbstractC04970iB r3, C04960iA r4) {
        super(r2, r3, r4);
        C05280ix A002 = C05290iy.A00();
        this.A00 = A002;
    }
}
