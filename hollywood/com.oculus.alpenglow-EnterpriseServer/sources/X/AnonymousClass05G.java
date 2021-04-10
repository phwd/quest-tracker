package X;

import android.view.KeyEvent;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.05G  reason: invalid class name */
public class AnonymousClass05G implements TextView.OnEditorActionListener {
    public final /* synthetic */ SearchView A00;

    public AnonymousClass05G(SearchView searchView) {
        this.A00 = searchView;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        this.A00.onSubmitQuery();
        return true;
    }
}
