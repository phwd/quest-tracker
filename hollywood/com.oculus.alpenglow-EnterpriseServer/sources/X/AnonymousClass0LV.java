package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.facebook.annotations.OkToExtend;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

@OkToExtend
/* renamed from: X.0LV  reason: invalid class name */
public class AnonymousClass0LV extends AbstractC02840au {
    public final String A00;
    public final boolean A01 = true;
    public final C05280ix A02;

    public AnonymousClass0LV(AnonymousClass0i0 r2, AbstractC04970iB r3, C04960iA r4, C05280ix r5, String str) {
        super(r2, r3, r4);
        this.A00 = str;
        this.A02 = r5;
    }

    private boolean A00(Context context, ApplicationInfo applicationInfo) {
        boolean z;
        String str = applicationInfo.packageName;
        C05280ix r2 = this.A02;
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
            if (A05()) {
                super.A00.A7Q(this.A00, AnonymousClass006.A05(str, " is not an app matching the targeted app filter, but fail-open."), null);
            }
            return false;
        }
        try {
            if (!r2.A02(applicationInfo.uid, context)) {
                if (A05()) {
                    super.A00.A7Q(this.A00, AnonymousClass006.A05(str, " is not an app matching the targeted app filter, but fail-open."), null);
                    return true;
                }
                return false;
            }
        } catch (SecurityException e) {
            super.A00.A7Q(this.A00, AnonymousClass006.A05("Unexpected exception in checking trusted app for ", str), e);
            boolean z2 = false;
            if (AbstractC02840au.A01(this) == AnonymousClass007.A0D) {
                z2 = true;
            }
            return !z2;
        }
        return true;
    }

    @Override // X.AbstractC02840au
    public final boolean A06(Context context, PackageInfo packageInfo) {
        return A00(context, packageInfo.applicationInfo);
    }

    @Override // X.AbstractC04930hy
    public final List<Intent> A2L(Intent intent, Context context, @Nullable String str) {
        ArrayList<PackageInfo> arrayList;
        List<PackageInfo> list;
        if (this.A01) {
            C05200ip.A01(intent, context, str, super.A00);
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
                super.A00.A7Q("BaseIntentScope", "Error querying PackageManager.", e);
                arrayList = new ArrayList();
            }
        } else {
            list = context.getPackageManager().getInstalledPackages(64);
        }
        arrayList = new ArrayList(1);
        for (PackageInfo packageInfo2 : list) {
            if (A06(context, packageInfo2)) {
                arrayList.add(packageInfo2);
            }
        }
        for (PackageInfo packageInfo3 : arrayList) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(packageInfo3.packageName);
            arrayList2.add(intent2);
        }
        if (arrayList2.isEmpty()) {
            super.A00.A7Q(this.A00, "No matching packages available.", null);
        }
        return arrayList2;
    }

    @Override // X.AbstractC04930hy
    @Nullable
    public final Intent A2N(Intent intent, Context context, @Nullable String str) {
        int i;
        int i2 = context.getApplicationInfo().uid;
        C05130ih A002 = C05200ip.A00(context, intent, false, null);
        if (A002 == null) {
            i = -1;
        } else {
            i = A002.A00;
        }
        if (this.A02.A03(A002, C05120ig.A10.contains(C05180im.A03(context, context.getPackageName())))) {
            return intent;
        }
        String format = String.format("Access denied. Process %d cannot receive broadcasts from %d", Integer.valueOf(i2), Integer.valueOf(i));
        super.A00.A7Q(this.A00, format, new SecurityException(format));
        return null;
    }

    @Override // X.AbstractC04930hy
    @Nullable
    public final Intent A2O(Intent intent, Context context, @Nullable String str) {
        List<ServiceInfo> A03 = AbstractC02840au.A03(intent, context, 65600);
        if (this.A01) {
            C05200ip.A01(intent, context, str, super.A00);
        }
        ArrayList arrayList = new ArrayList(A03.size());
        for (ServiceInfo serviceInfo : A03) {
            if (A00(context, ((ComponentInfo) serviceInfo).applicationInfo)) {
                arrayList.add(serviceInfo);
            }
        }
        if (arrayList.isEmpty()) {
            super.A00.A7Q(this.A00, "No matching packages available.", null);
            return null;
        }
        ComponentInfo componentInfo = (ComponentInfo) arrayList.get(0);
        if (arrayList.size() > 1) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ComponentInfo componentInfo2 = (ComponentInfo) it.next();
                try {
                    String str2 = componentInfo2.packageName;
                    ApplicationInfo applicationInfo = C05180im.A01(context, context.getPackageName()).applicationInfo;
                    if (applicationInfo != null) {
                        ApplicationInfo applicationInfo2 = C05180im.A01(context, str2).applicationInfo;
                        if (applicationInfo2 != null) {
                            int i = applicationInfo.uid;
                            int i2 = applicationInfo2.uid;
                            try {
                                int checkSignatures = context.getPackageManager().checkSignatures(i, i2);
                                if (!(i == i2 || checkSignatures == 0)) {
                                    componentInfo = componentInfo2;
                                    break;
                                }
                            } catch (RuntimeException e) {
                                throw new SecurityException(e);
                            }
                        } else {
                            throw new AnonymousClass0j2(str2);
                        }
                    } else {
                        throw new AnonymousClass0j2(context.getPackageName());
                    }
                } catch (SecurityException e2) {
                    super.A00.A7Q(this.A00, AnonymousClass006.A05("Error verifying the signature for ", componentInfo2.packageName), e2);
                }
            }
        }
        intent.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
        return intent;
    }
}
