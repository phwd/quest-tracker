package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;

/* renamed from: sk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4793sk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Uk1 f11296a;

    public C4793sk1(Uk1 uk1) {
        this.f11296a = uk1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Uk1 uk1 = this.f11296a;
        BookmarkBridge bookmarkBridge = (BookmarkBridge) obj;
        Objects.requireNonNull(uk1);
        if (bookmarkBridge != null) {
            bookmarkBridge.e.b(uk1.g0);
        }
    }
}
