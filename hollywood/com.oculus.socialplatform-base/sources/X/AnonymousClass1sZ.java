package X;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import com.oculus.socialplatform.R;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1sZ  reason: invalid class name */
public class AnonymousClass1sZ {
    public int A00 = 8388611;
    public View A01;
    public PopupWindow.OnDismissListener A02;
    public AnonymousClass1sY A03;
    public AbstractC11941tc A04;
    public boolean A05;
    public final int A06;
    public final int A07;
    public final Context A08;
    public final PopupWindow.OnDismissListener A09 = new C12001tk(this);
    public final C11581sN A0A;
    public final boolean A0B;

    public void A02() {
        this.A03 = null;
        PopupWindow.OnDismissListener onDismissListener = this.A02;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @NonNull
    public final AnonymousClass1sY A01() {
        AnonymousClass1sY r5 = this.A03;
        if (r5 == null) {
            Context context = this.A08;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                r5 = new View$OnKeyListenerC11671sd(context, this.A01, this.A06, this.A07, this.A0B);
            } else {
                r5 = new View$OnKeyListenerC11681se(context, this.A0A, this.A01, this.A06, this.A07, this.A0B);
            }
            C11581sN r2 = this.A0A;
            boolean z = r5 instanceof View$OnKeyListenerC11681se;
            if (!z) {
                View$OnKeyListenerC11671sd r1 = (View$OnKeyListenerC11671sd) r5;
                r2.A0E(r1, r1.A0J);
                if (r1.A6B()) {
                    View$OnKeyListenerC11671sd.A01(r1, r2);
                } else {
                    r1.A0L.add(r2);
                }
            }
            PopupWindow.OnDismissListener onDismissListener = this.A09;
            if (!z) {
                ((View$OnKeyListenerC11671sd) r5).A09 = onDismissListener;
            } else {
                ((View$OnKeyListenerC11681se) r5).A05 = onDismissListener;
            }
            View view = this.A01;
            if (!z) {
                View$OnKeyListenerC11671sd r22 = (View$OnKeyListenerC11671sd) r5;
                if (r22.A07 != view) {
                    r22.A07 = view;
                    r22.A02 = Gravity.getAbsoluteGravity(r22.A04, view.getLayoutDirection());
                }
            } else {
                ((View$OnKeyListenerC11681se) r5).A02 = view;
            }
            r5.A9h(this.A04);
            r5.A02(this.A05);
            int i = this.A00;
            if (!z) {
                View$OnKeyListenerC11671sd r12 = (View$OnKeyListenerC11671sd) r5;
                if (r12.A04 != i) {
                    r12.A04 = i;
                    r12.A02 = Gravity.getAbsoluteGravity(i, r12.A07.getLayoutDirection());
                }
            } else {
                ((View$OnKeyListenerC11681se) r5).A01 = i;
            }
            this.A03 = r5;
        }
        return r5;
    }

    public final void A04(@Nullable AbstractC11941tc r2) {
        this.A04 = r2;
        AnonymousClass1sY r0 = this.A03;
        if (r0 != null) {
            r0.A9h(r2);
        }
    }

    public final boolean A05() {
        AnonymousClass1sY r0 = this.A03;
        if (r0 == null || !r0.A6B()) {
            return false;
        }
        return true;
    }

    public AnonymousClass1sZ(@NonNull Context context, @NonNull C11581sN r3, @NonNull View view, boolean z, @AttrRes int i, @StyleRes int i2) {
        this.A08 = context;
        this.A0A = r3;
        this.A01 = view;
        this.A0B = z;
        this.A06 = i;
        this.A07 = i2;
    }

    public static void A00(AnonymousClass1sZ r4, int i, int i2, boolean z, boolean z2) {
        AnonymousClass1sY A012 = r4.A01();
        boolean z3 = A012 instanceof View$OnKeyListenerC11681se;
        if (!z3) {
            ((View$OnKeyListenerC11671sd) A012).A0D = z2;
        } else {
            ((View$OnKeyListenerC11681se) A012).A07 = z2;
        }
        if (z) {
            if ((Gravity.getAbsoluteGravity(r4.A00, r4.A01.getLayoutDirection()) & 7) == 5) {
                i -= r4.A01.getWidth();
            }
            if (!z3) {
                View$OnKeyListenerC11671sd r1 = (View$OnKeyListenerC11671sd) A012;
                r1.A0B = true;
                r1.A05 = i;
            } else {
                ((View$OnKeyListenerC11681se) A012).A0G.A9u(i);
            }
            if (!z3) {
                View$OnKeyListenerC11671sd r12 = (View$OnKeyListenerC11671sd) A012;
                r12.A0C = true;
                r12.A06 = i2;
            } else {
                ((View$OnKeyListenerC11681se) A012).A0G.AAG(i2);
            }
            int i3 = (int) ((r4.A08.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            A012.A00 = new Rect(i - i3, i2 - i3, i + i3, i2 + i3);
        }
        A012.AAO();
    }

    public final void A03() {
        if (A05()) {
            this.A03.dismiss();
        }
    }
}
