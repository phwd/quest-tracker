package org.chromium.chrome.browser.bookmarks;

import J.N;
import android.content.Context;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BookmarkBridge {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10619a;
    public long b;
    public boolean c;
    public final List d = new ArrayList();
    public final C1322Vq0 e = new C1322Vq0();

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class BookmarkItem {

        /* renamed from: a  reason: collision with root package name */
        public final String f10620a;
        public final String b;
        public final BookmarkId c;
        public final boolean d;
        public final BookmarkId e;
        public final boolean f;
        public final boolean g;
        public boolean h;

        public BookmarkItem(BookmarkId bookmarkId, String str, String str2, boolean z, BookmarkId bookmarkId2, boolean z2, boolean z3, long j, boolean z4) {
            this.c = bookmarkId;
            this.f10620a = str;
            this.b = str2;
            this.d = z;
            this.e = bookmarkId2;
            this.f = z2;
            this.g = z3;
            this.h = z4;
        }

        public boolean a() {
            return this.f;
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface BookmarksCallback {
        void onBookmarksAvailable(BookmarkId bookmarkId, List list);

        void onBookmarksFolderHierarchyAvailable(BookmarkId bookmarkId, List list);
    }

    public BookmarkBridge(Profile profile) {
        Object obj = ThreadUtils.f10596a;
        long MTRUIEfD = N.MTRUIEfD(this, profile);
        this.b = MTRUIEfD;
        this.f10619a = N.MHTPaGlQ(MTRUIEfD, this);
    }

    public static void addToBookmarkIdList(List list, long j, int i) {
        list.add(new BookmarkId(j, i));
    }

    public static void addToBookmarkIdListWithDepth(List list, long j, int i, List list2, int i2) {
        list.add(new BookmarkId(j, i));
        list2.add(Integer.valueOf(i2));
    }

    public static void addToList(List list, BookmarkItem bookmarkItem) {
        list.add(bookmarkItem);
    }

    public static BookmarkItem createBookmarkItem(long j, int i, String str, String str2, boolean z, long j2, int i2, boolean z2, boolean z3, long j3, boolean z4) {
        return new BookmarkItem(new BookmarkId(j, i), str, str2, z, new BookmarkId(j2, i2), z2, z3, j3, z4);
    }

    public void a() {
        long j = this.b;
        if (j != 0) {
            N.M$aEU5TZ(j, this);
            this.b = 0;
            this.c = false;
            this.d.clear();
        }
        this.e.clear();
    }

    public boolean b(Runnable runnable) {
        boolean z = true;
        if (this.c) {
            runnable.run();
            return true;
        }
        this.e.b(new C0451Hi(this, SystemClock.elapsedRealtime(), runnable));
        Context applicationContext = ContextUtils.getApplicationContext();
        if (!AbstractC0846Nw0.f8585a) {
            AbstractC0846Nw0.f8585a = true;
            C0785Mw0 mw0 = new C0785Mw0(applicationContext, PartnerBrowserCustomizations.c());
            if ((applicationContext.getApplicationInfo().flags & 1) != 1) {
                z = false;
            }
            if (!z) {
                mw0.a();
            } else if (mw0.c != 0) {
                C0724Lw0 lw0 = new C0724Lw0(mw0, null);
                Executor executor = AbstractC2032cb.f9616a;
                lw0.f();
                ((ExecutorC1463Ya) executor).execute(lw0.e);
            }
        }
        return false;
    }

    public final void bookmarkAllUserNodesRemoved() {
        Iterator it = this.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0512Ii) uq0.next()).a();
            } else {
                return;
            }
        }
    }

    public final void bookmarkModelChanged() {
        if (!this.f10619a) {
            Iterator it = this.e.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0512Ii) uq0.next()).a();
                } else {
                    return;
                }
            }
        }
    }

    public final void bookmarkModelLoaded() {
        this.c = true;
        Iterator it = this.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC0512Ii) uq0.next()).b();
        }
        if (!this.d.isEmpty()) {
            for (int i = 0; i < this.d.size(); i++) {
                C0573Ji ji = (C0573Ji) this.d.get(i);
                int i2 = ji.c;
                if (i2 == 0) {
                    BookmarkBridge bookmarkBridge = ji.d;
                    BookmarkId bookmarkId = ji.b;
                    BookmarksCallback bookmarksCallback = ji.f8309a;
                    Objects.requireNonNull(bookmarkBridge);
                    Object obj = ThreadUtils.f10596a;
                    if (bookmarkBridge.c) {
                        N.M4_aKMtg(bookmarkBridge.b, bookmarkBridge, bookmarkId, bookmarksCallback, new ArrayList());
                    } else {
                        bookmarkBridge.d.add(new C0573Ji(bookmarkId, bookmarksCallback, 0, bookmarkBridge, null));
                    }
                } else if (i2 == 1) {
                    BookmarkBridge bookmarkBridge2 = ji.d;
                    BookmarkId bookmarkId2 = ji.b;
                    BookmarksCallback bookmarksCallback2 = ji.f8309a;
                    Objects.requireNonNull(bookmarkBridge2);
                    Object obj2 = ThreadUtils.f10596a;
                    if (bookmarkBridge2.c) {
                        N.MbzcH$4D(bookmarkBridge2.b, bookmarkBridge2, bookmarkId2, bookmarksCallback2, new ArrayList());
                    } else {
                        bookmarkBridge2.d.add(new C0573Ji(bookmarkId2, bookmarksCallback2, 1, bookmarkBridge2, null));
                    }
                }
            }
            this.d.clear();
        }
    }

    public final void bookmarkNodeAdded(BookmarkItem bookmarkItem, int i) {
        if (!this.f10619a) {
            Iterator it = this.e.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0512Ii) uq0.next()).a();
                } else {
                    return;
                }
            }
        }
    }

    public final void bookmarkNodeChanged(BookmarkItem bookmarkItem) {
        if (!this.f10619a) {
            Iterator it = this.e.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0512Ii) uq0.next()).a();
                } else {
                    return;
                }
            }
        }
    }

    public final void bookmarkNodeChildrenReordered(BookmarkItem bookmarkItem) {
        if (!this.f10619a) {
            Iterator it = this.e.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0512Ii) uq0.next()).a();
                } else {
                    return;
                }
            }
        }
    }

    public final void bookmarkNodeMoved(BookmarkItem bookmarkItem, int i, BookmarkItem bookmarkItem2, int i2) {
        if (!this.f10619a) {
            Iterator it = this.e.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0512Ii) uq0.next()).a();
                } else {
                    return;
                }
            }
        }
    }

    public final void bookmarkNodeRemoved(BookmarkItem bookmarkItem, int i, BookmarkItem bookmarkItem2) {
        Iterator it = this.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                AbstractC0512Ii ii = (AbstractC0512Ii) uq0.next();
                boolean z = this.f10619a;
                Objects.requireNonNull(ii);
                if (!z) {
                    ii.a();
                }
            } else {
                return;
            }
        }
    }

    public BookmarkItem c(BookmarkId bookmarkId) {
        Object obj = ThreadUtils.f10596a;
        return (BookmarkItem) N.M4Ir5snM(this.b, this, bookmarkId.getId(), bookmarkId.getType());
    }

    public BookmarkId d() {
        Object obj = ThreadUtils.f10596a;
        return (BookmarkId) N.M7yxRJ0Q(this.b, this);
    }

    public final void destroyFromNative() {
        a();
    }

    public boolean e(Tab tab) {
        Object obj = ThreadUtils.f10596a;
        if (tab.G()) {
            return false;
        }
        long j = this.b;
        if (j == 0 || N.MUjtS5c8(j, this, tab.l(), false) == -1) {
            return false;
        }
        return true;
    }

    public final void editBookmarksEnabledChanged() {
        Iterator it = this.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0512Ii) uq0.next()).a();
            } else {
                return;
            }
        }
    }

    public final void extensiveBookmarkChangesBeginning() {
        this.f10619a = true;
    }

    public final void extensiveBookmarkChangesEnded() {
        this.f10619a = false;
        bookmarkModelChanged();
    }

    public boolean f() {
        Object obj = ThreadUtils.f10596a;
        long j = this.b;
        if (j == 0) {
            return false;
        }
        return N.M9xtlU8J(j);
    }

    public boolean g(BookmarkId bookmarkId) {
        Object obj = ThreadUtils.f10596a;
        return N.MCNIYDWB(this.b, this, bookmarkId.getId(), bookmarkId.getType());
    }
}
