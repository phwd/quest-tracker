package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaInfo extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C4199pD1();
    public final String F;
    public int G;
    public String H;
    public MediaMetadata I;

    /* renamed from: J  reason: collision with root package name */
    public long f9642J;
    public List K;
    public TextTrackStyle L;
    public String M;
    public List N;
    public List O;
    public String P;
    public VastAdsRequest Q;
    public long R;
    public JSONObject S;

    public MediaInfo(String str, int i, String str2, MediaMetadata mediaMetadata, long j, List list, TextTrackStyle textTrackStyle, String str3, List list2, List list3, String str4, VastAdsRequest vastAdsRequest, long j2) {
        this.F = str;
        this.G = i;
        this.H = str2;
        this.I = mediaMetadata;
        this.f9642J = j;
        this.K = list;
        this.L = textTrackStyle;
        this.M = str3;
        if (str3 != null) {
            try {
                this.S = new JSONObject(this.M);
            } catch (JSONException unused) {
                this.S = null;
                this.M = null;
            }
        } else {
            this.S = null;
        }
        this.N = list2;
        this.O = list3;
        this.P = str4;
        this.Q = vastAdsRequest;
        this.R = j2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a3 A[LOOP:0: B:3:0x0026->B:25:0x00a3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01ab A[LOOP:2: B:30:0x00ce->B:72:0x01ab, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00b1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b4 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A(org.json.JSONObject r39) {
        /*
        // Method dump skipped, instructions count: 442
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaInfo.A(org.json.JSONObject):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        JSONObject jSONObject = this.S;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaInfo.S;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || O40.a(jSONObject, jSONObject2)) && GF1.a(this.F, mediaInfo.F) && this.G == mediaInfo.G && GF1.a(this.H, mediaInfo.H) && GF1.a(this.I, mediaInfo.I) && this.f9642J == mediaInfo.f9642J && GF1.a(this.K, mediaInfo.K) && GF1.a(this.L, mediaInfo.L) && GF1.a(this.N, mediaInfo.N) && GF1.a(this.O, mediaInfo.O) && GF1.a(this.P, mediaInfo.P) && GF1.a(this.Q, mediaInfo.Q) && this.R == mediaInfo.R;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, Integer.valueOf(this.G), this.H, this.I, Long.valueOf(this.f9642J), String.valueOf(this.S), this.K, this.L, this.N, this.O, this.P, this.Q, Long.valueOf(this.R)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.S;
        List list = null;
        this.M = jSONObject == null ? null : jSONObject.toString();
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        int i2 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 4, this.H, false);
        AbstractC5758yO0.f(parcel, 5, this.I, i, false);
        long j = this.f9642J;
        AbstractC5758yO0.o(parcel, 6, 8);
        parcel.writeLong(j);
        AbstractC5758yO0.k(parcel, 7, this.K, false);
        AbstractC5758yO0.f(parcel, 8, this.L, i, false);
        AbstractC5758yO0.g(parcel, 9, this.M, false);
        List list2 = this.N;
        AbstractC5758yO0.k(parcel, 10, list2 == null ? null : Collections.unmodifiableList(list2), false);
        List list3 = this.O;
        if (list3 != null) {
            list = Collections.unmodifiableList(list3);
        }
        AbstractC5758yO0.k(parcel, 11, list, false);
        AbstractC5758yO0.g(parcel, 12, this.P, false);
        AbstractC5758yO0.f(parcel, 13, this.Q, i, false);
        long j2 = this.R;
        AbstractC5758yO0.o(parcel, 14, 8);
        parcel.writeLong(j2);
        AbstractC5758yO0.n(parcel, l);
    }

    public final JSONObject x() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", this.F);
            int i = this.G;
            jSONObject.put("streamType", i != 1 ? i != 2 ? "NONE" : "LIVE" : "BUFFERED");
            String str = this.H;
            if (str != null) {
                jSONObject.put("contentType", str);
            }
            MediaMetadata mediaMetadata = this.I;
            if (mediaMetadata != null) {
                jSONObject.put("metadata", mediaMetadata.A());
            }
            long j = this.f9642J;
            if (j <= -1) {
                jSONObject.put("duration", JSONObject.NULL);
            } else {
                jSONObject.put("duration", ((double) j) / 1000.0d);
            }
            if (this.K != null) {
                JSONArray jSONArray = new JSONArray();
                for (MediaTrack mediaTrack : this.K) {
                    jSONArray.put(mediaTrack.x());
                }
                jSONObject.put("tracks", jSONArray);
            }
            TextTrackStyle textTrackStyle = this.L;
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.x());
            }
            JSONObject jSONObject2 = this.S;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str2 = this.P;
            if (str2 != null) {
                jSONObject.put("entity", str2);
            }
            if (this.N != null) {
                JSONArray jSONArray2 = new JSONArray();
                for (AdBreakInfo adBreakInfo : this.N) {
                    jSONArray2.put(adBreakInfo.x());
                }
                jSONObject.put("breaks", jSONArray2);
            }
            if (this.O != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (AdBreakClipInfo adBreakClipInfo : this.O) {
                    jSONArray3.put(adBreakClipInfo.x());
                }
                jSONObject.put("breakClips", jSONArray3);
            }
            VastAdsRequest vastAdsRequest = this.Q;
            if (vastAdsRequest != null) {
                jSONObject.put("vmapAdsRequest", vastAdsRequest.A());
            }
            long j2 = this.R;
            if (j2 != -1) {
                jSONObject.put("startAbsoluteTime", ((double) j2) / 1000.0d);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public MediaInfo(JSONObject jSONObject) {
        this(jSONObject.getString("contentId"), -1, null, null, -1, null, null, null, null, null, null, null, -1);
        MediaInfo mediaInfo;
        String string = jSONObject.getString("streamType");
        if ("NONE".equals(string)) {
            mediaInfo = this;
            mediaInfo.G = 0;
        } else {
            mediaInfo = this;
            if ("BUFFERED".equals(string)) {
                mediaInfo.G = 1;
            } else if ("LIVE".equals(string)) {
                mediaInfo.G = 2;
            } else {
                mediaInfo.G = -1;
            }
        }
        mediaInfo.H = jSONObject.optString("contentType", null);
        if (jSONObject.has("metadata")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("metadata");
            MediaMetadata mediaMetadata = new MediaMetadata(jSONObject2.getInt("metadataType"));
            mediaInfo.I = mediaMetadata;
            mediaMetadata.C(jSONObject2);
        }
        mediaInfo.f9642J = -1;
        if (jSONObject.has("duration") && !jSONObject.isNull("duration")) {
            double optDouble = jSONObject.optDouble("duration", 0.0d);
            if (!Double.isNaN(optDouble) && !Double.isInfinite(optDouble)) {
                mediaInfo.f9642J = (long) (optDouble * 1000.0d);
            }
        }
        if (jSONObject.has("tracks")) {
            mediaInfo.K = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tracks");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                MediaTrack mediaTrack = new MediaTrack(0, 0, null, null, null, null, -1, null);
                mediaTrack.F = jSONObject3.getLong("trackId");
                String string2 = jSONObject3.getString("type");
                if ("TEXT".equals(string2)) {
                    mediaTrack.G = 1;
                } else if ("AUDIO".equals(string2)) {
                    mediaTrack.G = 2;
                } else if ("VIDEO".equals(string2)) {
                    mediaTrack.G = 3;
                } else {
                    String valueOf = String.valueOf(string2);
                    throw new JSONException(valueOf.length() != 0 ? "invalid type: ".concat(valueOf) : new String("invalid type: "));
                }
                mediaTrack.H = jSONObject3.optString("trackContentId", null);
                mediaTrack.I = jSONObject3.optString("trackContentType", null);
                mediaTrack.f9646J = jSONObject3.optString("name", null);
                mediaTrack.K = jSONObject3.optString("language", null);
                if (jSONObject3.has("subtype")) {
                    String string3 = jSONObject3.getString("subtype");
                    if ("SUBTITLES".equals(string3)) {
                        mediaTrack.L = 1;
                    } else if ("CAPTIONS".equals(string3)) {
                        mediaTrack.L = 2;
                    } else if ("DESCRIPTIONS".equals(string3)) {
                        mediaTrack.L = 3;
                    } else if ("CHAPTERS".equals(string3)) {
                        mediaTrack.L = 4;
                    } else if ("METADATA".equals(string3)) {
                        mediaTrack.L = 5;
                    } else {
                        mediaTrack.L = -1;
                    }
                } else {
                    mediaTrack.L = 0;
                }
                mediaTrack.N = jSONObject3.optJSONObject("customData");
                mediaInfo.K.add(mediaTrack);
            }
        } else {
            mediaInfo.K = null;
        }
        if (jSONObject.has("textTrackStyle")) {
            JSONObject jSONObject4 = jSONObject.getJSONObject("textTrackStyle");
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            textTrackStyle.F = (float) jSONObject4.optDouble("fontScale", 1.0d);
            textTrackStyle.G = TextTrackStyle.B(jSONObject4.optString("foregroundColor"));
            textTrackStyle.H = TextTrackStyle.B(jSONObject4.optString("backgroundColor"));
            if (jSONObject4.has("edgeType")) {
                String string4 = jSONObject4.getString("edgeType");
                if ("NONE".equals(string4)) {
                    textTrackStyle.I = 0;
                } else if ("OUTLINE".equals(string4)) {
                    textTrackStyle.I = 1;
                } else if ("DROP_SHADOW".equals(string4)) {
                    textTrackStyle.I = 2;
                } else if ("RAISED".equals(string4)) {
                    textTrackStyle.I = 3;
                } else if ("DEPRESSED".equals(string4)) {
                    textTrackStyle.I = 4;
                }
            }
            textTrackStyle.f9647J = TextTrackStyle.B(jSONObject4.optString("edgeColor"));
            if (jSONObject4.has("windowType")) {
                String string5 = jSONObject4.getString("windowType");
                if ("NONE".equals(string5)) {
                    textTrackStyle.K = 0;
                } else if ("NORMAL".equals(string5)) {
                    textTrackStyle.K = 1;
                } else if ("ROUNDED_CORNERS".equals(string5)) {
                    textTrackStyle.K = 2;
                }
            }
            textTrackStyle.L = TextTrackStyle.B(jSONObject4.optString("windowColor"));
            if (textTrackStyle.K == 2) {
                textTrackStyle.M = jSONObject4.optInt("windowRoundedCornerRadius", 0);
            }
            textTrackStyle.N = jSONObject4.optString("fontFamily", null);
            if (jSONObject4.has("fontGenericFamily")) {
                String string6 = jSONObject4.getString("fontGenericFamily");
                if ("SANS_SERIF".equals(string6)) {
                    textTrackStyle.O = 0;
                } else if ("MONOSPACED_SANS_SERIF".equals(string6)) {
                    textTrackStyle.O = 1;
                } else if ("SERIF".equals(string6)) {
                    textTrackStyle.O = 2;
                } else if ("MONOSPACED_SERIF".equals(string6)) {
                    textTrackStyle.O = 3;
                } else if ("CASUAL".equals(string6)) {
                    textTrackStyle.O = 4;
                } else if ("CURSIVE".equals(string6)) {
                    textTrackStyle.O = 5;
                } else if ("SMALL_CAPITALS".equals(string6)) {
                    textTrackStyle.O = 6;
                }
            }
            if (jSONObject4.has("fontStyle")) {
                String string7 = jSONObject4.getString("fontStyle");
                if ("NORMAL".equals(string7)) {
                    textTrackStyle.P = 0;
                } else if ("BOLD".equals(string7)) {
                    textTrackStyle.P = 1;
                } else if ("ITALIC".equals(string7)) {
                    textTrackStyle.P = 2;
                } else if ("BOLD_ITALIC".equals(string7)) {
                    textTrackStyle.P = 3;
                }
            }
            textTrackStyle.R = jSONObject4.optJSONObject("customData");
            mediaInfo.L = textTrackStyle;
        } else {
            mediaInfo.L = null;
        }
        A(jSONObject);
        mediaInfo.S = jSONObject.optJSONObject("customData");
        if (jSONObject.has("entity")) {
            mediaInfo.P = jSONObject.getString("entity");
        }
        mediaInfo.Q = VastAdsRequest.x(jSONObject.optJSONObject("vmapAdsRequest"));
        if (KF1.b && jSONObject.has("startAbsoluteTime") && !jSONObject.isNull("startAbsoluteTime")) {
            double optDouble2 = jSONObject.optDouble("startAbsoluteTime");
            if (!(Double.isNaN(optDouble2) || Double.isInfinite(optDouble2) || optDouble2 < 0.0d)) {
                mediaInfo.R = (long) (optDouble2 * 1000.0d);
            }
        }
    }
}
