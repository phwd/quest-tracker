package X;

import android.transition.Transition;
import java.util.ArrayList;

/* renamed from: X.0D9  reason: invalid class name */
public class AnonymousClass0D9 implements Transition.TransitionListener {
    public final /* synthetic */ C03580ck A00;
    public final /* synthetic */ Object A01;
    public final /* synthetic */ Object A02;
    public final /* synthetic */ Object A03;
    public final /* synthetic */ ArrayList A04;
    public final /* synthetic */ ArrayList A05;
    public final /* synthetic */ ArrayList A06;

    public final void onTransitionCancel(Transition transition) {
    }

    public final void onTransitionPause(Transition transition) {
    }

    public final void onTransitionResume(Transition transition) {
    }

    public AnonymousClass0D9(C03580ck r1, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.A00 = r1;
        this.A01 = obj;
        this.A04 = arrayList;
        this.A02 = obj2;
        this.A05 = arrayList2;
        this.A03 = obj3;
        this.A06 = arrayList3;
    }

    public final void onTransitionStart(Transition transition) {
        Object obj = this.A01;
        if (obj != null) {
            this.A00.A0B(obj, this.A04, null);
        }
        Object obj2 = this.A02;
        if (obj2 != null) {
            this.A00.A0B(obj2, this.A05, null);
        }
        Object obj3 = this.A03;
        if (obj3 != null) {
            this.A00.A0B(obj3, this.A06, null);
        }
    }

    public final void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
    }
}
