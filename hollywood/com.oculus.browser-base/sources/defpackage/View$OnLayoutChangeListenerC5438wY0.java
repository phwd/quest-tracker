package defpackage;

import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* renamed from: wY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC5438wY0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ C5948zY0 F;

    public View$OnLayoutChangeListenerC5438wY0(C5948zY0 zy0) {
        this.F = zy0;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        C5948zY0 zy0 = this.F;
        zy0.j.getWindowVisibleDisplayFrame(zy0.m);
        if (!zy0.m.equals(zy0.n)) {
            zy0.n.set(zy0.m);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) zy0.b.getLayoutParams();
            int i9 = layoutParams.bottomMargin;
            int i10 = layoutParams.width;
            int i11 = layoutParams.gravity;
            layoutParams.bottomMargin = zy0.c();
            if (zy0.h) {
                layoutParams.width = Math.min(zy0.j.getResources().getDimensionPixelSize(R.dimen.f25140_resource_name_obfuscated_RES_2131166133), zy0.j.getWidth() - (zy0.j.getResources().getDimensionPixelSize(R.dimen.f25110_resource_name_obfuscated_RES_2131166130) * 2));
                layoutParams.gravity = 81;
            }
            if (i9 != layoutParams.bottomMargin || i10 != layoutParams.width || i11 != layoutParams.gravity) {
                zy0.b.setLayoutParams(layoutParams);
            }
        }
    }
}
