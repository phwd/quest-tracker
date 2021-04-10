package defpackage;

/* renamed from: kG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3353kG1 extends IF1 {
    public final /* synthetic */ String q;
    public final /* synthetic */ String r;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3353kG1(YV yv, String str, String str2) {
        super(yv);
        this.q = str;
        this.r = str2;
    }

    @Override // defpackage.AbstractC4439qg
    public final void j(Z6 z6) {
        try {
            ((C3350kF1) z6).E(this.q, this.r, this);
        } catch (IllegalArgumentException | IllegalStateException unused) {
            m();
        }
    }
}
