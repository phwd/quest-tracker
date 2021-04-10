package defpackage;

import java.util.ArrayList;

/* renamed from: jn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3265jn1 extends AbstractC3094in1 {
    public final /* synthetic */ C4931ta F;
    public final /* synthetic */ ViewTreeObserver$OnPreDrawListenerC3436kn1 G;

    public C3265jn1(ViewTreeObserver$OnPreDrawListenerC3436kn1 kn1, C4931ta taVar) {
        this.G = kn1;
        this.F = taVar;
    }

    @Override // defpackage.AbstractC2753gn1
    public void c(AbstractC2924hn1 hn1) {
        ((ArrayList) this.F.get(this.G.G)).remove(hn1);
        hn1.w(this);
    }
}
