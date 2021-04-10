package X;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.05C  reason: invalid class name */
public class AnonymousClass05C implements View.OnFocusChangeListener {
    public final /* synthetic */ SearchView A00;

    public AnonymousClass05C(SearchView searchView) {
        this.A00 = searchView;
    }

    public final void onFocusChange(View view, boolean z) {
        SearchView searchView = this.A00;
        View.OnFocusChangeListener onFocusChangeListener = searchView.mOnQueryTextFocusChangeListener;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(searchView, z);
        }
    }
}
