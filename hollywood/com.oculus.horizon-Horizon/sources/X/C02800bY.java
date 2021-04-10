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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0bY  reason: invalid class name and case insensitive filesystem */
public final class C02800bY {
    public static final String A00 = String.format("Null metadata in caller identity, API=%d", Integer.valueOf(Build.VERSION.SDK_INT));

    @Nullable
    public static C02790bO A00(Context context, Intent intent) {
        return A01(context, intent, false, null);
    }

    @Nullable
    @SuppressLint({"DeprecatedMethod"})
    public static C02790bO A01(Context context, Intent intent, boolean z, @Nullable AnonymousClass0b1 r18) {
        String str;
        String str2;
        String str3;
        String str4;
        if (intent.hasExtra("_ci_")) {
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("_ci_");
            if (pendingIntent != null) {
                String creatorPackage = pendingIntent.getCreatorPackage();
                int creatorUid = pendingIntent.getCreatorUid();
                try {
                    AnonymousClass0bQ A03 = AnonymousClass0bU.A03(context, creatorPackage);
                    try {
                        if (Build.VERSION.SDK_INT >= 24) {
                            str2 = (String) PendingIntent.class.getMethod("getTag", String.class).invoke(pendingIntent, "");
                        } else {
                            str2 = ((Intent) PendingIntent.class.getMethod("getIntent", (Class[]) Collections.emptyList().toArray(new Class[0])).invoke(pendingIntent, new Object[0])).getAction();
                        }
                        if (str2 != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(new String(Base64.decode(str2, 11), "UTF-8"));
                                if (jSONObject.has("d")) {
                                    str3 = jSONObject.getString("d");
                                } else {
                                    str3 = null;
                                }
                                if (jSONObject.has("v")) {
                                    str4 = jSONObject.getString("v");
                                } else {
                                    str4 = null;
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
                                    A04(r18, "Caller identity has expired.", null);
                                    return null;
                                }
                                return new C02790bO(creatorUid, Collections.singletonList(creatorPackage), A03, str4, str3);
                            } catch (UnsupportedEncodingException | IllegalArgumentException | JSONException e) {
                                A04(r18, "Error parsing metadata from caller identity.", e);
                                return null;
                            }
                        }
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                        A04(r18, "Error extracting metadata from caller identity.", e2);
                    }
                    str = A00;
                } catch (SecurityException e3) {
                    A04(r18, "Failed to get signature.", e3);
                    return null;
                }
            } else {
                str = "Null caller identity intent extra.";
            }
        } else {
            str = "Missing caller identity intent extra.";
        }
        A04(r18, str, null);
        return null;
    }

    public static void A04(@Nullable AnonymousClass0b1 r1, String str, @Nullable Throwable th) {
        if (r1 != null) {
            r1.report("CallerInfoHelper", str, th);
        }
    }

    public static void A02(Intent intent, Context context, @Nullable String str) throws C02930bl {
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
            AnonymousClass0b4 r3 = new AnonymousClass0b4();
            r3.A09 = str3;
            r3.A03 = new ComponentName(context, "com.facebook.invalid_class.f4c3b00c");
            extras.putParcelable("_ci_", PendingIntent.getActivity(context, 0, AnonymousClass0b4.A01(r3, context), AnonymousClass0b4.A00(r3, 1140850688)));
            intent.putExtras(extras);
        } catch (Exception e) {
            throw new C02930bl(e);
        }
    }

    public static void A03(Intent intent, Context context, @Nullable String str, AnonymousClass0b1 r3) {
        try {
            A02(intent, context, str);
        } catch (C02930bl e) {
            r3.report("CallerInfoHelper", "Error attaching caller info to Intent.", e);
        }
    }
}
