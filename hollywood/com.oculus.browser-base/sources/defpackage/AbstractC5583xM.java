package defpackage;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: xM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5583xM extends C5349w {
    public static final Rect d = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    public static final C5073uM e = new C5073uM();
    public static final C5243vM f = new C5243vM();
    public final Rect g = new Rect();
    public final Rect h = new Rect();
    public final Rect i = new Rect();
    public final int[] j = new int[2];
    public final AccessibilityManager k;
    public final View l;
    public C5413wM m;
    public int n = Integer.MIN_VALUE;
    public int o = Integer.MIN_VALUE;
    public int p = Integer.MIN_VALUE;

    public AbstractC5583xM(View view) {
        if (view != null) {
            this.l = view;
            this.k = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            view.setFocusable(true);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("View may not be null");
    }

    @Override // defpackage.C5349w
    public H b(View view) {
        if (this.m == null) {
            this.m = new C5413wM(this);
        }
        return this.m;
    }

    @Override // defpackage.C5349w
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        u(d2);
    }

    public final boolean j(int i2) {
        if (this.n != i2) {
            return false;
        }
        this.n = Integer.MIN_VALUE;
        this.l.invalidate();
        y(i2, 65536);
        return true;
    }

    public final boolean k(int i2) {
        if (this.o != i2) {
            return false;
        }
        this.o = Integer.MIN_VALUE;
        w(i2, false);
        y(i2, 8);
        return true;
    }

    public final AccessibilityEvent l(int i2, int i3) {
        if (i2 != -1) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(i3);
            D r = r(i2);
            obtain.getText().add(r.g());
            obtain.setContentDescription(r.e());
            obtain.setScrollable(r.b.isScrollable());
            obtain.setPassword(r.b.isPassword());
            obtain.setEnabled(r.b.isEnabled());
            obtain.setChecked(r.b.isChecked());
            t(i2, obtain);
            if (!obtain.getText().isEmpty() || obtain.getContentDescription() != null) {
                obtain.setClassName(r.b.getClassName());
                obtain.setSource(this.l, i2);
                obtain.setPackageName(this.l.getContext().getPackageName());
                return obtain;
            }
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        AccessibilityEvent obtain2 = AccessibilityEvent.obtain(i3);
        this.l.onInitializeAccessibilityEvent(obtain2);
        return obtain2;
    }

    public final D m(int i2) {
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
        D d2 = new D(obtain);
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        obtain.setClassName("android.view.View");
        Rect rect = d;
        obtain.setBoundsInParent(rect);
        obtain.setBoundsInScreen(rect);
        View view = this.l;
        d2.c = -1;
        obtain.setParent(view);
        v(i2, d2);
        if (d2.g() == null && d2.e() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.h);
        if (!this.h.equals(rect)) {
            int actions = obtain.getActions();
            if ((actions & 64) != 0) {
                throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            } else if ((actions & 128) == 0) {
                obtain.setPackageName(this.l.getContext().getPackageName());
                View view2 = this.l;
                d2.d = i2;
                obtain.setSource(view2, i2);
                boolean z = false;
                if (this.n == i2) {
                    obtain.setAccessibilityFocused(true);
                    obtain.addAction(128);
                } else {
                    obtain.setAccessibilityFocused(false);
                    obtain.addAction(64);
                }
                boolean z2 = this.o == i2;
                if (z2) {
                    obtain.addAction(2);
                } else if (obtain.isFocusable()) {
                    obtain.addAction(1);
                }
                obtain.setFocused(z2);
                this.l.getLocationOnScreen(this.j);
                obtain.getBoundsInScreen(this.g);
                if (this.g.equals(rect)) {
                    obtain.getBoundsInParent(this.g);
                    if (d2.c != -1) {
                        D d3 = new D(AccessibilityNodeInfo.obtain());
                        for (int i3 = d2.c; i3 != -1; i3 = d3.c) {
                            View view3 = this.l;
                            d3.c = -1;
                            d3.b.setParent(view3, -1);
                            d3.b.setBoundsInParent(d);
                            v(i3, d3);
                            d3.b.getBoundsInParent(this.h);
                            Rect rect2 = this.g;
                            Rect rect3 = this.h;
                            rect2.offset(rect3.left, rect3.top);
                        }
                        d3.b.recycle();
                    }
                    this.g.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
                }
                if (this.l.getLocalVisibleRect(this.i)) {
                    this.i.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
                    if (this.g.intersect(this.i)) {
                        d2.b.setBoundsInScreen(this.g);
                        Rect rect4 = this.g;
                        if (rect4 != null && !rect4.isEmpty() && this.l.getWindowVisibility() == 0) {
                            ViewParent parent = this.l.getParent();
                            while (true) {
                                if (parent instanceof View) {
                                    View view4 = (View) parent;
                                    if (view4.getAlpha() <= 0.0f || view4.getVisibility() != 0) {
                                        break;
                                    }
                                    parent = view4.getParent();
                                } else if (parent != null) {
                                    z = true;
                                }
                            }
                        }
                        if (z) {
                            d2.b.setVisibleToUser(true);
                        }
                    }
                }
                return d2;
            } else {
                throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }
        } else {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
    }

    public final boolean n(MotionEvent motionEvent) {
        int i2;
        if (!this.k.isEnabled() || !this.k.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int o2 = o(motionEvent.getX(), motionEvent.getY());
            int i3 = this.p;
            if (i3 != o2) {
                this.p = o2;
                y(o2, 128);
                y(i3, 256);
            }
            if (o2 != Integer.MIN_VALUE) {
                return true;
            }
            return false;
        } else if (action != 10 || (i2 = this.p) == Integer.MIN_VALUE) {
            return false;
        } else {
            if (i2 != Integer.MIN_VALUE) {
                this.p = Integer.MIN_VALUE;
                y(Integer.MIN_VALUE, 128);
                y(i2, 256);
            }
            return true;
        }
    }

    public abstract int o(float f2, float f3);

    public abstract void p(List list);

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0146, code lost:
        if (r13 < ((r17 * r17) + ((r12 * 13) * r12))) goto L_0x0148;
     */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0152 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x014d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean q(int r20, android.graphics.Rect r21) {
        /*
        // Method dump skipped, instructions count: 494
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC5583xM.q(int, android.graphics.Rect):boolean");
    }

    public D r(int i2) {
        if (i2 != -1) {
            return m(i2);
        }
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(this.l);
        D d2 = new D(obtain);
        View view = this.l;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        view.onInitializeAccessibilityNodeInfo(obtain);
        ArrayList arrayList = new ArrayList();
        p(arrayList);
        if (obtain.getChildCount() <= 0 || arrayList.size() <= 0) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                d2.b.addChild(this.l, ((Integer) arrayList.get(i3)).intValue());
            }
            return d2;
        }
        throw new RuntimeException("Views cannot have both real and virtual children");
    }

    public abstract boolean s(int i2, int i3, Bundle bundle);

    public void t(int i2, AccessibilityEvent accessibilityEvent) {
    }

    public void u(D d2) {
    }

    public abstract void v(int i2, D d2);

    public void w(int i2, boolean z) {
    }

    public final boolean x(int i2) {
        int i3;
        if ((!this.l.isFocused() && !this.l.requestFocus()) || (i3 = this.o) == i2) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            k(i3);
        }
        if (i2 == Integer.MIN_VALUE) {
            return false;
        }
        this.o = i2;
        w(i2, true);
        y(i2, 8);
        return true;
    }

    public final boolean y(int i2, int i3) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.k.isEnabled() || (parent = this.l.getParent()) == null) {
            return false;
        }
        return parent.requestSendAccessibilityEvent(this.l, l(i2, i3));
    }

    public final void z(int i2) {
        int i3 = this.p;
        if (i3 != i2) {
            this.p = i2;
            y(i2, 128);
            y(i3, 256);
        }
    }
}
