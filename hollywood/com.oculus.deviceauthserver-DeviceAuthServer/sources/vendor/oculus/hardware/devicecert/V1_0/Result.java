package vendor.oculus.hardware.devicecert.V1_0;

import java.util.ArrayList;

public final class Result {
    public static final int FILESYSTEM_ERROR = 1;
    public static final int INVALID_CALL = 2;
    public static final int INVALID_PARAMETER = 3;
    public static final int INVALID_STATE = 4;
    public static final int OVRTZ_COMMAND_FAILED = 5;
    public static final int QSEE_COMMAND_FAILED = 6;
    public static final int SUCCESS = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "SUCCESS";
        }
        if (o == 1) {
            return "FILESYSTEM_ERROR";
        }
        if (o == 2) {
            return "INVALID_CALL";
        }
        if (o == 3) {
            return "INVALID_PARAMETER";
        }
        if (o == 4) {
            return "INVALID_STATE";
        }
        if (o == 5) {
            return "OVRTZ_COMMAND_FAILED";
        }
        if (o == 6) {
            return "QSEE_COMMAND_FAILED";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("SUCCESS");
        if ((o & 1) == 1) {
            list.add("FILESYSTEM_ERROR");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("INVALID_CALL");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("INVALID_PARAMETER");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("INVALID_STATE");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("OVRTZ_COMMAND_FAILED");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("QSEE_COMMAND_FAILED");
            flipped |= 6;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
