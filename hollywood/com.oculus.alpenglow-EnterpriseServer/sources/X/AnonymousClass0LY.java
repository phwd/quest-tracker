package X;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"InstanceMethodCanBeStatic"})
/* renamed from: X.0LY  reason: invalid class name */
public abstract class AnonymousClass0LY extends AbstractC02840au {
    public abstract boolean A07(Context context, ComponentInfo componentInfo);

    @Override // X.AbstractC02840au
    public final boolean A06(Context context, PackageInfo packageInfo) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC04930hy
    public final List<Intent> A2L(Intent intent, Context context, @Nullable String str) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC04930hy
    @Nullable
    public final Intent A2N(Intent intent, Context context, @Nullable String str) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC04930hy
    @Nullable
    public final Intent A2O(Intent intent, Context context, @Nullable String str) {
        String str2;
        if (AbstractC02840au.A04(intent, context)) {
            return null;
        }
        List<ServiceInfo> A03 = AbstractC02840au.A03(intent, context, 65600);
        if (A03.isEmpty()) {
            A03 = AbstractC02840au.A03(intent, context, 0);
        }
        ArrayList<ComponentInfo> arrayList = new ArrayList(A03.size());
        for (ServiceInfo serviceInfo : A03) {
            if (A07(context, serviceInfo)) {
                ApplicationInfo applicationInfo = ((ComponentInfo) serviceInfo).applicationInfo;
                if (applicationInfo == null || !"com.android.internal.app.ResolverActivity".equals(applicationInfo.className)) {
                    arrayList.add(serviceInfo);
                } else if (A05() || AbstractC02840au.A01(this) == AnonymousClass007.A01) {
                    arrayList.add(serviceInfo);
                    str2 = "Found potentially dangerous resolver but not removing: ";
                } else {
                    str2 = "Removed potentially dangerous resolver: ";
                }
            } else if (A05()) {
                arrayList.add(serviceInfo);
                str2 = "Non-external/third-party component detected, but allowing because of fail-open: ";
            } else {
                str2 = "Removed non-external/third-party component: ";
            }
            this.A00.A7Q("DifferentKeyIntentScope", AnonymousClass006.A05(str2, AbstractC02840au.A02(intent)), null);
        }
        if (!arrayList.isEmpty()) {
            Intent intent2 = intent;
            if (arrayList.size() != A03.size()) {
                if (arrayList.size() > 1) {
                    ArrayList arrayList2 = new ArrayList(arrayList.size());
                    for (ComponentInfo componentInfo : arrayList) {
                        Intent intent3 = new Intent(intent);
                        intent3.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
                        intent3.setPackage(componentInfo.packageName);
                        arrayList2.add(intent3);
                    }
                    Parcelable[] parcelableArr = new Intent[(arrayList2.size() - 1)];
                    int i = 0;
                    while (i < arrayList2.size() - 1) {
                        int i2 = i + 1;
                        parcelableArr[i] = arrayList2.get(i2);
                        i = i2;
                    }
                    Intent createChooser = Intent.createChooser((Intent) arrayList2.get(0), "Choose an app to launch.");
                    createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", parcelableArr);
                    intent2 = createChooser;
                } else {
                    ComponentInfo componentInfo2 = (ComponentInfo) arrayList.get(0);
                    intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
                    intent2 = intent;
                }
            }
            C04920hx.A00(intent2, this.A00, A05());
            return intent2;
        } else if (Build.VERSION.SDK_INT < 30 || !A03.isEmpty()) {
            this.A00.A7Q("DifferentKeyIntentScope", AnonymousClass006.A05("No matching different-signature components for: ", AbstractC02840au.A02(intent)), null);
            return null;
        } else {
            AbstractC04970iB r2 = this.A00;
            r2.A7Q("DifferentKeyIntentScope", AnonymousClass006.A07("No matching different-signature components for: ", AbstractC02840au.A02(intent), " on API 30+ device. Intent target is not in any PackageFinder aware app, so it's probably a non-FB app. Attempting to proceed."), null);
            C04920hx.A00(intent, r2, A05());
            return intent;
        }
    }

    public AnonymousClass0LY(AnonymousClass0i0 r1, AbstractC04970iB r2, C04960iA r3) {
        super(r1, r2, r3);
    }
}
