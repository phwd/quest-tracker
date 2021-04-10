package defpackage;

import org.chromium.base.Callback;

/* renamed from: dc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2208dc1 extends C0310Fb1 {
    public final /* synthetic */ Callback b;
    public final /* synthetic */ C1529Zb1 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2208dc1(C2891hc1 hc1, AbstractC0249Eb1 eb1, Callback callback, C1529Zb1 zb1) {
        super(eb1);
        this.b = callback;
        this.c = zb1;
    }

    @Override // defpackage.C0310Fb1
    public void a() {
        super.a();
        this.b.onResult(new C1686ac1(this.c, 1, null, 0));
    }
}
