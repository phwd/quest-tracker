package defpackage;

import android.animation.Animator;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.BackStackState;
import androidx.fragment.app.FragmentManagerState;
import androidx.fragment.app.FragmentState;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: KS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KS {
    public ArrayList A;
    public ArrayList B;
    public NS C;
    public Runnable D = new FS(this);

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f8365a = new ArrayList();
    public boolean b;
    public final C2015cT c = new C2015cT();
    public ArrayList d;
    public ArrayList e;
    public final LayoutInflater$Factory2C5935zS f = new LayoutInflater$Factory2C5935zS(this);
    public C0473Hs0 g;
    public final CS h = new CS(this, false);
    public final AtomicInteger i = new AtomicInteger();
    public ConcurrentHashMap j = new ConcurrentHashMap();
    public final DS k = new DS(this);
    public final BS l = new BS(this);
    public int m = -1;
    public C3721mS n;
    public AbstractC5255vS o;
    public AbstractComponentCallbacksC3550lS p;
    public AbstractComponentCallbacksC3550lS q;
    public AbstractC5765yS r = null;
    public AbstractC5765yS s = new ES(this);
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public ArrayList y;
    public ArrayList z;

    public static boolean R(int i2) {
        return Log.isLoggable("FragmentManager", i2);
    }

    public final void A() {
        if (!this.j.isEmpty()) {
            for (AbstractComponentCallbacksC3550lS lSVar : this.j.keySet()) {
                e(lSVar);
                a0(lSVar, lSVar.N());
            }
        }
    }

    public void B(HS hs, boolean z2) {
        if (!z2) {
            if (this.n != null) {
                f();
            } else if (this.w) {
                throw new IllegalStateException("FragmentManager has been destroyed");
            } else {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
        }
        synchronized (this.f8365a) {
            if (this.n != null) {
                this.f8365a.add(hs);
                l0();
            } else if (!z2) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public final void C(boolean z2) {
        if (this.b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.n == null) {
            if (this.w) {
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            throw new IllegalStateException("FragmentManager has not been attached to a host.");
        } else if (Looper.myLooper() == this.n.H.getLooper()) {
            if (!z2) {
                f();
            }
            if (this.y == null) {
                this.y = new ArrayList();
                this.z = new ArrayList();
            }
            this.b = true;
            try {
                G(null, null);
            } finally {
                this.b = false;
            }
        } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean D(boolean z2) {
        C(z2);
        boolean z3 = false;
        while (M(this.y, this.z)) {
            this.b = true;
            try {
                h0(this.y, this.z);
                g();
                z3 = true;
            } catch (Throwable th) {
                g();
                throw th;
            }
        }
        t0();
        y();
        this.c.b();
        return z3;
    }

    /* JADX INFO: finally extract failed */
    public void E(HS hs, boolean z2) {
        if (!z2 || (this.n != null && !this.w)) {
            C(z2);
            ((C0317Fe) hs).a(this.y, this.z);
            this.b = true;
            try {
                h0(this.y, this.z);
                g();
                t0();
                y();
                this.c.b();
            } catch (Throwable th) {
                g();
                throw th;
            }
        }
    }

    public final void F(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        ArrayList arrayList3 = arrayList2;
        boolean z2 = ((C0317Fe) arrayList.get(i2)).p;
        ArrayList arrayList4 = this.A;
        if (arrayList4 == null) {
            this.A = new ArrayList();
        } else {
            arrayList4.clear();
        }
        this.A.addAll(this.c.g());
        AbstractComponentCallbacksC3550lS lSVar = this.q;
        int i8 = i2;
        boolean z3 = false;
        while (true) {
            int i9 = 1;
            if (i8 < i3) {
                C0317Fe fe = (C0317Fe) arrayList.get(i8);
                int i10 = 3;
                if (!((Boolean) arrayList3.get(i8)).booleanValue()) {
                    ArrayList arrayList5 = this.A;
                    int i11 = 0;
                    while (i11 < fe.f8026a.size()) {
                        C2186dT dTVar = (C2186dT) fe.f8026a.get(i11);
                        int i12 = dTVar.f9783a;
                        if (i12 != i9) {
                            if (i12 == 2) {
                                AbstractComponentCallbacksC3550lS lSVar2 = dTVar.b;
                                int i13 = lSVar2.b0;
                                int size = arrayList5.size() - 1;
                                boolean z4 = false;
                                while (size >= 0) {
                                    AbstractComponentCallbacksC3550lS lSVar3 = (AbstractComponentCallbacksC3550lS) arrayList5.get(size);
                                    if (lSVar3.b0 != i13) {
                                        i7 = i13;
                                    } else if (lSVar3 == lSVar2) {
                                        i7 = i13;
                                        z4 = true;
                                    } else {
                                        if (lSVar3 == lSVar) {
                                            i7 = i13;
                                            fe.f8026a.add(i11, new C2186dT(9, lSVar3));
                                            i11++;
                                            lSVar = null;
                                        } else {
                                            i7 = i13;
                                        }
                                        C2186dT dTVar2 = new C2186dT(3, lSVar3);
                                        dTVar2.c = dTVar.c;
                                        dTVar2.e = dTVar.e;
                                        dTVar2.d = dTVar.d;
                                        dTVar2.f = dTVar.f;
                                        fe.f8026a.add(i11, dTVar2);
                                        arrayList5.remove(lSVar3);
                                        i11++;
                                    }
                                    size--;
                                    i13 = i7;
                                }
                                if (z4) {
                                    fe.f8026a.remove(i11);
                                    i11--;
                                } else {
                                    i6 = 1;
                                    dTVar.f9783a = 1;
                                    arrayList5.add(lSVar2);
                                    i11 += i6;
                                    i9 = i6;
                                    i10 = 3;
                                }
                            } else if (i12 == i10 || i12 == 6) {
                                arrayList5.remove(dTVar.b);
                                AbstractComponentCallbacksC3550lS lSVar4 = dTVar.b;
                                if (lSVar4 == lSVar) {
                                    fe.f8026a.add(i11, new C2186dT(9, lSVar4));
                                    i11++;
                                    lSVar = null;
                                }
                            } else if (i12 == 7) {
                                i6 = 1;
                            } else if (i12 == 8) {
                                fe.f8026a.add(i11, new C2186dT(9, lSVar));
                                i11++;
                                lSVar = dTVar.b;
                            }
                            i6 = 1;
                            i11 += i6;
                            i9 = i6;
                            i10 = 3;
                        } else {
                            i6 = i9;
                        }
                        arrayList5.add(dTVar.b);
                        i11 += i6;
                        i9 = i6;
                        i10 = 3;
                    }
                } else {
                    int i14 = 1;
                    ArrayList arrayList6 = this.A;
                    int size2 = fe.f8026a.size() - 1;
                    while (size2 >= 0) {
                        C2186dT dTVar3 = (C2186dT) fe.f8026a.get(size2);
                        int i15 = dTVar3.f9783a;
                        if (i15 != i14) {
                            if (i15 != 3) {
                                switch (i15) {
                                    case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                                        lSVar = null;
                                        break;
                                    case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                                        lSVar = dTVar3.b;
                                        break;
                                    case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                                        dTVar3.h = dTVar3.g;
                                        break;
                                }
                                size2--;
                                i14 = 1;
                            }
                            arrayList6.add(dTVar3.b);
                            size2--;
                            i14 = 1;
                        }
                        arrayList6.remove(dTVar3.b);
                        size2--;
                        i14 = 1;
                    }
                }
                z3 = z3 || fe.g;
                i8++;
                arrayList3 = arrayList2;
            } else {
                this.A.clear();
                if (!z2) {
                    AbstractC3552lT.o(this, arrayList, arrayList2, i2, i3, false, this.k);
                }
                int i16 = i2;
                while (i16 < i3) {
                    C0317Fe fe2 = (C0317Fe) arrayList.get(i16);
                    if (((Boolean) arrayList2.get(i16)).booleanValue()) {
                        fe2.d(-1);
                        fe2.l(i16 == i3 + -1);
                    } else {
                        fe2.d(1);
                        fe2.k();
                    }
                    i16++;
                }
                if (z2) {
                    C5271va vaVar = new C5271va(0);
                    a(vaVar);
                    int f0 = f0(arrayList, arrayList2, i2, i3, vaVar);
                    X(vaVar);
                    i4 = i2;
                    i5 = f0;
                } else {
                    i4 = i2;
                    i5 = i3;
                }
                if (i5 != i4 && z2) {
                    AbstractC3552lT.o(this, arrayList, arrayList2, i2, i5, true, this.k);
                    Z(this.m, true);
                }
                while (i4 < i3) {
                    C0317Fe fe3 = (C0317Fe) arrayList.get(i4);
                    if (((Boolean) arrayList2.get(i4)).booleanValue() && fe3.s >= 0) {
                        fe3.s = -1;
                    }
                    Objects.requireNonNull(fe3);
                    i4++;
                }
                return;
            }
        }
    }

    public final void G(ArrayList arrayList, ArrayList arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList arrayList3 = this.B;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            JS js = (JS) this.B.get(i2);
            if (arrayList == null || js.f8288a || (indexOf2 = arrayList.indexOf(js.b)) == -1 || arrayList2 == null || !((Boolean) arrayList2.get(indexOf2)).booleanValue()) {
                if ((js.c == 0) || (arrayList != null && js.b.n(arrayList, 0, arrayList.size()))) {
                    this.B.remove(i2);
                    i2--;
                    size--;
                    if (arrayList == null || js.f8288a || (indexOf = arrayList.indexOf(js.b)) == -1 || arrayList2 == null || !((Boolean) arrayList2.get(indexOf)).booleanValue()) {
                        js.a();
                    } else {
                        C0317Fe fe = js.b;
                        fe.q.h(fe, js.f8288a, false, false);
                    }
                }
            } else {
                this.B.remove(i2);
                i2--;
                size--;
                C0317Fe fe2 = js.b;
                fe2.q.h(fe2, js.f8288a, false, false);
            }
            i2++;
        }
    }

    public AbstractComponentCallbacksC3550lS H(String str) {
        return this.c.e(str);
    }

    public AbstractComponentCallbacksC3550lS I(int i2) {
        C2015cT cTVar = this.c;
        int size = cTVar.f9608a.size();
        while (true) {
            size--;
            if (size >= 0) {
                AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) cTVar.f9608a.get(size);
                if (lSVar != null && lSVar.a0 == i2) {
                    return lSVar;
                }
            } else {
                for (C1844bT bTVar : cTVar.b.values()) {
                    if (bTVar != null) {
                        AbstractComponentCallbacksC3550lS lSVar2 = bTVar.b;
                        if (lSVar2.a0 == i2) {
                            return lSVar2;
                        }
                    }
                }
                return null;
            }
        }
    }

    public AbstractComponentCallbacksC3550lS J(String str) {
        C2015cT cTVar = this.c;
        Objects.requireNonNull(cTVar);
        if (str != null) {
            int size = cTVar.f9608a.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) cTVar.f9608a.get(size);
                if (lSVar != null && str.equals(lSVar.c0)) {
                    return lSVar;
                }
            }
        }
        if (str != null) {
            for (C1844bT bTVar : cTVar.b.values()) {
                if (bTVar != null) {
                    AbstractComponentCallbacksC3550lS lSVar2 = bTVar.b;
                    if (str.equals(lSVar2.c0)) {
                        return lSVar2;
                    }
                }
            }
        }
        return null;
    }

    public AbstractComponentCallbacksC3550lS K(String str) {
        for (C1844bT bTVar : this.c.b.values()) {
            if (bTVar != null) {
                AbstractComponentCallbacksC3550lS lSVar = bTVar.b;
                if (!str.equals(lSVar.f10345J)) {
                    lSVar = lSVar.Y.K(str);
                }
                if (lSVar != null) {
                    return lSVar;
                }
            }
        }
        return null;
    }

    public final void L() {
        if (this.B != null) {
            while (!this.B.isEmpty()) {
                ((JS) this.B.remove(0)).a();
            }
        }
    }

    public final boolean M(ArrayList arrayList, ArrayList arrayList2) {
        synchronized (this.f8365a) {
            if (this.f8365a.isEmpty()) {
                return false;
            }
            int size = this.f8365a.size();
            boolean z2 = false;
            for (int i2 = 0; i2 < size; i2++) {
                z2 |= ((HS) this.f8365a.get(i2)).a(arrayList, arrayList2);
            }
            this.f8365a.clear();
            this.n.H.removeCallbacks(this.D);
            return z2;
        }
    }

    public final NS N(AbstractComponentCallbacksC3550lS lSVar) {
        NS ns = this.C;
        NS ns2 = (NS) ns.d.get(lSVar.f10345J);
        if (ns2 != null) {
            return ns2;
        }
        NS ns3 = new NS(ns.f);
        ns.d.put(lSVar.f10345J, ns3);
        return ns3;
    }

    public final ViewGroup O(AbstractComponentCallbacksC3550lS lSVar) {
        if (lSVar.b0 <= 0 || !this.o.b()) {
            return null;
        }
        View a2 = this.o.a(lSVar.b0);
        if (a2 instanceof ViewGroup) {
            return (ViewGroup) a2;
        }
        return null;
    }

    public AbstractC5765yS P() {
        AbstractC5765yS ySVar = this.r;
        if (ySVar != null) {
            return ySVar;
        }
        AbstractComponentCallbacksC3550lS lSVar = this.p;
        if (lSVar != null) {
            return lSVar.W.P();
        }
        return this.s;
    }

    public void Q(AbstractComponentCallbacksC3550lS lSVar) {
        if (R(2)) {
            AbstractC2531fV.p("hide: ", lSVar);
        }
        if (!lSVar.d0) {
            lSVar.d0 = true;
            lSVar.p0 = true ^ lSVar.p0;
            p0(lSVar);
        }
    }

    public final boolean S(AbstractComponentCallbacksC3550lS lSVar) {
        boolean z2;
        if (lSVar.g0 && lSVar.h0) {
            return true;
        }
        KS ks = lSVar.Y;
        Iterator it = ((ArrayList) ks.c.f()).iterator();
        boolean z3 = false;
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            AbstractComponentCallbacksC3550lS lSVar2 = (AbstractComponentCallbacksC3550lS) it.next();
            if (lSVar2 != null) {
                z3 = ks.S(lSVar2);
                continue;
            }
            if (z3) {
                z2 = true;
                break;
            }
        }
        return z2;
    }

    public boolean T(AbstractComponentCallbacksC3550lS lSVar) {
        if (lSVar == null) {
            return true;
        }
        KS ks = lSVar.W;
        if (!lSVar.equals(ks.q) || !T(ks.p)) {
            return false;
        }
        return true;
    }

    public boolean U() {
        return this.u || this.v;
    }

    public void V(AbstractComponentCallbacksC3550lS lSVar) {
        if (!this.c.c(lSVar.f10345J)) {
            C1844bT bTVar = new C1844bT(this.l, lSVar);
            bTVar.a(this.n.G.getClassLoader());
            this.c.b.put(lSVar.f10345J, bTVar);
            bTVar.c = this.m;
            if (R(2)) {
                AbstractC2531fV.p("Added fragment to active set ", lSVar);
            }
        }
    }

    public final void W(C1844bT bTVar) {
        AbstractComponentCallbacksC3550lS lSVar = bTVar.b;
        if (this.c.c(lSVar.f10345J)) {
            if (R(2)) {
                AbstractC2531fV.p("Removed fragment from active set ", lSVar);
            }
            C2015cT cTVar = this.c;
            Objects.requireNonNull(cTVar);
            AbstractComponentCallbacksC3550lS lSVar2 = bTVar.b;
            for (C1844bT bTVar2 : cTVar.b.values()) {
                if (bTVar2 != null) {
                    AbstractComponentCallbacksC3550lS lSVar3 = bTVar2.b;
                    if (lSVar2.f10345J.equals(lSVar3.M)) {
                        lSVar3.L = lSVar2;
                        lSVar3.M = null;
                    }
                }
            }
            cTVar.b.put(lSVar2.f10345J, null);
            String str = lSVar2.M;
            if (str != null) {
                lSVar2.L = cTVar.e(str);
            }
            i0(lSVar);
        }
    }

    public final void X(C5271va vaVar) {
        int i2 = vaVar.N;
        for (int i3 = 0; i3 < i2; i3++) {
            AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) vaVar.M[i3];
            if (!lSVar.P) {
                View Q0 = lSVar.Q0();
                lSVar.q0 = Q0.getAlpha();
                Q0.setAlpha(0.0f);
            }
        }
    }

    public void Y(AbstractComponentCallbacksC3550lS lSVar) {
        ViewGroup viewGroup;
        int indexOfChild;
        int indexOfChild2;
        if (this.c.c(lSVar.f10345J)) {
            a0(lSVar, this.m);
            if (lSVar.k0 != null) {
                C2015cT cTVar = this.c;
                Objects.requireNonNull(cTVar);
                ViewGroup viewGroup2 = lSVar.j0;
                View view = lSVar.k0;
                AbstractComponentCallbacksC3550lS lSVar2 = null;
                if (viewGroup2 != null && view != null) {
                    int indexOf = cTVar.f9608a.indexOf(lSVar);
                    while (true) {
                        indexOf--;
                        if (indexOf < 0) {
                            break;
                        }
                        AbstractComponentCallbacksC3550lS lSVar3 = (AbstractComponentCallbacksC3550lS) cTVar.f9608a.get(indexOf);
                        if (lSVar3.j0 == viewGroup2 && lSVar3.k0 != null) {
                            lSVar2 = lSVar3;
                            break;
                        }
                    }
                }
                if (lSVar2 != null && (indexOfChild2 = viewGroup.indexOfChild(lSVar.k0)) < (indexOfChild = (viewGroup = lSVar.j0).indexOfChild(lSVar2.k0))) {
                    viewGroup.removeViewAt(indexOfChild2);
                    viewGroup.addView(lSVar.k0, indexOfChild);
                }
                if (lSVar.o0 && lSVar.j0 != null) {
                    float f2 = lSVar.q0;
                    if (f2 > 0.0f) {
                        lSVar.k0.setAlpha(f2);
                    }
                    lSVar.q0 = 0.0f;
                    lSVar.o0 = false;
                    C4745sS a2 = AbstractC5085uS.a(this.n.G, this.o, lSVar, true);
                    if (a2 != null) {
                        Animation animation = a2.f11274a;
                        if (animation != null) {
                            lSVar.k0.startAnimation(animation);
                        } else {
                            a2.b.setTarget(lSVar.k0);
                            a2.b.start();
                        }
                    }
                }
            }
            if (lSVar.p0) {
                i(lSVar);
            }
        } else if (R(3)) {
            String str = "Ignoring moving " + lSVar + " to state " + this.m + "since it is not added to " + this;
        }
    }

    public void Z(int i2, boolean z2) {
        C3721mS mSVar;
        if (this.n == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        } else if (z2 || i2 != this.m) {
            this.m = i2;
            for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
                Y(lSVar);
            }
            Iterator it = ((ArrayList) this.c.f()).iterator();
            while (it.hasNext()) {
                AbstractComponentCallbacksC3550lS lSVar2 = (AbstractComponentCallbacksC3550lS) it.next();
                if (lSVar2 != null && !lSVar2.o0) {
                    Y(lSVar2);
                }
            }
            r0();
            if (this.t && (mSVar = this.n) != null && this.m == 4) {
                mSVar.e();
                this.t = false;
            }
        }
    }

    public final void a(C5271va vaVar) {
        int i2 = this.m;
        if (i2 >= 1) {
            int min = Math.min(i2, 3);
            for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
                if (lSVar.G < min) {
                    a0(lSVar, min);
                    if (lSVar.k0 != null && !lSVar.d0 && lSVar.o0) {
                        vaVar.add(lSVar);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00af, code lost:
        if (r2 != 3) goto L_0x07dc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x03e1  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x043d  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a0(defpackage.AbstractComponentCallbacksC3550lS r17, int r18) {
        /*
        // Method dump skipped, instructions count: 2060
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.KS.a0(lS, int):void");
    }

    public void b(AbstractComponentCallbacksC3550lS lSVar) {
        if (R(2)) {
            AbstractC2531fV.p("add: ", lSVar);
        }
        V(lSVar);
        if (!lSVar.e0) {
            this.c.a(lSVar);
            lSVar.Q = false;
            if (lSVar.k0 == null) {
                lSVar.p0 = false;
            }
            if (S(lSVar)) {
                this.t = true;
            }
        }
    }

    public void b0() {
        if (this.n != null) {
            this.u = false;
            this.v = false;
            for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
                if (lSVar != null) {
                    lSVar.Y.b0();
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: lS */
    /* JADX WARN: Multi-variable type inference failed */
    public void c(C3721mS mSVar, AbstractC5255vS vSVar, AbstractComponentCallbacksC3550lS lSVar) {
        if (this.n == null) {
            this.n = mSVar;
            this.o = vSVar;
            this.p = lSVar;
            if (lSVar != 0) {
                t0();
            }
            if (mSVar instanceof AbstractC0534Is0) {
                C0473Hs0 c2 = mSVar.c();
                this.g = c2;
                C3721mS mSVar2 = lSVar != 0 ? lSVar : mSVar;
                CS cs = this.h;
                Objects.requireNonNull(c2);
                AbstractC3499l80 Q = mSVar2.Q();
                if (((C4865t80) Q).b != EnumC3328k80.DESTROYED) {
                    cs.b.add(new C0351Fs0(c2, Q, cs));
                }
            }
            if (lSVar != 0) {
                this.C = lSVar.W.N(lSVar);
            } else if (mSVar instanceof AbstractC4823su1) {
                C4653ru1 L = mSVar.L();
                String canonicalName = NS.class.getCanonicalName();
                if (canonicalName != null) {
                    String f2 = AbstractC2531fV.f("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
                    Object obj = (AbstractC4312pu1) L.f11233a.get(f2);
                    if (!NS.class.isInstance(obj)) {
                        obj = new NS(true);
                        AbstractC4312pu1 pu1 = (AbstractC4312pu1) L.f11233a.put(f2, obj);
                        if (pu1 != null) {
                            pu1.a();
                        }
                    }
                    this.C = (NS) obj;
                    return;
                }
                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
            } else {
                this.C = new NS(false);
            }
        } else {
            throw new IllegalStateException("Already attached");
        }
    }

    public boolean c0() {
        return d0(null, -1, 0);
    }

    public void d(AbstractComponentCallbacksC3550lS lSVar) {
        if (R(2)) {
            AbstractC2531fV.p("attach: ", lSVar);
        }
        if (lSVar.e0) {
            lSVar.e0 = false;
            if (!lSVar.P) {
                this.c.a(lSVar);
                if (R(2)) {
                    AbstractC2531fV.p("add from attach: ", lSVar);
                }
                if (S(lSVar)) {
                    this.t = true;
                }
            }
        }
    }

    public final boolean d0(String str, int i2, int i3) {
        D(false);
        C(true);
        AbstractComponentCallbacksC3550lS lSVar = this.q;
        if (lSVar != null && i2 < 0 && lSVar.w().c0()) {
            return true;
        }
        boolean e0 = e0(this.y, this.z, null, i2, i3);
        if (e0) {
            this.b = true;
            try {
                h0(this.y, this.z);
            } finally {
                g();
            }
        }
        t0();
        y();
        this.c.b();
        return e0;
    }

    public final void e(AbstractComponentCallbacksC3550lS lSVar) {
        HashSet hashSet = (HashSet) this.j.get(lSVar);
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                C3089im imVar = (C3089im) it.next();
                synchronized (imVar) {
                    if (!imVar.f10160a) {
                        imVar.f10160a = true;
                        imVar.c = true;
                        AbstractC2919hm hmVar = imVar.b;
                        if (hmVar != null) {
                            try {
                                hmVar.a();
                            } catch (Throwable th) {
                                synchronized (imVar) {
                                    imVar.c = false;
                                    imVar.notifyAll();
                                    throw th;
                                }
                            }
                        }
                        synchronized (imVar) {
                            imVar.c = false;
                            imVar.notifyAll();
                        }
                    }
                }
            }
            hashSet.clear();
            j(lSVar);
            this.j.remove(lSVar);
        }
    }

    public boolean e0(ArrayList arrayList, ArrayList arrayList2, String str, int i2, int i3) {
        ArrayList arrayList3 = this.d;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.d.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            int i4 = -1;
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    C0317Fe fe = (C0317Fe) this.d.get(size2);
                    if ((str != null && str.equals(fe.i)) || (i2 >= 0 && i2 == fe.s)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        C0317Fe fe2 = (C0317Fe) this.d.get(size2);
                        if ((str == null || !str.equals(fe2.i)) && (i2 < 0 || i2 != fe2.s)) {
                            break;
                        }
                    }
                }
                i4 = size2;
            }
            if (i4 == this.d.size() - 1) {
                return false;
            }
            for (int size3 = this.d.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.d.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public final void f() {
        if (U()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public final int f0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3, C5271va vaVar) {
        boolean z2;
        boolean z3;
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            C0317Fe fe = (C0317Fe) arrayList.get(i5);
            boolean booleanValue = ((Boolean) arrayList2.get(i5)).booleanValue();
            int i6 = 0;
            while (true) {
                z2 = true;
                if (i6 >= fe.f8026a.size()) {
                    z3 = false;
                    break;
                } else if (C0317Fe.o((C2186dT) fe.f8026a.get(i6))) {
                    z3 = true;
                    break;
                } else {
                    i6++;
                }
            }
            if (!z3 || fe.n(arrayList, i5 + 1, i3)) {
                z2 = false;
            }
            if (z2) {
                if (this.B == null) {
                    this.B = new ArrayList();
                }
                JS js = new JS(fe, booleanValue);
                this.B.add(js);
                for (int i7 = 0; i7 < fe.f8026a.size(); i7++) {
                    C2186dT dTVar = (C2186dT) fe.f8026a.get(i7);
                    if (C0317Fe.o(dTVar)) {
                        dTVar.b.Z0(js);
                    }
                }
                if (booleanValue) {
                    fe.k();
                } else {
                    fe.l(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, fe);
                }
                a(vaVar);
            }
        }
        return i4;
    }

    public final void g() {
        this.b = false;
        this.z.clear();
        this.y.clear();
    }

    public void g0(AbstractComponentCallbacksC3550lS lSVar) {
        if (R(2)) {
            String str = "remove: " + lSVar + " nesting=" + lSVar.V;
        }
        boolean z2 = !lSVar.W();
        if (!lSVar.e0 || z2) {
            this.c.h(lSVar);
            if (S(lSVar)) {
                this.t = true;
            }
            lSVar.Q = true;
            p0(lSVar);
        }
    }

    public void h(C0317Fe fe, boolean z2, boolean z3, boolean z4) {
        if (z2) {
            fe.l(z4);
        } else {
            fe.k();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(fe);
        arrayList2.add(Boolean.valueOf(z2));
        if (z3) {
            AbstractC3552lT.o(this, arrayList, arrayList2, 0, 1, true, this.k);
        }
        if (z4) {
            Z(this.m, true);
        }
        Iterator it = ((ArrayList) this.c.f()).iterator();
        while (it.hasNext()) {
            AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) it.next();
            if (lSVar != null && lSVar.k0 != null && lSVar.o0 && fe.m(lSVar.b0)) {
                float f2 = lSVar.q0;
                if (f2 > 0.0f) {
                    lSVar.k0.setAlpha(f2);
                }
                if (z4) {
                    lSVar.q0 = 0.0f;
                } else {
                    lSVar.q0 = -1.0f;
                    lSVar.o0 = false;
                }
            }
        }
    }

    public final void h0(ArrayList arrayList, ArrayList arrayList2) {
        if (!arrayList.isEmpty()) {
            if (arrayList.size() == arrayList2.size()) {
                G(arrayList, arrayList2);
                int size = arrayList.size();
                int i2 = 0;
                int i3 = 0;
                while (i2 < size) {
                    if (!((C0317Fe) arrayList.get(i2)).p) {
                        if (i3 != i2) {
                            F(arrayList, arrayList2, i3, i2);
                        }
                        i3 = i2 + 1;
                        if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                            while (i3 < size && ((Boolean) arrayList2.get(i3)).booleanValue() && !((C0317Fe) arrayList.get(i3)).p) {
                                i3++;
                            }
                        }
                        F(arrayList, arrayList2, i2, i3);
                        i2 = i3 - 1;
                    }
                    i2++;
                }
                if (i3 != size) {
                    F(arrayList, arrayList2, i3, size);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error with the back stack records");
        }
    }

    public final void i(AbstractComponentCallbacksC3550lS lSVar) {
        Animator animator;
        if (lSVar.k0 != null) {
            C4745sS a2 = AbstractC5085uS.a(this.n.G, this.o, lSVar, !lSVar.d0);
            if (a2 == null || (animator = a2.b) == null) {
                if (a2 != null) {
                    lSVar.k0.startAnimation(a2.f11274a);
                    a2.f11274a.start();
                }
                lSVar.k0.setVisibility((!lSVar.d0 || lSVar.V()) ? 0 : 8);
                if (lSVar.V()) {
                    lSVar.W0(false);
                }
            } else {
                animator.setTarget(lSVar.k0);
                if (!lSVar.d0) {
                    lSVar.k0.setVisibility(0);
                } else if (lSVar.V()) {
                    lSVar.W0(false);
                } else {
                    ViewGroup viewGroup = lSVar.j0;
                    View view = lSVar.k0;
                    viewGroup.startViewTransition(view);
                    a2.b.addListener(new GS(this, viewGroup, view, lSVar));
                }
                a2.b.start();
            }
        }
        if (lSVar.P && S(lSVar)) {
            this.t = true;
        }
        lSVar.p0 = false;
        lSVar.q0();
    }

    public void i0(AbstractComponentCallbacksC3550lS lSVar) {
        if (!U()) {
            if ((this.C.c.remove(lSVar.f10345J) != null) && R(2)) {
                AbstractC2531fV.p("Updating retained Fragments: Removed ", lSVar);
            }
        }
    }

    public final void j(AbstractComponentCallbacksC3550lS lSVar) {
        lSVar.Y.x(1);
        if (lSVar.k0 != null) {
            ET et = lSVar.v0;
            et.F.e(EnumC3157j80.ON_DESTROY);
        }
        lSVar.G = 1;
        lSVar.i0 = false;
        lSVar.n0();
        if (lSVar.i0) {
            L90 l90 = ((M90) J90.b(lSVar)).b;
            if (l90.c.i() <= 0) {
                lSVar.U = false;
                this.l.n(lSVar, false);
                lSVar.j0 = null;
                lSVar.k0 = null;
                lSVar.v0 = null;
                lSVar.w0.a(null);
                lSVar.S = false;
                return;
            }
            C5859z.a(l90.c.j(0));
            throw null;
        }
        throw new P31(AbstractC2531fV.d("Fragment ", lSVar, " did not call through to super.onDestroyView()"));
    }

    public void j0(Parcelable parcelable) {
        C1844bT bTVar;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.F != null) {
                this.c.b.clear();
                Iterator it = fragmentManagerState.F.iterator();
                while (it.hasNext()) {
                    FragmentState fragmentState = (FragmentState) it.next();
                    if (fragmentState != null) {
                        AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) this.C.c.get(fragmentState.G);
                        if (lSVar != null) {
                            if (R(2)) {
                                AbstractC2531fV.p("restoreSaveState: re-attaching retained ", lSVar);
                            }
                            bTVar = new C1844bT(this.l, lSVar, fragmentState);
                        } else {
                            bTVar = new C1844bT(this.l, this.n.G.getClassLoader(), P(), fragmentState);
                        }
                        AbstractComponentCallbacksC3550lS lSVar2 = bTVar.b;
                        lSVar2.W = this;
                        if (R(2)) {
                            StringBuilder i2 = AbstractC2531fV.i("restoreSaveState: active (");
                            i2.append(lSVar2.f10345J);
                            i2.append("): ");
                            i2.append(lSVar2);
                            i2.toString();
                        }
                        bTVar.a(this.n.G.getClassLoader());
                        this.c.b.put(bTVar.b.f10345J, bTVar);
                        bTVar.c = this.m;
                    }
                }
                for (AbstractComponentCallbacksC3550lS lSVar3 : this.C.c.values()) {
                    if (!this.c.c(lSVar3.f10345J)) {
                        if (R(2)) {
                            String str = "Discarding retained Fragment " + lSVar3 + " that was not found in the set of active Fragments " + fragmentManagerState.F;
                        }
                        a0(lSVar3, 1);
                        lSVar3.Q = true;
                        a0(lSVar3, -1);
                    }
                }
                C2015cT cTVar = this.c;
                ArrayList<String> arrayList = fragmentManagerState.G;
                cTVar.f9608a.clear();
                if (arrayList != null) {
                    for (String str2 : arrayList) {
                        AbstractComponentCallbacksC3550lS e2 = cTVar.e(str2);
                        if (e2 != null) {
                            if (R(2)) {
                                String str3 = "restoreSaveState: added (" + str2 + "): " + e2;
                            }
                            cTVar.a(e2);
                        } else {
                            throw new IllegalStateException(AbstractC2531fV.g("No instantiated fragment for (", str2, ")"));
                        }
                    }
                }
                if (fragmentManagerState.H != null) {
                    this.d = new ArrayList(fragmentManagerState.H.length);
                    int i3 = 0;
                    while (true) {
                        BackStackState[] backStackStateArr = fragmentManagerState.H;
                        if (i3 >= backStackStateArr.length) {
                            break;
                        }
                        BackStackState backStackState = backStackStateArr[i3];
                        Objects.requireNonNull(backStackState);
                        C0317Fe fe = new C0317Fe(this);
                        int i4 = 0;
                        int i5 = 0;
                        while (true) {
                            int[] iArr = backStackState.F;
                            if (i4 >= iArr.length) {
                                break;
                            }
                            C2186dT dTVar = new C2186dT();
                            int i6 = i4 + 1;
                            dTVar.f9783a = iArr[i4];
                            if (R(2)) {
                                String str4 = "Instantiate " + fe + " op #" + i5 + " base fragment #" + backStackState.F[i6];
                            }
                            String str5 = (String) backStackState.G.get(i5);
                            if (str5 != null) {
                                dTVar.b = this.c.e(str5);
                            } else {
                                dTVar.b = null;
                            }
                            dTVar.g = EnumC3328k80.values()[backStackState.H[i5]];
                            dTVar.h = EnumC3328k80.values()[backStackState.I[i5]];
                            int[] iArr2 = backStackState.F;
                            int i7 = i6 + 1;
                            int i8 = iArr2[i6];
                            dTVar.c = i8;
                            int i9 = i7 + 1;
                            int i10 = iArr2[i7];
                            dTVar.d = i10;
                            int i11 = i9 + 1;
                            int i12 = iArr2[i9];
                            dTVar.e = i12;
                            int i13 = iArr2[i11];
                            dTVar.f = i13;
                            fe.b = i8;
                            fe.c = i10;
                            fe.d = i12;
                            fe.e = i13;
                            fe.c(dTVar);
                            i5++;
                            i4 = i11 + 1;
                        }
                        fe.f = backStackState.f9470J;
                        fe.i = backStackState.K;
                        fe.s = backStackState.L;
                        fe.g = true;
                        fe.j = backStackState.M;
                        fe.k = backStackState.N;
                        fe.l = backStackState.O;
                        fe.m = backStackState.P;
                        fe.n = backStackState.Q;
                        fe.o = backStackState.R;
                        fe.p = backStackState.S;
                        fe.d(1);
                        if (R(2)) {
                            String str6 = "restoreAllState: back stack #" + i3 + " (index " + fe.s + "): " + fe;
                            PrintWriter printWriter = new PrintWriter(new C1342Wa0("FragmentManager"));
                            fe.j("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.d.add(fe);
                        i3++;
                    }
                } else {
                    this.d = null;
                }
                this.i.set(fragmentManagerState.I);
                String str7 = fragmentManagerState.f9471J;
                if (str7 != null) {
                    AbstractComponentCallbacksC3550lS H = H(str7);
                    this.q = H;
                    u(H);
                }
            }
        }
    }

    public void k(AbstractComponentCallbacksC3550lS lSVar) {
        if (R(2)) {
            AbstractC2531fV.p("detach: ", lSVar);
        }
        if (!lSVar.e0) {
            lSVar.e0 = true;
            if (lSVar.P) {
                if (R(2)) {
                    AbstractC2531fV.p("remove from detach: ", lSVar);
                }
                this.c.h(lSVar);
                if (S(lSVar)) {
                    this.t = true;
                }
                p0(lSVar);
            }
        }
    }

    public Parcelable k0() {
        ArrayList arrayList;
        int size;
        L();
        A();
        D(true);
        this.u = true;
        C2015cT cTVar = this.c;
        Objects.requireNonNull(cTVar);
        ArrayList arrayList2 = new ArrayList(cTVar.b.size());
        for (C1844bT bTVar : cTVar.b.values()) {
            if (bTVar != null) {
                AbstractComponentCallbacksC3550lS lSVar = bTVar.b;
                FragmentState fragmentState = new FragmentState(lSVar);
                AbstractComponentCallbacksC3550lS lSVar2 = bTVar.b;
                if (lSVar2.G <= -1 || fragmentState.R != null) {
                    fragmentState.R = lSVar2.H;
                } else {
                    Bundle b2 = bTVar.b();
                    fragmentState.R = b2;
                    if (bTVar.b.M != null) {
                        if (b2 == null) {
                            fragmentState.R = new Bundle();
                        }
                        fragmentState.R.putString("android:target_state", bTVar.b.M);
                        int i2 = bTVar.b.N;
                        if (i2 != 0) {
                            fragmentState.R.putInt("android:target_req_state", i2);
                        }
                    }
                }
                arrayList2.add(fragmentState);
                if (R(2)) {
                    String str = "Saved state of " + lSVar + ": " + fragmentState.R;
                }
            }
        }
        BackStackState[] backStackStateArr = null;
        if (arrayList2.isEmpty()) {
            return null;
        }
        C2015cT cTVar2 = this.c;
        synchronized (cTVar2.f9608a) {
            if (cTVar2.f9608a.isEmpty()) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(cTVar2.f9608a.size());
                Iterator it = cTVar2.f9608a.iterator();
                while (it.hasNext()) {
                    AbstractComponentCallbacksC3550lS lSVar3 = (AbstractComponentCallbacksC3550lS) it.next();
                    arrayList.add(lSVar3.f10345J);
                    if (R(2)) {
                        String str2 = "saveAllState: adding fragment (" + lSVar3.f10345J + "): " + lSVar3;
                    }
                }
            }
        }
        ArrayList arrayList3 = this.d;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i3 = 0; i3 < size; i3++) {
                backStackStateArr[i3] = new BackStackState((C0317Fe) this.d.get(i3));
                if (R(2)) {
                    String str3 = "saveAllState: adding back stack #" + i3 + ": " + this.d.get(i3);
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.F = arrayList2;
        fragmentManagerState.G = arrayList;
        fragmentManagerState.H = backStackStateArr;
        fragmentManagerState.I = this.i.get();
        AbstractComponentCallbacksC3550lS lSVar4 = this.q;
        if (lSVar4 != null) {
            fragmentManagerState.f9471J = lSVar4.f10345J;
        }
        return fragmentManagerState;
    }

    public void l(Configuration configuration) {
        for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
            if (lSVar != null) {
                lSVar.onConfigurationChanged(configuration);
                lSVar.Y.l(configuration);
            }
        }
    }

    public void l0() {
        synchronized (this.f8365a) {
            ArrayList arrayList = this.B;
            boolean z2 = false;
            boolean z3 = arrayList != null && !arrayList.isEmpty();
            if (this.f8365a.size() == 1) {
                z2 = true;
            }
            if (z3 || z2) {
                this.n.H.removeCallbacks(this.D);
                this.n.H.post(this.D);
                t0();
            }
        }
    }

    public boolean m(MenuItem menuItem) {
        if (this.m < 1) {
            return false;
        }
        for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
            if (lSVar != null) {
                if (!lSVar.d0 && (lSVar.g0() || lSVar.Y.m(menuItem))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void m0(AbstractComponentCallbacksC3550lS lSVar, boolean z2) {
        ViewGroup O = O(lSVar);
        if (O != null && (O instanceof C5425wS)) {
            ((C5425wS) O).H = !z2;
        }
    }

    public void n() {
        this.u = false;
        this.v = false;
        x(1);
    }

    public void n0(AbstractComponentCallbacksC3550lS lSVar, EnumC3328k80 k80) {
        if (!lSVar.equals(H(lSVar.f10345J)) || !(lSVar.X == null || lSVar.W == this)) {
            throw new IllegalArgumentException("Fragment " + lSVar + " is not an active fragment of FragmentManager " + this);
        }
        lSVar.t0 = k80;
    }

    public boolean o(Menu menu, MenuInflater menuInflater) {
        if (this.m < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z2 = false;
        for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
            if (lSVar != null && lSVar.X() && lSVar.H0(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lSVar);
                z2 = true;
            }
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                AbstractComponentCallbacksC3550lS lSVar2 = (AbstractComponentCallbacksC3550lS) this.e.get(i2);
                if (arrayList == null || !arrayList.contains(lSVar2)) {
                    Objects.requireNonNull(lSVar2);
                }
            }
        }
        this.e = arrayList;
        return z2;
    }

    public void o0(AbstractComponentCallbacksC3550lS lSVar) {
        if (lSVar == null || (lSVar.equals(H(lSVar.f10345J)) && (lSVar.X == null || lSVar.W == this))) {
            AbstractComponentCallbacksC3550lS lSVar2 = this.q;
            this.q = lSVar;
            u(lSVar2);
            u(this.q);
            return;
        }
        throw new IllegalArgumentException("Fragment " + lSVar + " is not an active fragment of FragmentManager " + this);
    }

    public void p() {
        this.w = true;
        D(true);
        A();
        x(-1);
        this.n = null;
        this.o = null;
        this.p = null;
        if (this.g != null) {
            Iterator it = this.h.b.iterator();
            while (it.hasNext()) {
                ((AbstractC2748gm) it.next()).cancel();
            }
            this.g = null;
        }
    }

    public final void p0(AbstractComponentCallbacksC3550lS lSVar) {
        ViewGroup O = O(lSVar);
        if (O != null) {
            if (O.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                O.setTag(R.id.visible_removing_fragment_view_tag, lSVar);
            }
            ((AbstractComponentCallbacksC3550lS) O.getTag(R.id.visible_removing_fragment_view_tag)).Y0(lSVar.F());
        }
    }

    public void q() {
        for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
            if (lSVar != null) {
                lSVar.K0();
            }
        }
    }

    public void q0(AbstractComponentCallbacksC3550lS lSVar) {
        if (R(2)) {
            AbstractC2531fV.p("show: ", lSVar);
        }
        if (lSVar.d0) {
            lSVar.d0 = false;
            lSVar.p0 = !lSVar.p0;
        }
    }

    public void r(boolean z2) {
        for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
            if (lSVar != null) {
                lSVar.L0(z2);
            }
        }
    }

    public final void r0() {
        Iterator it = ((ArrayList) this.c.f()).iterator();
        while (it.hasNext()) {
            AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) it.next();
            if (lSVar != null && lSVar.l0) {
                if (this.b) {
                    this.x = true;
                } else {
                    lSVar.l0 = false;
                    a0(lSVar, this.m);
                }
            }
        }
    }

    public boolean s(MenuItem menuItem) {
        if (this.m < 1) {
            return false;
        }
        for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
            if (lSVar != null) {
                if (!lSVar.d0 && ((lSVar.g0 && lSVar.h0 && lSVar.u0(menuItem)) || lSVar.Y.s(menuItem))) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void s0(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new C1342Wa0("FragmentManager"));
        C3721mS mSVar = this.n;
        if (mSVar != null) {
            try {
                mSVar.f10420J.dump("  ", null, printWriter, new String[0]);
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        } else {
            try {
                z("  ", null, printWriter, new String[0]);
            } catch (Exception e3) {
                Log.e("FragmentManager", "Failed dumping state", e3);
            }
        }
        throw runtimeException;
    }

    public void t(Menu menu) {
        if (this.m >= 1) {
            for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
                if (lSVar != null && !lSVar.d0) {
                    if (lSVar.g0 && lSVar.h0) {
                        lSVar.v0();
                    }
                    lSVar.Y.t(menu);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r1 == null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r1 = r1.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        if (r1 <= 0) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        if (T(r4.p) == false) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        r0.f7810a = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r0 = r4.h;
        r1 = r4.d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void t0() {
        /*
            r4 = this;
            java.util.ArrayList r0 = r4.f8365a
            monitor-enter(r0)
            java.util.ArrayList r1 = r4.f8365a     // Catch:{ all -> 0x002f }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x002f }
            r2 = 1
            if (r1 != 0) goto L_0x0012
            CS r1 = r4.h     // Catch:{ all -> 0x002f }
            r1.f7810a = r2     // Catch:{ all -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            goto L_0x002e
        L_0x0012:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            CS r0 = r4.h
            java.util.ArrayList r1 = r4.d
            r3 = 0
            if (r1 == 0) goto L_0x001f
            int r1 = r1.size()
            goto L_0x0020
        L_0x001f:
            r1 = r3
        L_0x0020:
            if (r1 <= 0) goto L_0x002b
            lS r1 = r4.p
            boolean r1 = r4.T(r1)
            if (r1 == 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            r2 = r3
        L_0x002c:
            r0.f7810a = r2
        L_0x002e:
            return
        L_0x002f:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.KS.t0():void");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        AbstractComponentCallbacksC3550lS lSVar = this.p;
        if (lSVar != null) {
            sb.append(lSVar.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.p)));
            sb.append("}");
        } else if (this.n != null) {
            sb.append(C3721mS.class.getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.n)));
            sb.append("}");
        } else {
            sb.append("null");
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void u(AbstractComponentCallbacksC3550lS lSVar) {
        if (lSVar != null && lSVar.equals(H(lSVar.f10345J))) {
            boolean T = lSVar.W.T(lSVar);
            Boolean bool = lSVar.O;
            if (bool == null || bool.booleanValue() != T) {
                lSVar.O = Boolean.valueOf(T);
                lSVar.z0();
                KS ks = lSVar.Y;
                ks.t0();
                ks.u(ks.q);
            }
        }
    }

    public void v(boolean z2) {
        for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
            if (lSVar != null) {
                lSVar.M0(z2);
            }
        }
    }

    public boolean w(Menu menu) {
        boolean z2 = false;
        if (this.m >= 1) {
            for (AbstractComponentCallbacksC3550lS lSVar : this.c.g()) {
                if (lSVar != null && lSVar.N0(menu)) {
                    z2 = true;
                }
            }
        }
        return z2;
    }

    /* JADX INFO: finally extract failed */
    public final void x(int i2) {
        try {
            this.b = true;
            this.c.d(i2);
            Z(i2, false);
            this.b = false;
            D(true);
        } catch (Throwable th) {
            this.b = false;
            throw th;
        }
    }

    public final void y() {
        if (this.x) {
            this.x = false;
            r0();
        }
    }

    public void z(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String f2 = AbstractC2531fV.f(str, "    ");
        C2015cT cTVar = this.c;
        Objects.requireNonNull(cTVar);
        String str2 = str + "    ";
        if (!cTVar.b.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments:");
            for (C1844bT bTVar : cTVar.b.values()) {
                printWriter.print(str);
                if (bTVar != null) {
                    AbstractComponentCallbacksC3550lS lSVar = bTVar.b;
                    printWriter.println(lSVar);
                    lSVar.s(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size3 = cTVar.f9608a.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size3; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(((AbstractComponentCallbacksC3550lS) cTVar.f9608a.get(i2)).toString());
            }
        }
        ArrayList arrayList = this.e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size2; i3++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(((AbstractComponentCallbacksC3550lS) this.e.get(i3)).toString());
            }
        }
        ArrayList arrayList2 = this.d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size; i4++) {
                C0317Fe fe = (C0317Fe) this.d.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(fe.toString());
                fe.j(f2, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.i.get());
        synchronized (this.f8365a) {
            int size4 = this.f8365a.size();
            if (size4 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i5 = 0; i5 < size4; i5++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println((HS) this.f8365a.get(i5));
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.n);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.o);
        if (this.p != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.p);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.m);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.u);
        printWriter.print(" mStopped=");
        printWriter.print(this.v);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.w);
        if (this.t) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.t);
        }
    }
}
