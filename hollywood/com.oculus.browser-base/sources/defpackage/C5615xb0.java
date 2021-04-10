package defpackage;

import android.view.View;
import android.widget.Magnifier;

/* renamed from: xb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5615xb0 {

    /* renamed from: a  reason: collision with root package name */
    public Magnifier f11618a;
    public C3722mS0 b;

    public C5615xb0(C3722mS0 ms0) {
        this.b = ms0;
    }

    public void a(float f, float f2) {
        View a2 = this.b.a();
        if (a2 != null) {
            if (this.f11618a == null) {
                this.f11618a = new Magnifier(a2);
            }
            this.f11618a.show(f, f2);
        }
    }
}
