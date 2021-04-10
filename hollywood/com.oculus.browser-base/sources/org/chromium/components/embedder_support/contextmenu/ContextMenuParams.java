package org.chromium.components.embedder_support.contextmenu;

import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContextMenuParams {

    /* renamed from: a  reason: collision with root package name */
    public final long f10840a;
    public final GURL b;
    public final GURL c;
    public final String d;
    public final String e;
    public final GURL f;
    public final GURL g;
    public final C2512fL0 h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final int m;
    public final int n;
    public final int o;

    public ContextMenuParams(long j2, int i2, GURL gurl, GURL gurl2, String str, GURL gurl3, GURL gurl4, String str2, C2512fL0 fl0, boolean z, int i3, int i4, int i5) {
        this.f10840a = j2;
        this.b = gurl;
        this.c = gurl2;
        this.d = str;
        this.e = str2;
        this.f = gurl3;
        this.g = gurl4;
        this.h = fl0;
        boolean z2 = true;
        this.i = !gurl2.j();
        this.j = i2 == 1;
        this.k = i2 != 2 ? false : z2;
        this.l = z;
        this.m = i3;
        this.n = i4;
        this.o = i5;
    }

    public static ContextMenuParams create(long j2, int i2, GURL gurl, GURL gurl2, String str, GURL gurl3, GURL gurl4, String str2, GURL gurl5, int i3, boolean z, int i4, int i5, int i6) {
        C2512fL0 fl0;
        if (gurl5.j()) {
            fl0 = null;
        } else {
            fl0 = new C2512fL0(gurl5.h(), i3);
        }
        return new ContextMenuParams(j2, i2, gurl, gurl2, str, gurl3, gurl4, str2, fl0, z, i4, i5, i6);
    }

    public GURL a() {
        if (!this.i || this.c.j()) {
            return this.g;
        }
        return this.c;
    }

    public boolean b() {
        return this.g.g().equals("file");
    }

    public final long getNativePointer() {
        return this.f10840a;
    }
}
