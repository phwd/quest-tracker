package defpackage;

import android.view.accessibility.AccessibilityNodeInfo;
import org.chromium.content_public.browser.WebContents;

/* renamed from: HJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HJ0 extends C3002iD0 {
    public HJ0(WebContents webContents) {
        super(webContents);
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoText(AccessibilityNodeInfo accessibilityNodeInfo, String str, boolean z, boolean z2, String str2, int[] iArr, int[] iArr2, String[] strArr, String str3) {
        super.setAccessibilityNodeInfoText(accessibilityNodeInfo, str, z, z2, str2, iArr, iArr2, strArr, str3);
        if (str3 != null && !str3.isEmpty()) {
            accessibilityNodeInfo.setText(c(str, z2, str2, iArr, iArr2, strArr));
            accessibilityNodeInfo.setStateDescription(str3);
        }
    }
}
