package com.facebook.quicklog.identifiers;

public class WorkplaceShiftCoverQp {
    public static final short MODULE_ID = 7295;
    public static final int SHIFT_COVER_GROUP_ADMINS_QP = 478093416;
    public static final int SHIFT_COVER_GROUP_USERS_QP = 478093695;

    public static String getMarkerName(int i) {
        return i != 8296 ? i != 8575 ? "UNDEFINED_QPL_EVENT" : "WORKPLACE_SHIFT_COVER_QP_SHIFT_COVER_GROUP_USERS_QP" : "WORKPLACE_SHIFT_COVER_QP_SHIFT_COVER_GROUP_ADMINS_QP";
    }
}
