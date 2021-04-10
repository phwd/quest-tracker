package X;

import androidx.appcompat.widget.SearchView;

/* renamed from: X.05B  reason: invalid class name */
public class AnonymousClass05B implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.SearchView$2";
    public final /* synthetic */ SearchView A00;

    public AnonymousClass05B(SearchView searchView) {
        this.A00 = searchView;
    }

    public final void run() {
        AbstractC03680cx r1 = this.A00.mSuggestionsAdapter;
        if (r1 instanceof AnonymousClass0H0) {
            r1.A1g(null);
        }
    }
}
