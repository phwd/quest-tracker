package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;

/* renamed from: Yb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1466Yb extends AbstractC0823Nl {
    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Animatable animatable = (Animatable) obj;
        if (animatable instanceof E6) {
            ((L6) ((E6) animatable)).b(AbstractC2376ec.f9863a);
        } else if (animatable instanceof Animatable2) {
            ((Animatable2) animatable).registerAnimationCallback(AbstractC2206dc.f9793a);
        }
    }
}
