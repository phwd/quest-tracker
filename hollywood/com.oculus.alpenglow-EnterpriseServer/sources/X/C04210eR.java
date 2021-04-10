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
import com.oculus.alpenglow.R;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0eR  reason: invalid class name and case insensitive filesystem */
public class C04210eR {
    public int A00 = 8388611;
    public View A01;
    public PopupWindow.OnDismissListener A02;
    public AbstractC04220eS A03;
    public AbstractC000503a A04;
    public boolean A05;
    public final int A06;
    public final int A07;
    public final Context A08;
    public final PopupWindow.OnDismissListener A09 = new AnonymousClass03Z(this);
    public final C04280eZ A0A;
    public final boolean A0B;

    public void A02() {
        this.A03 = null;
        PopupWindow.OnDismissListener onDismissListener = this.A02;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @NonNull
    public final AbstractC04220eS A01() {
        AbstractC04220eS r2 = this.A03;
        if (r2 == null) {
            Context context = this.A08;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                r2 = new View$OnKeyListenerC01900Mx(context, this.A01, this.A06, this.A07, this.A0B);
            } else {
                r2 = new AnonymousClass0Mv(context, this.A0A, this.A01, this.A06, this.A07, this.A0B);
            }
            r2.A07(this.A0A);
            r2.A06(this.A09);
            r2.A05(this.A01);
            r2.A7m(this.A04);
            r2.A08(this.A05);
            r2.A02(this.A00);
            this.A03 = r2;
        }
        return r2;
    }

    public final void A04(@Nullable AbstractC000503a r2) {
        this.A04 = r2;
        AbstractC04220eS r0 = this.A03;
        if (r0 != null) {
            r0.A7m(r2);
        }
    }

    public final boolean A05() {
        AbstractC04220eS r0 = this.A03;
        if (r0 == null || !r0.A5a()) {
            return false;
        }
        return true;
    }

    public C04210eR(@NonNull Context context, @NonNull C04280eZ r3, @NonNull View view, boolean z, @AttrRes int i, @StyleRes int i2) {
        this.A08 = context;
        this.A0A = r3;
        this.A01 = view;
        this.A0B = z;
        this.A06 = i;
        this.A07 = i2;
    }

    public static void A00(C04210eR r4, int i, int i2, boolean z, boolean z2) {
        AbstractC04220eS A012 = r4.A01();
        A012.A09(z2);
        if (z) {
            if ((Gravity.getAbsoluteGravity(r4.A00, r4.A01.getLayoutDirection()) & 7) == 5) {
                i -= r4.A01.getWidth();
            }
            A012.A03(i);
            A012.A04(i2);
            int i3 = (int) ((r4.A08.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            A012.A00 = new Rect(i - i3, i2 - i3, i + i3, i2 + i3);
        }
        A012.A8P();
    }

    public final void A03() {
        if (A05()) {
            this.A03.dismiss();
        }
    }
}
