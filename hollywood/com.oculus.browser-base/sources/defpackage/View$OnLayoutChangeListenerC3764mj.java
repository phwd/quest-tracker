package defpackage;

import android.view.View;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: mj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC3764mj implements View.OnLayoutChangeListener {
    public final /* synthetic */ BottomSheet F;

    public View$OnLayoutChangeListenerC3764mj(BottomSheet bottomSheet) {
        this.F = bottomSheet;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i4 - i2 != i8 - i6 || i3 - i != i7 - i5) {
            BottomSheet bottomSheet = this.F;
            if (bottomSheet.N.d || !bottomSheet.r()) {
                BottomSheet bottomSheet2 = this.F;
                bottomSheet2.v(bottomSheet2.U, false, 0);
            }
        }
    }
}
