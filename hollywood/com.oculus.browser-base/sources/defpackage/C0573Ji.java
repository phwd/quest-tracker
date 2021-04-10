package defpackage;

import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.components.bookmarks.BookmarkId;

/* renamed from: Ji  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0573Ji {

    /* renamed from: a  reason: collision with root package name */
    public final BookmarkBridge.BookmarksCallback f8309a;
    public final BookmarkId b;
    public final int c;
    public final BookmarkBridge d;

    public C0573Ji(BookmarkId bookmarkId, BookmarkBridge.BookmarksCallback bookmarksCallback, int i, BookmarkBridge bookmarkBridge, C0451Hi hi) {
        this.b = bookmarkId;
        this.f8309a = bookmarksCallback;
        this.c = i;
        this.d = bookmarkBridge;
    }
}
