package defpackage;

import android.text.TextUtils;

/* renamed from: My  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0788My {

    /* renamed from: a  reason: collision with root package name */
    public String f8514a;
    public String b;

    public C0788My() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0788My)) {
            return false;
        }
        C0788My my = (C0788My) obj;
        return TextUtils.equals(this.f8514a, my.f8514a) && TextUtils.equals(this.b, my.b);
    }

    public int hashCode() {
        String str = this.f8514a;
        int i = 0;
        int hashCode = (1891 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return this.f8514a + "_" + this.b;
    }

    public C0788My(String str, String str2) {
        this.f8514a = str == null ? "" : str;
        this.b = str2 == null ? "" : str2;
    }
}
