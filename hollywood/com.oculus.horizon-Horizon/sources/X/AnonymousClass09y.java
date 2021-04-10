package X;

import android.graphics.Rect;
import android.transition.Transition;

/* renamed from: X.09y  reason: invalid class name */
public class AnonymousClass09y extends Transition.EpicenterCallback {
    public final /* synthetic */ Rect A00;
    public final /* synthetic */ C07360rz A01;

    public AnonymousClass09y(C07360rz r1, Rect rect) {
        this.A01 = r1;
        this.A00 = rect;
    }

    public final Rect onGetEpicenter(Transition transition) {
        return this.A00;
    }
}
