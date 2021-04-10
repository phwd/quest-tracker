package X;

import android.graphics.Rect;
import android.transition.TransitionSet;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class BB implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$6";
    public final /* synthetic */ Rect A00;
    public final /* synthetic */ View A01;
    public final /* synthetic */ UB A02;
    public final /* synthetic */ Fragment A03;
    public final /* synthetic */ Fragment A04;
    public final /* synthetic */ BD A05;
    public final /* synthetic */ BN A06;
    public final /* synthetic */ Object A07;
    public final /* synthetic */ Object A08;
    public final /* synthetic */ ArrayList A09;
    public final /* synthetic */ ArrayList A0A;
    public final /* synthetic */ boolean A0B;

    public BB(BN bn, UB ub, Object obj, BD bd, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.A06 = bn;
        this.A02 = ub;
        this.A08 = obj;
        this.A05 = bd;
        this.A09 = arrayList;
        this.A01 = view;
        this.A03 = fragment;
        this.A04 = fragment2;
        this.A0B = z;
        this.A0A = arrayList2;
        this.A07 = obj2;
        this.A00 = rect;
    }

    public final void run() {
        BN bn = this.A06;
        UB ub = this.A02;
        Object obj = this.A08;
        ub.clear();
        if (obj != null) {
            ArrayList<View> arrayList = this.A0A;
            ArrayList<View> arrayList2 = this.A09;
            TransitionSet transitionSet = (TransitionSet) obj;
            if (transitionSet != null) {
                transitionSet.getTargets().clear();
                transitionSet.getTargets().addAll(arrayList2);
                bn.A02(transitionSet, arrayList, arrayList2);
            }
        }
    }
}
