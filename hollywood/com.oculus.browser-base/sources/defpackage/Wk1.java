package defpackage;

import android.view.ViewTreeObserver;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: Wk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Wk1 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final ToolbarPhone F;

    public Wk1(ToolbarPhone toolbarPhone) {
        this.F = toolbarPhone;
    }

    public void onGlobalLayout() {
        ToolbarPhone toolbarPhone = this.F;
        Runnable runnable = toolbarPhone.V0;
        if (runnable != null) {
            runnable.run();
        }
        toolbarPhone.getViewTreeObserver().removeOnGlobalLayoutListener(toolbarPhone.i1);
    }
}
