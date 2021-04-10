package X;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;

/* renamed from: X.0Hi  reason: invalid class name */
public final class AnonymousClass0Hi extends AnonymousClass0Uo {
    public final AnonymousClass0kO A00;

    @Override // X.AnonymousClass0Uo
    public final boolean A09(Context context, ComponentInfo componentInfo) {
        boolean z;
        ApplicationInfo applicationInfo = componentInfo.applicationInfo;
        if (applicationInfo == null) {
            super.A00.report("ThirdPartyIntentScope", "Null application info.", null);
            return false;
        }
        try {
            z = this.A00.A04(AnonymousClass0kO.A00(applicationInfo.uid, context), context);
        } catch (SecurityException e) {
            super.A00.report("ThirdPartyIntentScope", AnonymousClass006.A07("Unexpected exception in checking trusted app for ", componentInfo.packageName), e);
            z = false;
            if (AbstractC03020km.A02(this) == AnonymousClass007.A04) {
                z = true;
            }
        }
        return !z;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Hi(C02580jL r2, AbstractC02660jW r3, C02650jV r4) {
        super(r2, r3, r4);
        AnonymousClass0kO A002 = AnonymousClass0kP.A00();
        this.A00 = A002;
    }
}
