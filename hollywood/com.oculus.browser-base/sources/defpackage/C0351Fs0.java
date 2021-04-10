package defpackage;

/* renamed from: Fs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0351Fs0 implements AbstractC3841n80, AbstractC2748gm {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3499l80 f8044a;
    public final CS b;
    public AbstractC2748gm c;
    public final /* synthetic */ C0473Hs0 d;

    public C0351Fs0(C0473Hs0 hs0, AbstractC3499l80 l80, CS cs) {
        this.d = hs0;
        this.f8044a = l80;
        this.b = cs;
        l80.a(this);
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        if (j80 == EnumC3157j80.ON_START) {
            C0473Hs0 hs0 = this.d;
            CS cs = this.b;
            hs0.b.add(cs);
            C0412Gs0 gs0 = new C0412Gs0(hs0, cs);
            cs.b.add(gs0);
            this.c = gs0;
        } else if (j80 == EnumC3157j80.ON_STOP) {
            AbstractC2748gm gmVar = this.c;
            if (gmVar != null) {
                gmVar.cancel();
            }
        } else if (j80 == EnumC3157j80.ON_DESTROY) {
            cancel();
        }
    }

    @Override // defpackage.AbstractC2748gm
    public void cancel() {
        this.f8044a.b(this);
        this.b.b.remove(this);
        AbstractC2748gm gmVar = this.c;
        if (gmVar != null) {
            gmVar.cancel();
            this.c = null;
        }
    }
}
