package X;

import android.view.View;
import java.util.ArrayList;

/* renamed from: X.0A3  reason: invalid class name */
public class AnonymousClass0A3 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransitionImpl$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass0A6 A01;
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

    public AnonymousClass0A3(AnonymousClass0A6 r1, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.A01 = r1;
        this.A00 = i;
        this.A04 = arrayList;
        this.A02 = arrayList2;
        this.A05 = arrayList3;
        this.A03 = arrayList4;
    }
}
