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
/* renamed from: X.0Ho  reason: invalid class name and case insensitive filesystem */
public class C00940Ho extends AnonymousClass0iD {
    public final String A00;
    public final C02870bf A01;
    public final boolean A02 = true;

    public C00940Ho(C02620au r2, AnonymousClass0b1 r3, C02660b0 r4, C02870bf r5, String str) {
        super(r2, r3, r4);
        this.A00 = str;
        this.A01 = r5;
    }

    @Nullable
    private Intent A00(Intent intent, Context context, @Nullable String str, List<? extends ComponentInfo> list) {
        if (this.A02) {
            C02800bY.A03(intent, context, str, super.A00);
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
                    ApplicationInfo applicationInfo = AnonymousClass0bU.A01(context, context.getPackageName()).applicationInfo;
                    if (applicationInfo != null) {
                        ApplicationInfo applicationInfo2 = AnonymousClass0bU.A01(context, str2).applicationInfo;
                        if (applicationInfo2 != null) {
                            int i = applicationInfo.uid;
                            int i2 = applicationInfo2.uid;
                            try {
                                int checkSignatures = context.getPackageManager().checkSignatures(i, i2);
                                if (!(i == i2 || checkSignatures == 0)) {
                                    componentInfo2 = componentInfo3;
                                    break;
                                }
                            } catch (RuntimeException e) {
                                throw new SecurityException(e);
                            }
                        } else {
                            throw new C02920bk(str2);
                        }
                    } else {
                        throw new C02920bk(context.getPackageName());
                    }
                } catch (SecurityException e2) {
                    super.A00.report(this.A00, AnonymousClass006.A05("Error verifying the signature for ", componentInfo3.packageName), e2);
                }
            }
        }
        intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
        return intent;
    }

    private boolean A01(Context context, ApplicationInfo applicationInfo) {
        boolean z;
        String str = applicationInfo.packageName;
        C02870bf r2 = this.A01;
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
                super.A00.report(this.A00, AnonymousClass006.A05(str, " is not an app matching the targeted app filter, but fail-open."), null);
            }
            return false;
        }
        try {
            if (!r2.A07(C02870bf.A00(applicationInfo.uid, context), context)) {
                if (A07()) {
                    super.A00.report(this.A00, AnonymousClass006.A05(str, " is not an app matching the targeted app filter, but fail-open."), null);
                    return true;
                }
                return false;
            }
        } catch (SecurityException e) {
            super.A00.report(this.A00, AnonymousClass006.A05("Unexpected exception in checking trusted app for ", str), e);
            boolean z2 = false;
            if (AnonymousClass0iD.A02(this) == AnonymousClass007.A0D) {
                z2 = true;
            }
            return !z2;
        }
        return true;
    }

    @Override // X.AnonymousClass0iD
    public final boolean A08(Context context, PackageInfo packageInfo) {
        return A01(context, packageInfo.applicationInfo);
    }

    @Override // X.AbstractC02610as
    public final List<Intent> A2T(Intent intent, Context context, @Nullable String str) {
        ArrayList<PackageInfo> arrayList;
        List<PackageInfo> list;
        if (this.A02) {
            C02800bY.A03(intent, context, str, super.A00);
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

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2S(Intent intent, Context context, @Nullable String str) {
        return A00(intent, context, str, AnonymousClass0iD.A04(intent, context, 65600));
    }

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2W(Intent intent, Context context, @Nullable String str) {
        int i;
        int i2 = context.getApplicationInfo().uid;
        C02790bO A002 = C02800bY.A00(context, intent);
        if (A002 == null) {
            i = -1;
        } else {
            i = A002.A00;
        }
        if (this.A01.A07(A002, context)) {
            return intent;
        }
        String format = String.format("Access denied. Process %d cannot receive broadcasts from %d", Integer.valueOf(i2), Integer.valueOf(i));
        super.A00.report(this.A00, format, new SecurityException(format));
        return null;
    }

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2X(Intent intent, Context context, @Nullable String str) {
        return A00(intent, context, str, AnonymousClass0iD.A05(intent, context, 65600));
    }
}
