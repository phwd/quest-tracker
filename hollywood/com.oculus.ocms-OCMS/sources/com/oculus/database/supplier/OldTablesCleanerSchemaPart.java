package com.oculus.database.supplier;

import android.database.sqlite.SQLiteDatabase;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

public class OldTablesCleanerSchemaPart extends SharedSQLiteSchemaPart {
    private static final String PART_NAME = "_old_tables_cleaner";
    private final ImmutableList<String> mOldTablesToDelete;

    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void clearAllData(SQLiteDatabase sQLiteDatabase) {
    }

    public OldTablesCleanerSchemaPart(int i, ImmutableList<String> immutableList) {
        super(PART_NAME, i);
        this.mOldTablesToDelete = immutableList;
    }

    private void dropOldTables(SQLiteDatabase sQLiteDatabase) {
        UnmodifiableIterator<String> it = this.mOldTablesToDelete.iterator();
        while (it.hasNext()) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + it.next());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        dropOldTables(sQLiteDatabase);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.database.supplier.SharedSQLiteSchemaPart
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        dropOldTables(sQLiteDatabase);
    }
}
