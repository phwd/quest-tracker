package defpackage;

/* renamed from: xX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5605xX extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final FX f11612a;

    public C5605xX(FX fx) {
        this.f11612a = fx;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0887Om0 om0 = this.f11612a.S;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        C0682Ld1 ld1 = om0.d;
        if (booleanValue) {
            ld1.f8430a.j();
        } else {
            ld1.b.post(ld1.d);
        }
    }
}
