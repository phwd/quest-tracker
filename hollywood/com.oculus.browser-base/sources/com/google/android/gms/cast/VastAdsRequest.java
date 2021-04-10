package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VastAdsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ME1();
    public final String F;
    public final String G;

    public VastAdsRequest(String str, String str2) {
        this.F = str;
        this.G = str2;
    }

    public static VastAdsRequest x(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new VastAdsRequest(jSONObject.optString("adTagUrl", null), jSONObject.optString("adsResponse", null));
    }

    public final JSONObject A() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.F;
            if (str != null) {
                jSONObject.put("adTagUrl", str);
            }
            String str2 = this.G;
            if (str2 != null) {
                jSONObject.put("adsResponse", str2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VastAdsRequest)) {
            return false;
        }
        VastAdsRequest vastAdsRequest = (VastAdsRequest) obj;
        return GF1.a(this.F, vastAdsRequest.F) && GF1.a(this.G, vastAdsRequest.G);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
