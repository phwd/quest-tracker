package X;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* renamed from: X.09u  reason: invalid class name and case insensitive filesystem */
public class RunnableC004609u implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$6";
    public final /* synthetic */ Rect A00;
    public final /* synthetic */ View A01;
    public final /* synthetic */ C07490ss A02;
    public final /* synthetic */ Fragment A03;
    public final /* synthetic */ Fragment A04;
    public final /* synthetic */ C004809w A05;
    public final /* synthetic */ AnonymousClass0A6 A06;
    public final /* synthetic */ Object A07;
    public final /* synthetic */ Object A08;
    public final /* synthetic */ ArrayList A09;
    public final /* synthetic */ ArrayList A0A;
    public final /* synthetic */ boolean A0B;

    public RunnableC004609u(AnonymousClass0A6 r1, C07490ss r2, Object obj, C004809w r4, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.A06 = r1;
        this.A02 = r2;
        this.A08 = obj;
        this.A05 = r4;
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
        AnonymousClass0A6 r6 = this.A06;
        C07490ss r0 = this.A02;
        Object obj = this.A08;
        C004809w r4 = this.A05;
        C07490ss<String, View> A012 = AnonymousClass09x.A01(r6, r0, obj, r4);
        if (A012 != null) {
            ArrayList arrayList = this.A09;
            arrayList.addAll(A012.values());
            arrayList.add(this.A01);
        }
        boolean z = this.A0B;
        if (obj != null) {
            r6.A0J(obj, this.A0A, this.A09);
            View A002 = AnonymousClass09x.A00(A012, r4, this.A07, z);
            if (A002 != null) {
                AnonymousClass0A6.A00(A002, this.A00);
            }
        }
    }
}
