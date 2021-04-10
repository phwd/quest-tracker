package defpackage;

import java.util.Locale;

/* renamed from: Bs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0107Bs0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f7765a;
    public final int b;
    public final int c;
    public final int d;

    public C0107Bs0(int i, int i2, int i3, int i4) {
        this.f7765a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public String a(String str) {
        if (!b()) {
            return "";
        }
        int i = this.f7765a;
        return str.subSequence(i, this.b + i).toString().toLowerCase(Locale.US);
    }

    public boolean b() {
        return this.b > 0;
    }
}
