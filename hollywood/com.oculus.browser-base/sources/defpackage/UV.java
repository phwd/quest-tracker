package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: UV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UV {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9031a = 12451000;
    public static final UV b = new UV();

    static {
        AtomicBoolean atomicBoolean = AbstractC3729mW.f10427a;
    }

    public Intent a(Context context, int i, String str) {
        if (i == 1 || i == 2) {
            if (context == null || !FE.b(context)) {
                StringBuilder i2 = AbstractC2531fV.i("gcore_");
                i2.append(f9031a);
                i2.append("-");
                if (!TextUtils.isEmpty(str)) {
                    i2.append(str);
                }
                i2.append("-");
                if (context != null) {
                    i2.append(context.getPackageName());
                }
                i2.append("-");
                if (context != null) {
                    try {
                        C3969nu0 a2 = C5858yz1.a(context);
                        i2.append(a2.f10518a.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                String sb = i2.toString();
                int i3 = AbstractC3866nG1.f10480a;
                Intent intent = new Intent("android.intent.action.VIEW");
                Uri.Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.gms");
                if (!TextUtils.isEmpty(sb)) {
                    appendQueryParameter.appendQueryParameter("pcampaignid", sb);
                }
                intent.setData(appendQueryParameter.build());
                intent.setPackage("com.android.vending");
                intent.addFlags(524288);
                return intent;
            }
            int i4 = AbstractC3866nG1.f10480a;
            Intent intent2 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
            intent2.setPackage("com.google.android.wearable.app");
            return intent2;
        } else if (i != 3) {
            return null;
        } else {
            int i5 = AbstractC3866nG1.f10480a;
            Uri fromParts = Uri.fromParts("package", "com.google.android.gms", null);
            Intent intent3 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent3.setData(fromParts);
            return intent3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x01a0 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00ff A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b(android.content.Context r12, int r13) {
        /*
        // Method dump skipped, instructions count: 419
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UV.b(android.content.Context, int):int");
    }

    public boolean c(int i) {
        AtomicBoolean atomicBoolean = AbstractC3729mW.f10427a;
        return i == 1 || i == 2 || i == 3 || i == 9;
    }
}
