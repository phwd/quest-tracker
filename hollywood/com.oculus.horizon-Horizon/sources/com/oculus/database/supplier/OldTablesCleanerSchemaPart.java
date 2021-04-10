package com.oculus.database.supplier;

import com.google.common.collect.ImmutableList;

public class OldTablesCleanerSchemaPart extends SharedSQLiteSchemaPart {
    public static final String PART_NAME = "_old_tables_cleaner";
    public final ImmutableList<String> mOldTablesToDelete;
}
