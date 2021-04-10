package defpackage;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: Fg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0325Fg1 {

    /* renamed from: a  reason: collision with root package name */
    public final CharSequence f8031a;
    public final SJ0 b;
    public final SJ0 c;
    public final boolean d;
    public final boolean e;

    public C0325Fg1(CharSequence charSequence, SJ0 sj0, SJ0 sj02, boolean z, boolean z2) {
        sj0.a(0, charSequence.length());
        if (!(sj02.f8889a == -1 && sj02.b == -1)) {
            sj02.a(0, charSequence.length());
        }
        this.f8031a = charSequence;
        this.b = sj0;
        this.c = sj02;
        this.d = z;
        this.e = z2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0325Fg1)) {
            return false;
        }
        C0325Fg1 fg1 = (C0325Fg1) obj;
        if (fg1 == this) {
            return true;
        }
        if (!TextUtils.equals(this.f8031a, fg1.f8031a) || !this.b.equals(fg1.b) || !this.c.equals(fg1.c) || this.d != fg1.d || this.e != fg1.e) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.c.hashCode() * 13) + (this.b.hashCode() * 11) + (this.f8031a.hashCode() * 7) + (this.d ? 19 : 0);
        if (this.e) {
            i = 23;
        }
        return hashCode + i;
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[5];
        objArr[0] = this.f8031a;
        objArr[1] = this.b;
        objArr[2] = this.c;
        objArr[3] = this.d ? "SIN" : "MUL";
        objArr[4] = this.e ? " ReplyToRequest" : "";
        return String.format(locale, "TextInputState {[%s] SEL%s COM%s %s%s}", objArr);
    }
}
