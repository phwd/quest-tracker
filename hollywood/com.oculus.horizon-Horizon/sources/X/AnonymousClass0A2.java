package X;

import android.graphics.Rect;
import android.transition.Transition;

/* renamed from: X.0A2  reason: invalid class name */
public class AnonymousClass0A2 extends Transition.EpicenterCallback {
    public final /* synthetic */ Rect A00;
    public final /* synthetic */ C07360rz A01;

    public AnonymousClass0A2(C07360rz r1, Rect rect) {
        this.A01 = r1;
        this.A00 = rect;
    }

    public final Rect onGetEpicenter(Transition transition) {
        Rect rect = this.A00;
        if (rect == null || rect.isEmpty()) {
            return null;
        }
        return rect;
    }
}
