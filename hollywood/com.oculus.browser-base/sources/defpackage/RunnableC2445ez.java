package defpackage;

import J.N;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.contextmenu.ContextMenuHelper;

/* renamed from: ez  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2445ez implements Runnable {
    public final ContextMenuHelper F;

    public RunnableC2445ez(ContextMenuHelper contextMenuHelper) {
        this.F = contextMenuHelper;
    }

    public void run() {
        ContextMenuHelper contextMenuHelper = this.F;
        boolean z = false;
        contextMenuHelper.m = false;
        contextMenuHelper.l = TimeUnit.MICROSECONDS.toMillis(N.MklbOJun());
        AbstractC3100ip1.f10165a.a("ContextMenu.Shown", contextMenuHelper.f10638a != null);
        if (AbstractC1611a80.e(contextMenuHelper.f.b)) {
            if (contextMenuHelper.f10638a != null) {
                z = true;
            }
            AbstractC3100ip1.f10165a.a("ContextMenu.Shown.ShoppingDomain", z);
        }
    }
}
