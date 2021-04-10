package defpackage;

import org.chromium.chrome.browser.bookmarks.BookmarkBridge;

/* renamed from: D9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class D9 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final F9 f7871a;

    public D9(F9 f9) {
        this.f7871a = f9;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f7871a.t = (BookmarkBridge) obj;
    }
}
