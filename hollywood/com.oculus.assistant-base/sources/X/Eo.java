package X;

import com.oculus.aidl.OVRServiceInterface;

public final class Eo {
    public static byte A00(byte b) {
        switch (b) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipStatus /*{ENCODED_INT: 17}*/:
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipMicrophoneMuted /*{ENCODED_INT: 18}*/:
            default:
                throw new IllegalArgumentException(AnonymousClass08.A00("type=", b));
            case 6:
                return 6;
            case 8:
            case 16:
                return 8;
            case 10:
                return 10;
            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
            case 20:
                return 11;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                return 12;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                return 13;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
                return 14;
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                return 15;
            case OVRServiceInterface.Stub.TRANSACTION_startPartyChat /*{ENCODED_INT: 19}*/:
                return 19;
        }
    }
}
