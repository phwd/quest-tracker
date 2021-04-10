package defpackage;

import android.transition.Transition;
import java.util.HashSet;

/* renamed from: CC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CC0 implements Transition.TransitionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ HashSet f7795a;
    public final /* synthetic */ EC0 b;

    public CC0(EC0 ec0, HashSet hashSet) {
        this.b = ec0;
        this.f7795a = hashSet;
    }

    public void onTransitionCancel(Transition transition) {
    }

    public void onTransitionEnd(Transition transition) {
        EC0 ec0 = this.b;
        ec0.c0 = false;
        C3209jS0 js0 = ec0.R;
        js0.c = this.f7795a;
        js0.e();
    }

    public void onTransitionPause(Transition transition) {
    }

    public void onTransitionResume(Transition transition) {
    }

    public void onTransitionStart(Transition transition) {
    }
}
