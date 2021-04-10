package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;

/* renamed from: Hi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0451Hi extends AbstractC0512Ii {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f8175a;
    public final /* synthetic */ Runnable b;
    public final /* synthetic */ BookmarkBridge c;

    public C0451Hi(BookmarkBridge bookmarkBridge, long j, Runnable runnable) {
        this.c = bookmarkBridge;
        this.f8175a = j;
        this.b = runnable;
    }

    @Override // defpackage.AbstractC0512Ii
    public void a() {
    }

    @Override // defpackage.AbstractC0512Ii
    public void b() {
        this.c.e.c(this);
        AbstractC3364kK0.k("PartnerBookmark.LoadingTime", SystemClock.elapsedRealtime() - this.f8175a);
        this.b.run();
    }
}
