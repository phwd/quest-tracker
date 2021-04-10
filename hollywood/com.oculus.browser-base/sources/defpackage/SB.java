package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.ColorDrawable;
import java.util.Objects;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;

/* renamed from: SB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SB extends AnimatorListenerAdapter {
    public final /* synthetic */ ColorDrawable F;
    public final /* synthetic */ CustomTabToolbar G;

    public SB(CustomTabToolbar customTabToolbar, ColorDrawable colorDrawable) {
        this.G = customTabToolbar;
        this.F = colorDrawable;
    }

    public void onAnimationEnd(Animator animator) {
        CustomTabToolbar customTabToolbar = this.G;
        customTabToolbar.m0 = false;
        int color = this.F.getColor();
        Objects.requireNonNull(customTabToolbar);
        boolean z = !AbstractC1270Uv.g(color);
        if (customTabToolbar.i0 != z) {
            customTabToolbar.i0 = z;
            WB wb = customTabToolbar.q0;
            int i = WB.F;
            wb.u();
        }
    }
}
