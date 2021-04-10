package com.oculus.extras.manager;

import com.oculus.appmanager.info.schema.ApkUpdateExtrasContract;
import com.oculus.database.supplier.AbstractDatabaseSupplier;

public class AbstractExtrasManager {
    public final AbstractDatabaseSupplier mDatabaseSupplier;
    public final String mIdColumnName;
    public final String mKeyColumnName;
    public final String mTableName = ApkUpdateExtrasContract.ExtrasTable.NAME;
    public final String mValueColumnName;

    public AbstractExtrasManager(AbstractDatabaseSupplier abstractDatabaseSupplier, String str, String str2, String str3) {
        this.mDatabaseSupplier = abstractDatabaseSupplier;
        this.mIdColumnName = str;
        this.mKeyColumnName = str2;
        this.mValueColumnName = str3;
    }
}
