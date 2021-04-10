package a.a;

/* compiled from: chromium-webapk7.dex */
public final class b extends i {
    public final /* synthetic */ g d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(g gVar) {
        super(gVar.g);
        this.d = gVar;
    }

    @Override // a.a.i
    public Object a(int i) {
        return this.d.f[i << 1];
    }

    @Override // a.a.i
    public void b(int i) {
        this.d.j(i);
    }
}
