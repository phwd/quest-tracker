package X;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.1pA  reason: invalid class name and case insensitive filesystem */
public class C10721pA implements TextWatcher {
    public final /* synthetic */ SearchView A00;

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public C10721pA(SearchView searchView) {
        this.A00 = searchView;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.A00.onTextChanged(charSequence);
    }
}
