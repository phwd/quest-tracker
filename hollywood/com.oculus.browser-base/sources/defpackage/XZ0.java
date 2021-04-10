package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: XZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XZ0 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ YZ0 F;

    public XZ0(YZ0 yz0) {
        this.F = yz0;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.F.U;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.F.U = view.getViewTreeObserver();
            }
            YZ0 yz0 = this.F;
            yz0.U.removeGlobalOnLayoutListener(yz0.O);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
