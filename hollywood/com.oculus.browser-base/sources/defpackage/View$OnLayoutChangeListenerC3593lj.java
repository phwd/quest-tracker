package defpackage;

import android.view.View;
import android.view.Window;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;
import org.chromium.components.browser_ui.bottomsheet.TouchRestrictingFrameLayout;

/* renamed from: lj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC3593lj implements View.OnLayoutChangeListener {
    public int F;
    public final /* synthetic */ Window G;
    public final /* synthetic */ C3493l60 H;
    public final /* synthetic */ BottomSheet I;

    public View$OnLayoutChangeListenerC3593lj(BottomSheet bottomSheet, Window window, C3493l60 l60) {
        this.I = bottomSheet;
        this.G = window;
        this.H = l60;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        C3493l60 l60;
        BottomSheet bottomSheet = this.I;
        int i9 = bottomSheet.P;
        int i10 = bottomSheet.Q;
        int i11 = i3 - i;
        bottomSheet.P = i11;
        int i12 = i4 - i2;
        bottomSheet.Q = i12;
        bottomSheet.k0 = bottomSheet.L + i12;
        if (!(i9 == i11 && i10 == i12)) {
            if (bottomSheet.U == 2 && !bottomSheet.p()) {
                this.I.v(3, false, 0);
            }
            this.I.S = -1.0f;
        }
        int i13 = this.I.Q;
        this.G.getDecorView().getWindowVisibleDisplayFrame(this.I.I);
        int min = this.I.g0 ? this.I.Q - Math.min(this.G.getDecorView().getHeight(), this.I.I.height()) : 0;
        if (min != this.F) {
            TouchRestrictingFrameLayout touchRestrictingFrameLayout = this.I.b0;
            touchRestrictingFrameLayout.setPadding(touchRestrictingFrameLayout.getPaddingLeft(), this.I.b0.getPaddingTop(), this.I.b0.getPaddingRight(), min);
        }
        BottomSheet bottomSheet2 = this.I;
        if (!(i10 == bottomSheet2.Q && this.F == min)) {
            if (!bottomSheet2.N.d || (l60 = this.H) == null) {
                bottomSheet2.a();
                BottomSheet bottomSheet3 = this.I;
                bottomSheet3.v(bottomSheet3.U, false, 0);
            } else {
                l60.d(bottomSheet2);
            }
        }
        this.F = min;
    }
}
