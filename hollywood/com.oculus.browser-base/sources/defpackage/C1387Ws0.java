package defpackage;

/* renamed from: Ws0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1387Ws0 implements ZZ {

    /* renamed from: a  reason: collision with root package name */
    public final ZZ f9178a;
    public boolean b;

    public C1387Ws0(ZZ zz) {
        this.f9178a = zz;
    }

    @Override // defpackage.ZZ
    public void a() {
        if (!this.b) {
            this.b = true;
            this.f9178a.a();
        }
    }
}
