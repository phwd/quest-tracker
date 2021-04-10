package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Iterator;

/* renamed from: U10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U10 extends W10 {
    public Rect I = new Rect();

    public U10(Context context) {
        super(context);
    }

    @Override // defpackage.W10
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        int i;
        int i2;
        int i3;
        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
        int i4 = 0;
        if (displayCutout != null) {
            i4 = displayCutout.getSafeInsetLeft();
            i2 = displayCutout.getSafeInsetTop();
            i = displayCutout.getSafeInsetRight();
            i3 = displayCutout.getSafeInsetBottom();
        } else {
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        Rect rect = this.I;
        if (rect.left != i4 || rect.top != i2 || rect.right != i || rect.bottom != i3) {
            rect.set(i4, i2, i, i3);
            Iterator it = this.H.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((V10) uq0.next()).f(this.I);
            }
        }
        super.onApplyWindowInsets(windowInsets);
        return windowInsets;
    }
}
