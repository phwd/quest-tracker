package X;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.1p7  reason: invalid class name and case insensitive filesystem */
public class View$OnFocusChangeListenerC10691p7 implements View.OnFocusChangeListener {
    public final /* synthetic */ SearchView A00;

    public View$OnFocusChangeListenerC10691p7(SearchView searchView) {
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
