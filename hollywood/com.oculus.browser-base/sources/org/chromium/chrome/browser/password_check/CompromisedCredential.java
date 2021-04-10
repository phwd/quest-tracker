package org.chromium.chrome.browser.password_check;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CompromisedCredential implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C3122ix();
    public final String F;
    public final GURL G;
    public final String H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final String f10730J;
    public final String K;
    public final String L;
    public final String M;
    public final long N;
    public final boolean O;
    public final boolean P;
    public final boolean Q;
    public final boolean R;

    public CompromisedCredential(String str, GURL gurl, String str2, String str3, String str4, String str5, String str6, String str7, long j, boolean z, boolean z2, boolean z3, boolean z4) {
        this.F = str;
        this.G = gurl;
        this.H = str2;
        this.I = str3;
        this.f10730J = str4;
        this.K = str5;
        this.L = str6;
        this.M = str7;
        this.N = j;
        this.O = z;
        this.P = z2;
        this.Q = z3;
        this.R = z4;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CompromisedCredential compromisedCredential = (CompromisedCredential) obj;
        return this.F.equals(compromisedCredential.F) && this.G.equals(compromisedCredential.G) && this.H.equals(compromisedCredential.H) && this.I.equals(compromisedCredential.I) && this.f10730J.equals(compromisedCredential.f10730J) && this.K.equals(compromisedCredential.K) && this.L.equals(compromisedCredential.L) && this.M.equals(compromisedCredential.M) && this.N == compromisedCredential.N && this.O == compromisedCredential.O && this.P == compromisedCredential.P && this.Q == compromisedCredential.Q && this.R == compromisedCredential.R;
    }

    public GURL getAssociatedUrl() {
        return this.G;
    }

    public String getPassword() {
        return this.K;
    }

    public String getSignonRealm() {
        return this.F;
    }

    public String getUsername() {
        return this.H;
    }

    public int hashCode() {
        return Objects.hash(this.F, this.G.f11029a, this.H, this.I, this.f10730J, this.K, this.L, this.M, Long.valueOf(this.N), Boolean.valueOf(this.O), Boolean.valueOf(this.P), Boolean.valueOf(this.Q), Boolean.valueOf(this.R));
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("CompromisedCredential{signonRealm='");
        i.append(this.F);
        i.append(", associatedUrl='");
        i.append(this.G);
        i.append('\'');
        i.append('\'');
        i.append(", username='");
        i.append(this.H);
        i.append('\'');
        i.append(", displayOrigin='");
        i.append(this.I);
        i.append('\'');
        i.append(", displayUsername='");
        i.append(this.f10730J);
        i.append('\'');
        i.append(", password='");
        i.append(this.K);
        i.append('\'');
        i.append(", passwordChangeUrl='");
        i.append(this.L);
        i.append('\'');
        i.append(", associatedApp='");
        i.append(this.M);
        i.append('\'');
        i.append(", creationTime=");
        i.append(this.N);
        i.append(", leaked=");
        i.append(this.O);
        i.append(", phished=");
        i.append(this.P);
        i.append(", hasStartableScript=");
        i.append(this.Q);
        i.append(", hasAutoChangeButton=");
        i.append(this.R);
        i.append('}');
        return i.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.F);
        parcel.writeString(this.G.l());
        parcel.writeString(this.H);
        parcel.writeString(this.I);
        parcel.writeString(this.f10730J);
        parcel.writeString(this.K);
        parcel.writeString(this.L);
        parcel.writeString(this.M);
        parcel.writeLong(this.N);
        parcel.writeBooleanArray(new boolean[]{this.O, this.P, this.Q, this.R});
    }
}
