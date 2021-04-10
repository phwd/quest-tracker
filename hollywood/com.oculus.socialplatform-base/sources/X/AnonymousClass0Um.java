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

/* renamed from: X.0Um  reason: invalid class name */
public final class AnonymousClass0Um extends AbstractC03020km {
    @Override // X.AnonymousClass0jJ
    @Nullable
    public final Intent A2q(Intent intent, Context context, @Nullable String str) {
        String A00;
        String str2 = null;
        AnonymousClass0k7 A002 = AnonymousClass0kG.A00(context, intent, false, null);
        if (A002 != null) {
            str2 = A002.A00();
        }
        String packageName = context.getPackageName();
        if (!packageName.equals(str2)) {
            if (A002 == null) {
                A00 = "no_app_identity";
            } else if (A002.A00() == null) {
                A00 = "null";
            } else {
                A00 = A002.A00();
            }
            String format = String.format("Access denied. %s cannot receive broadcasts from %s", packageName, A00);
            if (A07()) {
                this.A00.report("InternalIntentScope", AnonymousClass006.A07("Fail-open: ", format), null);
            } else {
                this.A00.report("InternalIntentScope", format, new SecurityException(format));
                return null;
            }
        }
        return intent;
    }

    @Override // X.AbstractC03020km
    public final boolean A08(Context context, PackageInfo packageInfo) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0jJ
    @Nullable
    public final Intent A2n(Intent intent, Context context, @Nullable String str) {
        AnonymousClass0kG.A02(intent, context, str, this.A00);
        if (!AbstractC03020km.A06(intent, context)) {
            return A00(intent, context, AbstractC03020km.A04(intent, context, 65600));
        }
        return intent;
    }

    @Override // X.AnonymousClass0jJ
    public final List<Intent> A2o(Intent intent, Context context, @Nullable String str) {
        AnonymousClass0kG.A02(intent, context, str, this.A00);
        if (!AbstractC03020km.A06(intent, context)) {
            intent.setPackage(context.getPackageName());
        }
        return Collections.singletonList(intent);
    }

    @Override // X.AnonymousClass0jJ
    @Nullable
    public final Intent A2r(Intent intent, Context context, @Nullable String str) {
        AnonymousClass0kG.A02(intent, context, str, this.A00);
        if (!AbstractC03020km.A06(intent, context)) {
            return A00(intent, context, AbstractC03020km.A05(intent, context, 65600));
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
                        this.A00.report("InternalIntentScope", AnonymousClass006.A07("Detected different package name component but fail open: ", str), null);
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

    public AnonymousClass0Um(C02580jL r1, AbstractC02660jW r2, C02650jV r3) {
        super(r1, r2, r3);
    }
}
