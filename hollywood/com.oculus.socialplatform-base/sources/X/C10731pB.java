package X;

import android.view.KeyEvent;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.1pB  reason: invalid class name and case insensitive filesystem */
public class C10731pB implements TextView.OnEditorActionListener {
    public final /* synthetic */ SearchView A00;

    public C10731pB(SearchView searchView) {
        this.A00 = searchView;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        this.A00.onSubmitQuery();
        return true;
    }
}
