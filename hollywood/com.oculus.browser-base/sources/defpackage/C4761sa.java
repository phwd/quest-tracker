package defpackage;

/* renamed from: sa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4761sa extends AbstractC1590a10 {
    public final /* synthetic */ C4931ta I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4761sa(C4931ta taVar) {
        super(taVar.L);
        this.I = taVar;
    }

    @Override // defpackage.AbstractC1590a10
    public Object a(int i) {
        return this.I.K[(i << 1) + 1];
    }

    @Override // defpackage.AbstractC1590a10
    public void b(int i) {
        this.I.i(i);
    }
}
