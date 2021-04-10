package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaStatus extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new TD1();
    public MediaInfo F;
    public long G;
    public int H;
    public double I;

    /* renamed from: J  reason: collision with root package name */
    public int f9645J;
    public int K;
    public long L;
    public long M;
    public double N;
    public boolean O;
    public long[] P;
    public int Q;
    public int R;
    public String S;
    public JSONObject T;
    public int U;
    public final ArrayList V = new ArrayList();
    public boolean W;
    public AdBreakStatus X;
    public VideoInfo Y;
    public C3921ne0 Z;
    public C0805Ne0 a0;
    public final SparseArray b0 = new SparseArray();

    public MediaStatus(MediaInfo mediaInfo, long j, int i, double d, int i2, int i3, long j2, long j3, double d2, boolean z, long[] jArr, int i4, int i5, String str, int i6, List list, boolean z2, AdBreakStatus adBreakStatus, VideoInfo videoInfo) {
        this.F = mediaInfo;
        this.G = j;
        this.H = i;
        this.I = d;
        this.f9645J = i2;
        this.K = i3;
        this.L = j2;
        this.M = j3;
        this.N = d2;
        this.O = z;
        this.P = jArr;
        this.Q = i4;
        this.R = i5;
        this.S = str;
        if (str != null) {
            try {
                this.T = new JSONObject(this.S);
            } catch (JSONException unused) {
                this.T = null;
                this.S = null;
            }
        } else {
            this.T = null;
        }
        this.U = i6;
        if (list != null && !list.isEmpty()) {
            C((MediaQueueItem[]) list.toArray(new MediaQueueItem[list.size()]));
        }
        this.W = z2;
        this.X = adBreakStatus;
        this.Y = videoInfo;
    }

    public static boolean E(int i, int i2, int i3, int i4) {
        if (i != 1) {
            return false;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                return i4 != 2;
            }
            if (i2 != 3) {
                return true;
            }
        }
        return i3 == 0;
    }

    public boolean A(long j) {
        return (j & this.M) != 0;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0354, code lost:
        if (r2 == false) goto L_0x0374;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01ac  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0217  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0233  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0265  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0357  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x03bb A[Catch:{ JSONException -> 0x03d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x03c6 A[Catch:{ JSONException -> 0x03d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x03f7  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x03f9  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x040a  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x0453 A[Catch:{ JSONException -> 0x0483 }] */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x0470 A[Catch:{ JSONException -> 0x0483 }] */
    /* JADX WARNING: Removed duplicated region for block: B:282:0x04da  */
    /* JADX WARNING: Removed duplicated region for block: B:367:0x06a2  */
    /* JADX WARNING: Removed duplicated region for block: B:407:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x016b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int B(org.json.JSONObject r32, int r33) {
        /*
        // Method dump skipped, instructions count: 1880
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaStatus.B(org.json.JSONObject, int):int");
    }

    public final void C(MediaQueueItem[] mediaQueueItemArr) {
        this.V.clear();
        this.b0.clear();
        for (int i = 0; i < mediaQueueItemArr.length; i++) {
            MediaQueueItem mediaQueueItem = mediaQueueItemArr[i];
            this.V.add(mediaQueueItem);
            this.b0.put(mediaQueueItem.G, Integer.valueOf(i));
        }
    }

    public boolean equals(Object obj) {
        JSONObject jSONObject;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        if ((this.T == null) == (mediaStatus.T == null) && this.G == mediaStatus.G && this.H == mediaStatus.H && this.I == mediaStatus.I && this.f9645J == mediaStatus.f9645J && this.K == mediaStatus.K && this.L == mediaStatus.L && this.N == mediaStatus.N && this.O == mediaStatus.O && this.Q == mediaStatus.Q && this.R == mediaStatus.R && this.U == mediaStatus.U && Arrays.equals(this.P, mediaStatus.P) && GF1.a(Long.valueOf(this.M), Long.valueOf(mediaStatus.M)) && GF1.a(this.V, mediaStatus.V) && GF1.a(this.F, mediaStatus.F)) {
            JSONObject jSONObject2 = this.T;
            return (jSONObject2 == null || (jSONObject = mediaStatus.T) == null || O40.a(jSONObject2, jSONObject)) && this.W == mediaStatus.W && GF1.a(this.X, mediaStatus.X) && GF1.a(this.Y, mediaStatus.Y) && GF1.a(this.Z, mediaStatus.Z) && AbstractC0895Oq0.a(this.a0, mediaStatus.a0);
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, Long.valueOf(this.G), Integer.valueOf(this.H), Double.valueOf(this.I), Integer.valueOf(this.f9645J), Integer.valueOf(this.K), Long.valueOf(this.L), Long.valueOf(this.M), Double.valueOf(this.N), Boolean.valueOf(this.O), Integer.valueOf(Arrays.hashCode(this.P)), Integer.valueOf(this.Q), Integer.valueOf(this.R), String.valueOf(this.T), Integer.valueOf(this.U), this.V, Boolean.valueOf(this.W), this.X, this.Y, this.Z, this.a0});
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.T;
        this.S = jSONObject == null ? null : jSONObject.toString();
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 2, this.F, i, false);
        long j = this.G;
        AbstractC5758yO0.o(parcel, 3, 8);
        parcel.writeLong(j);
        int i2 = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i2);
        double d = this.I;
        AbstractC5758yO0.o(parcel, 5, 8);
        parcel.writeDouble(d);
        int i3 = this.f9645J;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(i3);
        int i4 = this.K;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeInt(i4);
        long j2 = this.L;
        AbstractC5758yO0.o(parcel, 8, 8);
        parcel.writeLong(j2);
        long j3 = this.M;
        AbstractC5758yO0.o(parcel, 9, 8);
        parcel.writeLong(j3);
        double d2 = this.N;
        AbstractC5758yO0.o(parcel, 10, 8);
        parcel.writeDouble(d2);
        boolean z = this.O;
        AbstractC5758yO0.o(parcel, 11, 4);
        parcel.writeInt(z ? 1 : 0);
        long[] jArr = this.P;
        if (jArr != null) {
            int l2 = AbstractC5758yO0.l(parcel, 12);
            parcel.writeLongArray(jArr);
            AbstractC5758yO0.n(parcel, l2);
        }
        int i5 = this.Q;
        AbstractC5758yO0.o(parcel, 13, 4);
        parcel.writeInt(i5);
        int i6 = this.R;
        AbstractC5758yO0.o(parcel, 14, 4);
        parcel.writeInt(i6);
        AbstractC5758yO0.g(parcel, 15, this.S, false);
        int i7 = this.U;
        AbstractC5758yO0.o(parcel, 16, 4);
        parcel.writeInt(i7);
        AbstractC5758yO0.k(parcel, 17, this.V, false);
        boolean z2 = this.W;
        AbstractC5758yO0.o(parcel, 18, 4);
        parcel.writeInt(z2 ? 1 : 0);
        AbstractC5758yO0.f(parcel, 19, this.X, i, false);
        AbstractC5758yO0.f(parcel, 20, this.Y, i, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public MediaQueueItem x(int i) {
        Integer num = (Integer) this.b0.get(i);
        if (num == null) {
            return null;
        }
        return (MediaQueueItem) this.V.get(num.intValue());
    }
}
