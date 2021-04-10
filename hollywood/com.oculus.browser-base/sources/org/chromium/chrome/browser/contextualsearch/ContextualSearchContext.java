package org.chromium.chrome.browser.contextualsearch;

import J.N;
import android.text.TextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class ContextualSearchContext {

    /* renamed from: a  reason: collision with root package name */
    public long f10641a = N.Mz21Bkwn(this);
    public boolean b = false;
    public String c;
    public int d = -1;
    public int e = -1;
    public String f;
    public int g = -1;
    public String h;
    public String i;
    public String j;
    public int k = -1;
    public int l = -1;
    public String m;
    public int n = -1;
    public String o;
    public int p = -1;
    public String q = "";
    public String r = "";

    public final int a(int i2) {
        while (i2 < this.c.length()) {
            if (d(i2)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public final int b(int i2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (d(i3)) {
                return i3 + 1;
            }
        }
        return -1;
    }

    public String c() {
        if (this.f == null) {
            this.f = N.Mi_5NNCP(this.f10641a, this);
        }
        return this.f;
    }

    public final boolean d(int i2) {
        return !Character.isLetterOrDigit(this.c.charAt(i2)) && this.c.charAt(i2) != 173;
    }

    public void e(int i2, int i3) {
        this.d += i2;
        this.e += i3;
        g();
        N.M2pdefbB(getNativePointer(), this, i2, i3);
        f();
    }

    public abstract void f();

    public final void g() {
        int i2;
        int i3;
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.c) && (i2 = this.e) >= (i3 = this.d) && i3 >= 0 && i2 <= this.c.length()) {
            this.h = this.c.substring(this.d, this.e);
        }
    }

    public final long getNativePointer() {
        return this.f10641a;
    }
}
