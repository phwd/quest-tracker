package defpackage;

/* renamed from: CW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CW {

    /* renamed from: a  reason: collision with root package name */
    public final HW f7816a;
    public final JW b;
    public boolean c = true;

    public CW(HW hw, JW jw) {
        this.f7816a = hw;
        this.b = jw;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f7816a);
        sb.append(" ");
        sb.append(!this.c ? "+>" : "->");
        sb.append(" ");
        sb.append(this.b);
        return sb.toString();
    }
}
