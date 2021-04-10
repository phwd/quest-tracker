package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaTrack extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new XD1();
    public long F;
    public int G;
    public String H;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public String f9646J;
    public String K;
    public int L;
    public String M;
    public JSONObject N;

    public MediaTrack(long j, int i, String str, String str2, String str3, String str4, int i2, String str5) {
        this.F = j;
        this.G = i;
        this.H = str;
        this.I = str2;
        this.f9646J = str3;
        this.K = str4;
        this.L = i2;
        this.M = str5;
        if (str5 != null) {
            try {
                this.N = new JSONObject(this.M);
            } catch (JSONException unused) {
                this.N = null;
                this.M = null;
            }
        } else {
            this.N = null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) obj;
        JSONObject jSONObject = this.N;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaTrack.N;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || O40.a(jSONObject, jSONObject2)) && this.F == mediaTrack.F && this.G == mediaTrack.G && GF1.a(this.H, mediaTrack.H) && GF1.a(this.I, mediaTrack.I) && GF1.a(this.f9646J, mediaTrack.f9646J) && GF1.a(this.K, mediaTrack.K) && this.L == mediaTrack.L;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.F), Integer.valueOf(this.G), this.H, this.I, this.f9646J, this.K, Integer.valueOf(this.L), String.valueOf(this.N)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.N;
        this.M = jSONObject == null ? null : jSONObject.toString();
        int l = AbstractC5758yO0.l(parcel, 20293);
        long j = this.F;
        AbstractC5758yO0.o(parcel, 2, 8);
        parcel.writeLong(j);
        int i2 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 4, this.H, false);
        AbstractC5758yO0.g(parcel, 5, this.I, false);
        AbstractC5758yO0.g(parcel, 6, this.f9646J, false);
        AbstractC5758yO0.g(parcel, 7, this.K, false);
        int i3 = this.L;
        AbstractC5758yO0.o(parcel, 8, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.g(parcel, 9, this.M, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public final JSONObject x() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackId", this.F);
            int i = this.G;
            if (i == 1) {
                jSONObject.put("type", "TEXT");
            } else if (i == 2) {
                jSONObject.put("type", "AUDIO");
            } else if (i == 3) {
                jSONObject.put("type", "VIDEO");
            }
            String str = this.H;
            if (str != null) {
                jSONObject.put("trackContentId", str);
            }
            String str2 = this.I;
            if (str2 != null) {
                jSONObject.put("trackContentType", str2);
            }
            String str3 = this.f9646J;
            if (str3 != null) {
                jSONObject.put("name", str3);
            }
            if (!TextUtils.isEmpty(this.K)) {
                jSONObject.put("language", this.K);
            }
            int i2 = this.L;
            if (i2 == 1) {
                jSONObject.put("subtype", "SUBTITLES");
            } else if (i2 == 2) {
                jSONObject.put("subtype", "CAPTIONS");
            } else if (i2 == 3) {
                jSONObject.put("subtype", "DESCRIPTIONS");
            } else if (i2 == 4) {
                jSONObject.put("subtype", "CHAPTERS");
            } else if (i2 == 5) {
                jSONObject.put("subtype", "METADATA");
            }
            JSONObject jSONObject2 = this.N;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
