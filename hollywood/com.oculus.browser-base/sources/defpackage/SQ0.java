package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import androidx.appcompat.widget.SearchView;

/* renamed from: SQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SQ0 implements TextWatcher {
    public final /* synthetic */ SearchView F;

    public SQ0(SearchView searchView) {
        this.F = searchView;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        SearchView searchView = this.F;
        Editable text = searchView.V.getText();
        searchView.A0 = text;
        TextUtils.isEmpty(text);
        searchView.d0.setVisibility(8);
        searchView.f0.setVisibility(8);
        searchView.y();
        searchView.b0.setVisibility(8);
        if (searchView.r0 != null && !TextUtils.equals(charSequence, searchView.z0)) {
            searchView.r0.onQueryTextChange(charSequence.toString());
        }
        searchView.z0 = charSequence.toString();
    }
}
