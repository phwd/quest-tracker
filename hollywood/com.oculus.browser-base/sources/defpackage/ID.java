package defpackage;

import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import org.chromium.base.ContextUtils;

/* renamed from: ID  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ID {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f8207a = {"com.android.chrome", "org.chromium.chrome", "com.chrome.canary", "com.chrome.beta", "com.chrome.dev"};
    public static ID b;

    public int a(ResolveInfo resolveInfo) {
        if (resolveInfo == null || resolveInfo.match == 0) {
            return 0;
        }
        return TextUtils.equals(ContextUtils.getApplicationContext().getPackageName(), resolveInfo.activityInfo.packageName) ? 2 : 1;
    }
}
