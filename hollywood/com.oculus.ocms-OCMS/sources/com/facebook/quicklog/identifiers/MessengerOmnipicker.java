package com.facebook.quicklog.identifiers;

public class MessengerOmnipicker {
    public static final short MODULE_ID = 403;
    public static final int OMNIPICKER_FETCH_SUGGESTIONS_EVENT = 26425574;
    public static final int OMNISTORE_CONTACTS_SYNC_UPDATED = 26427217;

    public static String getMarkerName(int i) {
        return i != 14566 ? i != 16209 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_OMNIPICKER_OMNISTORE_CONTACTS_SYNC_UPDATED" : "MESSENGER_OMNIPICKER_OMNIPICKER_FETCH_SUGGESTIONS_EVENT";
    }
}
