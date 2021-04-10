package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: nj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3935nj extends AnimatorListenerAdapter {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ BottomSheet H;

    public C3935nj(BottomSheet bottomSheet, int i, int i2) {
        this.H = bottomSheet;
        this.F = i;
        this.G = i2;
    }

    public void onAnimationEnd(Animator animator) {
        BottomSheet bottomSheet = this.H;
        if (!bottomSheet.h0) {
            bottomSheet.O = null;
            StringBuilder i = AbstractC2531fV.i("Ending settle animation: target: ");
            i.append(this.F);
            i.append(", content null: ");
            i.append(this.H.a0 == null);
            AbstractC1220Ua0.d("BottomSheet", i.toString(), new Object[0]);
            this.H.s(this.F);
            this.H.V = -1;
        }
    }
}
