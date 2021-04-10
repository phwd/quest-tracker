package com.facebook.quicklog.identifiers;

public class AlohaGlobalPickerFunnel {
    public static final int ADD_NEW_OWNER_CLICKED = 882913177;
    public static final int ANOTHER_OWNER_PICKED = 882915433;
    public static final int CALLER_NAME = 882904032;
    public static final int CHANGED_OWNER_FOCUS = 882903501;
    public static final int LOADED_OWNERS = 882913345;
    public static final int LOADED_OWNERS_TEST = 882917088;
    public static final short MODULE_ID = 13472;
    public static final int SWITCHED_TO_OPEN_ACCESS = 882906292;

    public static String getMarkerName(int i) {
        return i != 2509 ? i != 3040 ? i != 5300 ? i != 12185 ? i != 12353 ? i != 14441 ? i != 16096 ? "UNDEFINED_QPL_EVENT" : "ALOHA_GLOBAL_PICKER_FUNNEL_LOADED_OWNERS_TEST" : "ALOHA_GLOBAL_PICKER_FUNNEL_ANOTHER_OWNER_PICKED" : "ALOHA_GLOBAL_PICKER_FUNNEL_LOADED_OWNERS" : "ALOHA_GLOBAL_PICKER_FUNNEL_ADD_NEW_OWNER_CLICKED" : "ALOHA_GLOBAL_PICKER_FUNNEL_SWITCHED_TO_OPEN_ACCESS" : "ALOHA_GLOBAL_PICKER_FUNNEL_CALLER_NAME" : "ALOHA_GLOBAL_PICKER_FUNNEL_CHANGED_OWNER_FOCUS";
    }
}
