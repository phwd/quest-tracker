package defpackage;

/* renamed from: Gs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0412Gs0 implements AbstractC2748gm {

    /* renamed from: a  reason: collision with root package name */
    public final CS f8117a;
    public final /* synthetic */ C0473Hs0 b;

    public C0412Gs0(C0473Hs0 hs0, CS cs) {
        this.b = hs0;
        this.f8117a = cs;
    }

    @Override // defpackage.AbstractC2748gm
    public void cancel() {
        this.b.b.remove(this.f8117a);
        this.f8117a.b.remove(this);
    }
}
