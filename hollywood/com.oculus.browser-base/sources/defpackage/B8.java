package defpackage;

import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatSpinner;

/* renamed from: B8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B8 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ AppCompatSpinner F;

    public B8(AppCompatSpinner appCompatSpinner) {
        this.F = appCompatSpinner;
    }

    public void onGlobalLayout() {
        if (!this.F.L.b()) {
            this.F.b();
        }
        ViewTreeObserver viewTreeObserver = this.F.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
    }
}
