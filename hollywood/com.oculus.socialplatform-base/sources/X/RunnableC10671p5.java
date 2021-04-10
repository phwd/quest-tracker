package X;

import androidx.appcompat.widget.SearchView;

/* renamed from: X.1p5  reason: invalid class name and case insensitive filesystem */
public class RunnableC10671p5 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.SearchView$2";
    public final /* synthetic */ SearchView A00;

    public RunnableC10671p5(SearchView searchView) {
        this.A00 = searchView;
    }

    public final void run() {
        AbstractC05360vQ r1 = this.A00.mSuggestionsAdapter;
        if (r1 instanceof View$OnClickListenerC10641ov) {
            r1.A22(null);
        }
    }
}
