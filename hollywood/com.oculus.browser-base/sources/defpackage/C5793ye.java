package defpackage;

import android.content.ComponentCallbacks;
import android.os.Handler;
import android.widget.PopupWindow;
import org.chromium.base.ContextUtils;

/* renamed from: ye  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5793ye implements PopupWindow.OnDismissListener {
    public final Runnable F;
    public final ComponentCallbacks G;

    public C5793ye(Runnable runnable, ComponentCallbacks componentCallbacks) {
        this.F = runnable;
        this.G = componentCallbacks;
    }

    public void onDismiss() {
        Runnable runnable = this.F;
        ComponentCallbacks componentCallbacks = this.G;
        new Handler().postDelayed(runnable, 200);
        ContextUtils.getApplicationContext().unregisterComponentCallbacks(componentCallbacks);
    }
}
