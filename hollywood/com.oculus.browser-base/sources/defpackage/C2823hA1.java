package defpackage;

import android.app.PendingIntent;
import java.util.Objects;

/* renamed from: hA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2823hA1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f10050a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final PendingIntent f;
    public final PendingIntent g;

    public C2823hA1(String str, int i, int i2, int i3, int i4, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        Objects.requireNonNull(str, "Null packageName");
        this.f10050a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = pendingIntent;
        this.g = pendingIntent2;
    }

    public static C2823hA1 a(String str, int i, int i2, int i3, int i4, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        return new C2823hA1(str, i, i2, i3, i4, pendingIntent, pendingIntent2);
    }

    public boolean b(int i) {
        if (i == 0) {
            if (this.g == null) {
                return false;
            }
        } else if (i != 1 || this.f == null) {
            return false;
        }
        return true;
    }

    public final boolean equals(Object obj) {
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2823hA1) {
            C2823hA1 ha1 = (C2823hA1) obj;
            return this.f10050a.equals(ha1.f10050a) && this.b == ha1.b && this.c == ha1.c && this.d == ha1.d && this.e == ha1.e && ((pendingIntent = this.f) != null ? pendingIntent.equals(ha1.f) : ha1.f == null) && ((pendingIntent2 = this.g) != null ? pendingIntent2.equals(ha1.g) : ha1.g == null);
        }
    }

    public final int hashCode() {
        int hashCode = (((((((((this.f10050a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c) * 1000003) ^ this.d) * 1000003) ^ this.e) * 1000003;
        PendingIntent pendingIntent = this.f;
        int i = 0;
        int hashCode2 = (hashCode ^ (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 1000003;
        PendingIntent pendingIntent2 = this.g;
        if (pendingIntent2 != null) {
            i = pendingIntent2.hashCode();
        }
        return hashCode2 ^ i;
    }

    public final String toString() {
        String str = this.f10050a;
        int i = this.b;
        int i2 = this.c;
        int i3 = this.d;
        int i4 = this.e;
        String valueOf = String.valueOf(this.f);
        String valueOf2 = String.valueOf(this.g);
        StringBuilder sb = new StringBuilder(valueOf2.length() + valueOf.length() + String.valueOf(str).length() + 207);
        sb.append("AppUpdateInfo{packageName=");
        sb.append(str);
        sb.append(", availableVersionCode=");
        sb.append(i);
        sb.append(", updateAvailability=");
        sb.append(i2);
        sb.append(", installStatus=");
        sb.append(i3);
        sb.append(", clientVersionStalenessDays=");
        sb.append(i4);
        sb.append(", immediateUpdateIntent=");
        sb.append(valueOf);
        sb.append(", flexibleUpdateIntent=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
