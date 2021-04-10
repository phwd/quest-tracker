package defpackage;

import android.net.Uri;
import org.chromium.chrome.browser.download.MimeUtils;

/* renamed from: hH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2842hH extends C2671gH {
    @Override // defpackage.C2671gH
    public boolean a(String str) {
        return C4721sH.b(str);
    }

    @Override // defpackage.C2671gH
    public Uri b(String str) {
        return OI.d(str);
    }

    @Override // defpackage.C2671gH
    public String c(String str, String str2, String str3) {
        return MimeUtils.remapGenericMimeType(str, str2, str3);
    }
}
