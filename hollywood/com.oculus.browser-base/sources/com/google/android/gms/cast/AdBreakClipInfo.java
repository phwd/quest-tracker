package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AdBreakClipInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C3683mC1();
    public final String F;
    public final String G;
    public final long H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final String f9637J;
    public final String K;
    public String L;
    public String M;
    public String N;
    public final long O;
    public final String P;
    public final VastAdsRequest Q;
    public JSONObject R;

    public AdBreakClipInfo(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, long j2, String str9, VastAdsRequest vastAdsRequest) {
        this.F = str;
        this.G = str2;
        this.H = j;
        this.I = str3;
        this.f9637J = str4;
        this.K = str5;
        this.L = str6;
        this.M = str7;
        this.N = str8;
        this.O = j2;
        this.P = str9;
        this.Q = vastAdsRequest;
        if (!TextUtils.isEmpty(str6)) {
            try {
                this.R = new JSONObject(str6);
            } catch (JSONException e) {
                Log.w("AdBreakClipInfo", String.format(Locale.ROOT, "Error creating AdBreakClipInfo: %s", e.getMessage()));
                this.L = null;
                this.R = new JSONObject();
            }
        } else {
            this.R = new JSONObject();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakClipInfo)) {
            return false;
        }
        AdBreakClipInfo adBreakClipInfo = (AdBreakClipInfo) obj;
        return GF1.a(this.F, adBreakClipInfo.F) && GF1.a(this.G, adBreakClipInfo.G) && this.H == adBreakClipInfo.H && GF1.a(this.I, adBreakClipInfo.I) && GF1.a(this.f9637J, adBreakClipInfo.f9637J) && GF1.a(this.K, adBreakClipInfo.K) && GF1.a(this.L, adBreakClipInfo.L) && GF1.a(this.M, adBreakClipInfo.M) && GF1.a(this.N, adBreakClipInfo.N) && this.O == adBreakClipInfo.O && GF1.a(this.P, adBreakClipInfo.P) && GF1.a(this.Q, adBreakClipInfo.Q);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, Long.valueOf(this.H), this.I, this.f9637J, this.K, this.L, this.M, this.N, Long.valueOf(this.O), this.P, this.Q});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        long j = this.H;
        AbstractC5758yO0.o(parcel, 4, 8);
        parcel.writeLong(j);
        AbstractC5758yO0.g(parcel, 5, this.I, false);
        AbstractC5758yO0.g(parcel, 6, this.f9637J, false);
        AbstractC5758yO0.g(parcel, 7, this.K, false);
        AbstractC5758yO0.g(parcel, 8, this.L, false);
        AbstractC5758yO0.g(parcel, 9, this.M, false);
        AbstractC5758yO0.g(parcel, 10, this.N, false);
        long j2 = this.O;
        AbstractC5758yO0.o(parcel, 11, 8);
        parcel.writeLong(j2);
        AbstractC5758yO0.g(parcel, 12, this.P, false);
        AbstractC5758yO0.f(parcel, 13, this.Q, i, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public final JSONObject x() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.F);
            jSONObject.put("duration", ((double) this.H) / 1000.0d);
            long j = this.O;
            if (j != -1) {
                jSONObject.put("whenSkippable", ((double) j) / 1000.0d);
            }
            String str = this.M;
            if (str != null) {
                jSONObject.put("contentId", str);
            }
            String str2 = this.f9637J;
            if (str2 != null) {
                jSONObject.put("contentType", str2);
            }
            String str3 = this.G;
            if (str3 != null) {
                jSONObject.put("title", str3);
            }
            String str4 = this.I;
            if (str4 != null) {
                jSONObject.put("contentUrl", str4);
            }
            String str5 = this.K;
            if (str5 != null) {
                jSONObject.put("clickThroughUrl", str5);
            }
            JSONObject jSONObject2 = this.R;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str6 = this.N;
            if (str6 != null) {
                jSONObject.put("posterUrl", str6);
            }
            String str7 = this.P;
            if (str7 != null) {
                jSONObject.put("hlsSegmentFormat", str7);
            }
            VastAdsRequest vastAdsRequest = this.Q;
            if (vastAdsRequest != null) {
                jSONObject.put("vastAdsRequest", vastAdsRequest.A());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
