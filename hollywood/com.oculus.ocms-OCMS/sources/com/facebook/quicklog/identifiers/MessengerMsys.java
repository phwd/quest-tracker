package com.facebook.quicklog.identifiers;

public class MessengerMsys {
    public static final int DB_FILE_DECRYPTION = 52830682;
    public static final int DB_FILE_DECRYPTION_COMPLETE = 52830242;
    public static final int DB_FILE_DECRYPTION_START = 52828723;
    public static final int DB_FILE_ENCRYPTION = 52833304;
    public static final int MESSENGER_MSYS_BOOTSTRAP = 52822019;
    public static final short MODULE_ID = 806;
    public static final int MSYS_MAILBOX_LOG_OUT = 52823776;

    public static String getMarkerName(int i) {
        return i != 3 ? i != 1760 ? i != 6707 ? i != 8226 ? i != 8666 ? i != 11288 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_MSYS_DB_FILE_ENCRYPTION" : "MESSENGER_MSYS_DB_FILE_DECRYPTION" : "MESSENGER_MSYS_DB_FILE_DECRYPTION_COMPLETE" : "MESSENGER_MSYS_DB_FILE_DECRYPTION_START" : "MESSENGER_MSYS_MSYS_MAILBOX_LOG_OUT" : "MESSENGER_MSYS_MESSENGER_MSYS_BOOTSTRAP";
    }
}
