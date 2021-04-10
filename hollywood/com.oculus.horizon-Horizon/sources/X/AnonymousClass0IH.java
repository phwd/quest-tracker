package X;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"InstanceMethodCanBeStatic"})
/* renamed from: X.0IH  reason: invalid class name */
public abstract class AnonymousClass0IH extends AnonymousClass0iD {
    public abstract boolean A09(Context context, ComponentInfo componentInfo);

    @Override // X.AnonymousClass0iD
    public final boolean A08(Context context, PackageInfo packageInfo) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC02610as
    public final List<Intent> A2T(Intent intent, Context context, @Nullable String str) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2W(Intent intent, Context context, @Nullable String str) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    private Intent A00(Intent intent, Context context, List<? extends ComponentInfo> list) {
        AnonymousClass0b1 r8;
        Intent intent2;
        Object[] objArr;
        String str;
        String str2;
        ArrayList<ComponentInfo> arrayList = new ArrayList(list.size());
        for (ComponentInfo componentInfo : list) {
            if (A09(context, componentInfo)) {
                ApplicationInfo applicationInfo = componentInfo.applicationInfo;
                if (applicationInfo == null || !"com.android.internal.app.ResolverActivity".equals(applicationInfo.className)) {
                    arrayList.add(componentInfo);
                } else if (A07() || AnonymousClass0iD.A02(this) == AnonymousClass007.A01) {
                    arrayList.add(componentInfo);
                    str2 = "Found potentially dangerous resolver but not removing: ";
                } else {
                    str2 = "Removed potentially dangerous resolver: ";
                }
            } else if (A07()) {
                arrayList.add(componentInfo);
                str2 = "Non-external/third-party component detected, but allowing because of fail-open: ";
            } else {
                str2 = "Removed non-external/third-party component: ";
            }
            this.A00.report("DifferentKeyIntentScope", AnonymousClass006.A05(str2, AnonymousClass0iD.A03(intent)), null);
        }
        if (!arrayList.isEmpty()) {
            Intent intent3 = intent;
            if (arrayList.size() != list.size()) {
                if (arrayList.size() > 1) {
                    ArrayList arrayList2 = new ArrayList(arrayList.size());
                    for (ComponentInfo componentInfo2 : arrayList) {
                        Intent intent4 = new Intent(intent);
                        intent4.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
                        intent4.setPackage(componentInfo2.packageName);
                        arrayList2.add(intent4);
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
                    intent3 = createChooser;
                } else {
                    ComponentInfo componentInfo3 = (ComponentInfo) arrayList.get(0);
                    intent.setComponent(new ComponentName(componentInfo3.packageName, componentInfo3.name));
                    intent3 = intent;
                }
            }
            r8 = this.A00;
            intent2 = intent3;
        } else if (Build.VERSION.SDK_INT < 30 || !list.isEmpty()) {
            this.A00.report("DifferentKeyIntentScope", AnonymousClass006.A05("No matching different-signature components for: ", AnonymousClass0iD.A03(intent)), null);
            return null;
        } else {
            r8 = this.A00;
            r8.report("DifferentKeyIntentScope", AnonymousClass006.A07("No matching different-signature components for: ", AnonymousClass0iD.A03(intent), " on API 30+ device. Intent target is not in any PackageFinder aware app, so it's probably a non-FB app. Attempting to proceed."), null);
            intent2 = intent;
        }
        boolean A07 = A07();
        if (intent2.getExtras() != null) {
            Bundle extras = intent2.getExtras();
            Set<String> keySet = extras.keySet();
            ArrayList arrayList3 = new ArrayList();
            for (String str3 : keySet) {
                Object obj = extras.get(str3);
                if (obj != null && AnonymousClass08q.class.getClassLoader().equals(obj.getClass().getClassLoader())) {
                    arrayList3.add(str3);
                }
            }
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (!A07) {
                    intent2.removeExtra(str4);
                    objArr = new Object[]{str4, extras.get(str4)};
                    str = "Removed an internal class in a different-key intent: %s => %s";
                } else {
                    objArr = new Object[]{str4, extras.get(str4)};
                    str = "Found an internal class in a different-key intent but not removing: %s => %s";
                }
                r8.report("ExternalIntentSanitization", String.format(str, objArr), null);
            }
        }
        return intent2;
    }

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2S(Intent intent, Context context, @Nullable String str) {
        if (AnonymousClass0iD.A06(intent, context)) {
            return null;
        }
        List<ActivityInfo> A04 = AnonymousClass0iD.A04(intent, context, 65600);
        if (A04.isEmpty()) {
            A04 = AnonymousClass0iD.A04(intent, context, 0);
        }
        return A00(intent, context, A04);
    }

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2X(Intent intent, Context context, @Nullable String str) {
        if (AnonymousClass0iD.A06(intent, context)) {
            return null;
        }
        List<ServiceInfo> A05 = AnonymousClass0iD.A05(intent, context, 65600);
        if (A05.isEmpty()) {
            A05 = AnonymousClass0iD.A05(intent, context, 0);
        }
        return A00(intent, context, A05);
    }

    public AnonymousClass0IH(C02620au r1, AnonymousClass0b1 r2, C02660b0 r3) {
        super(r1, r2, r3);
    }
}
