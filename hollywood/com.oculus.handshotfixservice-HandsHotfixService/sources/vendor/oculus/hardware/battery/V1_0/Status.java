package vendor.oculus.hardware.battery.V1_0;

import java.util.ArrayList;

public final class Status {
    public static final int CHARGING = 2;
    public static final int NOT_CHARGING = 3;
    public static final int NOT_PRESENT = 1;
    public static final int UNKNOWN = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNKNOWN";
        }
        if (o == 1) {
            return "NOT_PRESENT";
        }
        if (o == 2) {
            return "CHARGING";
        }
        if (o == 3) {
            return "NOT_CHARGING";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNKNOWN");
        if ((o & 1) == 1) {
            list.add("NOT_PRESENT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("CHARGING");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("NOT_CHARGING");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
