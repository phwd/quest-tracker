package X;

import android.transition.Transition;

/* renamed from: X.0A2  reason: invalid class name */
public class AnonymousClass0A2 implements Transition.TransitionListener {
    public final /* synthetic */ AnonymousClass0vA A00;
    public final /* synthetic */ Runnable A01;

    public final void onTransitionCancel(Transition transition) {
    }

    public final void onTransitionPause(Transition transition) {
    }

    public final void onTransitionResume(Transition transition) {
    }

    public final void onTransitionStart(Transition transition) {
    }

    public AnonymousClass0A2(AnonymousClass0vA r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    public final void onTransitionEnd(Transition transition) {
        this.A01.run();
    }
}
