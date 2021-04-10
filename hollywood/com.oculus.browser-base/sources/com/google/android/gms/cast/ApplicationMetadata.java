package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ApplicationMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C5227vF1();
    public String F;
    public String G;
    public List H;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public Uri f9640J;
    public String K;

    public ApplicationMetadata(String str, String str2, List list, String str3, Uri uri, String str4) {
        this.F = str;
        this.G = str2;
        this.H = list;
        this.I = str3;
        this.f9640J = uri;
        this.K = str4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return GF1.a(this.F, applicationMetadata.F) && GF1.a(this.G, applicationMetadata.G) && GF1.a(this.H, applicationMetadata.H) && GF1.a(this.I, applicationMetadata.I) && GF1.a(this.f9640J, applicationMetadata.f9640J) && GF1.a(this.K, applicationMetadata.K);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, this.H, this.I, this.f9640J, this.K});
    }

    public String toString() {
        String str = this.F;
        String str2 = this.G;
        List list = this.H;
        int size = list == null ? 0 : list.size();
        String str3 = this.I;
        String valueOf = String.valueOf(this.f9640J);
        String str4 = this.K;
        StringBuilder sb = new StringBuilder(String.valueOf(str4).length() + valueOf.length() + String.valueOf(str3).length() + String.valueOf(str2).length() + String.valueOf(str).length() + 110);
        sb.append("applicationId: ");
        sb.append(str);
        sb.append(", name: ");
        sb.append(str2);
        sb.append(", namespaces.count: ");
        sb.append(size);
        sb.append(", senderAppIdentifier: ");
        sb.append(str3);
        sb.append(", senderAppLaunchUrl: ");
        sb.append(valueOf);
        sb.append(", iconUrl: ");
        sb.append(str4);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.k(parcel, 4, null, false);
        AbstractC5758yO0.i(parcel, 5, x(), false);
        AbstractC5758yO0.g(parcel, 6, this.I, false);
        AbstractC5758yO0.f(parcel, 7, this.f9640J, i, false);
        AbstractC5758yO0.g(parcel, 8, this.K, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public List x() {
        return Collections.unmodifiableList(this.H);
    }
}
