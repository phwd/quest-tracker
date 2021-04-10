package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;

/* renamed from: xr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5662xr implements ContextMenuPopulatorFactory {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2787gz f11642a;
    public final Q31 b;
    public final int c;
    public final YM d;

    public C5662xr(AbstractC2787gz gzVar, Q31 q31, int i, YM ym) {
        this.f11642a = gzVar;
        this.b = q31;
        this.c = i;
        this.d = ym;
    }

    @Override // org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory
    public void a() {
        C4349q61 q61 = (C4349q61) this.f11642a;
        TabImpl tabImpl = q61.f11116a;
        tabImpl.P.c(q61.c);
    }

    @Override // org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory
    public AbstractC3128iz b(Context context, ContextMenuParams contextMenuParams, ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl) {
        return new C5492wr(this.f11642a, this.b, this.c, this.d, context, contextMenuParams, contextMenuNativeDelegateImpl);
    }
}
