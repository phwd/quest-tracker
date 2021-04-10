package defpackage;

/* renamed from: Wl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1372Wl extends AbstractC4669s {
    public final /* synthetic */ C1433Xl L;

    public C1372Wl(C1433Xl xl) {
        this.L = xl;
    }

    @Override // defpackage.AbstractC4669s
    public String g() {
        C1250Ul ul = (C1250Ul) this.L.F.get();
        if (ul == null) {
            return "Completer object has been garbage collected, future will fail soon";
        }
        StringBuilder i = AbstractC2531fV.i("tag=[");
        i.append(ul.f9047a);
        i.append("]");
        return i.toString();
    }
}
