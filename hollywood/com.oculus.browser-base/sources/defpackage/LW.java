package defpackage;

import androidx.gridlayout.widget.GridLayout;

/* renamed from: LW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LW {

    /* renamed from: a  reason: collision with root package name */
    public static final LW f8420a = GridLayout.r(Integer.MIN_VALUE, 1, GridLayout.M);
    public final boolean b;
    public final HW c;
    public final BW d;
    public final float e;

    public LW(boolean z, HW hw, BW bw, float f) {
        this.b = z;
        this.c = hw;
        this.d = bw;
        this.e = f;
    }

    public BW a(boolean z) {
        BW bw = this.d;
        if (bw != GridLayout.M) {
            return bw;
        }
        if (this.e == 0.0f) {
            return z ? GridLayout.R : GridLayout.W;
        }
        return GridLayout.a0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LW.class != obj.getClass()) {
            return false;
        }
        LW lw = (LW) obj;
        return this.d.equals(lw.d) && this.c.equals(lw.c);
    }

    public int hashCode() {
        return this.d.hashCode() + (this.c.hashCode() * 31);
    }

    public LW(boolean z, int i, int i2, BW bw, float f) {
        HW hw = new HW(i, i2 + i);
        this.b = z;
        this.c = hw;
        this.d = bw;
        this.e = f;
    }
}
