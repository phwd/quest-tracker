package com.oculus.appmanager.info.schema;

import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.database.sqlite.SqlColumn;
import com.oculus.database.sqlite.SqlKeys;
import com.oculus.downloader.extras.contract.ExtrasDatabase;

public class ApkUpdateExtrasContract {

    public static class ExtrasTable {
        public static SqlKeys.SqlKey FOREIGN_KEY = null;
        public static final String NAME = "apk_updates_extras";
        public static SqlKeys.SqlKey PRIMARY_KEY = new SqlKeys.PrimaryKey(ImmutableList.A08(Columns.APK_UPDATE_ID, Columns.EXTRA_KEY));

        public static class Columns {
            public static SqlColumn APK_UPDATE_ID = new SqlColumn("apk_update_id", ExtrasDatabase.ColumnsTypes.INTEGER);
            public static SqlColumn EXTRA_KEY = new SqlColumn("key", ExtrasDatabase.ColumnsTypes.TEXT);
            public static SqlColumn EXTRA_VALUE = new SqlColumn("value", ExtrasDatabase.ColumnsTypes.TEXT);
        }

        static {
            ImmutableList A07 = ImmutableList.A07(Columns.APK_UPDATE_ID);
            ApkUpdateInfoContract.Column column = ApkUpdateInfoContract.ApkUpdateTable.Columns.ID;
            FOREIGN_KEY = new SqlKeys.ForeignKey(A07, ImmutableList.A07(new SqlColumn(column.name, column.type)));
        }
    }
}
