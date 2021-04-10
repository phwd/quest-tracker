package defpackage;

import J.N;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Arrays;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Kq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0652Kq0 extends WebContentsAccessibilityImpl {
    public C0652Kq0(WebContents webContents) {
        super(webContents);
    }

    public void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
        int[] MVBiMGvZ;
        if (str.equals("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            if (!N.MZcfOSQW(this.K, this, i)) {
                N.M2WbOJ7$(this.K, this, i);
            }
            int i2 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
            int i3 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
            if (i3 > 0 && i2 >= 0 && (MVBiMGvZ = N.MVBiMGvZ(this.K, this, i, i2, i3)) != null) {
                RectF[] rectFArr = new RectF[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = i4 * 4;
                    Rect rect = new Rect(MVBiMGvZ[i5 + 0], MVBiMGvZ[i5 + 1], MVBiMGvZ[i5 + 2], MVBiMGvZ[i5 + 3]);
                    d(rect);
                    rectFArr[i4] = new RectF(rect);
                }
                accessibilityNodeInfo.getExtras().putParcelableArray(str, rectFArr);
            }
        }
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoOAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, String str) {
        if (z) {
            accessibilityNodeInfo.setAvailableExtraData(Arrays.asList("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY"));
        }
        accessibilityNodeInfo.setHintText(str);
    }
}
