package defpackage;

/* renamed from: JS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JS {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8288a;
    public final C0317Fe b;
    public int c;

    public JS(C0317Fe fe, boolean z) {
        this.f8288a = z;
        this.b = fe;
    }

    public void a() {
        boolean z = this.c > 0;
        for (AbstractComponentCallbacksC3550lS lSVar : this.b.q.c.g()) {
            lSVar.Z0(null);
        }
        C0317Fe fe = this.b;
        fe.q.h(fe, this.f8288a, !z, true);
    }
}
