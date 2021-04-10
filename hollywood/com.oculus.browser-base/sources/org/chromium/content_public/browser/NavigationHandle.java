package org.chromium.content_public.browser;

import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NavigationHandle {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10940a;
    public final boolean b;
    public final boolean c;
    public Integer d;
    public GURL e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public int j;
    public int k;

    public NavigationHandle(long j2, GURL gurl, boolean z, boolean z2, boolean z3) {
        this.e = gurl;
        this.f10940a = z;
        this.c = z2;
        this.b = z3;
    }

    public void didFinish(GURL gurl, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i2, int i3, int i4) {
        Integer num;
        this.e = gurl;
        this.g = z;
        this.f = z2;
        this.h = z3;
        this.i = z5;
        if (i2 == -1) {
            num = null;
        } else {
            num = Integer.valueOf(i2);
        }
        this.d = num;
        this.j = i3;
        this.k = i4;
    }

    public final void didRedirect(GURL gurl) {
        this.e = gurl;
    }

    public final void release() {
    }
}
