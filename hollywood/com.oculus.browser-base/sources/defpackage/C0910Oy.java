package defpackage;

/* renamed from: Oy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0910Oy extends AbstractC2248dq0 {
    public final long[] l;

    public C0910Oy(HT ht, long[] jArr, C4369qD0 qd0) {
        super(ht, qd0);
        this.l = jArr;
    }

    @Override // defpackage.AbstractC2248dq0
    public void t() {
        q("ContentRemovedTask.removeContent");
        C4198pD0 m = m();
        if (m != null) {
            AbstractC3172jD0.c().i(m.f11057a, this.k.c().b, this.l);
        }
    }
}
