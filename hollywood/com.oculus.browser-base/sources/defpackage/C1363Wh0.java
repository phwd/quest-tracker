package defpackage;

import com.google.android.gms.cast.CastDevice;

/* renamed from: Wh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1363Wh0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f9167a;
    public final String b;

    public C1363Wh0(String str, String str2, CastDevice castDevice) {
        this.f9167a = str;
        this.b = str2;
    }

    public static C1363Wh0 a(C2392eh0 eh0) {
        return new C1363Wh0(eh0.c, eh0.d, CastDevice.x(eh0.r));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1363Wh0)) {
            return false;
        }
        C1363Wh0 wh0 = (C1363Wh0) obj;
        return this.f9167a.equals(wh0.f9167a) && this.b.equals(wh0.b);
    }

    public int hashCode() {
        String str = this.f9167a;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return String.format("MediaSink: %s, %s", this.f9167a, this.b);
    }
}
