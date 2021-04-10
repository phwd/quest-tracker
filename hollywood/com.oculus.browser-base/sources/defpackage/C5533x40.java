package defpackage;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* renamed from: x40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5533x40 extends EK0 implements KK0 {
    public final LK0 A = new C3829n40(this);
    public Rect B;
    public long C;

    /* renamed from: a  reason: collision with root package name */
    public final List f11584a = new ArrayList();
    public final float[] b = new float[2];
    public XK0 c = null;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public int l = -1;
    public AbstractC4853t40 m;
    public int n = 0;
    public int o;
    public List p = new ArrayList();
    public int q;
    public RecyclerView r;
    public final Runnable s = new RunnableC3658m40(this);
    public VelocityTracker t;
    public List u;
    public List v;
    public View w = null;
    public int x = -1;
    public C3898nV y;
    public C5023u40 z;

    public C5533x40(AbstractC4853t40 t40) {
        this.m = t40;
    }

    public static boolean q(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    @Override // defpackage.KK0
    public void b(View view) {
    }

    @Override // defpackage.KK0
    public void d(View view) {
        s(view);
        XK0 L = this.r.L(view);
        if (L != null) {
            XK0 xk0 = this.c;
            if (xk0 == null || L != xk0) {
                n(L, false);
                if (this.f11584a.remove(L.G)) {
                    this.m.b(this.r, L);
                    return;
                }
                return;
            }
            t(null, 0);
        }
    }

    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        rect.setEmpty();
    }

    @Override // defpackage.EK0
    public void h(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
        float f2;
        float f3;
        this.x = -1;
        if (this.c != null) {
            p(this.b);
            float[] fArr = this.b;
            float f4 = fArr[0];
            f2 = fArr[1];
            f3 = f4;
        } else {
            f3 = 0.0f;
            f2 = 0.0f;
        }
        AbstractC4853t40 t40 = this.m;
        XK0 xk0 = this.c;
        List list = this.p;
        int i2 = this.n;
        Objects.requireNonNull(t40);
        int i3 = 0;
        for (int size = list.size(); i3 < size; size = size) {
            C4000o40 o40 = (C4000o40) list.get(i3);
            float f5 = o40.F;
            float f6 = o40.H;
            if (f5 == f6) {
                o40.N = o40.f10530J.G.getTranslationX();
            } else {
                o40.N = AbstractC2531fV.a(f6, f5, o40.R, f5);
            }
            float f7 = o40.G;
            float f8 = o40.I;
            if (f7 == f8) {
                o40.O = o40.f10530J.G.getTranslationY();
            } else {
                o40.O = AbstractC2531fV.a(f8, f7, o40.R, f7);
            }
            int save = canvas.save();
            t40.k(canvas, recyclerView, o40.f10530J, o40.N, o40.O, o40.K, false);
            canvas.restoreToCount(save);
            i3++;
        }
        if (xk0 != null) {
            int save2 = canvas.save();
            t40.k(canvas, recyclerView, xk0, f3, f2, i2, true);
            canvas.restoreToCount(save2);
        }
    }

    @Override // defpackage.EK0
    public void i(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
        boolean z2 = false;
        if (this.c != null) {
            p(this.b);
            float[] fArr = this.b;
            float f2 = fArr[0];
            float f3 = fArr[1];
        }
        AbstractC4853t40 t40 = this.m;
        XK0 xk0 = this.c;
        List list = this.p;
        Objects.requireNonNull(t40);
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            int save = canvas.save();
            View view = ((C4000o40) list.get(i2)).f10530J.G;
            canvas.restoreToCount(save);
        }
        if (xk0 != null) {
            canvas.restoreToCount(canvas.save());
        }
        for (int i3 = size - 1; i3 >= 0; i3--) {
            C4000o40 o40 = (C4000o40) list.get(i3);
            boolean z3 = o40.Q;
            if (z3 && !o40.M) {
                list.remove(i3);
            } else if (!z3) {
                z2 = true;
            }
        }
        if (z2) {
            recyclerView.invalidate();
        }
    }

    public void j(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.r;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.j0(this);
                RecyclerView recyclerView3 = this.r;
                LK0 lk0 = this.A;
                recyclerView3.b0.remove(lk0);
                if (recyclerView3.c0 == lk0) {
                    recyclerView3.c0 = null;
                }
                List list = this.r.o0;
                if (list != null) {
                    list.remove(this);
                }
                for (int size = this.p.size() - 1; size >= 0; size--) {
                    C4000o40 o40 = (C4000o40) this.p.get(0);
                    o40.L.cancel();
                    this.m.b(this.r, o40.f10530J);
                }
                this.p.clear();
                this.w = null;
                this.x = -1;
                VelocityTracker velocityTracker = this.t;
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                    this.t = null;
                }
                C5023u40 u40 = this.z;
                if (u40 != null) {
                    u40.f11384a = false;
                    this.z = null;
                }
                if (this.y != null) {
                    this.y = null;
                }
            }
            this.r = recyclerView;
            if (recyclerView != null) {
                Resources resources = recyclerView.getResources();
                this.f = resources.getDimension(R.dimen.f20110_resource_name_obfuscated_RES_2131165630);
                this.g = resources.getDimension(R.dimen.f20100_resource_name_obfuscated_RES_2131165629);
                this.q = ViewConfiguration.get(this.r.getContext()).getScaledTouchSlop();
                this.r.g(this);
                this.r.b0.add(this.A);
                this.r.h(this);
                this.z = new C5023u40(this);
                this.y = new C3898nV(this.r.getContext(), this.z);
            }
        }
    }

    public final int k(XK0 xk0, int i2) {
        if ((i2 & 12) == 0) {
            return 0;
        }
        int i3 = 8;
        int i4 = this.h > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            AbstractC4853t40 t40 = this.m;
            float f2 = this.g;
            Objects.requireNonNull(t40);
            velocityTracker.computeCurrentVelocity(1000, f2);
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            if (xVelocity <= 0.0f) {
                i3 = 4;
            }
            float abs = Math.abs(xVelocity);
            if ((i3 & i2) != 0 && i4 == i3) {
                AbstractC4853t40 t402 = this.m;
                float f3 = this.f;
                Objects.requireNonNull(t402);
                if (abs >= f3 && abs > Math.abs(yVelocity)) {
                    return i3;
                }
            }
        }
        float g2 = this.m.g(xk0) * ((float) this.r.getWidth());
        if ((i2 & i4) == 0 || Math.abs(this.h) <= g2) {
            return 0;
        }
        return i4;
    }

    public void l(int i2, MotionEvent motionEvent, int i3) {
        int e2;
        View o2;
        if (this.c == null && i2 == 2 && this.n != 2) {
            AbstractC4853t40 t40 = this.m;
            Objects.requireNonNull(t40);
            if (!(t40 instanceof TI)) {
                RecyclerView recyclerView = this.r;
                if (recyclerView.z0 != 1) {
                    IK0 ik0 = recyclerView.U;
                    int i4 = this.l;
                    XK0 xk0 = null;
                    if (i4 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i4);
                        float abs = Math.abs(motionEvent.getX(findPointerIndex) - this.d);
                        float abs2 = Math.abs(motionEvent.getY(findPointerIndex) - this.e);
                        float f2 = (float) this.q;
                        if ((abs >= f2 || abs2 >= f2) && ((abs <= abs2 || !ik0.f()) && ((abs2 <= abs || !ik0.g()) && (o2 = o(motionEvent)) != null))) {
                            xk0 = this.r.L(o2);
                        }
                    }
                    if (xk0 != null && (e2 = (this.m.e(this.r, xk0) & 65280) >> 8) != 0) {
                        float x2 = motionEvent.getX(i3);
                        float y2 = motionEvent.getY(i3);
                        float f3 = x2 - this.d;
                        float f4 = y2 - this.e;
                        float abs3 = Math.abs(f3);
                        float abs4 = Math.abs(f4);
                        float f5 = (float) this.q;
                        if (abs3 >= f5 || abs4 >= f5) {
                            if (abs3 > abs4) {
                                if (f3 < 0.0f && (e2 & 4) == 0) {
                                    return;
                                }
                                if (f3 > 0.0f && (e2 & 8) == 0) {
                                    return;
                                }
                            } else if (f4 < 0.0f && (e2 & 1) == 0) {
                                return;
                            } else {
                                if (f4 > 0.0f && (e2 & 2) == 0) {
                                    return;
                                }
                            }
                            this.i = 0.0f;
                            this.h = 0.0f;
                            this.l = motionEvent.getPointerId(0);
                            t(xk0, 1);
                        }
                    }
                }
            }
        }
    }

    public final int m(XK0 xk0, int i2) {
        if ((i2 & 3) == 0) {
            return 0;
        }
        int i3 = 2;
        int i4 = this.i > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            AbstractC4853t40 t40 = this.m;
            float f2 = this.g;
            Objects.requireNonNull(t40);
            velocityTracker.computeCurrentVelocity(1000, f2);
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            if (yVelocity <= 0.0f) {
                i3 = 1;
            }
            float abs = Math.abs(yVelocity);
            if ((i3 & i2) != 0 && i3 == i4) {
                AbstractC4853t40 t402 = this.m;
                float f3 = this.f;
                Objects.requireNonNull(t402);
                if (abs >= f3 && abs > Math.abs(xVelocity)) {
                    return i3;
                }
            }
        }
        float g2 = this.m.g(xk0) * ((float) this.r.getHeight());
        if ((i2 & i4) == 0 || Math.abs(this.i) <= g2) {
            return 0;
        }
        return i4;
    }

    public void n(XK0 xk0, boolean z2) {
        C4000o40 o40;
        int size = this.p.size();
        do {
            size--;
            if (size >= 0) {
                o40 = (C4000o40) this.p.get(size);
            } else {
                return;
            }
        } while (o40.f10530J != xk0);
        o40.P |= z2;
        if (!o40.Q) {
            o40.L.cancel();
        }
        this.p.remove(size);
    }

    public View o(MotionEvent motionEvent) {
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        XK0 xk0 = this.c;
        if (xk0 != null) {
            View view = xk0.G;
            if (q(view, x2, y2, this.j + this.h, this.k + this.i)) {
                return view;
            }
        }
        for (int size = this.p.size() - 1; size >= 0; size--) {
            C4000o40 o40 = (C4000o40) this.p.get(size);
            View view2 = o40.f10530J.G;
            if (q(view2, x2, y2, o40.N, o40.O)) {
                return view2;
            }
        }
        RecyclerView recyclerView = this.r;
        int e2 = recyclerView.M.e();
        while (true) {
            e2--;
            if (e2 < 0) {
                return null;
            }
            View d2 = recyclerView.M.d(e2);
            float translationX = d2.getTranslationX();
            float translationY = d2.getTranslationY();
            if (x2 >= ((float) d2.getLeft()) + translationX && x2 <= ((float) d2.getRight()) + translationX && y2 >= ((float) d2.getTop()) + translationY && y2 <= ((float) d2.getBottom()) + translationY) {
                return d2;
            }
        }
    }

    public final void p(float[] fArr) {
        if ((this.o & 12) != 0) {
            fArr[0] = (this.j + this.h) - ((float) this.c.G.getLeft());
        } else {
            fArr[0] = this.c.G.getTranslationX();
        }
        if ((this.o & 3) != 0) {
            fArr[1] = (this.k + this.i) - ((float) this.c.G.getTop());
        } else {
            fArr[1] = this.c.G.getTranslationY();
        }
    }

    public void r(XK0 xk0) {
        List list;
        int bottom;
        int abs;
        int top;
        int abs2;
        int left;
        int abs3;
        int right;
        int abs4;
        int i2;
        int i3;
        if (!this.r.isLayoutRequested() && this.n == 2) {
            Objects.requireNonNull(this.m);
            int i4 = (int) (this.j + this.h);
            int i5 = (int) (this.k + this.i);
            if (((float) Math.abs(i5 - xk0.G.getTop())) >= ((float) xk0.G.getHeight()) * 0.5f || ((float) Math.abs(i4 - xk0.G.getLeft())) >= ((float) xk0.G.getWidth()) * 0.5f) {
                List list2 = this.u;
                if (list2 == null) {
                    this.u = new ArrayList();
                    this.v = new ArrayList();
                } else {
                    list2.clear();
                    this.v.clear();
                }
                Objects.requireNonNull(this.m);
                int round = Math.round(this.j + this.h) - 0;
                int round2 = Math.round(this.k + this.i) - 0;
                int width = xk0.G.getWidth() + round + 0;
                int height = xk0.G.getHeight() + round2 + 0;
                int i6 = (round + width) / 2;
                int i7 = (round2 + height) / 2;
                IK0 ik0 = this.r.U;
                int z2 = ik0.z();
                int i8 = 0;
                while (i8 < z2) {
                    View y2 = ik0.y(i8);
                    if (y2 != xk0.G && y2.getBottom() >= round2 && y2.getTop() <= height && y2.getRight() >= round && y2.getLeft() <= width) {
                        XK0 L = this.r.L(y2);
                        i3 = round;
                        i2 = round2;
                        if (this.m.a(this.r, this.c, L)) {
                            int abs5 = Math.abs(i6 - ((y2.getRight() + y2.getLeft()) / 2));
                            int abs6 = Math.abs(i7 - ((y2.getBottom() + y2.getTop()) / 2));
                            int i9 = (abs6 * abs6) + (abs5 * abs5);
                            int i10 = 0;
                            int i11 = 0;
                            for (int size = this.u.size(); i11 < size && i9 > ((Integer) this.v.get(i11)).intValue(); size = size) {
                                i10++;
                                i11++;
                            }
                            this.u.add(i10, L);
                            this.v.add(i10, Integer.valueOf(i9));
                        }
                    } else {
                        i3 = round;
                        i2 = round2;
                    }
                    i8++;
                    round = i3;
                    round2 = i2;
                }
                List list3 = this.u;
                if (list3.size() != 0) {
                    Objects.requireNonNull(this.m);
                    int width2 = xk0.G.getWidth() + i4;
                    int height2 = xk0.G.getHeight() + i5;
                    int left2 = i4 - xk0.G.getLeft();
                    int top2 = i5 - xk0.G.getTop();
                    int size2 = list3.size();
                    int i12 = -1;
                    XK0 xk02 = null;
                    int i13 = 0;
                    while (i13 < size2) {
                        XK0 xk03 = (XK0) list3.get(i13);
                        if (left2 <= 0 || (right = xk03.G.getRight() - width2) >= 0) {
                            list = list3;
                        } else {
                            list = list3;
                            if (xk03.G.getRight() > xk0.G.getRight() && (abs4 = Math.abs(right)) > i12) {
                                i12 = abs4;
                                xk02 = xk03;
                            }
                        }
                        if (left2 < 0 && (left = xk03.G.getLeft() - i4) > 0 && xk03.G.getLeft() < xk0.G.getLeft() && (abs3 = Math.abs(left)) > i12) {
                            i12 = abs3;
                            xk02 = xk03;
                        }
                        if (top2 < 0 && (top = xk03.G.getTop() - i5) > 0 && xk03.G.getTop() < xk0.G.getTop() && (abs2 = Math.abs(top)) > i12) {
                            i12 = abs2;
                            xk02 = xk03;
                        }
                        if (top2 > 0 && (bottom = xk03.G.getBottom() - height2) < 0 && xk03.G.getBottom() > xk0.G.getBottom() && (abs = Math.abs(bottom)) > i12) {
                            i12 = abs;
                            xk02 = xk03;
                        }
                        i13++;
                        list3 = list;
                    }
                    if (xk02 == null) {
                        this.u.clear();
                        this.v.clear();
                        return;
                    }
                    int e2 = xk02.e();
                    xk0.e();
                    if (this.m.l(this.r, xk0, xk02)) {
                        AbstractC4853t40 t40 = this.m;
                        RecyclerView recyclerView = this.r;
                        Objects.requireNonNull(t40);
                        IK0 ik02 = recyclerView.U;
                        if (ik02 instanceof LinearLayoutManager) {
                            ((LinearLayoutManager) ik02).w1(xk0.G, xk02.G);
                            return;
                        }
                        if (ik02.f()) {
                            if (ik02.D(xk02.G) <= recyclerView.getPaddingLeft()) {
                                recyclerView.p0(e2);
                            }
                            if (ik02.G(xk02.G) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                                recyclerView.p0(e2);
                            }
                        }
                        if (ik02.g()) {
                            if (ik02.H(xk02.G) <= recyclerView.getPaddingTop()) {
                                recyclerView.p0(e2);
                            }
                            if (ik02.C(xk02.G) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                                recyclerView.p0(e2);
                            }
                        }
                    }
                }
            }
        }
    }

    public void s(View view) {
        if (view == this.w) {
            this.w = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0096, code lost:
        if (r2 > 0) goto L_0x00b5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void t(defpackage.XK0 r22, int r23) {
        /*
        // Method dump skipped, instructions count: 464
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5533x40.t(XK0, int):void");
    }

    public void u(MotionEvent motionEvent, int i2, int i3) {
        float x2 = motionEvent.getX(i3);
        float y2 = motionEvent.getY(i3);
        float f2 = x2 - this.d;
        this.h = f2;
        this.i = y2 - this.e;
        if ((i2 & 4) == 0) {
            this.h = Math.max(0.0f, f2);
        }
        if ((i2 & 8) == 0) {
            this.h = Math.min(0.0f, this.h);
        }
        if ((i2 & 1) == 0) {
            this.i = Math.max(0.0f, this.i);
        }
        if ((i2 & 2) == 0) {
            this.i = Math.min(0.0f, this.i);
        }
    }
}
