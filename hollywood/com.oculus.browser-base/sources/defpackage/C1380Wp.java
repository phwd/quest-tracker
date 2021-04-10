package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.offlinepages.ClientId;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Wp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1380Wp extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final BookmarkBridge.BookmarkItem f9174a;
    public final Tab b;
    public final C0695Li c;

    public C1380Wp(BookmarkBridge.BookmarkItem bookmarkItem, Tab tab, C0695Li li) {
        this.f9174a = bookmarkItem;
        this.b = tab;
        this.c = li;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        BookmarkId bookmarkId;
        BookmarkBridge.BookmarkItem bookmarkItem = this.f9174a;
        Tab tab = this.b;
        C0695Li li = this.c;
        BookmarkId bookmarkId2 = (BookmarkId) obj;
        int i = ChromeActivity.o0;
        if (bookmarkItem == null) {
            bookmarkId = null;
        } else {
            bookmarkId = bookmarkItem.c;
        }
        if (bookmarkId2 != null && !bookmarkId2.equals(bookmarkId)) {
            C1446Xr0 xr0 = AbstractC2254ds0.f9814a;
            WebContents l = tab.l();
            if (!(tab.p() || C3372kO0.W(tab) || l == null || l.g() || l.a())) {
                C1446Xr0 b2 = AbstractC2254ds0.b();
                Profile a2 = Profile.a(tab.l());
                Objects.requireNonNull(b2);
                OfflinePageBridge a3 = OfflinePageBridge.a(a2);
                if (a3 != null) {
                    a3.b(tab.l(), new ClientId("bookmark", bookmarkId2.toString()), new C1202Tr0());
                }
            }
        }
        li.a();
    }
}
