package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: LB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LB extends C4349q61 {
    public final /* synthetic */ boolean g;
    public final /* synthetic */ OB h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LB(OB ob, Tab tab, AbstractC0124Ca1 ca1, Q31 q31, Runnable runnable, Q31 q312, boolean z) {
        super(tab, ca1, q31, runnable, null);
        this.h = ob;
        this.g = z;
    }

    @Override // defpackage.AbstractC2787gz, defpackage.C4349q61
    public boolean a() {
        return this.h.j && !this.g;
    }
}
