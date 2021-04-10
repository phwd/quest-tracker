package defpackage;

import org.chromium.base.Callback;

/* renamed from: g50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2636g50 {

    /* renamed from: a  reason: collision with root package name */
    public final String f9975a;
    public final Callback b;
    public int c;

    public C2636g50(String str, int i, Callback callback) {
        this.f9975a = str;
        this.b = callback;
        this.c = i;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("AccessoryAction(");
        i.append(this.c);
        i.append(")");
        String sb = i.toString();
        int i2 = this.c;
        if (i2 == 0) {
            sb = "GENERATE_PASSWORD_AUTOMATIC";
        } else if (i2 == 1) {
            sb = "MANAGE_PASSWORDS";
        } else if (i2 == 2) {
            sb = "AUTOFILL_SUGGESTION";
        }
        StringBuilder i3 = AbstractC2531fV.i("'");
        i3.append(this.f9975a);
        i3.append("' of type ");
        i3.append(sb);
        return i3.toString();
    }
}
