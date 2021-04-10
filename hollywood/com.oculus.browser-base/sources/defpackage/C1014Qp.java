package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Qp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1014Qp extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ChromeActivity f8787a;

    public C1014Qp(ChromeActivity chromeActivity) {
        this.f8787a = chromeActivity;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ChromeActivity chromeActivity = this.f8787a;
        Profile profile = (Profile) obj;
        BookmarkBridge bookmarkBridge = (BookmarkBridge) chromeActivity.r0.H;
        if (bookmarkBridge != null) {
            bookmarkBridge.a();
        }
        chromeActivity.r0.m(profile == null ? null : new BookmarkBridge(profile));
    }
}
