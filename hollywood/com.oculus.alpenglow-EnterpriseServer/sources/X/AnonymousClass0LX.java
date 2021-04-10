package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0LX  reason: invalid class name */
public final class AnonymousClass0LX extends AbstractC02840au {
    @Override // X.AbstractC04930hy
    @Nullable
    public final Intent A2N(Intent intent, Context context, @Nullable String str) {
        String A00;
        String str2 = null;
        C05130ih A002 = C05200ip.A00(context, intent, false, null);
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
            if (A05()) {
                this.A00.A7Q("InternalIntentScope", AnonymousClass006.A05("Fail-open: ", format), null);
            } else {
                this.A00.A7Q("InternalIntentScope", format, new SecurityException(format));
                return null;
            }
        }
        return intent;
    }

    @Override // X.AbstractC02840au
    public final boolean A06(Context context, PackageInfo packageInfo) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC04930hy
    public final List<Intent> A2L(Intent intent, Context context, @Nullable String str) {
        C05200ip.A01(intent, context, str, this.A00);
        if (!AbstractC02840au.A04(intent, context)) {
            intent.setPackage(context.getPackageName());
        }
        return Collections.singletonList(intent);
    }

    @Override // X.AbstractC04930hy
    @Nullable
    public final Intent A2O(Intent intent, Context context, @Nullable String str) {
        AbstractC04970iB r3 = this.A00;
        C05200ip.A01(intent, context, str, r3);
        if (!AbstractC02840au.A04(intent, context)) {
            List<ServiceInfo> A03 = AbstractC02840au.A03(intent, context, 65600);
            ArrayList arrayList = new ArrayList(A03.size());
            for (ServiceInfo serviceInfo : A03) {
                ApplicationInfo applicationInfo = ((ComponentInfo) serviceInfo).applicationInfo;
                if (applicationInfo != null) {
                    String str2 = applicationInfo.packageName;
                    if (!str2.equals(context.getPackageName())) {
                        if (A05()) {
                            r3.A7Q("InternalIntentScope", AnonymousClass006.A05("Detected different package name component but fail open: ", str2), null);
                        }
                    }
                    arrayList.add(serviceInfo);
                }
            }
            if (arrayList.isEmpty()) {
                r3.A7Q("InternalIntentScope", "No matching internal components", null);
                return null;
            }
            ComponentInfo componentInfo = (ComponentInfo) arrayList.get(0);
            intent.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
        }
        return intent;
    }

    public AnonymousClass0LX(AnonymousClass0i0 r1, AbstractC04970iB r2, C04960iA r3) {
        super(r1, r2, r3);
    }
}
