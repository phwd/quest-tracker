package defpackage;

import java.util.Arrays;
import org.json.JSONObject;

/* renamed from: th0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4953th0 {

    /* renamed from: a  reason: collision with root package name */
    public final long f11361a;
    public final int b;
    public final JSONObject c;

    public C4953th0(long j, int i, boolean z, JSONObject jSONObject, QD1 qd1) {
        this.f11361a = j;
        this.b = i;
        this.c = jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C4953th0)) {
            return false;
        }
        C4953th0 th0 = (C4953th0) obj;
        return this.f11361a == th0.f11361a && this.b == th0.b && AbstractC0895Oq0.a(this.c, th0.c);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f11361a), Integer.valueOf(this.b), Boolean.FALSE, this.c});
    }
}
