package com.oculus.appmanager.info.schema;

import com.facebook.ultralight.Dependencies;
import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.schema.ApkUpdateExtrasContract;
import com.oculus.database.sqlite.SqlColumn;
import com.oculus.database.sqlite.SqlKeys;
import com.oculus.database.sqlite.SqlTable;
import javax.inject.Inject;

@Dependencies({})
public class ApkUpdateExtrasTable extends SqlTable {
    public static final ImmutableList<SqlColumn> ALL_COLUMNS = ImmutableList.A09(ApkUpdateExtrasContract.ExtrasTable.Columns.APK_UPDATE_ID, ApkUpdateExtrasContract.ExtrasTable.Columns.EXTRA_KEY, ApkUpdateExtrasContract.ExtrasTable.Columns.EXTRA_VALUE);
    public static final ImmutableList<SqlKeys.SqlKey> KEYS = ImmutableList.A08(ApkUpdateExtrasContract.ExtrasTable.PRIMARY_KEY, ApkUpdateExtrasContract.ExtrasTable.FOREIGN_KEY);

    @Inject
    public ApkUpdateExtrasTable() {
        super(ApkUpdateExtrasContract.ExtrasTable.NAME, ALL_COLUMNS, KEYS);
    }
}
