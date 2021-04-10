package com.facebook.quicklog.identifiers;

public class WorkplaceDraftFor {
    public static final short MODULE_ID = 842;
    public static final int WORK_DRAFT_FOR_DELETE = 55181315;
    public static final int WORK_DRAFT_FOR_EDIT = 55181316;
    public static final int WORK_DRAFT_FOR_POST = 55181317;
    public static final int WORK_DRAFT_FOR_REMOVE_DRAFT_AUTHOR = 55181313;
    public static final int WORK_DRAFT_FOR_SESSION = 55181314;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "WORKPLACE_DRAFT_FOR_WORK_DRAFT_FOR_POST" : "WORKPLACE_DRAFT_FOR_WORK_DRAFT_FOR_EDIT" : "WORKPLACE_DRAFT_FOR_WORK_DRAFT_FOR_DELETE" : "WORKPLACE_DRAFT_FOR_WORK_DRAFT_FOR_SESSION" : "WORKPLACE_DRAFT_FOR_WORK_DRAFT_FOR_REMOVE_DRAFT_AUTHOR";
    }
}
