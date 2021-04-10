package X;

import android.transition.Transition;

public class A1 implements Transition.TransitionListener {
    public final /* synthetic */ C0280a1 A00;
    public final /* synthetic */ Runnable A01;

    public final void onTransitionCancel(Transition transition) {
    }

    public final void onTransitionPause(Transition transition) {
    }

    public final void onTransitionResume(Transition transition) {
    }

    public final void onTransitionStart(Transition transition) {
    }

    public A1(C0280a1 a1Var, Runnable runnable) {
        this.A00 = a1Var;
        this.A01 = runnable;
    }

    public final void onTransitionEnd(Transition transition) {
        this.A01.run();
    }
}
