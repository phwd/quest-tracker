package defpackage;

/* renamed from: r50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4515r50 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0070Bd f11182a;
    public final int b;

    public C4515r50(AbstractC0070Bd bd, int i) {
        this.f11182a = bd;
        this.b = i;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC0070Bd bd = this.f11182a;
        int i = this.b;
        C2636g50 g50 = (C2636g50) obj;
        AbstractC3364kK0.g("KeyboardAccessory.AccessoryActionSelected", 2, 8);
        bd.b(i);
    }
}
