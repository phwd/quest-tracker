package org.chromium.chrome.browser.contextmenu;

import android.content.Context;
import android.view.View;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContextMenuHelper {

    /* renamed from: a  reason: collision with root package name */
    public final WebContents f10638a;
    public long b;
    public ContextMenuNativeDelegateImpl c;
    public AbstractC3128iz d;
    public ContextMenuPopulatorFactory e;
    public ContextMenuParams f;
    public AbstractC3299jz g;
    public WindowAndroid h;
    public Callback i;
    public Runnable j;
    public Runnable k;
    public long l;
    public boolean m;
    public boolean n;
    public W70 o;

    public ContextMenuHelper(long j2, WebContents webContents) {
        this.b = j2;
        this.f10638a = webContents;
    }

    public static ContextMenuHelper create(long j2, WebContents webContents) {
        return new ContextMenuHelper(j2, webContents);
    }

    public final void destroy() {
        AbstractC3299jz jzVar = this.g;
        if (jzVar != null) {
            ((SM0) jzVar).b();
            this.g = null;
        }
        ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl = this.c;
        if (contextMenuNativeDelegateImpl != null) {
            contextMenuNativeDelegateImpl.b = 0;
        }
        ContextMenuPopulatorFactory contextMenuPopulatorFactory = this.e;
        if (contextMenuPopulatorFactory != null) {
            contextMenuPopulatorFactory.a();
        }
        this.b = 0;
    }

    public final void setPopulatorFactory(ContextMenuPopulatorFactory contextMenuPopulatorFactory) {
        AbstractC3299jz jzVar = this.g;
        if (jzVar != null) {
            ((SM0) jzVar).b();
            this.g = null;
        }
        ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl = this.c;
        if (contextMenuNativeDelegateImpl != null) {
            contextMenuNativeDelegateImpl.b = 0;
        }
        this.d = null;
        ContextMenuPopulatorFactory contextMenuPopulatorFactory2 = this.e;
        if (contextMenuPopulatorFactory2 != null) {
            contextMenuPopulatorFactory2.a();
        }
        this.e = contextMenuPopulatorFactory;
    }

    public final void showContextMenu(ContextMenuParams contextMenuParams, RenderFrameHost renderFrameHost, View view, float f2) {
        if (!contextMenuParams.b()) {
            WindowAndroid I = this.f10638a.I();
            if (view != null && view.getVisibility() == 0 && view.getParent() != null && I != null && I.s0().get() != null && this.e != null && this.g == null) {
                this.c = new ContextMenuNativeDelegateImpl(this.f10638a, renderFrameHost, contextMenuParams);
                AbstractC3128iz b2 = this.e.b((Context) I.s0().get(), contextMenuParams, this.c);
                this.d = b2;
                this.n = b2.a();
                this.d.f();
                this.f = contextMenuParams;
                this.h = I;
                this.i = new C2274dz(this);
                this.j = new RunnableC2445ez(this);
                this.k = new RunnableC2616fz(this);
                List c2 = this.d.c();
                if (c2.isEmpty()) {
                    PostTask.b(Zo1.f9374a, this.k, 0);
                    return;
                }
                SM0 sm0 = new SM0(f2, this.c);
                this.g = sm0;
                this.o = this.d.e();
                if (!this.n) {
                    Objects.requireNonNull(X70.a());
                }
                W70 w70 = this.o;
                if (w70 != null) {
                    sm0.c(this.h, this.f10638a, this.f, c2, this.i, this.j, this.k, w70);
                } else {
                    sm0.c(this.h, this.f10638a, this.f, c2, this.i, this.j, this.k, null);
                }
            }
        }
    }
}
