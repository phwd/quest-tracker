package defpackage;

import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: dl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2235dl1 implements Runnable {
    public final ToolbarPhone F;

    public RunnableC2235dl1(ToolbarPhone toolbarPhone) {
        this.F = toolbarPhone;
    }

    public void run() {
        this.F.postInvalidateOnAnimation();
    }
}
