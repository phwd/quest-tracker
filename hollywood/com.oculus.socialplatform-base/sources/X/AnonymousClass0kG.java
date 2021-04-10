package X;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Base64;
import com.facebook.acra.NativeCrashDumpReporterUtil;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0kG  reason: invalid class name */
public final class AnonymousClass0kG {
    public static final String A00 = String.format("Null metadata in caller identity, API=%d", Integer.valueOf(Build.VERSION.SDK_INT));

    @Nullable
    @SuppressLint({"DeprecatedMethod"})
    public static AnonymousClass0k7 A00(Context context, Intent intent, boolean z, @Nullable AbstractC02660jW r15) {
        String str;
        String str2;
        String str3;
        if (intent.hasExtra("_ci_")) {
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("_ci_");
            if (pendingIntent != null) {
                String creatorPackage = pendingIntent.getCreatorPackage();
                int creatorUid = pendingIntent.getCreatorUid();
                try {
                    C02920k8 A03 = AnonymousClass0kC.A03(context, creatorPackage);
                    try {
                        String str4 = (String) PendingIntent.class.getMethod("getTag", String.class).invoke(pendingIntent, "");
                        if (str4 != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(new String(Base64.decode(str4, 11), "UTF-8"));
                                if (jSONObject.has("d")) {
                                    str2 = jSONObject.getString("d");
                                } else {
                                    str2 = null;
                                }
                                if (jSONObject.has("v")) {
                                    str3 = jSONObject.getString("v");
                                } else {
                                    str3 = null;
                                }
                                long parseLong = Long.parseLong(jSONObject.getString("t"));
                                int i = NativeCrashDumpReporterUtil.MAX_TIME_DIFF_BETWEEN_COREDUMP_AND_MINIDUMP_MS;
                                if (z) {
                                    i = NativeCrashDumpReporterUtil.MIN_TIME_ELAPSED_SINCE_LAST_COREDUMP_MS;
                                }
                                if (System.currentTimeMillis() - parseLong >= ((long) i)) {
                                    if (jSONObject.has("r")) {
                                        long parseLong2 = Long.parseLong(jSONObject.getString("r"));
                                        int i2 = NativeCrashDumpReporterUtil.MAX_TIME_DIFF_BETWEEN_COREDUMP_AND_MINIDUMP_MS;
                                        if (z) {
                                            i2 = NativeCrashDumpReporterUtil.MIN_TIME_ELAPSED_SINCE_LAST_COREDUMP_MS;
                                        }
                                        if (SystemClock.elapsedRealtime() - parseLong2 < ((long) i2)) {
                                        }
                                    }
                                    A03(r15, "Caller identity has expired.", null);
                                    return null;
                                }
                                return new AnonymousClass0k7(creatorUid, Collections.singletonList(creatorPackage), A03, str3, str2);
                            } catch (UnsupportedEncodingException | IllegalArgumentException | JSONException e) {
                                A03(r15, "Error parsing metadata from caller identity.", e);
                                return null;
                            }
                        }
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                        A03(r15, "Error extracting metadata from caller identity.", e2);
                    }
                    str = A00;
                } catch (SecurityException e3) {
                    A03(r15, "Failed to get signature.", e3);
                    return null;
                }
            } else {
                str = "Null caller identity intent extra.";
            }
        } else {
            str = "Missing caller identity intent extra.";
        }
        A03(r15, str, null);
        return null;
    }

    public static void A03(@Nullable AbstractC02660jW r1, String str, @Nullable Throwable th) {
        if (r1 != null) {
            r1.report(CallerInfoHelper.TAG, str, th);
        }
    }

    public static void A01(Intent intent, Context context, @Nullable String str) throws AnonymousClass0kU {
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
                str3 = Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 11);
            } catch (JSONException unused2) {
            }
            C02670jZ r2 = new C02670jZ();
            r2.A09 = str3;
            r2.A03 = new ComponentName(context, "com.facebook.invalid_class.f4c3b00c");
            extras.putParcelable("_ci_", r2.A02(context, 1140850688));
            intent.putExtras(extras);
        } catch (Exception e) {
            throw new AnonymousClass0kU(e);
        }
    }

    public static void A02(Intent intent, Context context, @Nullable String str, AbstractC02660jW r3) {
        try {
            A01(intent, context, str);
        } catch (AnonymousClass0kU e) {
            r3.report(CallerInfoHelper.TAG, "Error attaching caller info to Intent.", e);
        }
    }
}
