package org.chromium.chrome.browser.compositor;

import J.N;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CompositorViewHolder extends FrameLayout implements AbstractC0849Ny, AbstractC4692s70, F70, M30, AbstractC2230dk, V10, W, AbstractC1282Va1, ViewGroup.OnHierarchyChangeListener {
    public static final /* synthetic */ int F = 0;
    public C1322Vq0 G = new C1322Vq0();
    public WL H = new WL(new C1028Qw(this));
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final N30 f10633J = new N30();
    public D70 K;
    public LayerTitleCache L;
    public CompositorView M;
    public boolean N = true;
    public boolean O;
    public int P;
    public final ArrayList Q = new ArrayList();
    public boolean R;
    public Runnable S;
    public AbstractC0124Ca1 T;
    public C1551Zj U;
    public C1078Rq0 V = new C1078Rq0();
    public View W;
    public C1455Xw a0;
    public DA b0;
    public W10 c0;
    public boolean d0;
    public Runnable e0;
    public Tab f0;
    public View g0;
    public AbstractViewGroup$OnHierarchyChangeListenerC1520Yy h0;
    public AbstractC1404Xa1 i0 = new C1150Sw(this);
    public final Rect j0 = new Rect();
    public final Point k0 = new Point();
    public boolean l0;
    public boolean m0;
    public boolean n0;
    public boolean o0;
    public C2712ga p0;
    public Callback q0;
    public boolean r0;
    public boolean s0 = true;
    public ArrayList t0 = new ArrayList();
    public Set u0 = new HashSet();
    public Set v0 = new HashSet();
    public Set w0 = new HashSet();
    public MotionEvent x0;
    public View y0;

    public CompositorViewHolder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        addOnLayoutChangeListener(new View$OnLayoutChangeListenerC1211Tw(this));
        CompositorView compositorView = new CompositorView(getContext(), this);
        this.M = compositorView;
        addView(compositorView, 0, new FrameLayout.LayoutParams(-1, -2));
        setOnSystemUiVisibilityChangeListener(new View$OnSystemUiVisibilityChangeListenerC1272Uw(this));
        s();
        if (Build.VERSION.SDK_INT >= 26) {
            C3837n7.i(this, false);
        }
    }

    public static boolean y(View view) {
        return (view == null || view.getWindowToken() == null) ? false : true;
    }

    public void A() {
        AbstractC0124Ca1 ca1 = this.T;
        if (ca1 != null) {
            E(((AbstractC0246Ea1) ca1).j());
        }
    }

    public final void B() {
        D70 d70 = this.K;
        if (d70 != null) {
            d70.r();
        }
    }

    public void C() {
        CompositorView compositorView = this.M;
        long j = compositorView.K;
        if (j != 0) {
            N.M_Nkznfe(j, compositorView);
        }
    }

    public void D(WebContents webContents, View view, int i, int i2) {
        int i3;
        int i4;
        if (webContents != null && view != null) {
            C1551Zj zj = this.U;
            boolean z = false;
            int i5 = zj != null ? zj.N + zj.P : 0;
            if (this.m0) {
                i5 = c() + m();
            }
            if (y(view)) {
                boolean F2 = F(webContents);
                if (F2) {
                    int b = C3493l60.F.b(getRootView());
                    i4 = i2 + b;
                    i3 = b;
                } else {
                    i4 = i2;
                    i3 = 0;
                }
                webContents.p0(i, i4 - i5);
                if (F2) {
                    if (i3 > 0) {
                        z = true;
                    }
                    if (z || this.r0) {
                        this.r0 = z;
                        Rect rect = new Rect();
                        getRootView().getWindowVisibleDisplayFrame(rect);
                        if (z) {
                            int i6 = rect.left;
                            int i7 = rect.top;
                            CompositorView compositorView = this.M;
                            if (compositorView != null) {
                                N.MGbjFlrB(compositorView.K, compositorView, webContents, i6, i7, i, i3);
                                return;
                            }
                            return;
                        }
                        CompositorView compositorView2 = this.M;
                        if (compositorView2 != null) {
                            N.MGbjFlrB(compositorView2.K, compositorView2, webContents, 0, 0, 0, 0);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (!y(view)) {
                Point o = o();
                view.measure(View.MeasureSpec.makeMeasureSpec(o.x, 1073741824), View.MeasureSpec.makeMeasureSpec(o.y, 1073741824));
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                webContents.p0(view.getWidth(), view.getHeight() - i5);
            }
            C();
        }
    }

    public final void E(Tab tab) {
        AbstractC0240Dy h;
        if (tab != null) {
            tab.z();
        }
        C4563rM rMVar = null;
        View b = tab != null ? tab.b() : null;
        if (this.g0 != b) {
            G(false);
            Tab tab2 = this.f0;
            if (tab2 != tab) {
                this.r0 = false;
                if (tab2 != null) {
                    tab2.I(this.i0);
                }
                if (tab != null) {
                    tab.A(this.i0);
                    CompositorView compositorView = this.M;
                    N.MefOJ2yP(compositorView.K, compositorView);
                }
                AbstractViewGroup$OnHierarchyChangeListenerC1520Yy u = tab != null ? tab.u() : null;
                AbstractViewGroup$OnHierarchyChangeListenerC1520Yy yy = this.h0;
                if (yy != null) {
                    yy.H.c(this);
                }
                if (u != null) {
                    u.H.b(this);
                }
                this.h0 = u;
            }
            this.f0 = tab;
            this.g0 = b;
            G(this.N);
            Tab tab3 = this.f0;
            if (tab3 != null) {
                v(tab3);
            }
            if (this.s0) {
                if (Build.VERSION.SDK_INT >= 29 && (h = C0301Ey.h(getContext(), this, q())) != null) {
                    this.t0.add(h);
                }
                WebContents q = q();
                if (N.MxGt0EOk()) {
                    rMVar = new C4563rM(q);
                }
                if (rMVar != null) {
                    this.t0.add(rMVar);
                }
                this.s0 = false;
                return;
            }
            Iterator it = this.t0.iterator();
            while (it.hasNext()) {
                ((AbstractC0240Dy) it.next()).e(q());
            }
        }
    }

    public boolean F(WebContents webContents) {
        Tab tab = this.f0;
        return tab != null && tab.l() == webContents && ImeAdapterImpl.s0(webContents) != null && ImeAdapterImpl.s0(webContents).d0;
    }

    public final void G(boolean z) {
        if (this.g0 != null) {
            WebContents q = q();
            if (z) {
                if (this.g0 == e().b() && this.g0.getParent() != this) {
                    AbstractC2417ep1.k(this.g0);
                    if (q != null) {
                        d().setVisibility(0);
                        z();
                    }
                    addView(this.g0, 1);
                    setFocusable(false);
                    setFocusableInTouchMode(false);
                    View view = this.y0;
                    if (view == null || !view.hasFocus()) {
                        this.g0.requestFocus();
                    }
                }
            } else if (this.g0.getParent() == this) {
                setFocusable(this.O);
                setFocusableInTouchMode(this.O);
                if (q != null && !q.g()) {
                    d().setVisibility(4);
                }
                removeView(this.g0);
            }
        }
    }

    public final void H() {
        int i;
        TraceEvent.Y("CompositorViewHolder:updateContentViewChildrenDimension", null);
        ViewGroup d = d();
        if (d != null) {
            float b = b();
            float b2 = (float) AbstractC2571fk.b(this.U);
            for (int i2 = 0; i2 < d.getChildCount(); i2++) {
                View childAt = d.getChildAt(i2);
                if ((childAt.getLayoutParams() instanceof FrameLayout.LayoutParams) && 48 == (((FrameLayout.LayoutParams) childAt.getLayoutParams()).gravity & 112)) {
                    childAt.setTranslationY(b);
                    TraceEvent.h0("FullscreenManager:child.setTranslationY()");
                }
            }
            for (int i3 = 0; i3 < d.getChildCount(); i3++) {
                View childAt2 = d.getChildAt(i3);
                if (childAt2.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt2.getLayoutParams();
                    if (layoutParams.height == -1 && !(layoutParams.topMargin == (i = (int) b) && layoutParams.bottomMargin == ((int) b2))) {
                        layoutParams.topMargin = i;
                        layoutParams.bottomMargin = (int) b2;
                        childAt2.requestLayout();
                        TraceEvent.h0("FullscreenManager:child.requestLayout()");
                    }
                }
            }
            z();
        }
        TraceEvent.f0("CompositorViewHolder:updateContentViewChildrenDimension");
    }

    public final void I(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5) {
            this.n0 = true;
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.n0 = false;
        }
    }

    /* renamed from: J */
    public final void z() {
        CompositorView compositorView;
        if (!this.n0 && !this.o0) {
            C1551Zj zj = this.U;
            if (zj != null) {
                int i = zj.T;
                boolean z = false;
                if ((i == zj.N || i == zj.M) && (AbstractC2571fk.b(zj) == zj.P || AbstractC2571fk.b(zj) == zj.O)) {
                    C1551Zj zj2 = this.U;
                    if (zj2.T > zj2.N || AbstractC2571fk.b(zj2) > zj2.P) {
                        z = true;
                    }
                    if (z != this.m0) {
                        this.m0 = z;
                        WebContents q = q();
                        boolean z2 = this.m0;
                        if (!(q == null || (compositorView = this.M) == null)) {
                            N.MI$giMjY(compositorView.K, compositorView, q, z2);
                        }
                    }
                } else {
                    return;
                }
            }
            Point o = o();
            D(q(), d(), o.x, o.y);
        }
    }

    public final void a() {
        if (!this.Q.isEmpty()) {
            TraceEvent.h0("CompositorViewHolder.flushInvalidation");
            for (int i = 0; i < this.Q.size(); i++) {
                ((Runnable) this.Q.get(i)).run();
            }
            this.Q.clear();
        }
    }

    @Override // defpackage.AbstractC0849Ny
    public float b() {
        return this.U.c();
    }

    public int c() {
        C1551Zj zj = this.U;
        if (zj != null) {
            return zj.O;
        }
        return 0;
    }

    public void clearChildFocus(View view) {
    }

    public ViewGroup d() {
        Tab e = e();
        if (e != null) {
            return e.u();
        }
        return null;
    }

    public boolean dispatchDragEvent(DragEvent dragEvent) {
        WL wl = this.H;
        dragEvent.getAction();
        wl.a();
        boolean dispatchDragEvent = super.dispatchDragEvent(dragEvent);
        WL wl2 = this.H;
        int action = dragEvent.getAction();
        Objects.requireNonNull(wl2);
        if (action == 6 || action == 4 || action == 3) {
            wl2.c(0.0f);
        }
        return dispatchDragEvent;
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        C1455Xw xw = this.a0;
        if (xw == null || !xw.n(motionEvent)) {
            return super.dispatchHoverEvent(motionEvent);
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 2 || motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 5 || motionEvent.getActionMasked() == 6) {
            this.x0 = motionEvent;
        }
        if (motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1) {
            this.x0 = null;
        }
        Iterator it = this.G.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                return super.dispatchTouchEvent(motionEvent);
            }
            C0887Om0 om0 = (C0887Om0) uq0.next();
            if (((Boolean) om0.g.get()).booleanValue()) {
                om0.e.onTouchEvent(motionEvent);
                if (motionEvent.getAction() == 1) {
                    om0.b(true);
                }
            }
        }
    }

    public final Tab e() {
        AbstractC0124Ca1 ca1;
        if (this.K == null || (ca1 = this.T) == null) {
            return null;
        }
        Tab j = ((AbstractC0246Ea1) ca1).j();
        return j == null ? this.f0 : j;
    }

    @Override // defpackage.V10
    public void f(Rect rect) {
    }

    @Override // defpackage.V10
    public void g(int i, int i2, int i3, int i4) {
        if (this.d0) {
            t();
        }
    }

    @Override // defpackage.AbstractC2230dk
    public void h(int i, int i2) {
        if (this.f0 != null) {
            WebContents q = q();
            if (q != null) {
                q.o();
            }
            Point o = o();
            D(this.f0.l(), this.f0.u(), o.x, o.y);
            B();
        }
    }

    public IJ i() {
        return this.M.N.a();
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        B();
        if (z) {
            C();
        }
        H();
    }

    @Override // defpackage.AbstractC2230dk
    public void k(int i, int i2) {
        if (this.f0 != null) {
            WebContents q = q();
            if (q != null) {
                q.o();
            }
            Point o = o();
            D(this.f0.l(), this.f0.u(), o.x, o.y);
            B();
        }
    }

    @Override // defpackage.W
    public void l(boolean z) {
        if (z && this.a0 == null) {
            View view = new View(getContext());
            this.W = view;
            addView(view);
            C1455Xw xw = new C1455Xw(this, this.W);
            this.a0 = xw;
            AbstractC1920bu1.n(this.W, xw);
        }
    }

    public int m() {
        C1551Zj zj = this.U;
        if (zj != null) {
            return zj.M;
        }
        return 0;
    }

    public void n(RectF rectF) {
        r(rectF);
        C1551Zj zj = this.U;
        if (zj != null) {
            rectF.top += (float) zj.M;
            rectF.bottom -= (float) zj.O;
        }
    }

    public final Point o() {
        if (!this.d0 || !C3493l60.F.f(getContext(), this)) {
            this.k0.set(getWidth(), getHeight());
        } else {
            getWindowVisibleDisplayFrame(this.j0);
            this.k0.set(Math.min(this.j0.width(), getWidth()), Math.min(this.j0.height(), getHeight()));
        }
        return this.k0;
    }

    public void onAttachedToWindow() {
        this.f10633J.f8524a = this;
        super.onAttachedToWindow();
    }

    public void onChildViewAdded(View view, View view2) {
        H();
    }

    public void onChildViewRemoved(View view, View view2) {
        H();
    }

    public void onDetachedFromWindow() {
        a();
        this.f10633J.f8524a = null;
        super.onDetachedFromWindow();
        if (this.a0 != null) {
            this.W.setAccessibilityDelegate(null);
            this.a0 = null;
            removeView(this.W);
            this.W = null;
        }
    }

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        this.H.b(motionEvent, true);
        return super.onInterceptHoverEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        VL vl;
        VL vl2;
        int i;
        super.onInterceptTouchEvent(motionEvent);
        Iterator it = this.G.iterator();
        do {
            C1261Uq0 uq0 = (C1261Uq0) it;
            boolean z2 = true;
            z = false;
            if (uq0.hasNext()) {
                C0887Om0 om0 = (C0887Om0) uq0.next();
                if (((Boolean) om0.g.get()).booleanValue() && ((i = om0.i) == 2 || i == 3)) {
                    z = true;
                    continue;
                }
            } else {
                I(motionEvent);
                if (this.K == null) {
                    return false;
                }
                this.H.b(motionEvent, false);
                D70 d70 = this.K;
                boolean z3 = this.I;
                if (d70.S == null) {
                    return false;
                }
                if (motionEvent.getAction() == 0) {
                    d70.I = (int) motionEvent.getX();
                    d70.f7867J = (int) motionEvent.getY();
                }
                PointF m = d70.m(motionEvent);
                int size = d70.m0.size() - 1;
                while (true) {
                    vl = null;
                    if (size < 0) {
                        vl2 = null;
                        break;
                    }
                    if (((LO0) d70.m0.get(size)).u() && (vl2 = ((LO0) d70.m0.get(size)).c()) != null) {
                        if (m != null) {
                            float f = m.x;
                            float f2 = m.y;
                            vl2.c = f;
                            vl2.d = f2;
                        }
                        if (vl2.a(motionEvent, z3)) {
                            break;
                        }
                    }
                    size--;
                }
                if (vl2 == null) {
                    VL n = d70.S.n();
                    if (n != null) {
                        if (m != null) {
                            float f3 = m.x;
                            float f4 = m.y;
                            n.c = f3;
                            n.d = f4;
                        }
                        if (n.a(motionEvent, z3)) {
                            vl = n;
                        }
                    }
                    vl2 = vl;
                }
                d70.g0 = vl2 != d70.U;
                d70.U = vl2;
                if (vl2 != null) {
                    d70.S.R();
                }
                if (d70.U == null) {
                    z2 = false;
                }
                return z2;
            }
        } while (!z);
        return true;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            B();
        }
        super.onLayout(z, i, i2, i3, i4);
        x();
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.I = C3493l60.F.f(getContext(), this);
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        ViewGroup d = d();
        if (d == null) {
            return null;
        }
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (!d.isAttachedToWindow()) {
            return null;
        }
        return d.onResolvePointerIcon(motionEvent, i);
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.T != null) {
            Point o = o();
            for (TabModel tabModel : ((AbstractC0246Ea1) this.T).f7969a) {
                for (int i5 = 0; i5 < tabModel.getCount(); i5++) {
                    Tab tabAt = tabModel.getTabAt(i5);
                    if (tabAt != null) {
                        D(tabAt.l(), tabAt.u(), o.x, o.y);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r0.q(r3) == false) goto L_0x0028;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            super.onTouchEvent(r5)
            r4.I(r5)
            D70 r0 = r4.K
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0033
            VL r3 = r0.U
            if (r3 != 0) goto L_0x0011
            goto L_0x0028
        L_0x0011:
            boolean r3 = r0.g0
            if (r3 == 0) goto L_0x002a
            int r3 = r5.getActionMasked()
            if (r3 == 0) goto L_0x002a
            android.view.MotionEvent r3 = android.view.MotionEvent.obtain(r5)
            r3.setAction(r2)
            boolean r3 = r0.q(r3)
            if (r3 != 0) goto L_0x002a
        L_0x0028:
            r0 = r2
            goto L_0x0030
        L_0x002a:
            r0.g0 = r2
            boolean r0 = r0.q(r5)
        L_0x0030:
            if (r0 == 0) goto L_0x0033
            r2 = r1
        L_0x0033:
            WL r0 = r4.H
            r0.b(r5, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.CompositorViewHolder.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void p(RectF rectF) {
        float f;
        r(rectF);
        C1551Zj zj = this.U;
        if (zj != null) {
            rectF.top = zj.c() + rectF.top;
            f = (float) this.U.b();
        } else {
            f = 0.0f;
        }
        rectF.bottom -= ((float) c()) - f;
    }

    public WebContents q() {
        Tab e = e();
        if (e != null) {
            return e.l();
        }
        return null;
    }

    public void r(RectF rectF) {
        Point o = o();
        rectF.set(0.0f, 0.0f, (float) o.x, (float) o.y);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        if (r0.isAttachedToWindow() == false) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void s() {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.CompositorViewHolder.s():void");
    }

    public final void t() {
        ViewGroup d = d();
        if (d != null) {
            Point o = o();
            D(q(), d, o.x, o.y);
        }
        B();
    }

    public void u(Runnable runnable) {
        View view = this.y0;
        if (view != null) {
            view.clearFocus();
        }
        boolean z = false;
        if (hasFocus()) {
            z = C3493l60.F.d(this);
        }
        if (z) {
            this.S = runnable;
        } else {
            runnable.run();
        }
    }

    public final void v(Tab tab) {
        WebContents l = tab.l();
        if (l != null) {
            int width = this.M.getWidth();
            int height = this.M.getHeight();
            CompositorView compositorView = this.M;
            if (compositorView != null) {
                N.MzYzRqF3(compositorView.K, compositorView, l, width, height);
            }
            boolean z = this.m0;
            CompositorView compositorView2 = this.M;
            if (compositorView2 != null) {
                N.MI$giMjY(compositorView2.K, compositorView2, l, z);
            }
        }
        if (tab.b() != null) {
            if (!tab.isNativePage() || y(tab.b())) {
                Point o = o();
                D(l, tab.b(), o.x, o.y);
            }
        }
    }

    @Override // defpackage.AbstractC1282Va1
    public void w(boolean z) {
        setFocusable(!z);
    }

    public void x() {
        ViewParent parent;
        C1455Xw xw = this.a0;
        if (xw != null) {
            xw.y(xw.n, 65536);
            C1455Xw xw2 = this.a0;
            if (xw2.k.isEnabled() && (parent = xw2.l.getParent()) != null) {
                AccessibilityEvent l = xw2.l(-1, 2048);
                l.setContentChangeTypes(1);
                parent.requestSendAccessibilityEvent(xw2.l, l);
            }
        }
    }
}
