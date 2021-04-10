package vendor.oculus.hardware.devicecert.V1_0;

public final class Result {
    public static final String toString(int i) {
        if (i == 0) {
            return "SUCCESS";
        }
        if (i == 1) {
            return "FILESYSTEM_ERROR";
        }
        if (i == 2) {
            return "INVALID_CALL";
        }
        if (i == 3) {
            return "INVALID_PARAMETER";
        }
        if (i == 4) {
            return "INVALID_STATE";
        }
        if (i == 5) {
            return "OVRTZ_COMMAND_FAILED";
        }
        if (i == 6) {
            return "QSEE_COMMAND_FAILED";
        }
        return "0x" + Integer.toHexString(i);
    }
}
