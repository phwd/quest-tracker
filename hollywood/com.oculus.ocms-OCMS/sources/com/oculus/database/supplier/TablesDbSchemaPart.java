package com.oculus.database.supplier;

import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.oculus.database.sqlite.SqlTable;

public class TablesDbSchemaPart extends SharedSQLiteSchemaPart {
    private final ImmutableList<SqlTable> mTables;

    public TablesDbSchemaPart(String str, int i, ImmutableList<SqlTable> immutableList) {
        super(str, i);
        this.mTables = immutableList;
    }

    @VisibleForTesting
    public ImmutableList<SqlTable> getTables() {
        return this.mTables;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        UnmodifiableIterator<SqlTable> it = this.mTables.iterator();
        while (it.hasNext()) {
            it.next().create(sQLiteDatabase);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        UnmodifiableIterator<SqlTable> it = this.mTables.iterator();
        while (it.hasNext()) {
            it.next().upgrade(sQLiteDatabase, i, i2);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void onAppUpgrade(SQLiteDatabase sQLiteDatabase) {
        UnmodifiableIterator<SqlTable> it = this.mTables.iterator();
        while (it.hasNext()) {
            it.next().appUpgrade(sQLiteDatabase);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void onOpenAfterTransaction(SQLiteDatabase sQLiteDatabase) {
        UnmodifiableIterator<SqlTable> it = this.mTables.iterator();
        while (it.hasNext()) {
            it.next().open(sQLiteDatabase);
        }
    }

    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void clearAllData(SQLiteDatabase sQLiteDatabase) {
        UnmodifiableIterator<SqlTable> it = this.mTables.iterator();
        while (it.hasNext()) {
            it.next().clear(sQLiteDatabase);
        }
    }
}
