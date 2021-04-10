package X;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Base64;
import com.facebook.acra.LogCatCollector;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.Jm  reason: case insensitive filesystem */
public final class C0199Jm {
    static {
        new Object[1][0] = Integer.valueOf(Build.VERSION.SDK_INT);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[ExcHandler: SecurityException (unused java.lang.SecurityException), SYNTHETIC, Splitter:B:5:0x002b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.C0194Jh A00(android.content.Context r14, android.content.Intent r15) {
        /*
        // Method dump skipped, instructions count: 211
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0199Jm.A00(android.content.Context, android.content.Intent):X.Jh");
    }

    public static void A01(Intent intent, Context context, String str) {
        String str2;
        try {
            intent.setExtrasClassLoader(context.getClassLoader());
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.setClassLoader(context.getClassLoader());
            try {
                str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
                str2 = null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            JSONObject jSONObject = new JSONObject();
            String str3 = null;
            try {
                jSONObject.put("t", Long.toString(currentTimeMillis));
                jSONObject.put("r", Long.toString(elapsedRealtime));
                if (str != null) {
                    jSONObject.put("d", str);
                }
                if (str2 != null) {
                    jSONObject.put("v", str2);
                }
                str3 = Base64.encodeToString(jSONObject.toString().getBytes(LogCatCollector.UTF_8_ENCODING), 11);
            } catch (JSONException unused2) {
            }
            JG jg = new JG();
            jg.A07 = str3;
            jg.A02 = new ComponentName(context, "com.facebook.invalid_class.f4c3b00c");
            extras.putParcelable("_ci_", PendingIntent.getActivity(context, 0, JG.A00(jg, context), 1140850688));
            intent.putExtras(extras);
        } catch (Exception e) {
            throw new C0202Jp(e);
        }
    }

    public static void A02(Intent intent, Context context, String str, JC jc) {
        try {
            A01(intent, context, str);
        } catch (C0202Jp e) {
            jc.A4o("CallerInfoHelper", "Error attaching caller info to Intent.", e);
        }
    }
}
