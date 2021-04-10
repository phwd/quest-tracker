package com.facebook.quicklog.identifiers;

public class DiskIo {
    public static final int DISKIO_ANDROID = 23920642;
    public static final int LOCAL_TEST = 23920643;
    public static final short MODULE_ID = 365;
    public static final int SQLITEDISKIOEXCEPTION_ANDROID = 23920644;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "DISK_IO_SQLITEDISKIOEXCEPTION_ANDROID" : "DISK_IO_LOCAL_TEST" : "DISK_IO_DISKIO_ANDROID";
    }
}
