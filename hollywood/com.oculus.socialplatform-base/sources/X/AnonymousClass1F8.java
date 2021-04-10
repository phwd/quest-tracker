package X;

import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.ActionMenuItemView;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1F8  reason: invalid class name */
public abstract class AnonymousClass1F8 implements View.OnAttachStateChangeListener, View.OnTouchListener {
    public int A00;
    public Runnable A01;
    public Runnable A02;
    public boolean A03;
    public final float A04;
    public final int A05;
    public final int A06;
    public final View A07;
    public final int[] A08 = new int[2];

    public final void onViewAttachedToWindow(View view) {
    }

    public final void onViewDetachedFromWindow(View view) {
        this.A03 = false;
        this.A00 = -1;
        Runnable runnable = this.A01;
        if (runnable != null) {
            this.A07.removeCallbacks(runnable);
        }
    }

    public static void A00(AnonymousClass1F8 r2) {
        Runnable runnable = r2.A02;
        if (runnable != null) {
            r2.A07.removeCallbacks(runnable);
        }
        Runnable runnable2 = r2.A01;
        if (runnable2 != null) {
            r2.A07.removeCallbacks(runnable2);
        }
    }

    private final AnonymousClass1FA A01() {
        C11781sp r0;
        if (this instanceof AnonymousClass1t7) {
            return ((AnonymousClass1t7) this).A00;
        }
        if (!(this instanceof AnonymousClass1t0)) {
            AbstractC11991tj r02 = ((AnonymousClass1tA) this).A00.A00;
            if (r02 == null || (r0 = ((AnonymousClass1tP) r02).A00.A03) == null) {
                return null;
            }
            return r0.A01();
        }
        AnonymousClass1t5 r03 = ((AnonymousClass1t0) this).A01.A00.A07;
        if (r03 == null) {
            return null;
        }
        return r03.A01();
    }

    public final boolean A02() {
        AnonymousClass1FA A012;
        if (this instanceof AnonymousClass1t7) {
            AnonymousClass1sR r3 = ((AnonymousClass1t7) this).A01;
            if (r3.A02.A6B()) {
                return true;
            }
            r3.A02.AAP(r3.getTextDirection(), r3.getTextAlignment());
            return true;
        } else if (this instanceof AnonymousClass1t0) {
            ((AnonymousClass1t0) this).A01.A00.A05();
            return true;
        } else if (!(this instanceof AnonymousClass1tA)) {
            AnonymousClass1FA A013 = A01();
            if (A013 == null || A013.A6B()) {
                return true;
            }
            A013.AAO();
            return true;
        } else {
            AnonymousClass1tA r32 = (AnonymousClass1tA) this;
            ActionMenuItemView actionMenuItemView = r32.A00;
            AbstractC11921ta r2 = actionMenuItemView.A01;
            if (r2 == null || !r2.A5p(actionMenuItemView.A02) || (A012 = r32.A01()) == null || !A012.A6B()) {
                return false;
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0056, code lost:
        if (r2 == 3) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005b, code lost:
        if (r0 != false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009c, code lost:
        if (r1 != 3) goto L_0x009e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouch(android.view.View r15, android.view.MotionEvent r16) {
        /*
        // Method dump skipped, instructions count: 301
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1F8.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public AnonymousClass1F8(View view) {
        this.A07 = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.A04 = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.A06 = tapTimeout;
        this.A05 = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }
}
