package com.google.android.gms.common;

import X.C0327Re;
import X.RX;
import X.RY;
import X.S9;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.acra.CrashTimeDataCollector;
import com.facebook.acra.util.ProcFileReader;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.oculus.aidl.OVRServiceInterface;
import java.util.Arrays;

public final class ConnectionResult extends AbstractSafeParcelable {
    public static final ConnectionResult A04 = new ConnectionResult(0);
    public static final Parcelable.Creator CREATOR = new S9();
    public static final int SUCCESS = 0;
    public final int A00;
    public final PendingIntent A01;
    public final String A02;
    public final int A03;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof ConnectionResult) {
                ConnectionResult connectionResult = (ConnectionResult) obj;
                if (this.A00 != connectionResult.A00 || !RY.A00(this.A01, connectionResult.A01) || !RY.A00(this.A02, connectionResult.A02)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.A00), this.A01, this.A02});
    }

    public static String A00(int i) {
        if (i == 99) {
            return "UNFINISHED";
        }
        if (i == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i) {
            case ProcFileReader.CANNOT_DETERMINE_OPEN_FDS:
                return CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
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
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i) {
                    case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                        return "CANCELED";
                    case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
                        return "TIMEOUT";
                    case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                        return "INTERRUPTED";
                    case 16:
                        return "API_UNAVAILABLE";
                    case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipStatus /*{ENCODED_INT: 17}*/:
                        return "SIGN_IN_FAILED";
                    case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipMicrophoneMuted /*{ENCODED_INT: 18}*/:
                        return "SERVICE_UPDATING";
                    case OVRServiceInterface.Stub.TRANSACTION_startPartyChat /*{ENCODED_INT: 19}*/:
                        return "SERVICE_MISSING_PERMISSION";
                    case 20:
                        return "RESTRICTED_PROFILE";
                    case OVRServiceInterface.Stub.TRANSACTION_partyChatSetVolume /*{ENCODED_INT: 21}*/:
                        return "API_VERSION_UPDATE_REQUIRED";
                    case OVRServiceInterface.Stub.TRANSACTION_partyChatGetVolume /*{ENCODED_INT: 22}*/:
                        return "RESOLUTION_ACTIVITY_NOT_FOUND";
                    case OVRServiceInterface.Stub.TRANSACTION_getSharedMicrophoneData /*{ENCODED_INT: 23}*/:
                        return "API_DISABLED";
                    default:
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("UNKNOWN_ERROR_CODE(");
                        sb.append(i);
                        sb.append(")");
                        return sb.toString();
                }
        }
    }

    public final String toString() {
        RX rx = new RX(this);
        rx.A00("statusCode", A00(this.A00));
        rx.A00("resolution", this.A01);
        rx.A00("message", this.A02);
        return rx.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A03);
        C0327Re.A02(parcel, 2, this.A00);
        C0327Re.A04(parcel, 3, this.A01, i);
        C0327Re.A05(parcel, 4, this.A02);
        C0327Re.A01(parcel, A002);
    }

    public ConnectionResult(int i) {
        this(1, i, null, null);
    }

    public ConnectionResult(int i, int i2, PendingIntent pendingIntent, String str) {
        this.A03 = i;
        this.A00 = i2;
        this.A01 = pendingIntent;
        this.A02 = str;
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this(1, i, pendingIntent, null);
    }
}
