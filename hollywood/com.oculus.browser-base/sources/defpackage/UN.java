package defpackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: UN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UN extends AbstractC2924hn1 {
    public static final String[] c0 = {"android:visibility:visibility", "android:visibility:parent"};
    public int d0 = 3;

    public UN(int i) {
        if ((i & -4) == 0) {
            this.d0 = i;
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }

    public final void I(C4632rn1 rn1) {
        rn1.f11223a.put("android:visibility:visibility", Integer.valueOf(rn1.b.getVisibility()));
        rn1.f11223a.put("android:visibility:parent", rn1.b.getParent());
        int[] iArr = new int[2];
        rn1.b.getLocationOnScreen(iArr);
        rn1.f11223a.put("android:visibility:screenLocation", iArr);
    }

    public final Animator J(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        AbstractC4315pv1.f11100a.e(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AbstractC4315pv1.b, f2);
        ofFloat.addListener(new TN(view));
        a(new SN(this, view));
        return ofFloat;
    }

    public final Ev1 K(C4632rn1 rn1, C4632rn1 rn12) {
        Ev1 ev1 = new Ev1();
        ev1.f7987a = false;
        ev1.b = false;
        if (rn1 == null || !rn1.f11223a.containsKey("android:visibility:visibility")) {
            ev1.c = -1;
            ev1.e = null;
        } else {
            ev1.c = ((Integer) rn1.f11223a.get("android:visibility:visibility")).intValue();
            ev1.e = (ViewGroup) rn1.f11223a.get("android:visibility:parent");
        }
        if (rn12 == null || !rn12.f11223a.containsKey("android:visibility:visibility")) {
            ev1.d = -1;
            ev1.f = null;
        } else {
            ev1.d = ((Integer) rn12.f11223a.get("android:visibility:visibility")).intValue();
            ev1.f = (ViewGroup) rn12.f11223a.get("android:visibility:parent");
        }
        if (rn1 != null && rn12 != null) {
            int i = ev1.c;
            int i2 = ev1.d;
            if (!(i == i2 && ev1.e == ev1.f)) {
                if (i != i2) {
                    if (i == 0) {
                        ev1.b = false;
                        ev1.f7987a = true;
                    } else if (i2 == 0) {
                        ev1.b = true;
                        ev1.f7987a = true;
                    }
                } else if (ev1.f == null) {
                    ev1.b = false;
                    ev1.f7987a = true;
                } else if (ev1.e == null) {
                    ev1.b = true;
                    ev1.f7987a = true;
                }
            }
        } else if (rn1 == null && ev1.d == 0) {
            ev1.b = true;
            ev1.f7987a = true;
        } else if (rn12 == null && ev1.c == 0) {
            ev1.b = false;
            ev1.f7987a = true;
        }
        return ev1;
    }

    public Animator L(View view, C4632rn1 rn1) {
        Float f;
        AbstractC4315pv1.f11100a.c(view);
        return J(view, (rn1 == null || (f = (Float) rn1.f11223a.get("android:fade:transitionAlpha")) == null) ? 1.0f : f.floatValue(), 0.0f);
    }

    @Override // defpackage.AbstractC2924hn1
    public void e(C4632rn1 rn1) {
        I(rn1);
    }

    @Override // defpackage.AbstractC2924hn1
    public void h(C4632rn1 rn1) {
        I(rn1);
        rn1.f11223a.put("android:fade:transitionAlpha", Float.valueOf(AbstractC4315pv1.a(rn1.b)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        if (K(o(r1, false), r(r1, false)).f7987a != false) goto L_0x0066;
     */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01e3  */
    @Override // defpackage.AbstractC2924hn1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator l(android.view.ViewGroup r21, defpackage.C4632rn1 r22, defpackage.C4632rn1 r23) {
        /*
        // Method dump skipped, instructions count: 673
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UN.l(android.view.ViewGroup, rn1, rn1):android.animation.Animator");
    }

    @Override // defpackage.AbstractC2924hn1
    public String[] q() {
        return c0;
    }

    @Override // defpackage.AbstractC2924hn1
    public boolean s(C4632rn1 rn1, C4632rn1 rn12) {
        if (rn1 == null && rn12 == null) {
            return false;
        }
        if (rn1 != null && rn12 != null && rn12.f11223a.containsKey("android:visibility:visibility") != rn1.f11223a.containsKey("android:visibility:visibility")) {
            return false;
        }
        Ev1 K = K(rn1, rn12);
        if (!K.f7987a) {
            return false;
        }
        if (K.c == 0 || K.d == 0) {
            return true;
        }
        return false;
    }
}
