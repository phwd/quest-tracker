package X;

import android.transition.Transition;

/* renamed from: X.0A1  reason: invalid class name */
public class AnonymousClass0A1 implements Transition.TransitionListener {
    public final /* synthetic */ C07360rz A00;
    public final /* synthetic */ Runnable A01;

    public final void onTransitionCancel(Transition transition) {
    }

    public final void onTransitionPause(Transition transition) {
    }

    public final void onTransitionResume(Transition transition) {
    }

    public final void onTransitionStart(Transition transition) {
    }

    public AnonymousClass0A1(C07360rz r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    public final void onTransitionEnd(Transition transition) {
        this.A01.run();
    }
}
