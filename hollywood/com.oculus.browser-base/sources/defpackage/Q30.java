package defpackage;

/* renamed from: Q30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Q30 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final U30 f8736a;

    public Q30(U30 u30) {
        this.f8736a = u30;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        U30 u30 = this.f8736a;
        Boolean bool = (Boolean) obj;
        if (u30.d.wouldTriggerHelpUI("IPH_TabGroupsDragAndDrop")) {
            u30.b(new T30(u30, new R30(u30), new S30(u30)));
        }
    }
}
