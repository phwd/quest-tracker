package defpackage;

import android.view.View;

/* renamed from: z80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5885z80 {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC4308pt0 f11725a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;

    public C5885z80() {
        d();
    }

    public void a() {
        int i;
        if (this.d) {
            i = this.f11725a.g();
        } else {
            i = this.f11725a.k();
        }
        this.c = i;
    }

    public void b(View view, int i) {
        if (this.d) {
            this.c = this.f11725a.m() + this.f11725a.b(view);
        } else {
            this.c = this.f11725a.e(view);
        }
        this.b = i;
    }

    public void c(View view, int i) {
        int m = this.f11725a.m();
        if (m >= 0) {
            b(view, i);
            return;
        }
        this.b = i;
        if (this.d) {
            int g = (this.f11725a.g() - m) - this.f11725a.b(view);
            this.c = this.f11725a.g() - g;
            if (g > 0) {
                int c2 = this.c - this.f11725a.c(view);
                int k = this.f11725a.k();
                int min = c2 - (Math.min(this.f11725a.e(view) - k, 0) + k);
                if (min < 0) {
                    this.c = Math.min(g, -min) + this.c;
                    return;
                }
                return;
            }
            return;
        }
        int e2 = this.f11725a.e(view);
        int k2 = e2 - this.f11725a.k();
        this.c = e2;
        if (k2 > 0) {
            int g2 = (this.f11725a.g() - Math.min(0, (this.f11725a.g() - m) - this.f11725a.b(view))) - (this.f11725a.c(view) + e2);
            if (g2 < 0) {
                this.c -= Math.min(k2, -g2);
            }
        }
    }

    public void d() {
        this.b = -1;
        this.c = Integer.MIN_VALUE;
        this.d = false;
        this.e = false;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("AnchorInfo{mPosition=");
        i.append(this.b);
        i.append(", mCoordinate=");
        i.append(this.c);
        i.append(", mLayoutFromEnd=");
        i.append(this.d);
        i.append(", mValid=");
        i.append(this.e);
        i.append('}');
        return i.toString();
    }
}
