package org.chromium.chrome.browser.profiles;

import J.N;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.cookies.CookiesFetcher;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Profile implements BrowserContextHandle {

    /* renamed from: a  reason: collision with root package name */
    public final OTRProfileID f10752a;
    public long b;

    public Profile(long j) {
        this.b = j;
        if (N.MEt51B0E(j, this)) {
            this.f10752a = (OTRProfileID) N.MyopTl49(this.b, this);
        } else {
            this.f10752a = null;
        }
    }

    public static Profile a(WebContents webContents) {
        return (Profile) N.MvvJTucy(webContents);
    }

    public static Profile b() {
        Object obj = ThreadUtils.f10596a;
        if (ProfileManager.b) {
            return (Profile) N.M_3GXkeQ();
        }
        throw new IllegalStateException("Browser hasn't finished initialization yet!");
    }

    public static Profile create(long j) {
        return new Profile(j);
    }

    public Profile c() {
        return (Profile) N.Mhxya0Qy(this.b, this);
    }

    public ProfileKey d() {
        return (ProfileKey) N.MjGj0xKY(this.b, this);
    }

    public boolean e() {
        return N.MrGvO7pv(this.b, this);
    }

    public boolean f() {
        return N.MBL3czGJ(this.b, this);
    }

    public boolean g() {
        return this.f10752a != null;
    }

    @Override // org.chromium.components.embedder_support.browser_context.BrowserContextHandle
    public long getNativeBrowserContextPointer() {
        return N.MgjF0wyD(this.b);
    }

    public final long getNativePointer() {
        return this.b;
    }

    public final void onNativeDestroyed() {
        this.b = 0;
        if (this.f10752a != null) {
            CookiesFetcher.a();
        }
        Iterator it = ProfileManager.f10754a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1818bH0) uq0.next()).i(this);
            } else {
                return;
            }
        }
    }
}
