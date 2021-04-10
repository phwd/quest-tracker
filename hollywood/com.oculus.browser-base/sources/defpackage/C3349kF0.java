package defpackage;

import android.text.TextUtils;
import androidx.preference.Preference;

/* renamed from: kF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3349kF0 {

    /* renamed from: a  reason: collision with root package name */
    public int f10267a;
    public int b;
    public String c;

    public C3349kF0(Preference preference) {
        this.c = preference.getClass().getName();
        this.f10267a = preference.k0;
        this.b = preference.l0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C3349kF0)) {
            return false;
        }
        C3349kF0 kf0 = (C3349kF0) obj;
        if (this.f10267a == kf0.f10267a && this.b == kf0.b && TextUtils.equals(this.c, kf0.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.c.hashCode() + ((((527 + this.f10267a) * 31) + this.b) * 31);
    }
}
