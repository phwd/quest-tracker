package com.facebook.quicklog.identifiers;

public class PublicCommunityMigration {
    public static final int MIGRATE_TO_PC = 359149373;
    public static final short MODULE_ID = 5480;
    public static final int NOTIFICATION_LANDING = 359143250;
    public static final int VALUE_PROP_PAGE = 359138377;

    public static String getMarkerName(int i) {
        return i != 1097 ? i != 5970 ? i != 12093 ? "UNDEFINED_QPL_EVENT" : "PUBLIC_COMMUNITY_MIGRATION_MIGRATE_TO_PC" : "PUBLIC_COMMUNITY_MIGRATION_NOTIFICATION_LANDING" : "PUBLIC_COMMUNITY_MIGRATION_VALUE_PROP_PAGE";
    }
}
