package X;

import android.view.View;
import java.util.ArrayList;
import java.util.Map;

public class A5 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransitionImpl$3";
    public final /* synthetic */ A6 A00;
    public final /* synthetic */ ArrayList A01;
    public final /* synthetic */ Map A02;

    public A5(A6 a6, ArrayList arrayList, Map map) {
        this.A00 = a6;
        this.A01 = arrayList;
        this.A02 = map;
    }

    public final void run() {
        ArrayList arrayList = this.A01;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = (View) arrayList.get(i);
            view.setTransitionName((String) this.A02.get(view.getTransitionName()));
        }
    }
}
