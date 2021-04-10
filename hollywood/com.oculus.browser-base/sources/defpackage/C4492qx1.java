package defpackage;

import android.os.Build;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: qx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4492qx1 implements AbstractC5682xx1 {
    public C4492qx1(C3979nx1 nx1) {
    }

    @Override // defpackage.AbstractC5682xx1
    public Object a(WebContents webContents) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            return new HJ0(webContents);
        }
        if (i >= 28) {
            return new C3002iD0(webContents);
        }
        if (i >= 26) {
            return new C0652Kq0(webContents);
        }
        return new WebContentsAccessibilityImpl(webContents);
    }
}
