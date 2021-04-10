package defpackage;

import android.view.View;

/* renamed from: Ut1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ut1 extends Yt1 {
    public Ut1(int i, Class cls, int i2) {
        super(i, cls, i2);
    }

    @Override // defpackage.Yt1
    public Object b(View view) {
        return Boolean.valueOf(view.isScreenReaderFocusable());
    }
}
