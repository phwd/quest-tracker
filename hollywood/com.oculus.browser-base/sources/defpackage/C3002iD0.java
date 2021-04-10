package defpackage;

import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import org.chromium.content_public.browser.WebContents;

/* renamed from: iD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3002iD0 extends C0652Kq0 {
    public C3002iD0(WebContents webContents) {
        super(webContents);
        AutofillManager autofillManager = (AutofillManager) this.I.getSystemService(AutofillManager.class);
        if (autofillManager != null && autofillManager.isEnabled()) {
            o();
            f();
        }
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoPaneTitle(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        accessibilityNodeInfo.setPaneTitle(str);
    }
}
