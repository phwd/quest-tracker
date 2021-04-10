package X;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.Fragment;

/* renamed from: X.09t  reason: invalid class name and case insensitive filesystem */
public class RunnableC004509t implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$5";
    public final /* synthetic */ Rect A00;
    public final /* synthetic */ View A01;
    public final /* synthetic */ C07490ss A02;
    public final /* synthetic */ Fragment A03;
    public final /* synthetic */ Fragment A04;
    public final /* synthetic */ AnonymousClass0A6 A05;

    public RunnableC004509t(Fragment fragment, Fragment fragment2, C07490ss r3, View view, AnonymousClass0A6 r5, Rect rect) {
        this.A03 = fragment;
        this.A04 = fragment2;
        this.A02 = r3;
        this.A01 = view;
        this.A05 = r5;
        this.A00 = rect;
    }

    public final void run() {
        View view = this.A01;
        if (view != null) {
            AnonymousClass0A6.A00(view, this.A00);
        }
    }
}
