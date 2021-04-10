package defpackage;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

/* renamed from: EL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class EL extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final FL f7955a;

    public EL(FL fl) {
        this.f7955a = fl;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        TransitionDrawable transitionDrawable;
        Drawable drawable = (Drawable) obj;
        NL nl = this.f7955a.I.f;
        if (nl == null) {
            return;
        }
        if (drawable == null) {
            nl.l = null;
            nl.m.setImageDrawable(null);
            return;
        }
        Drawable drawable2 = nl.l;
        if (drawable2 == null || (drawable2 instanceof TransitionDrawable)) {
            transitionDrawable = drawable;
        } else {
            TransitionDrawable transitionDrawable2 = new TransitionDrawable(new Drawable[]{nl.l, drawable});
            transitionDrawable2.setCrossFadeEnabled(true);
            transitionDrawable2.startTransition(218);
            transitionDrawable = transitionDrawable2;
        }
        nl.m.setImageDrawable(transitionDrawable);
        nl.l = drawable;
    }
}
