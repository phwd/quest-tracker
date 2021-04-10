package defpackage;

import android.view.View;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: e40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnLayoutChangeListenerC2291e40 implements View.OnLayoutChangeListener {
    public final C3316k40 F;
    public final LinearLayout G;

    public View$OnLayoutChangeListenerC2291e40(C3316k40 k40, LinearLayout linearLayout) {
        this.F = k40;
        this.G = linearLayout;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        C3316k40 k40 = this.F;
        LinearLayout linearLayout = this.G;
        Objects.requireNonNull(k40);
        if (i != i5 || i2 != i6 || i3 != i7 || i4 != i8) {
            View findViewById = linearLayout.findViewById(R.id.container);
            int height = k40.f10258a.getWindow().getDecorView().getHeight();
            float f = k40.f10258a.getResources().getDisplayMetrics().density;
            findViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(AbstractC4089od0.b((((float) Math.round((((((float) height) / f) * 0.3f) / 48.0f) - 0.5f)) + 0.5f) * 48.0f, 72.0f, 408.0f) * f)));
        }
    }
}
