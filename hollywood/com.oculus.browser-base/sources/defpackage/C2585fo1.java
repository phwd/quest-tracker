package defpackage;

import J.N;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge;

/* renamed from: fo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2585fo1 {

    /* renamed from: a  reason: collision with root package name */
    public final C2756go1 f9955a;
    public final C4294po1 b;
    public final M70 c;

    public C2585fo1(Context context, C2756go1 go1, M70 m70, C4294po1 po1) {
        context.getPackageManager();
        this.f9955a = go1;
        this.c = m70;
        this.b = po1;
    }

    public static C2585fo1 a() {
        return AbstractApplicationC3785mq.g().g();
    }

    public static Boolean c(String str) {
        if (str == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = ContextUtils.getApplicationContext().getPackageManager().getPackageInfo(str, 4096);
            String[] strArr = packageInfo.requestedPermissions;
            int[] iArr = packageInfo.requestedPermissionsFlags;
            if (strArr != null) {
                boolean z = false;
                for (int i = 0; i < strArr.length; i++) {
                    if ("android.permission.ACCESS_COARSE_LOCATION".equals(strArr[i]) || "android.permission.ACCESS_FINE_LOCATION".equals(strArr[i])) {
                        if ((iArr[i] & 2) != 0) {
                            return Boolean.TRUE;
                        }
                        z = true;
                    }
                }
                if (z) {
                    return Boolean.FALSE;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            AbstractC1220Ua0.a("TwaPermissionManager", "Couldn't find name for client package: %s", str);
        }
        return null;
    }

    public Set b(C4649rt0 rt0) {
        SharedPreferences sharedPreferences = this.f9955a.f10023a;
        StringBuilder i = AbstractC2531fV.i("all_delegate_apps.");
        i.append(rt0.toString());
        String sb = i.toString();
        HashSet hashSet = null;
        Set<String> stringSet = sharedPreferences.getStringSet(sb, null);
        if (stringSet != null) {
            hashSet = new HashSet();
            for (String str : stringSet) {
                hashSet.add(new C1707aj1(new C2229dj1(Base64.decode(str, 3))));
            }
        }
        return hashSet;
    }

    public void d(C4649rt0 rt0, String str, int i, boolean z) {
        String str2;
        int b2;
        Integer num;
        boolean z2 = false;
        try {
            PackageManager packageManager = ContextUtils.getApplicationContext().getPackageManager();
            str2 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString();
            if (TextUtils.isEmpty(str2)) {
                AbstractC1220Ua0.a("TwaPermissionManager", "Invalid details for client package: %s", str2);
                str2 = null;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            AbstractC1220Ua0.a("TwaPermissionManager", "Couldn't find name for client package: %s", str);
        }
        if (str2 != null) {
            Boolean a2 = this.f9955a.a(i, rt0);
            Objects.requireNonNull(this.b);
            if (i == 5) {
                if (a2 == null) {
                    num = z ? 1 : 0;
                } else {
                    num = (a2.booleanValue() || !z) ? (!a2.booleanValue() || z) ? null : 2 : 3;
                }
                if (num != null) {
                    AbstractC3364kK0.g("TrustedWebActivity.LocationPermissionChanged", num.intValue(), 4);
                }
            }
            C2756go1 go1 = this.f9955a;
            boolean z3 = !((HashSet) go1.e()).contains(rt0.toString());
            if (!z3) {
                z3 = (z != go1.f10023a.getBoolean(go1.d(i, rt0), false)) || (str.equals(go1.f10023a.getString(go1.c(rt0), null)) ^ true) || (str2.equals(go1.f10023a.getString(go1.b(rt0), null)) ^ true);
            }
            Set<String> e = go1.e();
            ((HashSet) e).add(rt0.toString());
            go1.f10023a.edit().putStringSet("origins", e).apply();
            go1.f10023a.edit().putBoolean(go1.d(i, rt0), z).putString(go1.c(rt0), str).putString(go1.b(rt0), str2).apply();
            if (i == 6) {
                M70 m70 = this.c;
                if (!C0040Ap0.a()) {
                    C0040Ap0 ap0 = (C0040Ap0) m70.get();
                    Objects.requireNonNull(ap0);
                    if (!C0040Ap0.a()) {
                        String a3 = ap0.b.a(rt0.toString());
                        if (!"sites".equals(a3) && (b2 = ap0.b.b(a3)) != 2) {
                            C2756go1 go12 = ap0.f7699a;
                            if (b2 == 0) {
                                z2 = true;
                            }
                            SharedPreferences.Editor edit = go12.f10023a.edit();
                            StringBuilder i2 = AbstractC2531fV.i("pre_twa_notification_permission.");
                            i2.append(rt0.toString());
                            edit.putBoolean(i2.toString(), z2).apply();
                            ((C0771Mp0) ap0.b.f8480a).b.deleteNotificationChannel(a3);
                        }
                    }
                }
            }
            if (z3) {
                long j = InstalledWebappBridge.f10621a;
                if (j != 0) {
                    N.MPWzS9sk(j, i);
                }
            }
        }
    }
}
