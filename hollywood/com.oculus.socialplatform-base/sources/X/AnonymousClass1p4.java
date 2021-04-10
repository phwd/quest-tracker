package X;

import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.1p4  reason: invalid class name */
public class AnonymousClass1p4 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.SearchView$SearchAutoComplete$1";
    public final /* synthetic */ SearchView.SearchAutoComplete A00;

    public AnonymousClass1p4(SearchView.SearchAutoComplete searchAutoComplete) {
        this.A00 = searchAutoComplete;
    }

    public final void run() {
        SearchView.SearchAutoComplete searchAutoComplete = this.A00;
        if (searchAutoComplete.A01) {
            ((InputMethodManager) searchAutoComplete.getContext().getSystemService("input_method")).showSoftInput(searchAutoComplete, 0);
            searchAutoComplete.A01 = false;
        }
    }
}
