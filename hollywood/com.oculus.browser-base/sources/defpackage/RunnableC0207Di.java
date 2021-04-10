package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;

/* renamed from: Di  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0207Di implements Runnable {
    public final C0329Fi F;
    public final BookmarkBridge.BookmarkItem G;

    public RunnableC0207Di(C0329Fi fi, BookmarkBridge.BookmarkItem bookmarkItem) {
        this.F = fi;
        this.G = bookmarkItem;
    }

    public void run() {
        C0329Fi fi = this.F;
        BookmarkBridge.BookmarkItem bookmarkItem = this.G;
        Callback callback = fi.d;
        if (callback != null) {
            callback.onResult(bookmarkItem);
            fi.d = null;
        }
        ((C5638xj) fi.f8034a).p(fi.e, true, 0);
    }
}
