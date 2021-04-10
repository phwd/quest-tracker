package X;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.oculus.os.ActivityManagerUtils;
import org.json.JSONObject;

/* renamed from: X.Vv  reason: case insensitive filesystem */
public final class C0398Vv {
    public static C0459Zp A00;
    public static String A01;
    public static JSONObject A02;
    public static int A03;
    public static final Handler A04 = new Handler(Looper.getMainLooper());
    public static final Runnable A05 = RunnableC0397Vt.A00;
    public static final boolean A06;
    public static final boolean A07;

    public static int A00() {
        if (A03 == 0) {
            try {
                String[] split = BX.A00().getPackageManager().getPackageInfo("com.oculus.vrshell", 0).versionName.split("\\.");
                if (split.length > 0) {
                    A03 = Integer.parseInt(split[0]);
                }
            } catch (PackageManager.NameNotFoundException | NumberFormatException unused) {
            }
            if (A03 == 0) {
                C0139Dd.A0A("SYSTEMINFO", "Failed to parse vrshell version.");
            }
        }
        return A03;
    }

    public static String A01() {
        if (A01 == null) {
            A01 = new ActivityManagerUtils().getForegroundApp(BX.A00());
            Handler handler = A04;
            Runnable runnable = A05;
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 5000);
        }
        return A01;
    }

    public static boolean A02() {
        String foregroundApp = new ActivityManagerUtils().getForegroundApp(BX.A00());
        if ("com.oculus.guardian".equals(foregroundApp) || "com.oculus.systemdriver".equals(foregroundApp)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0030, code lost:
        if ("com.oculus.shellenv".equals(r1) != false) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A03() {
        /*
            java.lang.String r0 = X.C0398Vv.A01
            if (r0 != 0) goto L_0x001f
            com.oculus.os.ActivityManagerUtils r1 = new com.oculus.os.ActivityManagerUtils
            r1.<init>()
            android.app.Application r0 = X.BX.A00()
            java.lang.String r0 = r1.getForegroundApp(r0)
            X.C0398Vv.A01 = r0
            android.os.Handler r3 = X.C0398Vv.A04
            java.lang.Runnable r2 = X.C0398Vv.A05
            r3.removeCallbacks(r2)
            r0 = 5000(0x1388, double:2.4703E-320)
            r3.postDelayed(r2, r0)
        L_0x001f:
            java.lang.String r1 = X.C0398Vv.A01
            java.lang.String r0 = "com.oculus.vrshell"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0032
            java.lang.String r0 = "com.oculus.shellenv"
            boolean r1 = r0.equals(r1)
            r0 = 0
            if (r1 == 0) goto L_0x0033
        L_0x0032:
            r0 = 1
        L_0x0033:
            r0 = r0 ^ 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0398Vv.A03():boolean");
    }

    static {
        String str = Build.MODEL;
        A06 = str.contains("Quest");
        A07 = str.equals("Quest 2");
    }
}
