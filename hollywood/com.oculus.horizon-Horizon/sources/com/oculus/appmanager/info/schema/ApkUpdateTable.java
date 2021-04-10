package com.oculus.appmanager.info.schema;

import com.facebook.ultralight.Dependencies;
import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.database.sqlite.SqlColumn;
import com.oculus.database.sqlite.SqlTable;
import javax.inject.Inject;

@Dependencies({})
public class ApkUpdateTable extends SqlTable {
    public static final ImmutableList<SqlColumn> ALL_COLUMNS = ImmutableList.A0B(A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.ID), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.ITEM_ID), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.BASE_VERSION), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.TARGET_VERSION), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.ACCESS_TOKEN), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.IDENTIFIER), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.DOWNLOAD_URL), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.DOWNLOAD_SIZE), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.DISPLAY_TITLE), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.UPDATE_TYPE), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.IS_UPDATE), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.DEPENDENCIES), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.REQUESTING_PACKAGE), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.REQUEST_ORIGIN), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.SIGNATURE), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.EXTERNAL_SIGNATURES), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.DOWNLOAD_CHECKSUM), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.BINARY_ID), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.REQUEST_HEADERS), A00(ApkUpdateInfoContract.ApkUpdateTable.Columns.DOWNLOAD_CHECKSUM_ALG));

    @Inject
    public ApkUpdateTable() {
        super(ALL_COLUMNS);
    }

    public static SqlColumn A00(ApkUpdateInfoContract.Column column) {
        return new SqlColumn(column.name, column.type);
    }
}
