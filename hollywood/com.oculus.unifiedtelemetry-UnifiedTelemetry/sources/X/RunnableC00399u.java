package X;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* renamed from: X.9u  reason: invalid class name and case insensitive filesystem */
public class RunnableC00399u implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$6";
    public final /* synthetic */ Rect A00;
    public final /* synthetic */ View A01;
    public final /* synthetic */ C0298aW A02;
    public final /* synthetic */ Fragment A03;
    public final /* synthetic */ Fragment A04;
    public final /* synthetic */ C00419w A05;
    public final /* synthetic */ A6 A06;
    public final /* synthetic */ Object A07;
    public final /* synthetic */ Object A08;
    public final /* synthetic */ ArrayList A09;
    public final /* synthetic */ ArrayList A0A;
    public final /* synthetic */ boolean A0B;

    public RunnableC00399u(A6 a6, C0298aW aWVar, Object obj, C00419w r4, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.A06 = a6;
        this.A02 = aWVar;
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
        A6 a6 = this.A06;
        C0298aW aWVar = this.A02;
        Object obj = this.A08;
        aWVar.clear();
        if (obj != null) {
            a6.A0C(obj, this.A0A, this.A09);
        }
    }
}
