package X;

import android.transition.Transition;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class BA implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$4";
    public final /* synthetic */ View A00;
    public final /* synthetic */ Fragment A01;
    public final /* synthetic */ BN A02;
    public final /* synthetic */ Object A03;
    public final /* synthetic */ Object A04;
    public final /* synthetic */ ArrayList A05;
    public final /* synthetic */ ArrayList A06;
    public final /* synthetic */ ArrayList A07;

    public BA(Object obj, BN bn, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.A03 = obj;
        this.A02 = bn;
        this.A00 = view;
        this.A01 = fragment;
        this.A07 = arrayList;
        this.A05 = arrayList2;
        this.A06 = arrayList3;
        this.A04 = obj2;
    }

    public final void run() {
        Object obj = this.A03;
        if (obj != null) {
            BN bn = this.A02;
            View view = this.A00;
            ((Transition) obj).removeTarget(view);
            this.A05.addAll(BE.A01(bn, obj, this.A07, view));
        }
        ArrayList<View> arrayList = this.A06;
        if (arrayList != null) {
            Object obj2 = this.A04;
            if (obj2 != null) {
                ArrayList<View> arrayList2 = new ArrayList<>();
                arrayList2.add(this.A00);
                this.A02.A02(obj2, arrayList, arrayList2);
            }
            arrayList.clear();
            arrayList.add(this.A00);
        }
    }
}
