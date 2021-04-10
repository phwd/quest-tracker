package X;

import android.view.View;
import java.util.ArrayList;

public class A3 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransitionImpl$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ A6 A01;
    public final /* synthetic */ ArrayList A02;
    public final /* synthetic */ ArrayList A03;
    public final /* synthetic */ ArrayList A04;
    public final /* synthetic */ ArrayList A05;

    public final void run() {
        for (int i = 0; i < this.A00; i++) {
            ((View) this.A04.get(i)).setTransitionName((String) this.A02.get(i));
            ((View) this.A05.get(i)).setTransitionName((String) this.A03.get(i));
        }
    }

    public A3(A6 a6, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.A01 = a6;
        this.A00 = i;
        this.A04 = arrayList;
        this.A02 = arrayList2;
        this.A05 = arrayList3;
        this.A03 = arrayList4;
    }
}
