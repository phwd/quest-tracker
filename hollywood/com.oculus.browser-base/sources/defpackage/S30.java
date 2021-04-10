package defpackage;

/* renamed from: S30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class S30 implements AbstractC0211Dj0 {

    /* renamed from: a  reason: collision with root package name */
    public final U30 f8872a;

    public S30(U30 u30) {
        this.f8872a = u30;
    }

    @Override // defpackage.AbstractC0211Dj0
    public void a(int i) {
        U30 u30 = this.f8872a;
        u30.d.shouldTriggerHelpUI("IPH_TabGroupsDragAndDrop");
        u30.d.dismissed("IPH_TabGroupsDragAndDrop");
    }
}
