package com.oculus.browser;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebVRNavigationDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public Tab f9717a;
    public NavigationHandle b;
    public String c;
    public boolean d;
    public boolean e;
    public int f;

    public WebVRNavigationDescriptor(Tab tab, boolean z, boolean z2) {
        this.f9717a = tab;
        this.c = tab.getUrl().h();
        this.d = z;
        this.e = z2;
        this.f = 0;
    }

    public Tab a() {
        Tab tab = this.f9717a;
        if (tab == null || tab.x()) {
            return null;
        }
        return this.f9717a;
    }

    public boolean b(Tab tab) {
        return this.f == 2 && this.f9717a == tab;
    }

    public int getStatus() {
        return this.f;
    }

    public boolean isDeepLinking() {
        return this.d;
    }

    public boolean isNavigating() {
        return this.f == 2;
    }

    public boolean isNavigationCompleted() {
        return this.f == 3;
    }

    public WebVRNavigationDescriptor(Tab tab, NavigationHandle navigationHandle) {
        this.f9717a = tab;
        this.b = navigationHandle;
        this.c = navigationHandle.e.h();
        this.d = false;
        this.e = false;
    }
}
