package defpackage;

/* renamed from: G50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G50 {

    /* renamed from: a  reason: collision with root package name */
    public int f8061a;
    public final C2636g50 b;

    public G50(int i, C2636g50 g50) {
        this.f8061a = i;
        this.b = g50;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("BarItem(");
        i.append(this.f8061a);
        i.append(")");
        String sb = i.toString();
        int i2 = this.f8061a;
        if (i2 == 0) {
            sb = "ACTION_BUTTON";
        } else if (i2 == 1) {
            sb = "SUGGESTION";
        } else if (i2 == 2) {
            sb = "TAB_LAYOUT";
        }
        StringBuilder j = AbstractC2531fV.j(sb, ": ");
        j.append(this.b);
        return j.toString();
    }
}
