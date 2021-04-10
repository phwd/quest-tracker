package com.google.android.gms.common.api;

import X.AbstractC0312Qj;
import X.C0327Re;
import X.RH;
import X.RX;
import X.RY;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.acra.util.ProcFileReader;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.oculus.aidl.OVRServiceInterface;
import java.util.Arrays;

public final class Status extends AbstractSafeParcelable implements AbstractC0312Qj, ReflectedParcelable {
    public static final Status A05 = new Status(16, null);
    public static final Status A06 = new Status(18, null);
    public static final Status A07 = new Status(8, null);
    public static final Status A08 = new Status(14, null);
    public static final Status A09 = new Status(0, null);
    public static final Status A0A = new Status(15, null);
    public static final Status A0B = new Status(17, null);
    public static final Parcelable.Creator CREATOR = new RH();
    public final int A00;
    public final PendingIntent A01;
    public final ConnectionResult A02;
    public final String A03;
    public final int A04;

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.A04), Integer.valueOf(this.A00), this.A03, this.A01, this.A02});
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Status) {
            Status status = (Status) obj;
            if (this.A04 != status.A04 || this.A00 != status.A00 || !RY.A00(this.A03, status.A03) || !RY.A00(this.A01, status.A01) || !RY.A00(this.A02, status.A02)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final String toString() {
        RX rx = new RX(this);
        String str = this.A03;
        if (str == null) {
            int i = this.A00;
            switch (i) {
                case ProcFileReader.CANNOT_DETERMINE_OPEN_FDS:
                    str = "SUCCESS_CACHE";
                    break;
                case 0:
                    str = "SUCCESS";
                    break;
                case 1:
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                default:
                    StringBuilder sb = new StringBuilder(32);
                    sb.append("unknown status code: ");
                    sb.append(i);
                    str = sb.toString();
                    break;
                case 2:
                    str = "SERVICE_VERSION_UPDATE_REQUIRED";
                    break;
                case 3:
                    str = "SERVICE_DISABLED";
                    break;
                case 4:
                    str = "SIGN_IN_REQUIRED";
                    break;
                case 5:
                    str = "INVALID_ACCOUNT";
                    break;
                case 6:
                    str = "RESOLUTION_REQUIRED";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                    str = "NETWORK_ERROR";
                    break;
                case 8:
                    str = "INTERNAL_ERROR";
                    break;
                case 10:
                    str = "DEVELOPER_ERROR";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                    str = "ERROR";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
                    str = "INTERRUPTED";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                    str = "TIMEOUT";
                    break;
                case 16:
                    str = "CANCELED";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipStatus /*{ENCODED_INT: 17}*/:
                    str = "API_NOT_CONNECTED";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipMicrophoneMuted /*{ENCODED_INT: 18}*/:
                    str = "DEAD_CLIENT";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_startPartyChat /*{ENCODED_INT: 19}*/:
                    str = "REMOTE_EXCEPTION";
                    break;
                case 20:
                    str = "CONNECTION_SUSPENDED_DURING_CALL";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_partyChatSetVolume /*{ENCODED_INT: 21}*/:
                    str = "RECONNECTION_TIMED_OUT_DURING_UPDATE";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_partyChatGetVolume /*{ENCODED_INT: 22}*/:
                    str = "RECONNECTION_TIMED_OUT";
                    break;
            }
        }
        rx.A00("statusCode", str);
        rx.A00("resolution", this.A01);
        return rx.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A00);
        C0327Re.A05(parcel, 2, this.A03);
        C0327Re.A04(parcel, 3, this.A01, i);
        C0327Re.A04(parcel, 4, this.A02, i);
        C0327Re.A02(parcel, 1000, this.A04);
        C0327Re.A01(parcel, A002);
    }

    public Status(int i, int i2, String str, PendingIntent pendingIntent, ConnectionResult connectionResult) {
        this.A04 = i;
        this.A00 = i2;
        this.A03 = str;
        this.A01 = pendingIntent;
        this.A02 = connectionResult;
    }

    public Status(int i, String str) {
        this(1, i, str, null, null);
    }
}
