package com.facebook.quicklog.identifiers;

public class AlohaHome {
    public static final int BIND_VIEW = 55771139;
    public static final int CREATE_BINDER = 55771137;
    public static final int CREATE_VIEW = 55771138;
    public static final int FEED_COUNT_INITIAL = 55778133;
    public static final int LOAD_APPS = 55782696;
    public static final int LOAD_CONTACTS = 55773148;
    public static final int LOAD_FEED = 55782382;
    public static final int LOAD_OWNER = 55786285;
    public static final int LOAD_SLIDES = 55774483;
    public static final int MISSED_CALL_UPDATE = 55782995;
    public static final int MODEL_UPDATED = 55771140;
    public static final short MODULE_ID = 851;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 2012 ? i != 3347 ? i != 6997 ? i != 11246 ? i != 11560 ? i != 11859 ? i != 15149 ? "UNDEFINED_QPL_EVENT" : "ALOHA_HOME_LOAD_OWNER" : "ALOHA_HOME_MISSED_CALL_UPDATE" : "ALOHA_HOME_LOAD_APPS" : "ALOHA_HOME_LOAD_FEED" : "ALOHA_HOME_FEED_COUNT_INITIAL" : "ALOHA_HOME_LOAD_SLIDES" : "ALOHA_HOME_LOAD_CONTACTS" : "ALOHA_HOME_MODEL_UPDATED" : "ALOHA_HOME_BIND_VIEW" : "ALOHA_HOME_CREATE_VIEW" : "ALOHA_HOME_CREATE_BINDER";
    }
}
