package defpackage;

import J.N;
import android.text.TextUtils;

/* renamed from: qO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4397qO {
    public static boolean a(String str, String str2, boolean z) {
        String MMltG$kc = N.MMltG$kc(str, str2);
        Boolean valueOf = TextUtils.isEmpty(MMltG$kc) ? null : Boolean.valueOf(Boolean.parseBoolean(MMltG$kc));
        return (valueOf == null || valueOf.booleanValue() == z) ? false : true;
    }
}
