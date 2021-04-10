package defpackage;

import android.view.View;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;

/* renamed from: Xq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Xq1 implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final UrlBarApi26 f9238a;
    public final Callback b;

    public Xq1(UrlBarApi26 urlBarApi26, Callback callback) {
        this.f9238a = urlBarApi26;
        this.b = callback;
    }

    public void onFocusChange(View view, boolean z) {
        UrlBarApi26 urlBarApi26 = this.f9238a;
        Callback callback = this.b;
        if (z) {
            urlBarApi26.j(false);
        }
        callback.onResult(Boolean.valueOf(z));
    }
}
