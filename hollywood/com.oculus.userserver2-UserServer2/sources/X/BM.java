package X;

import android.view.View;
import java.util.ArrayList;
import java.util.Map;

public class BM implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransitionImpl$3";
    public final /* synthetic */ BN A00;
    public final /* synthetic */ ArrayList A01;
    public final /* synthetic */ Map A02;

    public BM(BN bn, ArrayList arrayList, Map map) {
        this.A00 = bn;
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
