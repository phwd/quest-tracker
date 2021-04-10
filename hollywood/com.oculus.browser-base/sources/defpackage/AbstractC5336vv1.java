package defpackage;

import android.view.View;

/* renamed from: vv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5336vv1 extends AbstractC5166uv1 {
    public static boolean f = true;

    @Override // defpackage.AbstractC5846yv1
    public void d(View view, int i, int i2, int i3, int i4) {
        if (f) {
            try {
                view.setLeftTopRightBottom(i, i2, i3, i4);
            } catch (NoSuchMethodError unused) {
                f = false;
            }
        }
    }
}
