package com.google.android.gms.cast;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TextTrackStyle extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new LE1();
    public float F;
    public int G;
    public int H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f9647J;
    public int K;
    public int L;
    public int M;
    public String N;
    public int O;
    public int P;
    public String Q;
    public JSONObject R;

    public TextTrackStyle(float f, int i, int i2, int i3, int i4, int i5, int i6, int i7, String str, int i8, int i9, String str2) {
        this.F = f;
        this.G = i;
        this.H = i2;
        this.I = i3;
        this.f9647J = i4;
        this.K = i5;
        this.L = i6;
        this.M = i7;
        this.N = str;
        this.O = i8;
        this.P = i9;
        this.Q = str2;
        if (str2 != null) {
            try {
                this.R = new JSONObject(this.Q);
            } catch (JSONException unused) {
                this.R = null;
                this.Q = null;
            }
        } else {
            this.R = null;
        }
    }

    public static String A(int i) {
        return String.format("#%02X%02X%02X%02X", Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.alpha(i)));
    }

    public static int B(String str) {
        if (str != null && str.length() == 9 && str.charAt(0) == '#') {
            try {
                return Color.argb(Integer.parseInt(str.substring(7, 9), 16), Integer.parseInt(str.substring(1, 3), 16), Integer.parseInt(str.substring(3, 5), 16), Integer.parseInt(str.substring(5, 7), 16));
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextTrackStyle)) {
            return false;
        }
        TextTrackStyle textTrackStyle = (TextTrackStyle) obj;
        JSONObject jSONObject = this.R;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = textTrackStyle.R;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || O40.a(jSONObject, jSONObject2)) && this.F == textTrackStyle.F && this.G == textTrackStyle.G && this.H == textTrackStyle.H && this.I == textTrackStyle.I && this.f9647J == textTrackStyle.f9647J && this.K == textTrackStyle.K && this.M == textTrackStyle.M && GF1.a(this.N, textTrackStyle.N) && this.O == textTrackStyle.O && this.P == textTrackStyle.P;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.F), Integer.valueOf(this.G), Integer.valueOf(this.H), Integer.valueOf(this.I), Integer.valueOf(this.f9647J), Integer.valueOf(this.K), Integer.valueOf(this.L), Integer.valueOf(this.M), this.N, Integer.valueOf(this.O), Integer.valueOf(this.P), String.valueOf(this.R)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.R;
        this.Q = jSONObject == null ? null : jSONObject.toString();
        int l = AbstractC5758yO0.l(parcel, 20293);
        float f = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeFloat(f);
        int i2 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i2);
        int i3 = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i3);
        int i4 = this.I;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeInt(i4);
        int i5 = this.f9647J;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(i5);
        int i6 = this.K;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeInt(i6);
        int i7 = this.L;
        AbstractC5758yO0.o(parcel, 8, 4);
        parcel.writeInt(i7);
        int i8 = this.M;
        AbstractC5758yO0.o(parcel, 9, 4);
        parcel.writeInt(i8);
        AbstractC5758yO0.g(parcel, 10, this.N, false);
        int i9 = this.O;
        AbstractC5758yO0.o(parcel, 11, 4);
        parcel.writeInt(i9);
        int i10 = this.P;
        AbstractC5758yO0.o(parcel, 12, 4);
        parcel.writeInt(i10);
        AbstractC5758yO0.g(parcel, 13, this.Q, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public final JSONObject x() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fontScale", (double) this.F);
            int i = this.G;
            if (i != 0) {
                jSONObject.put("foregroundColor", A(i));
            }
            int i2 = this.H;
            if (i2 != 0) {
                jSONObject.put("backgroundColor", A(i2));
            }
            int i3 = this.I;
            if (i3 == 0) {
                jSONObject.put("edgeType", "NONE");
            } else if (i3 == 1) {
                jSONObject.put("edgeType", "OUTLINE");
            } else if (i3 == 2) {
                jSONObject.put("edgeType", "DROP_SHADOW");
            } else if (i3 == 3) {
                jSONObject.put("edgeType", "RAISED");
            } else if (i3 == 4) {
                jSONObject.put("edgeType", "DEPRESSED");
            }
            int i4 = this.f9647J;
            if (i4 != 0) {
                jSONObject.put("edgeColor", A(i4));
            }
            int i5 = this.K;
            if (i5 == 0) {
                jSONObject.put("windowType", "NONE");
            } else if (i5 == 1) {
                jSONObject.put("windowType", "NORMAL");
            } else if (i5 == 2) {
                jSONObject.put("windowType", "ROUNDED_CORNERS");
            }
            int i6 = this.L;
            if (i6 != 0) {
                jSONObject.put("windowColor", A(i6));
            }
            if (this.K == 2) {
                jSONObject.put("windowRoundedCornerRadius", this.M);
            }
            String str = this.N;
            if (str != null) {
                jSONObject.put("fontFamily", str);
            }
            switch (this.O) {
                case 0:
                    jSONObject.put("fontGenericFamily", "SANS_SERIF");
                    break;
                case 1:
                    jSONObject.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");
                    break;
                case 2:
                    jSONObject.put("fontGenericFamily", "SERIF");
                    break;
                case 3:
                    jSONObject.put("fontGenericFamily", "MONOSPACED_SERIF");
                    break;
                case 4:
                    jSONObject.put("fontGenericFamily", "CASUAL");
                    break;
                case 5:
                    jSONObject.put("fontGenericFamily", "CURSIVE");
                    break;
                case 6:
                    jSONObject.put("fontGenericFamily", "SMALL_CAPITALS");
                    break;
            }
            int i7 = this.P;
            if (i7 == 0) {
                jSONObject.put("fontStyle", "NORMAL");
            } else if (i7 == 1) {
                jSONObject.put("fontStyle", "BOLD");
            } else if (i7 == 2) {
                jSONObject.put("fontStyle", "ITALIC");
            } else if (i7 == 3) {
                jSONObject.put("fontStyle", "BOLD_ITALIC");
            }
            JSONObject jSONObject2 = this.R;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public TextTrackStyle() {
        this(1.0f, 0, 0, -1, 0, -1, 0, 0, null, -1, -1, null);
    }
}
