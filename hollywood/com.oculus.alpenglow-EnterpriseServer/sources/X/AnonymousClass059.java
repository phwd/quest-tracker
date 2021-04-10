package X;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.059  reason: invalid class name */
public class AnonymousClass059 implements TextWatcher {
    public final /* synthetic */ SearchView A00;

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public AnonymousClass059(SearchView searchView) {
        this.A00 = searchView;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.A00.onTextChanged(charSequence);
    }
}
