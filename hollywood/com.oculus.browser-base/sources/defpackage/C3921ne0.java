package defpackage;

import java.util.Arrays;

/* renamed from: ne0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3921ne0 {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f10506a = new NF1("MediaLiveSeekableRange");
    public final long b;
    public final long c;
    public final boolean d;
    public final boolean e;

    public C3921ne0(long j, long j2, boolean z, boolean z2) {
        this.b = Math.max(j, 0L);
        this.c = Math.max(j2, 0L);
        this.d = z;
        this.e = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3921ne0)) {
            return false;
        }
        C3921ne0 ne0 = (C3921ne0) obj;
        return this.b == ne0.b && this.c == ne0.c && this.d == ne0.d && this.e == ne0.e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.b), Long.valueOf(this.c), Boolean.valueOf(this.d), Boolean.valueOf(this.e)});
    }
}
