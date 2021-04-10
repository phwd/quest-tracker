package X;

import android.transition.Transition;

public class BI implements Transition.TransitionListener {
    public final /* synthetic */ Th A00;
    public final /* synthetic */ Runnable A01;

    public final void onTransitionCancel(Transition transition) {
    }

    public final void onTransitionPause(Transition transition) {
    }

    public final void onTransitionResume(Transition transition) {
    }

    public final void onTransitionStart(Transition transition) {
    }

    public BI(Th th, Runnable runnable) {
        this.A00 = th;
        this.A01 = runnable;
    }

    public final void onTransitionEnd(Transition transition) {
        this.A01.run();
    }
}
