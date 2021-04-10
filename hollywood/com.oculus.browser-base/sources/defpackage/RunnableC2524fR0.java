package defpackage;

import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.SearchView;

/* renamed from: fR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2524fR0 implements Runnable {
    public final /* synthetic */ SearchView.SearchAutoComplete F;

    public RunnableC2524fR0(SearchView.SearchAutoComplete searchAutoComplete) {
        this.F = searchAutoComplete;
    }

    public void run() {
        SearchView.SearchAutoComplete searchAutoComplete = this.F;
        if (searchAutoComplete.K) {
            ((InputMethodManager) searchAutoComplete.getContext().getSystemService("input_method")).showSoftInput(searchAutoComplete, 0);
            searchAutoComplete.K = false;
        }
    }
}
