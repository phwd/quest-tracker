package X;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.squareup.okhttp.internal.DiskLruCache;
import java.util.Locale;

/* renamed from: X.0Wh  reason: invalid class name */
public final class AnonymousClass0Wh {
    public final Context A00;
    public final C01600Wb A01;
    public final String A02 = "MQTT";

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/0Wb;Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljava/lang/String;)V */
    public AnonymousClass0Wh(Context context, C01600Wb r3) {
        this.A00 = context;
        this.A01 = r3;
    }

    private String A00(String str) {
        String str2;
        String str3;
        if (str != null) {
            int length = str.length();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt == '&') {
                    str3 = "&amp;";
                } else if (charAt < ' ' || charAt > '~') {
                    sb.append("&#");
                    sb.append(Integer.toString(charAt));
                    str3 = ";";
                } else {
                    sb.append(charAt);
                }
                sb.append(str3);
            }
            str2 = sb.toString();
        } else {
            str2 = "";
        }
        return str2.replace("/", "-").replace(";", "-");
    }

    public final String A01() {
        String str;
        boolean z;
        Object[] objArr = new Object[10];
        objArr[0] = "FBAN";
        objArr[1] = this.A02;
        objArr[2] = "FBAV";
        C01600Wb r2 = this.A01;
        objArr[3] = r2.A01;
        objArr[4] = "FBBV";
        objArr[5] = r2.A00;
        objArr[6] = "FBDM";
        Context context = this.A00;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Point point = new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
        AnonymousClass0Wc r9 = AnonymousClass0Wc.A00;
        WindowManager windowManager = (WindowManager) r9.A00(context, "window", WindowManager.class);
        if (!(windowManager == null || windowManager.getDefaultDisplay() == null)) {
            windowManager.getDefaultDisplay().getSize(point);
        }
        StringBuilder sb = new StringBuilder("{density=");
        sb.append(displayMetrics.density);
        sb.append(",width=");
        sb.append(point.x);
        sb.append(",height=");
        sb.append(point.y);
        sb.append("}");
        objArr[7] = A00(sb.toString());
        objArr[8] = "FBLC";
        objArr[9] = A00(Locale.getDefault().toString());
        String format = String.format(null, "%s/%s;%s/%s;%s/%s;%s/%s;%s/%s;", objArr);
        Object[] objArr2 = new Object[14];
        objArr2[0] = "FBCR";
        TelephonyManager telephonyManager = (TelephonyManager) r9.A00(context, "phone", TelephonyManager.class);
        if (telephonyManager != null) {
            str = telephonyManager.getNetworkOperatorName();
        } else {
            str = "";
        }
        objArr2[1] = A00(str);
        objArr2[2] = "FBMF";
        objArr2[3] = A00(Build.MANUFACTURER);
        objArr2[4] = "FBBD";
        objArr2[5] = A00(Build.BRAND);
        objArr2[6] = "FBPN";
        objArr2[7] = context.getPackageName();
        objArr2[8] = "FBDV";
        objArr2[9] = A00(Build.MODEL);
        objArr2[10] = "FBSV";
        objArr2[11] = A00(Build.VERSION.RELEASE);
        objArr2[12] = "FBLR";
        try {
            z = context.getPackageManager().hasSystemFeature("android.hardware.ram.low");
        } catch (Exception unused) {
            z = false;
        }
        objArr2[13] = A00(z ? DiskLruCache.VERSION_1 : "0");
        return AnonymousClass006.A0A("[", format, String.format(null, "%s/%s;%s/%s;%s/%s;%s/%s;%s/%s;%s/%s;%s/%s;", objArr2), String.format(null, "%s/%s;", "FBBK", DiskLruCache.VERSION_1), String.format(null, "%s/%s:%s;", "FBCA", A00(Build.CPU_ABI), A00(Build.CPU_ABI2)), "]");
    }
}
