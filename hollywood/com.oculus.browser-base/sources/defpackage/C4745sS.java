package defpackage;

import android.animation.Animator;
import android.view.animation.Animation;

/* renamed from: sS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4745sS {

    /* renamed from: a  reason: collision with root package name */
    public final Animation f11274a;
    public final Animator b;

    public C4745sS(Animation animation) {
        this.f11274a = animation;
        this.b = null;
        if (animation == null) {
            throw new IllegalStateException("Animation cannot be null");
        }
    }

    public C4745sS(Animator animator) {
        this.f11274a = null;
        this.b = animator;
    }
}
