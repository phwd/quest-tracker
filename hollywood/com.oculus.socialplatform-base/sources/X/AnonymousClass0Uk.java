package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.facebook.annotations.OkToExtend;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

@OkToExtend
/* renamed from: X.0Uk  reason: invalid class name */
public class AnonymousClass0Uk extends AbstractC03020km {
    public final String A00 = "FamilyIntentScope";
    public final AnonymousClass0kO A01;
    public final boolean A02;

    public AnonymousClass0Uk(C02580jL r3, AbstractC02660jW r4, C02650jV r5, AnonymousClass0kO r6) {
        super(r3, r4, r5);
        this.A01 = r6;
        this.A02 = true;
    }

    @Nullable
    private Intent A00(Intent intent, Context context, @Nullable String str, List<? extends ComponentInfo> list) {
        if (this.A02) {
            AnonymousClass0kG.A02(intent, context, str, super.A00);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (ComponentInfo componentInfo : list) {
            if (A01(context, componentInfo.applicationInfo)) {
                arrayList.add(componentInfo);
            }
        }
        if (arrayList.isEmpty()) {
            super.A00.report(this.A00, "No matching packages available.", null);
            return null;
        }
        ComponentInfo componentInfo2 = (ComponentInfo) arrayList.get(0);
        if (arrayList.size() > 1) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ComponentInfo componentInfo3 = (ComponentInfo) it.next();
                try {
                    String str2 = componentInfo3.packageName;
                    ApplicationInfo applicationInfo = AnonymousClass0kC.A01(context, context.getPackageName()).applicationInfo;
                    if (applicationInfo != null) {
                        ApplicationInfo applicationInfo2 = AnonymousClass0kC.A01(context, str2).applicationInfo;
                        if (applicationInfo2 == null) {
                            throw new AnonymousClass0kT(str2);
                        } else if (!AnonymousClass0kC.A06(context, applicationInfo.uid, applicationInfo2.uid)) {
                            componentInfo2 = componentInfo3;
                            break;
                        }
                    } else {
                        throw new AnonymousClass0kT(context.getPackageName());
                    }
                } catch (SecurityException e) {
                    super.A00.report(this.A00, AnonymousClass006.A07("Error verifying the signature for ", componentInfo3.packageName), e);
                }
            }
        }
        intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
        return intent;
    }

    private boolean A01(Context context, ApplicationInfo applicationInfo) {
        boolean z;
        String str = applicationInfo.packageName;
        AnonymousClass0kO r2 = this.A01;
        if (TextUtils.isEmpty(str) || !r2.A01.isEmpty()) {
            z = true;
        } else {
            Iterator<Set<String>> it = r2.A00.values().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().contains(str)) {
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        if (!z) {
            if (A07()) {
                super.A00.report(this.A00, AnonymousClass006.A07(str, " is not an app matching the targeted app filter, but fail-open."), null);
            }
            return false;
        }
        try {
            if (!r2.A04(AnonymousClass0kO.A00(applicationInfo.uid, context), context)) {
                if (A07()) {
                    super.A00.report(this.A00, AnonymousClass006.A07(str, " is not an app matching the targeted app filter, but fail-open."), null);
                    return true;
                }
                return false;
            }
        } catch (SecurityException e) {
            super.A00.report(this.A00, AnonymousClass006.A07("Unexpected exception in checking trusted app for ", str), e);
            boolean z2 = false;
            if (AbstractC03020km.A02(this) == AnonymousClass007.A04) {
                z2 = true;
            }
            return !z2;
        }
        return true;
    }

    @Override // X.AbstractC03020km
    public final boolean A08(Context context, PackageInfo packageInfo) {
        return A01(context, packageInfo.applicationInfo);
    }

    @Override // X.AnonymousClass0jJ
    public final List<Intent> A2o(Intent intent, Context context, @Nullable String str) {
        ArrayList<PackageInfo> arrayList;
        List<PackageInfo> list;
        if (this.A02) {
            AnonymousClass0kG.A02(intent, context, str, super.A00);
        }
        ArrayList arrayList2 = new ArrayList(1);
        String str2 = intent.getPackage();
        if (str2 != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str2, 64);
                if (packageInfo != null) {
                    list = Arrays.asList(packageInfo);
                } else {
                    list = new ArrayList<>();
                }
            } catch (PackageManager.NameNotFoundException | RuntimeException e) {
                super.A00.report("BaseIntentScope", "Error querying PackageManager.", e);
                arrayList = new ArrayList();
            }
        } else {
            list = context.getPackageManager().getInstalledPackages(64);
        }
        arrayList = new ArrayList(1);
        for (PackageInfo packageInfo2 : list) {
            if (A08(context, packageInfo2)) {
                arrayList.add(packageInfo2);
            }
        }
        for (PackageInfo packageInfo3 : arrayList) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(packageInfo3.packageName);
            arrayList2.add(intent2);
        }
        if (arrayList2.isEmpty()) {
            super.A00.report(this.A00, "No matching packages available.", null);
        }
        return arrayList2;
    }

    @Override // X.AnonymousClass0jJ
    @Nullable
    public final Intent A2n(Intent intent, Context context, @Nullable String str) {
        return A00(intent, context, str, AbstractC03020km.A04(intent, context, 65600));
    }

    @Override // X.AnonymousClass0jJ
    @Nullable
    public final Intent A2q(Intent intent, Context context, @Nullable String str) {
        int i;
        int i2 = context.getApplicationInfo().uid;
        AnonymousClass0k7 A002 = AnonymousClass0kG.A00(context, intent, false, null);
        if (A002 == null) {
            i = -1;
        } else {
            i = A002.A00;
        }
        if (this.A01.A04(A002, context)) {
            return intent;
        }
        String format = String.format("Access denied. Process %d cannot receive broadcasts from %d", Integer.valueOf(i2), Integer.valueOf(i));
        super.A00.report(this.A00, format, new SecurityException(format));
        return null;
    }

    @Override // X.AnonymousClass0jJ
    @Nullable
    public final Intent A2r(Intent intent, Context context, @Nullable String str) {
        return A00(intent, context, str, AbstractC03020km.A05(intent, context, 65600));
    }
}
