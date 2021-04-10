package X;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

public class BG implements Transition.TransitionListener {
    public final /* synthetic */ View A00;
    public final /* synthetic */ Th A01;
    public final /* synthetic */ ArrayList A02;

    public final void onTransitionCancel(Transition transition) {
    }

    public final void onTransitionPause(Transition transition) {
    }

    public final void onTransitionResume(Transition transition) {
    }

    public BG(Th th, View view, ArrayList arrayList) {
        this.A01 = th;
        this.A00 = view;
        this.A02 = arrayList;
    }

    public final void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
        this.A00.setVisibility(8);
        ArrayList arrayList = this.A02;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((View) arrayList.get(i)).setVisibility(0);
        }
    }

    public final void onTransitionStart(Transition transition) {
        transition.removeListener(this);
        transition.addListener(this);
    }
}
