package com.facebook.quicklog.identifiers;

public class CommunityReputation {
    public static final short MODULE_ID = 13606;
    public static final int VOTE_SHEET_TTRC = 891686230;

    public static String getMarkerName(int i) {
        return i != 3414 ? "UNDEFINED_QPL_EVENT" : "COMMUNITY_REPUTATION_VOTE_SHEET_TTRC";
    }
}
