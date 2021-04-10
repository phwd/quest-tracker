package defpackage;

import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatSpinner;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: F8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F8 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ H8 F;

    public F8(H8 h8) {
        this.F = h8;
    }

    public void onGlobalLayout() {
        H8 h8 = this.F;
        AppCompatSpinner appCompatSpinner = h8.l0;
        Objects.requireNonNull(h8);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (!(appCompatSpinner.isAttachedToWindow() && appCompatSpinner.getGlobalVisibleRect(h8.j0))) {
            this.F.dismiss();
            return;
        }
        this.F.t();
        this.F.d();
    }
}
