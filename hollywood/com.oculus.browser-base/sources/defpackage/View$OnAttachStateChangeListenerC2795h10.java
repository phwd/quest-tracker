package defpackage;

import android.view.View;
import java.util.Objects;

/* renamed from: h10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnAttachStateChangeListenerC2795h10 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ C3649m10 F;

    public View$OnAttachStateChangeListenerC2795h10(C3649m10 m10) {
        this.F = m10;
    }

    public void onViewAttachedToWindow(View view) {
        B10 b10 = this.F.R;
        if (b10 != null) {
            Objects.requireNonNull(b10);
        }
    }

    public void onViewDetachedFromWindow(View view) {
        B10 b10 = this.F.R;
        if (b10 != null) {
            b10.b();
        }
    }
}
