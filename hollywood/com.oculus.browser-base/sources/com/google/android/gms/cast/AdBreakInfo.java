package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AdBreakInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C4373qE1();
    public final long F;
    public final String G;
    public final long H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public String[] f9638J;
    public final boolean K;

    public AdBreakInfo(long j, String str, long j2, boolean z, String[] strArr, boolean z2) {
        this.F = j;
        this.G = str;
        this.H = j2;
        this.I = z;
        this.f9638J = strArr;
        this.K = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakInfo)) {
            return false;
        }
        AdBreakInfo adBreakInfo = (AdBreakInfo) obj;
        return GF1.a(this.G, adBreakInfo.G) && this.F == adBreakInfo.F && this.H == adBreakInfo.H && this.I == adBreakInfo.I && Arrays.equals(this.f9638J, adBreakInfo.f9638J) && this.K == adBreakInfo.K;
    }

    public int hashCode() {
        return this.G.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        long j = this.F;
        AbstractC5758yO0.o(parcel, 2, 8);
        parcel.writeLong(j);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        long j2 = this.H;
        AbstractC5758yO0.o(parcel, 4, 8);
        parcel.writeLong(j2);
        boolean z = this.I;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeInt(z ? 1 : 0);
        AbstractC5758yO0.h(parcel, 6, this.f9638J, false);
        boolean z2 = this.K;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeInt(z2 ? 1 : 0);
        AbstractC5758yO0.n(parcel, l);
    }

    public final JSONObject x() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.G);
            jSONObject.put("position", ((double) this.F) / 1000.0d);
            jSONObject.put("isWatched", this.I);
            jSONObject.put("isEmbedded", this.K);
            jSONObject.put("duration", ((double) this.H) / 1000.0d);
            if (this.f9638J != null) {
                JSONArray jSONArray = new JSONArray();
                for (String str : this.f9638J) {
                    jSONArray.put(str);
                }
                jSONObject.put("breakClipIds", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
