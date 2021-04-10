package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CastDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C1642aI1();
    public String F;
    public String G;
    public InetAddress H;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public String f9641J;
    public String K;
    public int L;
    public List M;
    public int N;
    public int O;
    public String P;
    public String Q;
    public int R;
    public String S;
    public byte[] T;
    public String U;

    public CastDevice(String str, String str2, String str3, String str4, String str5, int i, List list, int i2, int i3, String str6, String str7, int i4, String str8, byte[] bArr, String str9) {
        List list2;
        String str10 = "";
        this.F = str == null ? str10 : str;
        String str11 = str2 == null ? str10 : str2;
        this.G = str11;
        if (!TextUtils.isEmpty(str11)) {
            try {
                this.H = InetAddress.getByName(this.G);
            } catch (UnknownHostException e) {
                String str12 = this.G;
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message).length() + String.valueOf(str12).length() + 48);
                sb.append("Unable to convert host address (");
                sb.append(str12);
                sb.append(") to ipaddress: ");
                sb.append(message);
                Log.i("CastDevice", sb.toString());
            }
        }
        this.I = str3 == null ? str10 : str3;
        this.f9641J = str4 == null ? str10 : str4;
        this.K = str5 == null ? str10 : str5;
        this.L = i;
        if (list != null) {
            list2 = list;
        } else {
            list2 = new ArrayList();
        }
        this.M = list2;
        this.N = i2;
        this.O = i3;
        this.P = str6 != null ? str6 : str10;
        this.Q = str7;
        this.R = i4;
        this.S = str8;
        this.T = bArr;
        this.U = str9;
    }

    public static CastDevice x(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice) bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    public boolean A(int i) {
        return (this.N & i) == i;
    }

    public boolean equals(Object obj) {
        byte[] bArr;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        String str = this.F;
        return str == null ? castDevice.F == null : GF1.a(str, castDevice.F) && GF1.a(this.H, castDevice.H) && GF1.a(this.f9641J, castDevice.f9641J) && GF1.a(this.I, castDevice.I) && GF1.a(this.K, castDevice.K) && this.L == castDevice.L && GF1.a(this.M, castDevice.M) && this.N == castDevice.N && this.O == castDevice.O && GF1.a(this.P, castDevice.P) && GF1.a(Integer.valueOf(this.R), Integer.valueOf(castDevice.R)) && GF1.a(this.S, castDevice.S) && GF1.a(this.Q, castDevice.Q) && GF1.a(this.K, castDevice.K) && this.L == castDevice.L && (((bArr = this.T) == null && castDevice.T == null) || Arrays.equals(bArr, castDevice.T)) && GF1.a(this.U, castDevice.U);
    }

    public int hashCode() {
        String str = this.F;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        return String.format("\"%s\" (%s)", this.I, this.F);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.g(parcel, 4, this.I, false);
        AbstractC5758yO0.g(parcel, 5, this.f9641J, false);
        AbstractC5758yO0.g(parcel, 6, this.K, false);
        int i2 = this.L;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.k(parcel, 8, Collections.unmodifiableList(this.M), false);
        int i3 = this.N;
        AbstractC5758yO0.o(parcel, 9, 4);
        parcel.writeInt(i3);
        int i4 = this.O;
        AbstractC5758yO0.o(parcel, 10, 4);
        parcel.writeInt(i4);
        AbstractC5758yO0.g(parcel, 11, this.P, false);
        AbstractC5758yO0.g(parcel, 12, this.Q, false);
        int i5 = this.R;
        AbstractC5758yO0.o(parcel, 13, 4);
        parcel.writeInt(i5);
        AbstractC5758yO0.g(parcel, 14, this.S, false);
        AbstractC5758yO0.b(parcel, 15, this.T, false);
        AbstractC5758yO0.g(parcel, 16, this.U, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
