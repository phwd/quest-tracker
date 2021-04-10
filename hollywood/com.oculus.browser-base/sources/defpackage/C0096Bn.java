package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Bn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0096Bn extends AbstractC2924hn1 {
    public static final String[] c0 = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property d0 = new C4800sn(PointF.class, "boundsOrigin");
    public static final Property e0 = new C4970tn(PointF.class, "topLeft");
    public static final Property f0 = new C5140un(PointF.class, "bottomRight");
    public static final Property g0 = new C5310vn(PointF.class, "bottomRight");
    public static final Property h0 = new C5480wn(PointF.class, "topLeft");
    public static final Property i0 = new C5650xn(PointF.class, "position");
    public static C3877nK0 j0 = new C3877nK0();
    public int[] k0 = new int[2];

    public final void I(C4632rn1 rn1) {
        View view = rn1.b;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (view.isLaidOut() || view.getWidth() != 0 || view.getHeight() != 0) {
            rn1.f11223a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            rn1.f11223a.put("android:changeBounds:parent", rn1.b.getParent());
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public void e(C4632rn1 rn1) {
        I(rn1);
    }

    @Override // defpackage.AbstractC2924hn1
    public void h(C4632rn1 rn1) {
        I(rn1);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v9, resolved type: android.animation.AnimatorSet */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.AbstractC2924hn1
    public Animator l(ViewGroup viewGroup, C4632rn1 rn1, C4632rn1 rn12) {
        int i;
        ObjectAnimator objectAnimator;
        C0096Bn bn;
        if (rn1 == null || rn12 == null) {
            return null;
        }
        Map map = rn1.f11223a;
        Map map2 = rn12.f11223a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view = rn12.b;
        Rect rect = (Rect) rn1.f11223a.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) rn12.f11223a.get("android:changeBounds:bounds");
        int i2 = rect.left;
        int i3 = rect2.left;
        int i4 = rect.top;
        int i5 = rect2.top;
        int i6 = rect.right;
        int i7 = rect2.right;
        int i8 = rect.bottom;
        int i9 = rect2.bottom;
        int i10 = i6 - i2;
        int i11 = i8 - i4;
        int i12 = i7 - i3;
        int i13 = i9 - i5;
        Rect rect3 = (Rect) rn1.f11223a.get("android:changeBounds:clip");
        Rect rect4 = (Rect) rn12.f11223a.get("android:changeBounds:clip");
        if ((i10 == 0 || i11 == 0) && (i12 == 0 || i13 == 0)) {
            i = 0;
        } else {
            i = (i2 == i3 && i4 == i5) ? 0 : 1;
            if (!(i6 == i7 && i8 == i9)) {
                i++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i++;
        }
        if (i <= 0) {
            return null;
        }
        AbstractC4315pv1.b(view, i2, i4, i6, i8);
        if (i != 2) {
            bn = this;
            if (i2 == i3 && i4 == i5) {
                objectAnimator = AbstractC0713Lq0.a(view, g0, bn.b0.a((float) i6, (float) i8, (float) i7, (float) i9));
            } else {
                objectAnimator = AbstractC0713Lq0.a(view, h0, bn.b0.a((float) i2, (float) i4, (float) i3, (float) i5));
            }
        } else if (i10 == i12 && i11 == i13) {
            bn = this;
            objectAnimator = AbstractC0713Lq0.a(view, i0, bn.b0.a((float) i2, (float) i4, (float) i3, (float) i5));
        } else {
            bn = this;
            C0035An an = new C0035An(view);
            ObjectAnimator a2 = AbstractC0713Lq0.a(an, e0, bn.b0.a((float) i2, (float) i4, (float) i3, (float) i5));
            ObjectAnimator a3 = AbstractC0713Lq0.a(an, f0, bn.b0.a((float) i6, (float) i8, (float) i7, (float) i9));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(a2, a3);
            animatorSet.addListener(new C5820yn(bn, an));
            objectAnimator = animatorSet;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            AbstractC3286ju1.a(viewGroup4, true);
            bn.a(new C5990zn(bn, viewGroup4));
        }
        return objectAnimator;
    }

    @Override // defpackage.AbstractC2924hn1
    public String[] q() {
        return c0;
    }
}
