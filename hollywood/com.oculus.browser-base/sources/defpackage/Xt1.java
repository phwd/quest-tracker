package defpackage;

import android.view.View;

/* renamed from: Xt1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Xt1 extends Yt1 {
    public Xt1(int i, Class cls, int i2) {
        super(i, cls, i2);
    }

    @Override // defpackage.Yt1
    public Object b(View view) {
        return Boolean.valueOf(view.isAccessibilityHeading());
    }

    public void d(View view, Object obj) {
        view.setAccessibilityHeading(((Boolean) obj).booleanValue());
    }

    public boolean e(Object obj, Object obj2) {
        return !a((Boolean) obj, (Boolean) obj2);
    }
}
