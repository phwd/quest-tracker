package defpackage;

import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Oz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0912Oz extends AbstractC0855Oa1 {
    public final /* synthetic */ ContextualSearchManager c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0912Oz(ContextualSearchManager contextualSearchManager, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.c = contextualSearchManager;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        if ((!this.c.m0 && tab.getId() != i2) || ((AbstractC0246Ea1) this.c.G.P()).r()) {
            this.c.i(0);
            this.c.P.f();
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        if (tab.l() != this.c.g()) {
            this.c.i(0);
        }
    }
}
