package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import org.chromium.chrome.browser.bookmarks.BookmarkTextInputLayout;

/* renamed from: Oi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0877Oi implements TextWatcher {
    public final /* synthetic */ BookmarkTextInputLayout F;

    public C0877Oi(BookmarkTextInputLayout bookmarkTextInputLayout) {
        this.F = bookmarkTextInputLayout;
    }

    public void afterTextChanged(Editable editable) {
        BookmarkTextInputLayout bookmarkTextInputLayout = this.F;
        if (bookmarkTextInputLayout.l1 != null) {
            bookmarkTextInputLayout.w(TextUtils.isEmpty(bookmarkTextInputLayout.f9696J.getText().toString().trim()) ? bookmarkTextInputLayout.l1 : null);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
