package X;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Base64;
import com.facebook.acra.LogCatCollector;
import com.facebook.acra.NativeCrashDumpReporterUtil;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.hK  reason: case insensitive filesystem */
public final class C0414hK {
    public static final String A00 = String.format("Null metadata in caller identity, API=%d", Integer.valueOf(Build.VERSION.SDK_INT));

    @Nullable
    @SuppressLint({"DeprecatedMethod"})
    public static C0408hC A00(Context context, Intent intent, AbstractC0382ge geVar) {
        String str;
        String str2;
        String str3;
        if (intent.hasExtra("_ci_")) {
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("_ci_");
            if (pendingIntent != null) {
                String creatorPackage = pendingIntent.getCreatorPackage();
                int creatorUid = pendingIntent.getCreatorUid();
                try {
                    C0409hD A02 = C0413hH.A02(C0413hH.A01(C0413hH.A00(context, creatorPackage)));
                    try {
                        String str4 = (String) PendingIntent.class.getMethod("getTag", String.class).invoke(pendingIntent, "");
                        if (str4 != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(new String(Base64.decode(str4, 11), LogCatCollector.UTF_8_ENCODING));
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
                                long j = (long) NativeCrashDumpReporterUtil.MAX_TIME_DIFF_BETWEEN_COREDUMP_AND_MINIDUMP_MS;
                                if (System.currentTimeMillis() - parseLong >= j) {
                                    if (jSONObject.has("r")) {
                                        if (SystemClock.elapsedRealtime() - Long.parseLong(jSONObject.getString("r")) < j) {
                                        }
                                    }
                                    A01(geVar, "Caller identity has expired.", null);
                                    return null;
                                }
                                return new C0408hC(creatorUid, Collections.singletonList(creatorPackage), A02, str3, str2);
                            } catch (UnsupportedEncodingException | IllegalArgumentException | JSONException e) {
                                A01(geVar, "Error parsing metadata from caller identity.", e);
                                return null;
                            }
                        }
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                        A01(geVar, "Error extracting metadata from caller identity.", e2);
                    }
                    str = A00;
                } catch (SecurityException e3) {
                    A01(geVar, "Failed to get signature.", e3);
                    return null;
                }
            } else {
                str = "Null caller identity intent extra.";
            }
        } else {
            str = "Missing caller identity intent extra.";
        }
        A01(geVar, str, null);
        return null;
    }

    public static void A01(@Nullable AbstractC0382ge geVar, String str, @Nullable Throwable th) {
        if (geVar != null) {
            geVar.A4i("CallerInfoHelper", str, th);
        }
    }
}
