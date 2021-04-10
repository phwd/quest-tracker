package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

public class QT {
    public static final QT A00 = new QT();

    public final Intent A02(Context context, int i, String str) {
        if (i == 1 || i == 2) {
            if (context == null || !S5.A00(context)) {
                StringBuilder sb = new StringBuilder("gcore_");
                sb.append(12451000);
                sb.append("-");
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str);
                }
                sb.append("-");
                if (context != null) {
                    sb.append(context.getPackageName());
                }
                sb.append("-");
                if (context != null) {
                    try {
                        S7 A002 = S8.A00(context);
                        sb.append(A002.A00.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                String obj = sb.toString();
                Intent intent = new Intent("android.intent.action.VIEW");
                Uri.Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.gms");
                if (!TextUtils.isEmpty(obj)) {
                    appendQueryParameter.appendQueryParameter("pcampaignid", obj);
                }
                intent.setData(appendQueryParameter.build());
                intent.setPackage("com.android.vending");
                intent.addFlags(524288);
                return intent;
            }
            Intent intent2 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
            intent2.setPackage("com.google.android.wearable.app");
            return intent2;
        } else if (i != 3) {
            return null;
        } else {
            Uri fromParts = Uri.fromParts("package", "com.google.android.gms", null);
            Intent intent3 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent3.setData(fromParts);
            return intent3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0098, code lost:
        if (r13.getPackageManager().hasSystemFeature("android.hardware.type.embedded") != false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a6, code lost:
        if (r0.booleanValue() != false) goto L_0x00a8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01e3 A[EDGE_INSN: B:140:0x01e3->B:125:0x01e3 ?: BREAK  , ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A01(android.content.Context r13, int r14) {
        /*
        // Method dump skipped, instructions count: 549
        */
        throw new UnsupportedOperationException("Method not decompiled: X.QT.A01(android.content.Context, int):int");
    }

    public int isGooglePlayServicesAvailable(Context context) {
        return A01(context, 12451000);
    }
}
