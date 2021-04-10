package X;

import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* renamed from: X.09u  reason: invalid class name */
public class AnonymousClass09u implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$4";
    public final /* synthetic */ View A00;
    public final /* synthetic */ Fragment A01;
    public final /* synthetic */ AnonymousClass0A7 A02;
    public final /* synthetic */ Object A03;
    public final /* synthetic */ Object A04;
    public final /* synthetic */ ArrayList A05;
    public final /* synthetic */ ArrayList A06;
    public final /* synthetic */ ArrayList A07;

    public AnonymousClass09u(Object obj, AnonymousClass0A7 r2, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.A03 = obj;
        this.A02 = r2;
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
            AnonymousClass0A7 r2 = this.A02;
            View view = this.A00;
            r2.A07(obj, view);
            this.A05.addAll(AnonymousClass09y.A01(r2, obj, this.A07, view));
        }
        ArrayList<View> arrayList = this.A06;
        if (arrayList != null) {
            Object obj2 = this.A04;
            if (obj2 != null) {
                ArrayList<View> arrayList2 = new ArrayList<>();
                arrayList2.add(this.A00);
                this.A02.A0B(obj2, arrayList, arrayList2);
            }
            arrayList.clear();
            arrayList.add(this.A00);
        }
    }
}
