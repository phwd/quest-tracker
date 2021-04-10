package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.oculus.os.Version;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ConnectionResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C4025oC1();
    public static final ConnectionResult F = new ConnectionResult(0);
    public final int G;
    public final int H;
    public final PendingIntent I;

    /* renamed from: J  reason: collision with root package name */
    public final String f9654J;

    public ConnectionResult(int i) {
        this.G = 1;
        this.H = i;
        this.I = null;
        this.f9654J = null;
    }

    public static String B(int i) {
        if (i == 99) {
            return "UNFINISHED";
        }
        if (i == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                return "NETWORK_ERROR";
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                return "INTERNAL_ERROR";
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                return "SERVICE_INVALID";
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                return "DEVELOPER_ERROR";
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i) {
                    case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                        return "CANCELED";
                    case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                        return "TIMEOUT";
                    case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                        return "INTERRUPTED";
                    case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                        return "API_UNAVAILABLE";
                    case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                        return "SIGN_IN_FAILED";
                    case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                        return "SERVICE_UPDATING";
                    case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                        return "SERVICE_MISSING_PERMISSION";
                    case Version.VERSION_20 /*{ENCODED_INT: 20}*/:
                        return "RESTRICTED_PROFILE";
                    case Version.VERSION_21 /*{ENCODED_INT: 21}*/:
                        return "API_VERSION_UPDATE_REQUIRED";
                    default:
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("UNKNOWN_ERROR_CODE(");
                        sb.append(i);
                        sb.append(")");
                        return sb.toString();
                }
        }
    }

    public final boolean A() {
        return this.H == 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        return this.H == connectionResult.H && AbstractC0895Oq0.a(this.I, connectionResult.I) && AbstractC0895Oq0.a(this.f9654J, connectionResult.f9654J);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.H), this.I, this.f9654J});
    }

    public final String toString() {
        C0834Nq0 nq0 = new C0834Nq0(this, null);
        nq0.a("statusCode", B(this.H));
        nq0.a("resolution", this.I);
        nq0.a("message", this.f9654J);
        return nq0.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.G;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.H;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.f(parcel, 3, this.I, i, false);
        AbstractC5758yO0.g(parcel, 4, this.f9654J, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public final boolean x() {
        return (this.H == 0 || this.I == null) ? false : true;
    }

    public ConnectionResult(int i, int i2, PendingIntent pendingIntent, String str) {
        this.G = i;
        this.H = i2;
        this.I = pendingIntent;
        this.f9654J = str;
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this.G = 1;
        this.H = i;
        this.I = pendingIntent;
        this.f9654J = null;
    }
}
