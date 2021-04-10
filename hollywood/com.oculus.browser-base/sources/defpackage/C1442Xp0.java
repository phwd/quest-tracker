package defpackage;

/* renamed from: Xp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1442Xp0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f9236a;
    public final String b;
    public final int c;

    public C1442Xp0(int i, int i2, String str) {
        this.f9236a = i2;
        this.b = str;
        this.c = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1442Xp0)) {
            return false;
        }
        C1442Xp0 xp0 = (C1442Xp0) obj;
        if (this.f9236a == xp0.f9236a && this.b.equals(xp0.b) && this.c == xp0.c) {
            return true;
        }
        return false;
    }
}
