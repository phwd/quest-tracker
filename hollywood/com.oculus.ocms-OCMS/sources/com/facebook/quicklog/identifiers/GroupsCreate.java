package com.facebook.quicklog.identifiers;

public class GroupsCreate {
    public static final int GROUPS_COMET_CREATE_BUTTON_CLICK = 133629496;
    public static final int GROUPS_CREATE_FORM_LOAD = 133629646;
    public static final int GROUPS_CREATION_WWW = 133643077;
    public static final short MODULE_ID = 2039;

    public static String getMarkerName(int i) {
        return i != 1592 ? i != 1742 ? i != 15173 ? "UNDEFINED_QPL_EVENT" : "GROUPS_CREATE_GROUPS_CREATION_WWW" : "GROUPS_CREATE_GROUPS_CREATE_FORM_LOAD" : "GROUPS_CREATE_GROUPS_COMET_CREATE_BUTTON_CLICK";
    }
}
