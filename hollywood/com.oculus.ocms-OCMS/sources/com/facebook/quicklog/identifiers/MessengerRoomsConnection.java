package com.facebook.quicklog.identifiers;

public class MessengerRoomsConnection {
    public static final int CREATE_ROOM = 992747273;
    public static final short MODULE_ID = 15148;

    public static String getMarkerName(int i) {
        return i != 7945 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_ROOMS_CONNECTION_CREATE_ROOM";
    }
}
