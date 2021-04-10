package X;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.05F  reason: invalid class name */
public class AnonymousClass05F implements View.OnKeyListener {
    public final /* synthetic */ SearchView A00;

    public AnonymousClass05F(SearchView searchView) {
        this.A00 = searchView;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        SearchView searchView = this.A00;
        if (searchView.mSearchable != null) {
            if (searchView.mSearchSrcTextView.isPopupShowing() && searchView.mSearchSrcTextView.getListSelection() != -1) {
                return searchView.onSuggestionsKey(view, i, keyEvent);
            }
            if (TextUtils.getTrimmedLength(searchView.mSearchSrcTextView.getText()) != 0 && keyEvent.hasNoModifiers() && keyEvent.getAction() == 1 && i == 66) {
                view.cancelLongPress();
                searchView.launchQuerySearch(0, null, searchView.mSearchSrcTextView.getText().toString());
                return true;
            }
        }
        return false;
    }
}
