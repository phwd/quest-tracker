package defpackage;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* renamed from: G8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G8 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener F;
    public final /* synthetic */ H8 G;

    public G8(H8 h8, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        this.G = h8;
        this.F = onGlobalLayoutListener;
    }

    public void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.G.l0.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.F);
        }
    }
}
