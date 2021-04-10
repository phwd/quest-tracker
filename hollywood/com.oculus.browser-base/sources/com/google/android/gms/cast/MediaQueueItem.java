package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaQueueItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ND1();
    public MediaInfo F;
    public int G;
    public boolean H;
    public double I;

    /* renamed from: J  reason: collision with root package name */
    public double f9644J;
    public double K;
    public long[] L;
    public String M;
    public JSONObject N;

    public MediaQueueItem(MediaInfo mediaInfo, int i, boolean z, double d, double d2, double d3, long[] jArr, String str) {
        this.I = Double.NaN;
        this.F = mediaInfo;
        this.G = i;
        this.H = z;
        this.I = d;
        this.f9644J = d2;
        this.K = d3;
        this.L = jArr;
        this.M = str;
        if (str != null) {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueItem)) {
            return false;
        }
        MediaQueueItem mediaQueueItem = (MediaQueueItem) obj;
        JSONObject jSONObject = this.N;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaQueueItem.N;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || O40.a(jSONObject, jSONObject2)) && GF1.a(this.F, mediaQueueItem.F) && this.G == mediaQueueItem.G && this.H == mediaQueueItem.H && ((Double.isNaN(this.I) && Double.isNaN(mediaQueueItem.I)) || this.I == mediaQueueItem.I) && this.f9644J == mediaQueueItem.f9644J && this.K == mediaQueueItem.K && Arrays.equals(this.L, mediaQueueItem.L);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, Integer.valueOf(this.G), Boolean.valueOf(this.H), Double.valueOf(this.I), Double.valueOf(this.f9644J), Double.valueOf(this.K), Integer.valueOf(Arrays.hashCode(this.L)), String.valueOf(this.N)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.N;
        this.M = jSONObject == null ? null : jSONObject.toString();
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 2, this.F, i, false);
        int i2 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i2);
        boolean z = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(z ? 1 : 0);
        double d = this.I;
        AbstractC5758yO0.o(parcel, 5, 8);
        parcel.writeDouble(d);
        double d2 = this.f9644J;
        AbstractC5758yO0.o(parcel, 6, 8);
        parcel.writeDouble(d2);
        double d3 = this.K;
        AbstractC5758yO0.o(parcel, 7, 8);
        parcel.writeDouble(d3);
        long[] jArr = this.L;
        if (jArr != null) {
            int l2 = AbstractC5758yO0.l(parcel, 8);
            parcel.writeLongArray(jArr);
            AbstractC5758yO0.n(parcel, l2);
        }
        AbstractC5758yO0.g(parcel, 9, this.M, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public final boolean x(JSONObject jSONObject) {
        boolean z;
        boolean z2;
        int i;
        boolean z3 = false;
        if (jSONObject.has("media")) {
            this.F = new MediaInfo(jSONObject.getJSONObject("media"));
            z = true;
        } else {
            z = false;
        }
        if (jSONObject.has("itemId") && this.G != (i = jSONObject.getInt("itemId"))) {
            this.G = i;
            z = true;
        }
        if (jSONObject.has("autoplay") && this.H != (z2 = jSONObject.getBoolean("autoplay"))) {
            this.H = z2;
            z = true;
        }
        double optDouble = jSONObject.optDouble("startTime");
        if (Double.isNaN(optDouble) != Double.isNaN(this.I) || (!Double.isNaN(optDouble) && Math.abs(optDouble - this.I) > 1.0E-7d)) {
            this.I = optDouble;
            z = true;
        }
        if (jSONObject.has("playbackDuration")) {
            double d = jSONObject.getDouble("playbackDuration");
            if (Math.abs(d - this.f9644J) > 1.0E-7d) {
                this.f9644J = d;
                z = true;
            }
        }
        if (jSONObject.has("preloadTime")) {
            double d2 = jSONObject.getDouble("preloadTime");
            if (Math.abs(d2 - this.K) > 1.0E-7d) {
                this.K = d2;
                z = true;
            }
        }
        long[] jArr = null;
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            long[] jArr2 = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr2[i2] = jSONArray.getLong(i2);
            }
            long[] jArr3 = this.L;
            if (jArr3 != null && jArr3.length == length) {
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    } else if (this.L[i3] != jArr2[i3]) {
                        break;
                    } else {
                        i3++;
                    }
                }
            }
            z3 = true;
            jArr = jArr2;
        }
        if (z3) {
            this.L = jArr;
            z = true;
        }
        if (!jSONObject.has("customData")) {
            return z;
        }
        this.N = jSONObject.getJSONObject("customData");
        return true;
    }

    public MediaQueueItem(JSONObject jSONObject) {
        this(null, 0, true, Double.NaN, Double.POSITIVE_INFINITY, 0.0d, null, null);
        x(jSONObject);
    }
}
