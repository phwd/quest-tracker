package X;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1FD  reason: invalid class name */
public final class AnonymousClass1FD implements View.OnHoverListener, View.OnAttachStateChangeListener, View.OnLongClickListener {
    public static AnonymousClass1FD A09;
    public static AnonymousClass1FD A0A;
    public int A00;
    public int A01;
    public AnonymousClass1FE A02;
    public boolean A03;
    public final int A04;
    public final View A05;
    public final Runnable A06 = new AnonymousClass1FG(this);
    public final CharSequence A07;
    public final Runnable A08 = new AnonymousClass1FH(this);

    public final void onViewAttachedToWindow(View view) {
    }

    public static void A00(AnonymousClass1FD r4) {
        AnonymousClass1FD r0 = A0A;
        if (r0 != null) {
            r0.A05.removeCallbacks(r0.A06);
        }
        A0A = r4;
        if (r4 != null) {
            r4.A05.postDelayed(r4.A06, (long) ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void A01() {
        if (A09 == this) {
            A09 = null;
            AnonymousClass1FE r1 = this.A02;
            if (r1 != null) {
                View view = r1.A02;
                if (view.getParent() != null) {
                    ((WindowManager) r1.A00.getSystemService("window")).removeView(view);
                }
                this.A02 = null;
                this.A00 = Integer.MAX_VALUE;
                this.A01 = Integer.MAX_VALUE;
                this.A05.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (A0A == this) {
            A00(null);
        }
        this.A05.removeCallbacks(this.A08);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0134  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A02(boolean r19) {
        /*
        // Method dump skipped, instructions count: 383
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1FD.A02(boolean):void");
    }

    public final boolean onHover(View view, MotionEvent motionEvent) {
        if (this.A02 == null || !this.A03) {
            View view2 = this.A05;
            AccessibilityManager accessibilityManager = (AccessibilityManager) view2.getContext().getSystemService("accessibility");
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                return false;
            }
            int action = motionEvent.getAction();
            if (action != 7) {
                if (action == 10) {
                    this.A00 = Integer.MAX_VALUE;
                    this.A01 = Integer.MAX_VALUE;
                    A01();
                    return false;
                }
            } else if (view2.isEnabled() && this.A02 == null) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                int abs = Math.abs(x - this.A00);
                int i = this.A04;
                if (abs <= i && Math.abs(y - this.A01) <= i) {
                    return false;
                }
                this.A00 = x;
                this.A01 = y;
                A00(this);
                return false;
            }
        }
        return false;
    }

    public AnonymousClass1FD(View view, CharSequence charSequence) {
        this.A05 = view;
        this.A07 = charSequence;
        this.A04 = AnonymousClass07g.A03(ViewConfiguration.get(view.getContext()));
        this.A00 = Integer.MAX_VALUE;
        this.A01 = Integer.MAX_VALUE;
        this.A05.setOnLongClickListener(this);
        this.A05.setOnHoverListener(this);
    }

    public final boolean onLongClick(View view) {
        this.A00 = view.getWidth() >> 1;
        this.A01 = view.getHeight() >> 1;
        A02(true);
        return true;
    }

    public final void onViewDetachedFromWindow(View view) {
        A01();
    }
}
