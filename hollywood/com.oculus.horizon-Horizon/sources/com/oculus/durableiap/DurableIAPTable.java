package com.oculus.durableiap;

import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.oculus.database.sqlite.SqlColumn;
import com.oculus.database.sqlite.SqlKeys;
import com.oculus.database.sqlite.SqlTable;
import com.oculus.durableiap.DurableIAPDBContract;

@Dependencies({})
public class DurableIAPTable extends SqlTable {
    public static final ImmutableList<SqlColumn> ALL_COLUMNS = ImmutableList.A0A(A00(DurableIAPDBContract.Columns.USER_ID), A00(DurableIAPDBContract.Columns.APPLICATION_GROUPING_ID), A00(DurableIAPDBContract.Columns.SKU), A00(DurableIAPDBContract.Columns.PURCHASE_ID), A00(DurableIAPDBContract.Columns.GRANT_TIME), A00(DurableIAPDBContract.Columns.EXPIRATION_TIME));
    public static final ImmutableList<SqlKeys.SqlKey> ALL_KEYS;
    public static final ImmutableList<SqlColumn> PRIMARY_KEY;

    static {
        ImmutableList<SqlColumn> A09 = ImmutableList.A09(A00(DurableIAPDBContract.Columns.USER_ID), A00(DurableIAPDBContract.Columns.APPLICATION_GROUPING_ID), A00(DurableIAPDBContract.Columns.PURCHASE_ID));
        PRIMARY_KEY = A09;
        ImmutableList.Builder A02 = ImmutableList.A02();
        A02.add((Object) new SqlKeys.PrimaryKey(A09));
        ALL_KEYS = A02.build();
    }

    @Inject
    public DurableIAPTable() {
        super("durable_iap", ALL_COLUMNS, ALL_KEYS);
    }

    public static SqlColumn A00(DurableIAPDBContract.Column column) {
        return new SqlColumn(column.name, column.type);
    }
}
