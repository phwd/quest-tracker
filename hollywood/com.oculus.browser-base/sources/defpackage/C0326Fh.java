package defpackage;

/* renamed from: Fh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0326Fh extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3741mb0 f8032a;

    public C0326Fh(C3741mb0 mb0) {
        this.f8032a = mb0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3741mb0 mb0 = this.f8032a;
        int intValue = ((Integer) obj).intValue();
        if (intValue == 13 || intValue == 14) {
            C3912nb0.a(mb0.f10434a, 1);
        } else {
            C3912nb0.a(mb0.f10434a, 2);
        }
    }
}
