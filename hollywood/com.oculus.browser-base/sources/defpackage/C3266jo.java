package defpackage;

/* renamed from: jo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3266jo implements AbstractC2737gi0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5653xo f10237a;

    public C3266jo(C5653xo xoVar) {
        this.f10237a = xoVar;
    }

    @Override // defpackage.AbstractC2737gi0
    public void a(int i) {
        C5653xo xoVar = this.f10237a;
        xoVar.e.post(new RunnableC3950no(xoVar, i));
    }
}
