package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;
import java.util.Iterator;

/* renamed from: W10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class W10 extends View {
    public static final /* synthetic */ int F = 0;
    public final Rect G = new Rect();
    public final C1322Vq0 H = new C1322Vq0();

    public W10(Context context) {
        super(context);
        setVisibility(4);
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        int systemWindowInsetLeft = windowInsets.getSystemWindowInsetLeft();
        int systemWindowInsetTop = windowInsets.getSystemWindowInsetTop();
        int systemWindowInsetRight = windowInsets.getSystemWindowInsetRight();
        int systemWindowInsetBottom = windowInsets.getSystemWindowInsetBottom();
        Rect rect = this.G;
        if (rect.left != systemWindowInsetLeft || rect.top != systemWindowInsetTop || rect.right != systemWindowInsetRight || rect.bottom != systemWindowInsetBottom) {
            rect.set(systemWindowInsetLeft, systemWindowInsetTop, systemWindowInsetRight, systemWindowInsetBottom);
            Iterator it = this.H.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((V10) uq0.next()).g(systemWindowInsetLeft, systemWindowInsetTop, systemWindowInsetRight, systemWindowInsetBottom);
            }
        }
        return windowInsets;
    }
}
