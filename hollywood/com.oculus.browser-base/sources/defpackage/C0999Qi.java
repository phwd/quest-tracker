package defpackage;

import android.app.Activity;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;

/* renamed from: Qi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0999Qi extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f8780a;
    public final Tab b;
    public final View$OnClickListenerC5098uY0 c;
    public final C0695Li d;
    public final Activity e;
    public final boolean f;

    public C0999Qi(Callback callback, Tab tab, View$OnClickListenerC5098uY0 uy0, C0695Li li, Activity activity, boolean z) {
        this.f8780a = callback;
        this.b = tab;
        this.c = uy0;
        this.d = li;
        this.e = activity;
        this.f = z;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        BookmarkId bookmarkId;
        Callback callback = this.f8780a;
        Tab tab = this.b;
        View$OnClickListenerC5098uY0 uy0 = this.c;
        C0695Li li = this.d;
        Activity activity = this.e;
        boolean z = this.f;
        BookmarkBridge.BookmarkItem bookmarkItem = (BookmarkBridge.BookmarkItem) obj;
        if (bookmarkItem == null) {
            callback.onResult(null);
            return;
        }
        if (bookmarkItem.c.getType() == 2) {
            bookmarkId = AbstractC1243Ui.b(tab.n(), tab.getTitle(), uy0, li, activity);
        } else {
            bookmarkId = AbstractC1243Ui.a(li, tab, uy0, activity, z);
        }
        AbstractC3364kK0.g("Bookmarks.BottomSheet.DestinationFolder", bookmarkItem.c.getType(), 3);
        callback.onResult(bookmarkId);
    }
}
