package defpackage;

import android.text.TextUtils;

/* renamed from: fd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2550fd {

    /* renamed from: a  reason: collision with root package name */
    public final String f9934a;
    public final boolean b;

    public C2550fd(String str, boolean z) {
        this.f9934a = str;
        this.b = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2550fd)) {
            return false;
        }
        C2550fd fdVar = (C2550fd) obj;
        return this.b == fdVar.b && TextUtils.equals(this.f9934a, fdVar.f9934a);
    }

    public int hashCode() {
        String str = this.f9934a;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        if (this.b) {
            i = 1023;
        }
        return hashCode ^ i;
    }
}
