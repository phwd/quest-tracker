package com.facebook.quicklog.identifiers;

public class EntityCards {
    public static final int CARD_CONFIGURATION_WAIT_TIME = 1048577;
    public static final int ENTITY_CARDS_PAGE_DOWNLOAD = 1048580;
    public static final int ENTITY_CARDS_SCROLL_WAITTIME = 1048579;
    public static final int INITIAL_CARDS_LOADED = 1048578;
    public static final short MODULE_ID = 16;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "ENTITY_CARDS_ENTITY_CARDS_PAGE_DOWNLOAD" : "ENTITY_CARDS_ENTITY_CARDS_SCROLL_WAITTIME" : "ENTITY_CARDS_INITIAL_CARDS_LOADED" : "ENTITY_CARDS_CARD_CONFIGURATION_WAIT_TIME";
    }
}
