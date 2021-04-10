package X;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.05E  reason: invalid class name */
public class AnonymousClass05E implements View.OnClickListener {
    public final /* synthetic */ SearchView A00;

    public AnonymousClass05E(SearchView searchView) {
        this.A00 = searchView;
    }

    public final void onClick(View view) {
        SearchView searchView = this.A00;
        if (view == searchView.mSearchButton) {
            searchView.onSearchClicked();
        } else if (view == searchView.mCloseButton) {
            searchView.onCloseClicked();
        } else if (view == searchView.mGoButton) {
            searchView.onSubmitQuery();
        } else if (view == searchView.mVoiceButton) {
            searchView.onVoiceClicked();
        } else if (view == searchView.mSearchSrcTextView) {
            searchView.forceSuggestionQuery();
        }
    }
}
