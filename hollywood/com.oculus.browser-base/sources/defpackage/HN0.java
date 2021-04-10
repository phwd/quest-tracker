package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.Gravity;

/* renamed from: HN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HN0 extends IN0 {
    public HN0(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    @Override // defpackage.IN0
    public void a(int i, int i2, int i3, Rect rect, Rect rect2) {
        Gravity.apply(i, i2, i3, rect, rect2, 0);
    }

    public void getOutline(Outline outline) {
        c();
        outline.setRoundRect(this.h, this.g);
    }
}
