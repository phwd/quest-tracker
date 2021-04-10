package defpackage;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: OI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class OI {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f8616a = {R.string.f51750_resource_name_obfuscated_RES_2131952492, R.string.f51760_resource_name_obfuscated_RES_2131952493, R.string.f51730_resource_name_obfuscated_RES_2131952490};

    public static long a(String str, String str2, String str3, String str4, long j, String str5, String str6) {
        Context applicationContext = ContextUtils.getApplicationContext();
        return ((DownloadManager) applicationContext.getSystemService("download")).addCompletedDownload(str, str2, true, str3, str4, j, !new C0650Kp0(applicationContext).a(), d(str5), TextUtils.isEmpty(str6) ? null : Uri.parse(str6));
    }

    public static String b(Context context, long j) {
        return c(context, f8616a, j);
    }

    public static String c(Context context, int[] iArr, long j) {
        float f;
        float f2;
        int i;
        if (j / 1048576 < 1) {
            i = iArr[0];
            f2 = (float) j;
            f = 1024.0f;
        } else if (j / 1073741824 < 1) {
            i = iArr[1];
            f2 = (float) j;
            f = 1048576.0f;
        } else {
            i = iArr[2];
            f2 = (float) j;
            f = 1.07374182E9f;
        }
        return context.getResources().getString(i, Float.valueOf(f2 / f));
    }

    public static Uri d(String str) {
        Uri parse = TextUtils.isEmpty(str) ? null : Uri.parse(str);
        if (parse != null) {
            String scheme = parse.normalizeScheme().getScheme();
            if (scheme == null) {
                return null;
            }
            if (!scheme.equals("https") && !scheme.equals("http")) {
                return null;
            }
        }
        return parse;
    }
}
