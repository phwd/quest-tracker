package defpackage;

import com.google.android.gms.cast.MediaInfo;
import java.util.Arrays;
import org.json.JSONObject;

/* renamed from: oe0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4092oe0 {

    /* renamed from: a  reason: collision with root package name */
    public MediaInfo f10565a;
    public Boolean b;
    public long c;
    public double d;
    public long[] e;
    public JSONObject f;
    public String g;
    public String h;

    public C4092oe0(MediaInfo mediaInfo, C0805Ne0 ne0, Boolean bool, long j, double d2, long[] jArr, JSONObject jSONObject, String str, String str2, AbstractC5561xD1 xd1) {
        this.f10565a = mediaInfo;
        this.b = bool;
        this.c = j;
        this.d = d2;
        this.e = jArr;
        this.f = jSONObject;
        this.g = str;
        this.h = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C4092oe0)) {
            return false;
        }
        C4092oe0 oe0 = (C4092oe0) obj;
        return AbstractC0895Oq0.a(this.f10565a, oe0.f10565a) && AbstractC0895Oq0.a(null, null) && AbstractC0895Oq0.a(this.b, oe0.b) && this.c == oe0.c && this.d == oe0.d && Arrays.equals(this.e, oe0.e) && AbstractC0895Oq0.a(this.f, oe0.f) && AbstractC0895Oq0.a(this.g, oe0.g) && AbstractC0895Oq0.a(this.h, oe0.h);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f10565a, null, this.b, Long.valueOf(this.c), Double.valueOf(this.d), this.e, this.f, this.g, this.h});
    }
}
