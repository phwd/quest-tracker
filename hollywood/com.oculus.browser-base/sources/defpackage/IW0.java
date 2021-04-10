package defpackage;

import android.view.View;

/* renamed from: IW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IW0 extends XK0 {
    public ZH0 Z;
    public YH0 a0;
    public UH0 b0;

    public IW0(JW0 jw0, View view, YH0 yh0) {
        super(view);
        this.a0 = yh0;
    }

    public void x(UH0 uh0) {
        ZH0 zh0 = this.Z;
        if (zh0 != null) {
            zh0.b();
        }
        this.b0 = uh0;
        if (uh0 != null) {
            this.Z = ZH0.a(uh0, this.G, this.a0);
        }
    }
}
