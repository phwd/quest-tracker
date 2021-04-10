package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;

/* renamed from: N6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N6 extends D6 {
    public final /* synthetic */ O6 b;

    public N6(O6 o6) {
        this.b = o6;
    }

    @Override // defpackage.D6
    public void a(Drawable drawable) {
        O6 o6 = this.b;
        Handler handler = o6.f8600a;
        Animatable animatable = o6.b;
        animatable.getClass();
        handler.post(new M6(animatable));
    }
}
