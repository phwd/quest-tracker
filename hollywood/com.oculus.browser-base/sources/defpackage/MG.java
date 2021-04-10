package defpackage;

/* renamed from: MG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class MG extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0804Ne f8468a;

    public MG(AbstractC0804Ne ne) {
        this.f8468a = ne;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f8468a.a(((Boolean) obj).booleanValue());
    }
}
