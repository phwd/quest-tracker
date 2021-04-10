package defpackage;

import J.N;
import org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader$FetchFaviconCallback;

/* renamed from: Jw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0603Jw0 implements PartnerBookmarksReader$FetchFaviconCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f8328a;
    public final /* synthetic */ C0785Mw0 b;

    public C0603Jw0(C0785Mw0 mw0, String str) {
        this.b = mw0;
        this.f8328a = str;
    }

    @Override // org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader$FetchFaviconCallback
    public void onFaviconFetch() {
        synchronized (this.b.e) {
            this.b.f++;
        }
    }

    @Override // org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader$FetchFaviconCallback
    public void onFaviconFetched(int i) {
        AbstractC3364kK0.g("PartnerBookmark.FaviconThrottleFetchResult", i, 8);
        synchronized (this.b.e) {
            boolean z = true;
            if (i == 6) {
                this.b.h = true;
                for (AbstractC0664Kw0 kw0 : C0785Mw0.f8512a) {
                    kw0.a(N.McXhQJZC(this.f8328a));
                }
            }
            this.b.b.b(this.f8328a, i);
            C0785Mw0 mw0 = this.b;
            int i2 = mw0.f - 1;
            mw0.f = i2;
            if (i2 != 0 || !mw0.i || !mw0.j) {
                z = false;
            }
            if (z) {
                mw0.b();
            }
        }
    }
}
