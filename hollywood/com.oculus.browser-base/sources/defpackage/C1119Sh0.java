package defpackage;

import android.text.TextUtils;
import java.util.Objects;

/* renamed from: Sh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1119Sh0 {

    /* renamed from: a  reason: collision with root package name */
    public String f8909a;
    public int b;
    public int c;

    public C1119Sh0(String str, int i, int i2) {
        this.f8909a = str;
        this.b = i;
        this.c = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1119Sh0)) {
            return false;
        }
        C1119Sh0 sh0 = (C1119Sh0) obj;
        return (this.b < 0 || sh0.b < 0) ? TextUtils.equals(this.f8909a, sh0.f8909a) && this.c == sh0.c : TextUtils.equals(this.f8909a, sh0.f8909a) && this.b == sh0.b && this.c == sh0.c;
    }

    public int hashCode() {
        return Objects.hash(this.f8909a, Integer.valueOf(this.c));
    }
}
