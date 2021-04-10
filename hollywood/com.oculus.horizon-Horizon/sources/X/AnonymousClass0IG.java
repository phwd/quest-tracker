package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0IG  reason: invalid class name */
public final class AnonymousClass0IG extends AnonymousClass0iD {
    @Override // X.AnonymousClass0iD
    public final boolean A08(Context context, PackageInfo packageInfo) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2S(Intent intent, Context context, @Nullable String str) {
        C02800bY.A03(intent, context, str, this.A00);
        if (!AnonymousClass0iD.A06(intent, context)) {
            return A00(intent, context, AnonymousClass0iD.A04(intent, context, 65600));
        }
        return intent;
    }

    @Override // X.AbstractC02610as
    public final List<Intent> A2T(Intent intent, Context context, @Nullable String str) {
        C02800bY.A03(intent, context, str, this.A00);
        if (!AnonymousClass0iD.A06(intent, context)) {
            intent.setPackage(context.getPackageName());
        }
        return Collections.singletonList(intent);
    }

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2X(Intent intent, Context context, @Nullable String str) {
        C02800bY.A03(intent, context, str, this.A00);
        if (!AnonymousClass0iD.A06(intent, context)) {
            return A00(intent, context, AnonymousClass0iD.A05(intent, context, 65600));
        }
        return intent;
    }

    @Nullable
    private Intent A00(Intent intent, Context context, List<? extends ComponentInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ComponentInfo componentInfo : list) {
            ApplicationInfo applicationInfo = componentInfo.applicationInfo;
            if (applicationInfo != null) {
                String str = applicationInfo.packageName;
                if (!str.equals(context.getPackageName())) {
                    if (A07()) {
                        this.A00.report("InternalIntentScope", AnonymousClass006.A05("Detected different package name component but fail open: ", str), null);
                    }
                }
                arrayList.add(componentInfo);
            }
        }
        if (arrayList.isEmpty()) {
            this.A00.report("InternalIntentScope", "No matching internal components", null);
            return null;
        }
        ComponentInfo componentInfo2 = (ComponentInfo) arrayList.get(0);
        intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
        return intent;
    }

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2W(Intent intent, Context context, @Nullable String str) {
        String str2;
        C02790bO A00 = C02800bY.A00(context, intent);
        if (A00 != null) {
            str2 = A00.A01();
        } else {
            str2 = null;
        }
        String packageName = context.getPackageName();
        if (!packageName.equals(str2)) {
            String format = String.format("Access denied. %s cannot receive broadcasts from %s", packageName, C02790bO.A00(A00));
            if (A07()) {
                this.A00.report("InternalIntentScope", AnonymousClass006.A05("Fail-open: ", format), null);
            } else {
                this.A00.report("InternalIntentScope", format, new SecurityException(format));
                return null;
            }
        }
        return intent;
    }

    public AnonymousClass0IG(C02620au r1, AnonymousClass0b1 r2, C02660b0 r3) {
        super(r1, r2, r3);
    }
}
