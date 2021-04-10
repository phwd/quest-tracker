package defpackage;

import com.oculus.os.Version;

/* renamed from: bw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1924bw {
    public static String a(int i) {
        switch (i) {
            case -1:
                return "SUCCESS_CACHE";
            case 0:
                return "SUCCESS";
            case 1:
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
            case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
            default:
                return AbstractC2531fV.s(32, "unknown status code: ", i);
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
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                return "DEVELOPER_ERROR";
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                return "ERROR";
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                return "INTERRUPTED";
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                return "TIMEOUT";
            case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                return "CANCELED";
            case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                return "API_NOT_CONNECTED";
            case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                return "DEAD_CLIENT";
        }
    }
}
