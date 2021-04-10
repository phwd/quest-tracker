package X;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.1p0  reason: invalid class name and case insensitive filesystem */
public class View$OnClickListenerC10661p0 implements View.OnClickListener {
    public final /* synthetic */ SearchView A00;

    public View$OnClickListenerC10661p0(SearchView searchView) {
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
