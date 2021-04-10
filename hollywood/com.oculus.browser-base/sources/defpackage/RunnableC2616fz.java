package defpackage;

import J.N;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.contextmenu.ContextMenuHelper;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;

/* renamed from: fz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2616fz implements Runnable {
    public final ContextMenuHelper F;

    public RunnableC2616fz(ContextMenuHelper contextMenuHelper) {
        this.F = contextMenuHelper;
    }

    public void run() {
        ContextMenuHelper contextMenuHelper = this.F;
        boolean z = contextMenuHelper.m;
        StringBuilder i = AbstractC2531fV.i("ContextMenu.TimeToTakeAction.");
        i.append(z ? "SelectedItem" : "Abandoned");
        String sb = i.toString();
        long millis = TimeUnit.MICROSECONDS.toMillis(N.MklbOJun()) - contextMenuHelper.l;
        AbstractC3364kK0.k(sb, millis);
        ContextMenuParams contextMenuParams = contextMenuHelper.f;
        if (contextMenuParams.i && N.MO0TyD6h(contextMenuHelper.f10638a, contextMenuParams.c) == 2) {
            AbstractC3364kK0.k(sb + ".PerformanceClassFast", millis);
        }
        contextMenuHelper.g = null;
        ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl = contextMenuHelper.c;
        if (contextMenuNativeDelegateImpl != null) {
            contextMenuNativeDelegateImpl.b = 0;
            contextMenuHelper.c = null;
        }
        AbstractC3128iz izVar = contextMenuHelper.d;
        if (izVar != null) {
            izVar.b();
            contextMenuHelper.d = null;
        }
        W70 w70 = contextMenuHelper.o;
        if (w70 != null) {
            Objects.requireNonNull(w70.b);
            Objects.requireNonNull(w70.b);
        }
        long j = contextMenuHelper.b;
        if (j != 0) {
            N.McrcWTzG(j, contextMenuHelper);
        }
    }
}
