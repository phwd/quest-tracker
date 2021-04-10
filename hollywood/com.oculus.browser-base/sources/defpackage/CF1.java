package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: CF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CF1 {

    /* renamed from: a  reason: collision with root package name */
    public static CF1 f7798a;
    public final Context b;
    public String c;
    public final AtomicInteger d = new AtomicInteger((int) SystemClock.elapsedRealtime());

    public CF1(Context context) {
        this.b = context.getApplicationContext();
    }

    public static String a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    public final String b(Bundle bundle, String str) {
        String a2 = a(bundle, str);
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String a3 = a(bundle, str.concat("_loc_key"));
        if (TextUtils.isEmpty(a3)) {
            return null;
        }
        Resources resources = this.b.getResources();
        int identifier = resources.getIdentifier(a3, "string", this.b.getPackageName());
        if (identifier == 0) {
            String substring = str.concat("_loc_key").substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(a3).length() + String.valueOf(substring).length() + 49);
            sb.append(substring);
            sb.append(" resource not found: ");
            sb.append(a3);
            sb.append(" Default value will be used.");
            Log.w("GcmNotification", sb.toString());
            return null;
        }
        String a4 = a(bundle, str.concat("_loc_args"));
        if (TextUtils.isEmpty(a4)) {
            return resources.getString(identifier);
        }
        try {
            JSONArray jSONArray = new JSONArray(a4);
            int length = jSONArray.length();
            Object[] objArr = new String[length];
            for (int i = 0; i < length; i++) {
                objArr[i] = jSONArray.opt(i);
            }
            return resources.getString(identifier, objArr);
        } catch (JSONException unused) {
            String substring2 = str.concat("_loc_args").substring(6);
            StringBuilder sb2 = new StringBuilder(String.valueOf(a4).length() + String.valueOf(substring2).length() + 41);
            sb2.append("Malformed ");
            sb2.append(substring2);
            sb2.append(": ");
            sb2.append(a4);
            sb2.append("  Default value will be used.");
            Log.w("GcmNotification", sb2.toString());
            return null;
        } catch (MissingFormatArgumentException e) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(a4).length() + String.valueOf(a3).length() + 58);
            sb3.append("Missing format argument for ");
            sb3.append(a3);
            sb3.append(": ");
            sb3.append(a4);
            sb3.append(" Default value will be used.");
            Log.w("GcmNotification", sb3.toString(), e);
            return null;
        }
    }
}
