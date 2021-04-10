package defpackage;

import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: XK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class XK0 {
    public static final List F = Collections.emptyList();
    public final View G;
    public WeakReference H;
    public int I = -1;

    /* renamed from: J  reason: collision with root package name */
    public int f9202J = -1;
    public long K = -1;
    public int L = -1;
    public int M = -1;
    public XK0 N = null;
    public XK0 O = null;
    public int P;
    public List Q = null;
    public List R = null;
    public int S = 0;
    public PK0 T = null;
    public boolean U = false;
    public int V = 0;
    public int W = -1;
    public RecyclerView X;
    public AbstractC5750yK0 Y;

    public XK0(View view) {
        if (view != null) {
            this.G = view;
            return;
        }
        throw new IllegalArgumentException("itemView may not be null");
    }

    public void a(Object obj) {
        if (obj == null) {
            b(1024);
        } else if ((1024 & this.P) == 0) {
            if (this.Q == null) {
                ArrayList arrayList = new ArrayList();
                this.Q = arrayList;
                this.R = Collections.unmodifiableList(arrayList);
            }
            this.Q.add(obj);
        }
    }

    public void b(int i) {
        this.P = i | this.P;
    }

    public void c() {
        this.f9202J = -1;
        this.M = -1;
    }

    public void d() {
        this.P &= -33;
    }

    public final int e() {
        RecyclerView recyclerView = this.X;
        if (recyclerView == null) {
            return -1;
        }
        return recyclerView.I(this);
    }

    @Deprecated
    public final int f() {
        RecyclerView recyclerView;
        AbstractC5750yK0 yk0;
        int I2;
        if (this.Y == null || (recyclerView = this.X) == null || (yk0 = recyclerView.T) == null || (I2 = recyclerView.I(this)) == -1 || this.Y != yk0) {
            return -1;
        }
        return I2;
    }

    public final int g() {
        int i = this.M;
        return i == -1 ? this.I : i;
    }

    public List h() {
        if ((this.P & 1024) != 0) {
            return F;
        }
        List list = this.Q;
        if (list == null || list.size() == 0) {
            return F;
        }
        return this.R;
    }

    public boolean i(int i) {
        return (i & this.P) != 0;
    }

    public boolean j() {
        return (this.G.getParent() == null || this.G.getParent() == this.X) ? false : true;
    }

    public boolean k() {
        return (this.P & 1) != 0;
    }

    public boolean l() {
        return (this.P & 4) != 0;
    }

    public final boolean m() {
        if ((this.P & 16) == 0) {
            View view = this.G;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (!view.hasTransientState()) {
                return true;
            }
        }
        return false;
    }

    public boolean n() {
        return (this.P & 8) != 0;
    }

    public boolean o() {
        return this.T != null;
    }

    public boolean p() {
        return (this.P & 256) != 0;
    }

    public boolean q() {
        return (this.P & 2) != 0;
    }

    public void r(int i, boolean z) {
        if (this.f9202J == -1) {
            this.f9202J = this.I;
        }
        if (this.M == -1) {
            this.M = this.I;
        }
        if (z) {
            this.M += i;
        }
        this.I += i;
        if (this.G.getLayoutParams() != null) {
            ((JK0) this.G.getLayoutParams()).c = true;
        }
    }

    public void s() {
        this.P = 0;
        this.I = -1;
        this.f9202J = -1;
        this.K = -1;
        this.M = -1;
        this.S = 0;
        this.N = null;
        this.O = null;
        List list = this.Q;
        if (list != null) {
            list.clear();
        }
        this.P &= -1025;
        this.V = 0;
        this.W = -1;
        RecyclerView.l(this);
    }

    public void t(int i, int i2) {
        this.P = (i & i2) | (this.P & (~i2));
    }

    public String toString() {
        StringBuilder j = AbstractC2531fV.j(getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName(), "{");
        j.append(Integer.toHexString(hashCode()));
        j.append(" position=");
        j.append(this.I);
        j.append(" id=");
        j.append(this.K);
        j.append(", oldPos=");
        j.append(this.f9202J);
        j.append(", pLpos:");
        j.append(this.M);
        StringBuilder sb = new StringBuilder(j.toString());
        if (o()) {
            sb.append(" scrap ");
            sb.append(this.U ? "[changeScrap]" : "[attachedScrap]");
        }
        if (l()) {
            sb.append(" invalid");
        }
        if (!k()) {
            sb.append(" unbound");
        }
        boolean z = true;
        if ((this.P & 2) != 0) {
            sb.append(" update");
        }
        if (n()) {
            sb.append(" removed");
        }
        if (v()) {
            sb.append(" ignored");
        }
        if (p()) {
            sb.append(" tmpDetached");
        }
        if (!m()) {
            StringBuilder i = AbstractC2531fV.i(" not recyclable(");
            i.append(this.S);
            i.append(")");
            sb.append(i.toString());
        }
        if ((this.P & 512) == 0 && !l()) {
            z = false;
        }
        if (z) {
            sb.append(" undefined adapter position");
        }
        if (this.G.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }

    public final void u(boolean z) {
        int i = this.S;
        int i2 = z ? i - 1 : i + 1;
        this.S = i2;
        if (i2 < 0) {
            this.S = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
        } else if (!z && i2 == 1) {
            this.P |= 16;
        } else if (z && i2 == 0) {
            this.P &= -17;
        }
    }

    public boolean v() {
        return (this.P & 128) != 0;
    }

    public boolean w() {
        return (this.P & 32) != 0;
    }
}
