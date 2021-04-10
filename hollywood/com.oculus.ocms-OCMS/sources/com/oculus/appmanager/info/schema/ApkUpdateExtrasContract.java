package com.oculus.appmanager.info.schema;

import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.database.sqlite.SqlColumn;
import com.oculus.database.sqlite.SqlKeys;

public class ApkUpdateExtrasContract {

    public static class ExtrasTable {
        public static SqlKeys.SqlKey FOREIGN_KEY = new SqlKeys.ForeignKey(ImmutableList.of(Columns.APK_UPDATE_ID), ApkUpdateInfoContract.ApkUpdateTable.NAME, ImmutableList.of(new SqlColumn(ApkUpdateInfoContract.ApkUpdateTable.Columns.ID.name, ApkUpdateInfoContract.ApkUpdateTable.Columns.ID.type)));
        public static String NAME = "apk_updates_extras";
        public static SqlKeys.SqlKey PRIMARY_KEY = new SqlKeys.PrimaryKey(ImmutableList.of(Columns.APK_UPDATE_ID, Columns.EXTRA_KEY));

        public static class Columns {
            public static SqlColumn APK_UPDATE_ID = new SqlColumn("apk_update_id", "INTEGER");
            public static SqlColumn EXTRA_KEY = new SqlColumn("key", "TEXT");
            public static SqlColumn EXTRA_VALUE = new SqlColumn("value", "TEXT");
        }
    }
}
