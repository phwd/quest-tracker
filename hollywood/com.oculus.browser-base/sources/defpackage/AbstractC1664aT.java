package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment$SavedState;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragmentAdvanced;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragmentBasic;

/* renamed from: aT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1664aT extends AbstractC5750yK0 {
    public final AbstractC3499l80 I;

    /* renamed from: J  reason: collision with root package name */
    public final KS f9431J;
    public final C4083ob0 K = new C4083ob0();
    public final C4083ob0 L = new C4083ob0();
    public final C4083ob0 M = new C4083ob0();
    public YS N;
    public US O = new US();
    public boolean P = false;
    public boolean Q = false;

    public AbstractC1664aT(AbstractActivityC3892nS nSVar) {
        KS Y = nSVar.Y();
        AbstractC3499l80 Q2 = nSVar.Q();
        this.f9431J = Y;
        this.I = Q2;
        r(true);
    }

    public static boolean v(String str, String str2) {
        return str.startsWith(str2) && str.length() > str2.length();
    }

    @Override // defpackage.AbstractC5750yK0
    public long c(int i) {
        return (long) i;
    }

    @Override // defpackage.AbstractC5750yK0
    public void i(RecyclerView recyclerView) {
        if (this.N == null) {
            YS ys = new YS(this);
            this.N = ys;
            ViewPager2 a2 = ys.a(recyclerView);
            ys.d = a2;
            VS vs = new VS(ys);
            ys.f9274a = vs;
            a2.I.f10461a.add(vs);
            WS ws = new WS(ys);
            ys.b = ws;
            this.F.registerObserver(ws);
            XS xs = new XS(ys);
            ys.c = xs;
            this.I.a(xs);
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        ClearBrowsingDataFragment clearBrowsingDataFragment;
        Bundle bundle;
        DT dt = (DT) xk0;
        long j = dt.K;
        int id = ((FrameLayout) dt.G).getId();
        Long w = w(id);
        if (!(w == null || w.longValue() == j)) {
            y(w.longValue());
            this.M.j(w.longValue());
        }
        this.M.i(j, Integer.valueOf(id));
        long j2 = (long) i;
        if (!this.K.c(j2)) {
            C0841Nu nu = (C0841Nu) this;
            if (i == 0) {
                clearBrowsingDataFragment = new ClearBrowsingDataFragmentBasic();
            } else if (i == 1) {
                clearBrowsingDataFragment = new ClearBrowsingDataFragmentAdvanced();
            } else {
                throw new RuntimeException(AbstractC2531fV.w("invalid position: ", i));
            }
            clearBrowsingDataFragment.K0 = nu.R;
            Fragment$SavedState fragment$SavedState = (Fragment$SavedState) this.L.e(j2);
            if (clearBrowsingDataFragment.W == null) {
                if (fragment$SavedState == null || (bundle = fragment$SavedState.F) == null) {
                    bundle = null;
                }
                clearBrowsingDataFragment.H = bundle;
                this.K.i(j2, clearBrowsingDataFragment);
            } else {
                throw new IllegalStateException("Fragment already added");
            }
        }
        FrameLayout frameLayout = (FrameLayout) dt.G;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (frameLayout.isAttachedToWindow()) {
            if (frameLayout.getParent() == null) {
                frameLayout.addOnLayoutChangeListener(new PS(this, frameLayout, dt));
            } else {
                throw new IllegalStateException("Design assumption violated.");
            }
        }
        u();
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        int i2 = DT.Z;
        FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        frameLayout.setId(View.generateViewId());
        frameLayout.setSaveEnabled(false);
        return new DT(frameLayout);
    }

    @Override // defpackage.AbstractC5750yK0
    public void n(RecyclerView recyclerView) {
        YS ys = this.N;
        ViewPager2 a2 = ys.a(recyclerView);
        a2.I.f10461a.remove(ys.f9274a);
        AbstractC1664aT aTVar = ys.f;
        aTVar.F.unregisterObserver(ys.b);
        ys.f.I.b(ys.c);
        ys.d = null;
        this.N = null;
    }

    @Override // defpackage.AbstractC5750yK0
    public /* bridge */ /* synthetic */ boolean o(XK0 xk0) {
        DT dt = (DT) xk0;
        return true;
    }

    @Override // defpackage.AbstractC5750yK0
    public void p(XK0 xk0) {
        x((DT) xk0);
        u();
    }

    @Override // defpackage.AbstractC5750yK0
    public void q(XK0 xk0) {
        Long w = w(((FrameLayout) ((DT) xk0).G).getId());
        if (w != null) {
            y(w.longValue());
            this.M.j(w.longValue());
        }
    }

    public void s(View view, FrameLayout frameLayout) {
        if (frameLayout.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        } else if (view.getParent() != frameLayout) {
            if (frameLayout.getChildCount() > 0) {
                frameLayout.removeAllViews();
            }
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            frameLayout.addView(view);
        }
    }

    public boolean t(long j) {
        return j >= 0 && j < ((long) 2);
    }

    public void u() {
        AbstractComponentCallbacksC3550lS lSVar;
        View view;
        if (this.Q && !z()) {
            C5271va vaVar = new C5271va(0);
            for (int i = 0; i < this.K.k(); i++) {
                long h = this.K.h(i);
                if (!t(h)) {
                    vaVar.add(Long.valueOf(h));
                    this.M.j(h);
                }
            }
            if (!this.P) {
                this.Q = false;
                for (int i2 = 0; i2 < this.K.k(); i2++) {
                    long h2 = this.K.h(i2);
                    boolean z = true;
                    if (!this.M.c(h2) && ((lSVar = (AbstractComponentCallbacksC3550lS) this.K.f(h2, null)) == null || (view = lSVar.k0) == null || view.getParent() == null)) {
                        z = false;
                    }
                    if (!z) {
                        vaVar.add(Long.valueOf(h2));
                    }
                }
            }
            C5101ua uaVar = new C5101ua(vaVar);
            while (uaVar.hasNext()) {
                y(((Long) uaVar.next()).longValue());
            }
        }
    }

    public final Long w(int i) {
        Long l = null;
        for (int i2 = 0; i2 < this.M.k(); i2++) {
            if (((Integer) this.M.l(i2)).intValue() == i) {
                if (l == null) {
                    l = Long.valueOf(this.M.h(i2));
                } else {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
            }
        }
        return l;
    }

    public void x(DT dt) {
        AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) this.K.e(dt.K);
        if (lSVar != null) {
            FrameLayout frameLayout = (FrameLayout) dt.G;
            View view = lSVar.k0;
            if (!lSVar.U() && view != null) {
                throw new IllegalStateException("Design assumption violated.");
            } else if (lSVar.U() && view == null) {
                this.f9431J.l.f7737a.add(new AS(new RS(this, lSVar, frameLayout), false));
            } else if (!lSVar.U() || view.getParent() == null) {
                if (lSVar.U()) {
                    s(view, frameLayout);
                } else if (!z()) {
                    this.f9431J.l.f7737a.add(new AS(new RS(this, lSVar, frameLayout), false));
                    US us = this.O;
                    Objects.requireNonNull(us);
                    ArrayList arrayList = new ArrayList();
                    Iterator it = us.f9028a.iterator();
                    if (!it.hasNext()) {
                        try {
                            lSVar.X0(false);
                            C0317Fe fe = new C0317Fe(this.f9431J);
                            fe.i(0, lSVar, "f" + dt.K, 1);
                            fe.r(lSVar, EnumC3328k80.STARTED);
                            fe.h();
                            this.N.b(false);
                        } finally {
                            this.O.b(arrayList);
                        }
                    } else {
                        C5859z.a(it.next());
                        throw null;
                    }
                } else if (!this.f9431J.w) {
                    this.I.a(new QS(this, dt));
                }
            } else if (view.getParent() != frameLayout) {
                s(view, frameLayout);
            }
        } else {
            throw new IllegalStateException("Design assumption violated.");
        }
    }

    public final void y(long j) {
        Bundle b;
        ViewParent parent;
        AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) this.K.f(j, null);
        if (lSVar != null) {
            View view = lSVar.k0;
            if (!(view == null || (parent = view.getParent()) == null)) {
                ((FrameLayout) parent).removeAllViews();
            }
            if (!t(j)) {
                this.L.j(j);
            }
            if (!lSVar.U()) {
                this.K.j(j);
            } else if (z()) {
                this.Q = true;
            } else {
                if (lSVar.U() && t(j)) {
                    C4083ob0 ob0 = this.L;
                    KS ks = this.f9431J;
                    C1844bT bTVar = (C1844bT) ks.c.b.get(lSVar.f10345J);
                    if (bTVar == null || !bTVar.b.equals(lSVar)) {
                        ks.s0(new IllegalStateException(AbstractC2531fV.d("Fragment ", lSVar, " is not currently in the FragmentManager")));
                    }
                    ob0.i(j, (bTVar.b.G <= -1 || (b = bTVar.b()) == null) ? null : new Fragment$SavedState(b));
                }
                US us = this.O;
                Objects.requireNonNull(us);
                ArrayList arrayList = new ArrayList();
                Iterator it = us.f9028a.iterator();
                if (!it.hasNext()) {
                    try {
                        C0317Fe fe = new C0317Fe(this.f9431J);
                        fe.p(lSVar);
                        fe.h();
                        this.K.j(j);
                    } finally {
                        this.O.b(arrayList);
                    }
                } else {
                    C5859z.a(it.next());
                    throw null;
                }
            }
        }
    }

    public boolean z() {
        return this.f9431J.U();
    }
}
