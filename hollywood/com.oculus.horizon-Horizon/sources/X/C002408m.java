package X;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;

/* renamed from: X.08m  reason: invalid class name and case insensitive filesystem */
public final class C002408m extends AnonymousClass0IH {
    public final C02870bf A00;

    @Override // X.AnonymousClass0IH
    public final boolean A09(Context context, ComponentInfo componentInfo) {
        boolean z;
        ApplicationInfo applicationInfo = componentInfo.applicationInfo;
        if (applicationInfo == null) {
            super.A00.report("ThirdPartyIntentScope", "Null application info.", null);
            return false;
        }
        try {
            z = this.A00.A07(C02870bf.A00(applicationInfo.uid, context), context);
        } catch (SecurityException e) {
            super.A00.report("ThirdPartyIntentScope", AnonymousClass006.A05("Unexpected exception in checking trusted app for ", componentInfo.packageName), e);
            z = false;
            if (AnonymousClass0iD.A02(this) == AnonymousClass007.A0D) {
                z = true;
            }
        }
        return !z;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C002408m(C02620au r2, AnonymousClass0b1 r3, C02660b0 r4) {
        super(r2, r3, r4);
        C02870bf A002 = C02880bg.A00();
        this.A00 = A002;
    }
}
