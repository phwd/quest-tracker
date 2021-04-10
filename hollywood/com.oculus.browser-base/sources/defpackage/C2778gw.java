package defpackage;

/* renamed from: gw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2778gw implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractActivityC3119iw f10034a;

    public C2778gw(AbstractActivityC3119iw iwVar) {
        this.f10034a = iwVar;
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        if (j80 == EnumC3157j80.ON_DESTROY && !this.f10034a.isChangingConfigurations()) {
            this.f10034a.L().a();
        }
    }
}
